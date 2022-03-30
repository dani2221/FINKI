from constraint import *


def simona(vr):
    return vr == 13 or vr == 14 or vr == 16 or vr == 19


def marija(vr):
    return vr == 14 or vr == 15 or vr == 18


def petar(vr):
    return vr == 12 or vr == 13 or vr == 16 or vr == 17 or vr == 18 or vr == 19


def simona_here(s):
    return s == 1


def at_least_two(s, m, p):
    if s == 1:
        return m == 1 or p == 1
    else:
        return False


def check(s, m, p, vr):
    if m == 1 and not marija(vr):
        return False
    if p == 1 and not petar(vr):
        return False
    if s == 1 and not simona(vr):
        return False
    return True


if __name__ == '__main__':
    problem = Problem(BacktrackingSolver())

    # ---Dadeni se promenlivite, dodadete gi domenite-----
    problem.addVariable("Marija_prisustvo", [0, 1])
    problem.addVariable("Simona_prisustvo", [0, 1])
    problem.addVariable("Petar_prisustvo", [0, 1])
    problem.addVariable("vreme_sostanok", range(12, 21))
    # ----------------------------------------------------

    # ---Tuka dodadete gi ogranichuvanjata----------------
    problem.addConstraint(simona, ['vreme_sostanok'])
    problem.addConstraint(simona_here, ['Simona_prisustvo'])
    problem.addConstraint(check, ["Simona_prisustvo", "Marija_prisustvo", "Petar_prisustvo", "vreme_sostanok"])
    problem.addConstraint(at_least_two, ["Simona_prisustvo", "Marija_prisustvo", "Petar_prisustvo"])

    # ----------------------------------------------------

    [print(solution) for solution in problem.getSolutions()]