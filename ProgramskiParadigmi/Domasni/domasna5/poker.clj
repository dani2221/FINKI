(ns user.poker)

(defn suit
  "Ja vrakja vtorata bukva od kartata"
  [card]
  (str (second (seq card))))

(defn rank
  "Vrakja vrednosta na kartata, ako e brojka ja vrakja brojkata, ako T J Q K A, vrakja vrednosta na taa brojka so mapa"
  [card]
  (cond
    (Character/isDigit (first card)) (Integer/valueOf (str (first card)))
    :else ((read-string (str ":" (first card))) {:T 10 :J 11 :Q 12 :K 13 :A 14})))

(defn pair?
  "Proveruva dali postoi 1 kombinacija na dve karti so isti rank"
  [hand]
  (= (count (filter #(= (second %) 2) (frequencies (map rank hand)))) 1))

(defn three-of-a-kind?
  "Proveruva dali postoi 1 kombinacija na tri karti so isti rank"
  [hand]
  (= (count (filter #(= (second %) 3) (frequencies (map rank hand)))) 1))

(defn four-of-a-kind?
  "Proveruva dali postoi 1 kombinacija na cetri karti so isti rank"
  [hand]
  (= (count (filter #(= (second %) 4) (frequencies (map rank hand)))) 1))

(defn flush-color?
  "Pomosna funkcija koja so every proveruva dali kartite se so ista boja"
  [hand]
  (every? #(= % (suit (first hand))) (map suit hand)))

(defn map-ace-alt-rank
  "Pomosna funkcija koja go mapira K vo 1 za proveruvanje podredenost"
  [card]
  (cond (= (rank card) 14) 1 :else (rank card)))

(defn all-ordered?
  "Pomosna funkcija koja proveruva dali site karti se vo red.
   Toa go pravi so toa shto gi sortira rankovite na kartite i go sporeduva so range izmegju minimalnata i maksimalnata vrednost na kartite
   Spoeno e so or i map-ace-alt-rank za da se fati slucajot na K 1 2 3 4"
  [hand]
  (or
   (= (sort (map rank hand)) (range (reduce min (map rank hand)) (inc (reduce max (map rank hand)))))
   (= (sort (map map-ace-alt-rank hand)) (range (reduce min (map map-ace-alt-rank hand)) (inc (reduce max (map map-ace-alt-rank hand)))))))

(defn flush?
  "Proveruva dali site karti se so ista boja, no da ne se podredeni spored uslovot na zadacata"
  [hand]
  (and (flush-color? hand) (not (all-ordered? hand))))

(defn full-house?
  "Proveruva dali ima i three of a kind i pair.
   Nema potreba za proverka dali kartite se razlicni vo three of a kind i pair, spored kako raboti pair funckijata"
  [hand]
  (and (three-of-a-kind? hand) (pair? hand)))

(defn two-pairs?
  "So frequencies funkcijata sto ke izvadi za sekoj rank kolku karti ima. Se proveruva dali ima tocno 2 takvi elementi so po 2 isti ranks"
  [hand]
  (= (count (filter #(= (second %) 2) (frequencies (map rank hand)))) 2))

(defn straight?
  "Ja koristi all-ordered? za straight"
  [hand]
  (all-ordered? hand))

(defn straight-flush?
  "Proveruva dali se ista boja i se podredeni. Se koristi flush-color bidejki taa funkcija ne proveruva podredenost, dodeka flush bi vratila false ako se podredeni"
  [hand]
  (and (flush-color? hand) (straight? hand)))

(defn value
  "Ednostavno mapiranje vo vrednost"
  [hand]
  (cond
    (straight-flush? hand) 8
    (four-of-a-kind? hand) 7
    (full-house? hand) 6
    (flush? hand) 5
    (straight? hand) 4
    (three-of-a-kind? hand) 3
    (two-pairs? hand) 2
    (pair? hand) 1
    :else 0))


(defn kickers
  "Se definira custom sort funkcija za sekogas da ja dava vrednosta dokolku ima povekje karti so ist rank, a potoa po samata vrednost na rankot"
  [hand]
  (reverse (keys (sort-by (fn [[a b]] (+ (* 14 b) a)) (frequencies (map rank hand))))))

(defn higher-kicker?
  "Bidejki kickers se podredeni, se zemaat prvite elementi od 2ta kickers i se sporeduvaat po vrednost, se dodeka ne se ispraznat.
   Ako se ispraznat vrakja false"
  [kicker1 kicker2]
  (cond
    (and (empty? kicker1) (empty? kicker2)) false
    (> (first kicker1) (first kicker2)) true
    (< (first kicker1) (first kicker2)) false
    :else (higher-kicker? (rest kicker1) (rest kicker2))))

(defn beats?
  "Najprvo gi sporeduva hands po value. Ako se isti gi sporeduva po kickers"
  [hand1 hand2]
  (cond
    (> (value hand1) (value hand2)) true
    (< (value hand1) (value hand2)) false
    :else (higher-kicker? (kickers hand1) (kickers hand2))))

(defn winning-hand
  "Nalik na reduce funkcija, koja gi pominuva elementite dve po dve i odreduva koja e pobednicka"
  [& hands]
  (cond
    (= (count hands) 1) (first hands)
    (beats? (first hands) (second hands)) (apply winning-hand (cons (first hands) (rest (rest hands))))
    :else (apply winning-hand (cons (second hands) (rest (rest hands))))))