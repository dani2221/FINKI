if __name__ == '__main__':
    tekst = 'New to Python or choosing between Python 2 and Python 3? Read Python 2 or Python 3.'
    freq = dict()
    for zbor in tekst.split(' '):
        freq[zbor] = 1 if zbor not in freq.keys() else freq[zbor]+1
    for k,v in sorted(freq.items()):
        print(f'{k}: {v}')