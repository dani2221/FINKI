(ns user (:use clojure.test))

(deftest test-atomic?
  (is (atomic? 2)) ;klasicen test case
  (is (atomic? nil)) ;atomic so nil
  (is (not (atomic? '(2 34)))) ;atomic so lista
  (is (not (atomic? '()))) ;atomic so prazna lista
  (is (not (atomic? [1 2])))) ;atomic so vektor lista

(deftest test-member?
  (is (member? 4 '(4 2 4 5 634 7 34 ))) ;klasicen member
  (is (not(member? 4 '()))) ;member so prazna lista
  (is (not (member? 0 '(4 2 4 5 634 7 34)))) ;member ne prisuten
  (is (member? '(2 3) '(4 2 4 5 634 7 (2 3) 23))) ;member so lista prisuten
  (is (not (member? '(2 3) '(4 2 4 5 634 7 23))))) ;member so lista neprisuten

(deftest test-my-count
  (is (= (my-count '()) 0)) ;count prazna lista
  (is (= (my-count '(1)) 1)) ;count eden element
  (is (= (my-count '(a (b c (d e)) f)) 3)) ;count vnatresni listi
  (is (= (my-count '((()) ())) 2)) ;count vnatresni listi bez elementi
  (is (= (my-count '(())) 1))) ;count vnatresna lista bez elementi

(deftest test-append
  (is (= (append '(:a :b :c) '(1 (2 3))) '(:a :b :c 1 (2 3)))) ;test primer od zadaca
  (is (= (append '(:a :b :c) '()) '(:a :b :c))) ;vtora lista prazna
  (is (= (append '() '(:a :b :c)) '(:a :b :c))) ;prva lista prazna
  (is (= (append '() '()) '())) ; dvete listi prazni
  (is (= (append '((())) '((()))) '((()) (()))))) ; vnatresni listi

(deftest test-zip
  (is (= (zip '(:a :b :c) '(1 (2 3) 4 5)) '((:a 1) (:b (2 3)) (:c 4)))) ;test primer od zadaca
  (is (= (zip '() '(1 2 3)) '())) ;zip prva prazna lista
  (is (= (zip '(1 2 3) '()) '())) ;zip vtora prazna lista
  (is (= (zip '() '()) '())) ;zip dve prazni listi
  (is (= (zip '((((()))) 2 3) '(:a :b)) '(((((()))) :a) (2 :b))))) ;zip lista od listi

(deftest test-lookup
  (is (= (lookup 2 '((4 3) (5 2) (2 4))) 4)) ;test primer od zadaca
  (is (= (lookup '(2) '((4 3) (5 2) ((2) 13) (2 4))) 13)) ;lookup so lista key
  (is (= (lookup '(((()))) '(((()) 3) (5 2) ((((()))) 13) (2 4))) 13)) ;lookup so mnogu vnatreni listi key 
  (is (not (lookup 10 '((4 3) (5 2) (2 4))))) ;lookup bez da se najde vrednost 
  (is (not (lookup '(2) '((4 3) (5 2) (2 4)))))) ;lookup bez da se najde vrednost so key lista

(deftest test-my-merge
  (is (= (my-merge '(3 7 12 19 19 25 30) '(4 7 10 12 20)) '(3 4 7 7 10 12 12 19 19 20 25 30))) ;test primer od zadaca 
  (is (= (my-merge '(3 7 12 19 19 25 30) '()) '(3 7 12 19 19 25 30))) ;vtora lista prazna
  (is (= (my-merge '() '(3 7 12 19 19 25 30)) '(3 7 12 19 19 25 30))) ;prva lista prazna
  (is (= (my-merge '() '()) '())) ;dvete listi prazni
  (is (= (my-merge '(1 3 5 7 9) '(0 2 4 6 8)) '(0 1 2 3 4 5 6 7 8 9)))) ;site elementi 0-10 merge

(deftest test-count-all
  (is (= (count-all '(a (b c () (25) nil) ())) 5)) ;test primer od zadaca
  (is (= (count-all '((((((()))))) ( () () ) ())) 0)) ;mnogu vnatreni listi bez atomic elementi
  (is (= (count-all '(((((((a)))))))) 1)) ;mnogu vnatresni listi so eden atomic element
  (is (= (count-all '(a b c)) 3)) ;simple scenario
  (is (= (count-all '() ) 0))) ;prazna lista

(deftest test-my-drop
  (is (= (my-drop 3 '(a b c d e)) '(d e))) ;test primer od zadaca
  (is (= (my-drop 2 '(nil (2 3) c d e)) '(c d e))) ;test so nil i vnatresni zagradi
  (is (= (my-drop 10 '(a b c d e)) '())) ;my-drop so n>len(lst)
  (is (= (my-drop 5 '(a b c d e)) '())) ;my-drop so n==len(lst)
  (is (= (my-drop 0 '(a b c d e)) '(a b c d e)))) ;my-drop so n 0

(deftest test-my-take
  (is (= (my-take 3 '(a b c d e)) '(a b c))) ;test primer od zadaca
  (is (= (my-take 2 '(nil (2 3) c d e)) '(nil (2 3)))) ;test so nil i vnatresni zagradi
  (is (= (my-take 10 '(a b c d e)) '(a b c d e))) ;my-take so n>len(lst)
  (is (= (my-take 5 '(a b c d e)) '(a b c d e))) ;my-take so n==len(lst)
  (is (= (my-take 0 '(a b c d e)) '()))) ;my-take so n 0

(deftest test-my-reverse
  (is (= (my-reverse '(1 2 3 4)) '(4 3 2 1))) ;simple test primer
  (is (= (my-reverse '(1 2 (3 4))) '((3 4) 2 1))) ;test primer od zadaca
  (is (= (my-reverse '()) '())) ;my-reverso so prazna lista
  (is (= (my-reverse '(((nil)) nil)) '(nil ((nil))))) ;spravuvanje so lista od listi
  (is (= (my-reverse '(2)) '(2)))) ;eden element vo lista

(deftest test-remove-duplicates
  (is (= (remove-duplicates '(1 2 3 4 5 3 21 3 1)) '(2 4 5 21 3 1))) ;test podolga lista
  (is (= (remove-duplicates '(1 2 3 1 4 1 2)) '(3 4 1 2))) ;test primer od zadaca
  (is (= (remove-duplicates '()) '())) ;prazna lista
  (is (= (remove-duplicates '(1 1 1 1 1 1 1)) '(1))) ;ist element povekje pati
  (is (= (remove-duplicates '((1 1 1) 1 1 1 1 (1 1 1) 1 1)) '((1 1 1) 1)))) ;ist element vo podlisti

(deftest test-my-flatten
  (is (= (my-flatten '(((1 1) (2 3)) ((5 7)))) '((1 1) (2 3) (5 7)))) ;test primer od zadaca
  (is (= (my-flatten '(((2 3)) (5))) '((2 3) 5))) ;po prost test ponmalce elementi
  (is (= (my-flatten '((()) ())) '(()))) ;lista od listi test
  (is (= (my-flatten '(() ())) '())) ;0 elementi rezultat test
  (is (= (my-flatten '(((1 1) (2 3)) ((5 7) ((5 7))))) '((1 1) (2 3) (5 7) ((5 7)))))) ;po dlaboka struktura


(deftest test-buzz
  (is (= (buzz '(1 2 3 4 5 6 7 8 77)) '(1 2 3 4 5 6 :buzz 8 :buzz))) ;test primer so broj deliv na 7
  (is (= (buzz '(1 2 3 4 5 6 7 772 9)) '(1 2 3 4 5 6 :buzz :buzz 9))) ;test primer so broj nedeliv na 7 ali sodrzi 7
  (is (= (buzz '(1 2 3 4 5 6 9)) '(1 2 3 4 5 6 9))) ;bez nitu eden uslov
  (is (= (buzz '(7 77 771 27)) '(:buzz :buzz :buzz :buzz))) ;site so ispolnet uslov 
  (is (= (buzz '()) '()))) ;prazna lista

(deftest test-divisors-of
  (is (= (divisors-of 12) '(2 3 4 6))) ;test primer od zadacata
  (is (= (divisors-of 5) '())) ;test bez deliteli
  (is (= (divisors-of 15) '(3 5))) ;test pogolem broj
  (is (= (divisors-of 100) '(2 4 5 10 20 25 50))) ;test uste pogolem broj
  (is (= (divisors-of 2) '()))) ;test 2ka

(deftest test-longest
  (is (= (longest '("abc" "abcdef" "xy" "lmnop")) "abcdef")) ;test primer od zadacata 
  (is (= (longest '("abc" "abcdef" "xy" "lmnop" "sioafs")) "abcdef")) ;povekje ista dolzina - vrati prv
  (is (= (longest '("abc" "abcdef" "iudwrirwo" "lmrioewonop")) "lmrioewonop")) ;podredeni po dolzina rastecki
  (is (= (longest '("lmrioewonop" "iudwrirwo" "abcdef" "abc")) "lmrioewonop")) ;podredeni po dolzina opagjacki
  (is (= (longest '("abc")) "abc"))) ;eden element


(deftest test-my-map
  (is (= (my-map inc '(2 3 4)) '(3 4 5))) ;obicen increment na elementi 
  (is (= (my-map inc '()) '())) ;prazna lista
  (is (= (my-map dec '(2 3 4)) '(1 2 3))) ;dec na elementi
  (is (= (my-map #(* 5 %) '(2 3 4)) '(10 15 20))) ;mnozenje so 5
  (is (= (my-map #(/ % 5) '(10 15 20)) '(2 3 4)))) ;delenje so 5

(deftest test-my-filter
  (is (= (my-filter seq? '(2 3 (3 32) 2 4 (5))) '((3 32) (5)))) ;filtriranje na listi
  (is (= (my-filter seq? '()) '())) ;prazna input lista
  (is (= (my-filter #(not(seq? %)) '(2 3 (3 32) 2 4 (5))) '(2 3 2 4))) ;filtriranje site ne sekventni elementi
  (is (= (my-filter seq? '(2 3 2 4)) '())) ;filtriranje 0 elementi rezultat
  (is (= (my-filter #(not (seq? %)) '(2 3 2 4)) '(2 3 2 4)))) ;filtriranje site elementi rezultat

(deftest test-my-reduce
  (is (= (my-reduce + '(1 2 3 4 5)) 15)) ;reduce - soberi lista
  (is (= (my-reduce + '()) nil)) ;reduce - soberi prazna lista
  (is (= (my-reduce + '(2)) 2)) ;reduce - soberi lista so 1 element
  (is (= (my-reduce + 2 '(1 2 3 4 5)) 17)) ;reduce - soberi lista so poceten value
  (is (= (my-reduce + 2 '()) 2))) ;reduce - soberi prazna lista so poceten value

(deftest test-my-flat-map
  (is (= (my-flat-map rest '((2 3 9) (4 5 8) (6 7 1))) '(3 9 5 8 7 1))) ;flat-map zemi gi ostanatite elementi od sekoja lista
  (is (= (my-flat-map divisors-of '(3 5 8 10)) '(2 4 2 5))) ;flat-map divisors na 4 broevi
  (is (= (my-flat-map rest '()) '())) ;flat-map prazna lista
  (is (= (my-flat-map rest '((2 ((3 9))) ((4 5) 8) (6 7 1))) '(((3 9)) 8 7 1))) ;flat-map rest od listi od lista
  (is (= (my-flat-map rest '((1))) '()))) ;flat-map eden element lista so rest

(run-tests)
