student(teo).
student(mira).
student(bruno).
student(igor).

hrana(sendvic).
hrana(pita).
hrana(hamburger).
hrana(pica).

hobi(krstozbor).
hobi(pisuvanje).
hobi(citanje).
hobi(fotografija).

maica(bela).
maica(zolta).
maica(sina).
maica(crvena).

devojka(mira).


jade(teo, sendvic).
jade(mira, pita).

jade(X, pica) :- 
    student(X),
    X \= mira,
    X \= teo,
    nosi_maica(Y, bela),
    (pozicija(X, Y) ; pozicija(Y, X)), !.

jade(X, hamburger):-
    student(X),
    X \= mira,
    X \= teo,
    not(jade(X, pica)).

ima_hobi(mira, krstozbor).
ima_hobi(igor, citanje).

ima_hobi(X, pisuvanje):- 
    student(X),
    X \= mira,
    X \= igor,
    jade(X, hamburger).

ima_hobi(X, fotografija):- 
    student(X),
    X \= mira,
    X \= igor,
    not(ima_hobi(X, pisuvanje)).

nosi_maica(bruno, zolta).

nosi_maica(X, bela) :-
    student(X),
    devojka(X).

nosi_maica(X, sina) :-
    student(X),
    X \= bruno,
    devojka(Y),
    pozicija(Y,X).

nosi_maica(X,crvena) :- 
    student(X),
    maica(Y),
    X \= bruno,
    Y \= zolta,    
    Y \= bela,
    Y \= sina,
    not(nosi_maica(X, sina)),
    not(nosi_maica(X, bela)).

teo_najlevo(X, Y) :- student(X), student(Y), Y\=teo.

pozicija(teo, Y) :-
    student(Y),
    teo_najlevo(teo, Y),
    jade(Y, pita).

pozicija(X, teo) :-
    student(X),
    teo_najlevo(X, teo),
    jade(X, pita).

pozicija(X, bruno) :-
    student(X),
    X \= bruno,
    teo_najlevo(X, bruno),
    jade(X, pica).

pozicija(X, Y) :-
    student(X),
    student(Y),
    X \= bruno,
    Y \= bruno,
    X \= teo,
    Y \= teo,
    teo_najlevo(X, Y),
    X \= Y.

reshenie(L) :-
    pozicija(X,_),
    !,
    resenie_vnatresno(X,L).

resenie_vnatresno(X, [[X,Hr,Ho,Ma]]) :-
    not(pozicija(X,_)),
    !,
    jade(X, Hr),
    ima_hobi(X,Ho),
    nosi_maica(X,Ma).

resenie_vnatresno(X,[[X,Hr,Ho,Ma]|L]) :-
    pozicija(X,Y),
    nema_povtoruvanje(X,Y),
    !,
    resenie_vnatresno(Y,L),
    jade(X, Hr),
    ima_hobi(X,Ho),
    nosi_maica(X,Ma).

nema_povtoruvanje(X, _) :-
    not(pozicija(_,X)).
nema_povtoruvanje(X, Y) :-
    pozicija(N,X),
    N \= Y,
    nema_povtoruvanje(N,Y).
