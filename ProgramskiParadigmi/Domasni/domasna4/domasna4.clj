(ns user)

(defn atomic? 
  "Proveruva dali element ne e kolekcija"
  [x]
  (not (coll? x)))

(defn member?
  "Rekurzivno proveruva vo listata dali go ima elementot x. Ako se dojde do prazna lista vrakja false"
  [x l]
  (if (= (first l) x)
    true
    (if (seq l) 
      (member? x (rest l))
      false)))

(defn my-count-inside
  "Rekurzivno ja pominuva listata i dodava 1 na counterot se dodeka ne se dojde do prazna lista.
   Se vrakja counterot koga listata ke ostane prazna"
  [l cnt]
  (if (seq l)
    (my-count-inside (rest l) (inc cnt))
    cnt))
(defn my-count
  "Wrapper function za my-count-inside sto go inicializira counterot na 0"
  [l]
  (my-count-inside l 0))

(defn append
  "Rekurzivno dodava elementi od napred na lst2 od lst1 se dodeka lst1 ne se isprazni
   Dodavanjeto na elementi se pravi vo backtracking-ot na rekurzijata za da ne se svrti redosledod na elementite"
  [lst1 lst2]
  (if (seq lst1)
    (cons (first lst1) (append (rest lst1) lst2))
    lst2))

