(ns user.boja)

(defn rotate
  "Go zema posledniot element i go stava na pocetok"
  [lst]
  (cons (last lst) (reverse (rest (reverse lst)))))

(defn check-valid?
  "Proveruva dali e validna konfiguracijata na matricata.
   Za sekoj element vo input red proveruva elementot da ne ist so elementite na poziciite na redovite nad nego"
  [row matrix]
  (every? (fn [el] (every? (fn [mtxrow] (not (= (nth mtxrow (first el)) (second el)))) matrix)) (map-indexed (fn [i el] (list i el)) row)))

(defn solve-inner
  "Rekurzivna funkcija za resavanje na problemot
   - Ako input matricata e prazna ja vraka result-matrix reversed transformirana vo vektori
   - Ako se napraveni ist broj na rotacii so broj na elementi vo eden red znaci ne e najdeno resenie, vrakja false
   - Ako e validna konfiguracijata na rezultantnata matrica so noviot red i rekurzivno bi bila validna za narednite redovi, se stava toj red vo rezultantnata matrica i se prodolzuva so sledniot red
   - Ako nisto od ova ne e ispolneto, odnosno ne e validna konfiguracijata na rezultantnata matrica ni vo narednite redovi, pravi rotacija na redot i se povikuva samata funkcija povtorno
   "
  [matrix result-matrix rotations]
  (cond
    (empty? matrix) (mapv vec (reverse result-matrix))
    (= rotations (count (first matrix))) false
    (and (check-valid? (first matrix) result-matrix) (solve-inner (rest matrix) (cons (first matrix) result-matrix) 0)) (solve-inner (rest matrix) (cons (first matrix) result-matrix) 0)
    :else (solve-inner (cons (rotate (first matrix)) (rest matrix)) result-matrix (inc rotations))))

(defn solve
  "Wrapper za solve-inner so inicijalizirana prazna rezultanta matrica i startni 0 rotacii"
  [matrix]
  (solve-inner matrix '() 0))

(defn show
  "Pravi vizuelizacija slicno kako kaj sudokuto, samo tuka dinamicki se odreduva brojot na horizontalni crti"
  [matrix]
  (println (str (apply str (map (fn [_] "----") (first matrix))) "-\n"
       (reduce str
               (map (fn [row] (str "|" (reduce str (map #(str " " % " " "|") row)) (apply str (concat (cons "\n" (map (fn [_] "----") (first matrix))) "-\n")))) matrix)))))

