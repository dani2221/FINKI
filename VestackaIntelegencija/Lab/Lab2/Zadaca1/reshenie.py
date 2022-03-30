from constraint import *

if __name__ == '__main__':
    clenovi_count = int(input())
    clenovi = dict()
    for i in range(clenovi_count):
        ipt = input().split(" ")
        clenovi[float(ipt[0])] = ipt[1]

    lideri_count = int(input())
    lideri = dict()
    for i in range(lideri_count):
        ipt = input().split(" ")
        lideri[float(ipt[0])] = ipt[1]

    problem = Problem(BacktrackingSolver())
    problem.addVariable(1, Domain(lideri.keys()))
    problem.addVariables(range(2, 7), Domain(clenovi.keys()))

    problem.addConstraint(AllDifferentConstraint(), [1, 2, 3, 4, 5, 6])
    problem.addConstraint(MaxSumConstraint(100), [1, 2, 3, 4, 5, 6])

    solutions = problem.getSolutions()
    solutions.sort(key=lambda e: sum(e.values()), reverse=True)
    solution = solutions[0]
    print(f'Total score: {sum(solution.values())}')
    for k in solution:
        if k == 1:
            print(f'Team leader: {lideri[solution[k]]}')
        else:
            print(f'Participant {k - 1}: {clenovi[solution[k]]}')



