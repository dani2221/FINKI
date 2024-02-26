(ns user.sudoku)
(require '[clojure.set :as set])

(def initial-sudoku
  [[5 3 0 0 7 0 0 0 0]
   [6 0 0 1 9 5 0 0 0]
   [0 9 8 0 0 0 0 6 0]
   [8 0 0 0 6 0 0 0 3]
   [4 0 0 8 0 3 0 0 1]
   [7 0 0 0 2 0 0 0 6]
   [0 6 0 0 0 0 2 8 0]
   [0 0 0 4 1 9 0 0 5]
   [0 0 0 0 8 0 0 7 9]])


(defn map-numbers
  "Funkcija koja mapira od broj vo mnozestvo od toj broj. Osven za 0 kade sto mapira vo mnozestvo od site cifri"
  [x]
  (cond
    (= x 0) #{1 2 3 4 5 6 7 8 9}
    :else #{x}))

(defn transform
  "Funkcija koja ja koristi map-numbers i nested map za da se dobie posakuvaniot rezultat"
  [matrix]
  (mapv #(mapv map-numbers %) matrix))

(defn transform-back
  "Funkcija koja pretvara resheno sudoku nazad od mnozestva vo brojki"
  [matrix]
  (mapv #(mapv first %) matrix))

(defn index-value
  "Vrakja vrednost na matricata na odreden indeks
   Odzema na prvo na y pa na x se dodeka ne stigne do vrednosta"
  [x y matrix]
  (cond
    (and (= x 0) (= y 0)) (first (first matrix))
    (= x 0) (index-value x (dec y) (list (rest (first matrix))))
    :else (index-value (dec x) y (rest matrix))))

(defn odredi-pole
  "Go naogja poleto (edno od devet) vo format lista (startX startY endX endY) za dadeni x i y"
  [x y]
  (cond
    (and (< x 3) (< y 3)) '(0 0 3 3)
    (and (< x 6) (< y 3)) '(3 0 6 3)
    (and (< x 9) (< y 3)) '(6 0 9 3)
    (and (< x 3) (< y 6)) '(0 3 3 6)
    (and (< x 6) (< y 6)) '(3 3 6 6)
    (and (< x 9) (< y 6)) '(6 3 9 6)
    (and (< x 3) (< y 9)) '(0 6 3 9)
    (and (< x 6) (< y 9)) '(3 6 6 9)
    (and (< x 9) (< y 9)) '(6 6 9 9)))

(defn brishi-iskoristeni-pole
  "Vrakja mnozestvo na site vrednosti koi treba da se izbrishat od mnozestvoto vrednosti na eden element prisuten vo dadeno pole
   Ja pominuva matricata na poleto 3x3 i pri backtracking dodava na rezultantnoto mnozestvo elementi koi ne mozat da se iskoristat bidejki tie se vekje definirani vo poleto"
  [pole matrix]
  (cond
    (= (second pole) (second (rest (rest pole)))) #{}
    (= (first pole) (first (rest (rest pole)))) (brishi-iskoristeni-pole (list (- (first pole) 3) (inc (second pole)) (first (rest (rest pole))) (second (rest (rest pole)))) matrix)
    (= (count (index-value (first pole) (second pole) matrix)) 1) (conj (brishi-iskoristeni-pole (list (inc (first pole)) (second pole) (first (rest (rest pole))) (second (rest (rest pole)))) matrix) (first (index-value (first pole) (second pole) matrix)))
    :else (brishi-iskoristeni-pole (list (inc (first pole)) (second pole) (first (rest (rest pole))) (second (rest (rest pole)))) matrix)))

(defn brishi-iskoristeni-x
  "Vrakja mnozestvo na site vrednosti koi treba da se izbrishat od mnozestvoto vrednosti na eden element na X oskata 
   Gi pominuva site elementi na dadenata x oska i vo rezultantnoto mnozestvo pri backtracking gi dodava definiranite elementi"
  [x brojac matrix]
  (cond
    (= brojac 9) #{}
    (= (count (index-value x brojac matrix)) 1) (conj (brishi-iskoristeni-x x (inc brojac) matrix) (first (index-value x brojac matrix)))
    :else (brishi-iskoristeni-x x (inc brojac) matrix)))

(defn brishi-iskoristeni-y
  "Istoto od brishi-iskoristeni-x, sega samo za y oskata"
  [y brojac matrix]
  (cond
    (= brojac 9) #{}
    (= (count (index-value brojac y matrix)) 1) (conj (brishi-iskoristeni-y y (inc brojac) matrix) (first (index-value brojac y matrix)))
    :else (brishi-iskoristeni-y y (inc brojac) matrix)))

(defn brishi-iskoristeni
  "step 1 od algoritamot predlozen vo zadacata
   So map se pominuvaat site elementi vo matricata i za sekoe so pomos na set operatori za razlika i unija se brishat elementite vekje definirani
   za istoto pole (brishi-iskoristeni-pole), x (brishi-iskoristeni-x) i y (brishi-iskoristeni-y) oskata
   Se preripuvaat tie kade sto vekje ima definirano 1 element i se vrakja istata vrednost"
  [matrix]
  (mapv (fn [i] (mapv (fn [j] (cond
                                (= (count (index-value i j matrix)) 1) (index-value i j matrix)
                                :else (set/difference (index-value i j matrix) (set/union (brishi-iskoristeni-pole (odredi-pole i j) matrix) (brishi-iskoristeni-x i 0 matrix) (brishi-iskoristeni-y j 0 matrix))))) (range 0 9))) (range 0 9)))


(defn proveri-broj-pole
  "Ista zamisla so brishi-iskoristeni-pole, no ovde vrakja true ili false vo zavisnost dali daden argument na vlez - broj e prisuten vo drugite mnozestvata vo poleto
   Funkcijata isto taka prima i argumenti x i y za da se znae lokacijata na brojot za koj sto prebaruvame da se skokne. Ako go nema ova sekogas ke vrakjase true"
  [broj pole matrix x y]
  (cond
    (= (second pole) (second (rest (rest pole)))) false
    (and (= x (first pole)) (= y (second pole))) (proveri-broj-pole broj (list (inc (first pole)) (second pole) (first (rest (rest pole))) (second (rest (rest pole)))) matrix x y)
    (= (first pole) (first (rest (rest pole)))) (proveri-broj-pole broj (list (- (first pole) 3) (inc (second pole)) (first (rest (rest pole))) (second (rest (rest pole)))) matrix x y)
    (contains? (index-value (first pole) (second pole) matrix) broj) true
    :else (proveri-broj-pole broj (list (inc (first pole)) (second pole) (first (rest (rest pole))) (second (rest (rest pole)))) matrix x y)))

(defn proveri-broj-x
  "Isto kako brishi-iskoristeni-x samo sto vrakja true/false vo zavisnost dali broj go ima vo elementite na x oskata"
  [x brojac matrix y broj]
  (cond
    (= brojac 9) false
    (= brojac y) (proveri-broj-x x (inc brojac) matrix y broj)
    (contains? (index-value x brojac matrix) broj) true
    :else (proveri-broj-x x (inc brojac) matrix y broj)))

(defn proveri-broj-y
  "Isto kako brishi-iskoristeni-y samo sto vrakja true/false vo zavisnost dali broj go ima vo elementite na y oskata"
  [y brojac matrix x broj]
  (cond
    (= brojac 9) false
    (= brojac x) (proveri-broj-y y (inc brojac) matrix x broj)
    (contains? (index-value brojac y matrix) broj) true
    :else (proveri-broj-y y (inc brojac) matrix x broj)))

(defn proveri-filter
  "Step 2 od algoritamot predlozen vo zadacata, za sekoe pole proveruva dali postoi vrednost od mnozestvoto koja ja nema vo drugite, ako da ja stava vrednosta na toa pole"
  [matrix]
  (mapv (fn [i] (mapv (fn [j]
                        (let [res (filter #(not (or
                                                 (proveri-broj-pole % (odredi-pole i j) matrix i j)
                                                 (proveri-broj-x i 0 matrix j %)
                                                 (proveri-broj-y j 0 matrix i %)))
                                          (index-value i j matrix))]
                          (cond
                            (empty? res) (index-value i j matrix)
                            :else #{(first res)}))) (range 0 9))) (range 0 9)))

(defn check-solved?
  "Proveruva dali site polinja imaat vrednost taka sto gi filrira site mnozestva so 1 element i so reduce proveruva dali postojat 81 takvi mnozestva"
  [matrix]
  (= (reduce + (map (fn [row] (count (filter #(= (count %) 1) row))) matrix)) 81))

(defn display-board
  "Go prikazuva sudokuto so | i - okolu brojkite"
  [matrix]
  (str "-------------------------------------\n"
       (reduce str
               (map (fn [row] (str "|" (reduce str (map #(str " " % " " "|") row)) "\n-------------------------------------\n")) matrix))))

(defn solve
  "Celosnoto resenie za solve, rekurzivno se povikuva so argument step shto oznacuva dali e na red step1 ili step2 od zadacata
  Krajniot slucaj e koga matricata ke e solved, i togas go vrakja rezultantnoto sudoku "
  ([matrix step]
   (cond
     (check-solved? matrix) matrix
     (= step 0) (solve (brishi-iskoristeni matrix) 1)
     :else (solve (proveri-filter matrix) 0)))
  ([matrix] (transform-back (solve (transform matrix) 0))))

(defn display
  "Ednostavna funkcija koja ja koristi display-board za printanje na inicialnoto i reshenoto sudoku"
  [matrix]
  (println "INITIAL:")
  (println (display-board matrix))
  (println "SOLVED:")
  (println (display-board (solve matrix))))
