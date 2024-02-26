(ns user.boja (:use clojure.test))

(deftest test-solve
  (is (= (solve [[1 2 3 4 0]]) [[1 2 3 4 0]])) ; test so edna pracka
  (is (= (solve [[0 1 2] [0 1 2] [0 1 2]]) [[0 1 2] [2 0 1] [1 2 0]])) ; test site isti - pravi rotacii
  (is (= (solve [[0 1 2 3 4] [4 3 2 1 0] [3 0 2 1 4]]) false)) ; ne postoi reshenie vo bilo koja rotacija
  (is (= (solve [[0 1 2 3 4 5 6] [0 2 1 3 4 5 6] [2 0 3 1 4 6 5]]) [[0 1 2 3 4 5 6] [5 6 0 2 1 3 4] [1 4 6 5 2 0 3]])) ; mnogu broevi malku pracki
  (is (= (solve [[0 1] [1 0] [0 1] [0 1]]) false)) ; malku broevi mnogu pracki
  (is (= (solve [[0]]) [[0]])) ; eden broj edna pracka
  (is (= (solve [[0 1 2 3 4] [4 3 2 1 0]]) false)) ; 5 broja 2 pracki no so bilo koja rotacija se poklopuvaat
  (is (= (solve [[0 1 2 3] [3 2 1 0] [2 0 3 1]]) [[0 1 2 3] [3 2 1 0] [2 0 3 1]])) ; validno resenie
  (is (= (solve [[0 1] [1 0]]) [[0 1] [1 0]])) ; validno resenie kako input
  (is (= (solve [[] [] [] []]) false))) ; prazni pracki

(run-tests)
