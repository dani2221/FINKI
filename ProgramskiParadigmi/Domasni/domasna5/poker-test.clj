(ns user.poker (:use clojure.test))

(def high-seven ["2H" "3S" "4C" "5C" "7D"])
(def pair-hand ["2H" "2S" "4C" "5C" "7D"])
(def two-pairs-hand ["2H" "2S" "4C" "4D" "7D"])
(def three-of-a-kind-hand ["2H" "2S" "2C" "4D" "7D"])
(def four-of-a-kind-hand ["2H" "2S" "2C" "2D" "7D"])
(def straight-hand ["2H" "3S" "6C" "5D" "4D"])
(def low-ace-straight-hand ["2H" "3S" "4C" "5D" "AD"])
(def high-ace-straight-hand ["TH" "AS" "QC" "KD" "JD"])
(def flush-hand ["2H" "4H" "5H" "9H" "7H"])
(def full-house-hand ["2H" "5D" "2D" "2C" "5S"])
(def straight-flush-hand ["2H" "3H" "6H" "5H" "4H"])
(def low-ace-straight-flush-hand ["2D" "3D" "4D" "5D" "AD"])
(def high-ace-straight-flush-hand ["TS" "AS" "QS" "KS" "JS"])

(deftest test-suit
  (is (= (suit "2C") "C"))
  (is (= (suit "TS") "S"))
  (is (= (suit "AD") "D"))
  (is (= (suit "JH") "H"))
  (is (= (suit "6S") "S")))

(deftest test-rank
  (is (= (rank "2C") 2))
  (is (= (rank "TS") 10))
  (is (= (rank "AD") 14))
  (is (= (rank "JH") 11))
  (is (= (rank "6S") 6)))

(deftest test-pair?
  (is (pair? pair-hand))
  (is (not (pair? ["2C" "3D" "4H" "5S" "6C"]))))

(deftest test-three-of-a-kind?
  (is (three-of-a-kind? three-of-a-kind-hand))
  (is (not (three-of-a-kind? ["2C" "3D" "4H" "5S" "6C"]))))

(deftest test-four-of-a-kind?
  (is (four-of-a-kind? four-of-a-kind-hand))
  (is (not (four-of-a-kind? ["2C" "3D" "4H" "5S" "6C"]))))

(deftest test-flush?
  (is (not (flush? ["2H" "3H" "6H" "5H" "4H"]))) ; ne e flush bidejki se podredeni (samo straight-flush)
  (is (flush? flush-hand)))

(deftest test-full-house?
  (is (full-house? full-house-hand))
  (is (not (full-house? ["2C" "3D" "4H" "5S" "6C"]))))

(deftest test-two-pairs?
  (is (two-pairs? two-pairs-hand))
  (is (not (two-pairs? ["2C" "3D" "4H" "5S" "6C"]))))

(deftest test-straight?
  (is (straight? straight-hand))
  (is (not (straight? ["2C" "3D" "9H" "5S" "6C"]))))

(deftest test-straight-flush?
  (is (straight-flush? straight-flush-hand))
  (is (not (straight-flush? ["2C" "3D" "4H" "5S" "6C"]))))

(deftest test-value
  (is (= (value high-seven) 0)) ; nema nisto
  (is (= (value pair-hand) 1)) ; pair
  (is (= (value two-pairs-hand) 2)) ; two pait
  (is (= (value three-of-a-kind-hand) 3)) ; three of a kind
  (is (= (value straight-hand) 4)) ; straigth 
  (is (= (value flush-hand) 5)) ; flush 
  (is (= (value full-house-hand) 6)) ; full house
  (is (= (value four-of-a-kind-hand) 7)) ; four of a kind
  (is (= (value straight-flush-hand) 8))) ; straigth flush

(deftest test-kickers
  (is (= (kickers ["2H" "3S" "6C" "5D" "4D"]) '(6 5 4 3 2))) ; straight
  (is (= (kickers ["5H" "AD" "5C" "7D" "AS"]) '(14 5 7))) ; two pair
  (is (= (kickers ["KC" "KD" "KH" "QS" "QC"]) '(13 12))) ; full house
  (is (= (kickers ["AC" "9S" "3C" "7S" "5C"]) '(14 9 7 5 3))); nisto
  (is (= (kickers ["2C" "2D" "2H" "2S" "3C"]) '(2 3))) ; four of a kind
  (is (= (kickers ["2C" "2D" "2H" "7S" "5C"]) '(2 7 5))) ; three of a kind
  (is (= (kickers ["2C" "2D" "3H" "7S" "5C"]) '(2 7 5 3)))) ; pair

(deftest test-higher-kicker?
  (is (= (higher-kicker? '(8 5 9) '(8 7 3)) false))
  (is (= (higher-kicker? '(10 9 7) '(10 8 6)) true))
  (is (= (higher-kicker? '(2 4 6) '(2 4 6)) false))
  (is (= (higher-kicker? '(14 13 12) '(14 13 11)) true))
  (is (= (higher-kicker? '(5 5 5) '(5 5 5)) false)))

(deftest test-beats?
  (is (not (beats? ["2H" "3S" "6C" "5D" "4D"] ["2C" "3D" "4H" "5S" "6C"]))) ; equal, beats vrakja false
  (is (beats? ["KC" "QD" "JH" "TS" "9C"] ["AC" "5S" "3C" "4S" "5C"])) ; straight vs nisto
  (is (beats? ["KC" "KD" "KH" "QS" "QC"] ["2C" "2D" "2H" "3S" "3C"])) ; 2 full house, leviot pogolem
  (is (beats? ["AC" "2C" "3C" "4C" "5C"] ["KC" "2C" "5C" "QC" "7C"])) ; straight flush vs flush
  (is (not (beats? ["2H" "3S" "6C" "5D" "4D"] ["2C" "2D" "2H" "2S" "5C"])))); straight vs 4 of a kind 

(deftest test-winning-hand
  (is (= (winning-hand ["2H" "3S" "9C" "5D" "4D"] ["TC" "3D" "2H" "5S" "AC"]
                       ["KC" "2D" "8H" "4S" "2C"] ["AC" "2S" "3C" "4S" "5C"])
         ["AC" "2S" "3C" "4S" "5C"])) ; pobeduva straight
  (is (= (winning-hand ["2C" "2D" "2H" "3S" "3C"] ["KC" "KD" "KH" "QS" "QC"]
                       ["AC" "2S" "3C" "4S" "5C"] ["2H" "3S" "6C" "5D" "4D"])
         ["KC" "KD" "KH" "QS" "QC"])) ; pobeduva najvisok full house
  (is (= (winning-hand ["2C" "2D" "2H" "2S" "5C"] ["KC" "KD" "KH" "QS" "QC"]
                       ["AC" "2S" "3C" "4S" "5C"] ["AC" "2S" "3C" "4S" "5C"])
         ["2C" "2D" "2H" "2S" "5C"])) ; pobeduva 4 of a kind vs straight i full-house
  (is (= (winning-hand ["2H" "3S" "6C" "5D" "4D"] ["2C" "3D" "4H" "5S" "6C"]
                       ["KC" "QD" "JH" "TS" "9C"] ["TH" "9S" "8C" "7D" "6H"])
         ["KC" "QD" "JH" "TS" "9C"])) ; site straight, pobeduva na kicker
  (is (= (winning-hand ["2C" "2D" "2H" "2S" "5C"]) 
         ["2C" "2D" "2H" "2S" "5C"])) ; test so eden hand
  )

(run-tests)