import math

from constraint import *

if __name__ == '__main__':
    n = int(input())
    problem = Problem(BacktrackingSolver())
    variables = range(1,n+1)
    for variable in variables:
        problem.addVariable(variable, [(i,j) for i in range(n) for j in range(n)])

    # ---Tuka dodadete gi ogranichuvanjata----------------
    problem.addConstraint(AllDifferentConstraint(),Domain(variables))
    for var1 in variables:
        for var2 in variables:
            if var1 == var2:
                continue
            problem.addConstraint(lambda q1,q2: q1[0]!=q2[0], (var1, var2))
            problem.addConstraint(lambda q1,q2: q1[1]!=q2[1], (var1, var2))
            # big brain reshenie za dijagonalite - se presmetuva koeficientot na prava pomegju dvete kralici
            problem.addConstraint(lambda q1,q2: (q1[1]-q2[1])/(q1[0]-q2[0])!=1, (var1, var2))
            problem.addConstraint(lambda q1,q2: (q1[1]-q2[1])/(q1[0]-q2[0])!=-1, (var1, var2))
    # ----------------------------------------------------
    if n>=6:
        print(problem.getSolution())
    else:
        print(len(problem.getSolutions()))