(defn zip
  "Rekurzivno gi zema elementite na dvete listi, formira nova lista i vo backtracking ja dodava ovaa lista na rezultatot od napred"
  [lst1 lst2]
  (if (and (seq lst1) (seq lst2))
    (cons (list (first lst1) (first lst2)) (zip (rest lst1) (rest lst2)))
    '()))

(defn lookup
  "Rekurzivno ja pominuva listata se dodeka ne najde prv element na vnatresnata lista koj e ednakov na klucot. Togas vrakja so vrednosta. Ako ne go najde vrakja nil"
  [key list-of-pairs]
  (if (seq list-of-pairs)
    (if (= (first (first list-of-pairs)) key)
      (first (rest (first list-of-pairs)))
      (lookup key (rest list-of-pairs)))
    nil))

(defn my-merge
  "Rekurzivno gi pominuva listite so toa sto proveruva za prvite elementi od lst1 i lst 2 koj ima pomala vrednost za da se dodade od napred na rezultatot pri backtracking.
   Dokolku se isprazni ednata lista ednostavno se vrakja drugata.",
  [lst1 lst2]
  (cond
    (and (empty? lst1) (empty? lst2)) '()
    (empty? lst2) lst1
    (empty? lst1) lst2
    (<= (first lst1) (first lst2)) (cons (first lst1) (my-merge (rest lst1) lst2))
    (< (first lst2) (first lst1)) (cons (first lst2) (my-merge lst1 (rest lst2)))
  ))

(defn count-all
  "Rekurzivno ja pominuva listata
   Ako elementot e lista, povikaj ja istata funkcija na toj element i vrati go rezultatot od prethodnata rekurzija + brojot vo vnatresnata lista, 
   Ako ne e lista vrati brojot od prethodna rekurzija + 1"
  [lst]
  (cond
    (empty? lst) 0
    (atomic? (first lst)) (+ (count-all (rest lst)) 1)
    (not (atomic? (first lst))) (+ (count-all (rest lst)) (count-all (first lst)))))

(defn my-drop
  "Vo sekoja rekurzivna iteracija se se krati glavata od listata i namaluva brojacot dur ne stigne 0.
   Koga ke stigne 0 ja vrakjame listata sto ostanala"
  [n lst]
  (cond
    (= n 0) lst
    (empty? lst) '()
    :else (my-drop (dec n) (rest lst))))

(defn my-take
  "n se namaluva za 1 (pri forward-pass). Koga ke stigne do 0, se vrakja prazna lista i pri backtracking se lepat elementite od napred koi treba da se zacuvaat
   Ne go iskoristiv hintot :)"
  [n lst]
  (cond 
    (or (= n 0) (empty? lst)) '()
    :else (cons (first lst) (my-take (dec n) (rest lst)))))

(defn my-reverse-inside
  "Funkcija koja vo forward-pass od rekurzijata go dodava prviot element vo rezultantnata lista odnapred.
   Koga inicijalnata lista e prazna, se vrakja ovoj rezultat"
  [lst res]
  (cond 
    (empty? lst) res
    :else (my-reverse-inside (rest lst) (cons (first lst) res))))
(defn my-reverse
  "Wrapper za my-reverse-inside sto inicijalizira prazna rezultantna lista"
  [lst]
  (my-reverse-inside lst '()))

(defn remove-duplicates
  "Ja koristi funkcijata member od pogore. Idejata e da se proveri dali elementot e prisuten vo ostatokot od listata
   Ako e, vo backtracking ne go dodavaj elementot od napred.
   Gi brishe prvite pojavuvanja na elementite i gi ostava poslednite"
  [lst]
  (cond
    (empty? lst) ()
    (member? (first lst) (rest lst)) (remove-duplicates (rest lst))
    :else (cons (first lst) (remove-duplicates (rest lst)))))

(defn my-flatten-inside
  "Ja koristi append funkcijata od pogore za da gi dodade elementite od sekoja lista vo rezultantnata koja e prazna na pocetok.
   Ja vrakja ovaa rezultantna lista koga lst ke stane prazna"
  [lst res]
  (cond
    (empty? lst) res
    :else (my-flatten-inside (rest lst) (append res (first lst)))))

(defn my-flatten
  "Wrapper function za my-flatten-inside sto ja inicializira rezultantnata prazna lista",
  [lst]
  (my-flatten-inside lst '()))



(defn zameni-buzz
  "Predikat sto odlucuva dali vlezen broj treba da se zameni so :buzz.
   Gleda dali se deli so 7 ili dali 7 e member na listata od string reprezentacija na broj"
  [broj]
  (cond 
    (or (= (mod broj 7) 0) (member? \7 (seq (str broj)))) :buzz
    :else broj))

(defn buzz
  "Go koristi zameni-buzz? so map za da smeni vo :buzz kade sto e potrebno"
  [lst]
  (map zameni-buzz lst))

(defn divisors-of
  "So pomos na range funkcijata se generira lista od 2 do n-1 broevi.
   Za sekoe so pomos na anonymous funkcija i filter se filtriraat samo tie sto se deliteli"
  [n]
  (filter #(= (mod n %) 0) (range 2 n)))

(defn longest
  "Ja koristi reduce so anonymous funkcija koja go koristi my-count od pogore za da go sporedi stringot so maksimalniot.
   Ako e pogolem se vrakja toj, ako ne se vrakja maksimalniot. Reduce ova go pravi za sekoj element vo listata
   Na reduce mu davam prazen string kako inicialen maksimum"
  [lst]
  (reduce (fn [max current]
            (cond 
              (> (my-count current) (my-count max)) current
              :else max))
          "" lst))



(defn my-map
  "Ja pominuva listata rekurzivno so ostatokot od listata se dodeka ne stane prazna.
   Ja presmetuva vrednosta na vleznata funkcija vrz prviot element.
   Pri backtracking gi lepi elementite od napred na rezultanntata lista. "
  [f lst]
  (cond
    (empty? lst) '()
    :else (cons (f (first lst)) (my-map f (rest lst)))))

(defn my-filter
  "Ja pominuva listata rekurzivno so ostatokot od listata se dodeka ne stane prazna.
   Vo zavisnost od toa dali e ispolnet uslovot na zadacata pri backtracking se dodava elementot na rezultantnata lista od napred. "
  [f lst]
  (cond
    (empty? lst) '()
    (f (first lst)) (cons (first lst) (my-filter f (rest lst)))
    (not (f (first lst))) (my-filter f (rest lst))))

(defn my-reduce
  "my-reduce ima dve implementacii:
   - ako e povikan so value (3 argumenta), se povikuva funkcijata na toa value i prviot element i rekurzivno se povikuva istata implementacija od my-reduce so 3 argumenti.
     Koga ke se isprazni listata se vrakja vrednosta na value
   - ako e povikan bez value, se presmetuva nov value kako f na prvite 2 clena. Potoa rekurzivno se povikuva prvata implementacija so value."
  ([f value lst] 
   (cond (empty? lst) value
         :else (my-reduce f (f value (first lst)) (rest lst))))
  ([f lst] 
   (cond 
     (empty? (rest lst)) (first lst) 
     :else (my-reduce f (f (first lst) (first (rest lst))) (rest (rest lst))))))

(defn my-flat-map
  "Moze ednostavno da se implementira so gornite predikati my-map i my-flatten.
   my-map najprvo ja izvrshuva f na sekoj element od lst pa potoa
   my-flatten brishe edno nivo od listata"
  [f lst]
  (my-flatten (my-map f lst)))




