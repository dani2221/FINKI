from constraint import *


def mlcnst(v1, v2):
    return v1[-2:] != v2[-2:]


def allcnst(v1, v2):
    den1 = v1[:3]
    den2 = v2[:3]
    cas1 = int(v1[-2:])
    cas2 = int(v2[-2:])
    return den1 != den2 or abs(cas1 - cas2) > 1


if __name__ == '__main__':
    problem = Problem(BacktrackingSolver())
    casovi_AI = int(input())
    casovi_ML = int(input())
    casovi_R = int(input())
    casovi_BI = int(input())

    AI_predavanja_domain = ["Mon_11", "Mon_12", "Wed_11", "Wed_12", "Fri_11", "Fri_12"]
    ML_predavanja_domain = ["Mon_12", "Mon_13", "Mon_15", "Wed_12", "Wed_13", "Wed_15", "Fri_11", "Fri_12", "Fri_15"]
    R_predavanja_domain = ["Mon_10", "Mon_11", "Mon_12", "Mon_13", "Mon_14", "Mon_15", "Wed_10", "Wed_11", "Wed_12",
                           "Wed_13", "Wed_14", "Wed_15", "Fri_10", "Fri_11", "Fri_12", "Fri_13", "Fri_14", "Fri_15"]
    BI_predavanja_domain = ["Mon_10", "Mon_11", "Wed_10", "Wed_11", "Fri_10", "Fri_11"]

    AI_vezbi_domain = ["Tue_10", "Tue_11", "Tue_12", "Tue_13", "Thu_10", "Thu_11", "Thu_12", "Thu_13"]
    ML_vezbi_domain = ["Tue_11", "Tue_13", "Tue_14", "Thu_11", "Thu_13", "Thu_14"]
    BI_vezbi_domain = ["Tue_10", "Tue_11", "Thu_10", "Thu_11"]

    # ---Tuka dodadete gi promenlivite--------------------
    AI_predavanja = [f'AI_cas_{i}' for i in range(1, casovi_AI + 1)]
    ML_predavanja = [f'ML_cas_{i}' for i in range(1, casovi_ML + 1)]
    R_predavanja = [f'R_cas_{i}' for i in range(1, casovi_R + 1)]
    BI_predavanja = [f'BI_cas_{i}' for i in range(1, casovi_BI + 1)]
    problem.addVariables(AI_predavanja, Domain(AI_predavanja_domain))
    problem.addVariables(ML_predavanja, Domain(ML_predavanja_domain))
    problem.addVariables(R_predavanja, Domain(R_predavanja_domain))
    problem.addVariables(BI_predavanja, Domain(BI_predavanja_domain))
    problem.addVariable('AI_vezbi', Domain(AI_vezbi_domain))
    problem.addVariable('ML_vezbi', Domain(ML_vezbi_domain))
    problem.addVariable('BI_vezbi', Domain(BI_vezbi_domain))

    site = AI_predavanja + ML_predavanja + R_predavanja + BI_predavanja + ['AI_vezbi', 'ML_vezbi', 'BI_vezbi']
    # ---Tuka dodadete gi ogranichuvanjata----------------
    for var1 in site:
        for var2 in site:
            if var1 == var2:
                continue
            problem.addConstraint(allcnst, (var1, var2))

    for var1 in [t for t in site if t[:2] == "ML"]:
        for var2 in [t for t in site if t[:2] == "ML"]:
            if var1 == var2:
                continue
            problem.addConstraint(mlcnst, (var1, var2))
    # ----------------------------------------------------
    solution = problem.getSolution()

    print(solution)