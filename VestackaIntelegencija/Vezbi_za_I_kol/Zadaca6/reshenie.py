from constraint import *

def blok(num):
    if num%9 < 3 and num//9 < 3:
        return 1
    if num%9 < 3 and num//9 < 6:
        return 2
    if num % 9 < 3:
        return 3

    if num%9 < 6 and num//9 < 3:
        return 4
    if num%9 < 6 and num//9 < 6:
        return 5
    if num%9 < 6:
        return 6

    if num%9 < 9 and num//9 < 3:
        return 7
    if num%9 < 9 and num//9 < 6:
        return 8

    return 9

if __name__ == '__main__':
    type = input()
    problem = Problem(BacktrackingSolver())
    if type == "RecursiveBacktrackingSolver":
        problem = Problem(RecursiveBacktrackingSolver())
    if type == "MinConflictsSolver":
        problem = Problem(MinConflictsSolver())
    variables = tuple(range(81))
    domain = tuple(range(10))
    for variable in variables:
        problem.addVariable(variable, Domain(domain))

    # ---Tuka dodadete gi ogranichuvanjata----------------
    # 00 01 02 03 04 05 06 07 08
    # 09 10 11 12 13 14 15 16 17
    # 18 19 20 21 22 23 24 25 26
    # 27 28 29 30 31 32 33 34 35
    for var1 in variables:
        for var2 in [var for var in variables if var%9==var1%9]:
            if var1 == var2:
                continue
            problem.addConstraint(lambda x,y: x!=y,(var1,var2))
        for var2 in [var for var in variables if var//9==var1//9]:
            if var1 == var2:
                continue
            problem.addConstraint(lambda x,y: x!=y,(var1,var2))
        for var2 in [var for var in variables if blok(var)==blok(var1)]:
            if var1 == var2:
                continue
            problem.addConstraint(lambda x,y: x!=y,(var1,var2))
    # ----------------------------------------------------

    print(problem.getSolution())