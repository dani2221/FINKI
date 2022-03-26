from constraint import *


def valid(s, e, n, d, m, o, r, y):
    return 1000 * s + 100 * e + 10 * n + d + 1000 * m + 100 * o + 10 * r + e == 10000 * m + 1000 * o + 100 * n + 10 * e + y


if __name__ == '__main__':
    problem = Problem(BacktrackingSolver())
    variables = ["S", "E", "N", "D", "M", "O", "R", "Y"]
    for variable in variables:
        problem.addVariable(variable, Domain(set(range(10))))

    # ---Tuka dodadete gi ogranichuvanjata----------------
    problem.addConstraint(AllDifferentConstraint(),variables)
    problem.addConstraint(valid, variables)

    # ----------------------------------------------------

    print(problem.getSolution())