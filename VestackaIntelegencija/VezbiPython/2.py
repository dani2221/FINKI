import math

if __name__ == '__main__':
    instructions = 'UP 5 DOWN 3 LEFT 3 RIGHT 2'
    poz = (0,0)
    spl = instructions.split(" ")
    for i,val in enumerate(spl):
        if spl[i] == 'RIGHT':
            poz = (poz[0]+int(spl[i+1]), poz[1])
        if spl[i] == 'LEFT':
            poz = (poz[0]-int(spl[i+1]), poz[1])
        if spl[i] == 'UP':
            poz = (poz[0], poz[1]+int(spl[i+1]))
        if spl[i] == 'DOWN':
            poz = (poz[0], poz[1]-int(spl[i+1]))

    print(poz)
    euclidean = math.sqrt((0 - poz[0]) ^ 2 + (0 - poz[1]) ^ 2)
    manhattan = math.fabs(poz[0]) + math.fabs(poz[1])
    print(euclidean)
    print(manhattan)