izbrisi_i_vrati_posleden([Posleden], [], Posleden).
izbrisi_i_vrati_posleden([X|Dr], [X|Po], Posleden) :- 
    izbrisi_i_vrati_posleden(Dr, Po, Posleden).

neparen_palindrom([_]).
neparen_palindrom([X|L]) :- 
    izbrisi_i_vrati_posleden(L, LnoPos, Pos),
    X == Pos,
    !,
    neparen_palindrom(LnoPos).






proveri_nazad([_]).
proveri_nazad([_,_]).
proveri_nazad([X,Y,Z|L]) :-
    Y>X,
    Y>Z,
    proveri_nazad([Z|L]).
   
proveri(L) :-
    [X,Y]=L,
    X<Y.
proveri(L) :-
    [_,_,_|_]=L,
    proveri_nazad(L).






podniza_so_dolzina(_,[],0).
podniza_so_dolzina([X|L],[X|LR],N) :- 
    podniza_so_dolzina(L, LR, M), 
    N is M+1.

ista_podniza(_,[]).
ista_podniza([X|L],[Y|PL]) :- 
    X==Y,
    ista_podniza(L,PL).
    
pojavuvanja_vo_niza([],_,0).
pojavuvanja_vo_niza([X|L],PL,Broj) :-
    ista_podniza([X|L],PL), pojavuvanja_vo_niza(L,PL,BrojN), Broj is BrojN + 1;
    pojavuvanja_vo_niza(L,PL,Broj).

maks_el(X, XNiza, Y, _, Z, ZNiza) :-
    X>Y, Z=X, ZNiza=XNiza.
maks_el(X, _, Y, YNiza, Z, ZNiza) :-
    X=<Y, Z=Y, ZNiza=YNiza.

naj_podniza_rec([],_,M,_,M).
naj_podniza_rec([X|LF],N,L,MaxBrojPojav, MaksNiza) :- 
    podniza_so_dolzina([X|LF],CheckNiza,N),
    pojavuvanja_vo_niza([X|LF],CheckNiza,OutBroj),
    maks_el(OutBroj, CheckNiza, MaxBrojPojav, MaksNiza, MaksRecBroj, MaksRecNiza),
	naj_podniza_rec(LF,N,L,MaksRecBroj, MaksRecNiza).
naj_podniza_rec([X|LF],N,L,_, MaksNiza) :- 
    not(podniza_so_dolzina([X|LF],_,N)),
    naj_podniza_rec([],_,L,_,MaksNiza).

naj_podniza(LF,N,L) :-
	naj_podniza_rec(LF,N,L,0,L), !.







brisi_prvo(X,[X|L],L).
brisi_prvo(X,[Y|L1],[Y|L2]) :- brisi_prvo(X,L1,L2).

zalepi_odnapred(_,[],[]).
zalepi_odnapred(X, [P|L], [Temp|LR]) :-
    zalepi_odnapred(X,L,LR),
    Temp = [X|P].
    
dodadi([],L, L).
dodadi([X|O],L,[X|NL]) :- dodadi(O,L,NL).

perm_test([],_,[[]]).
perm_test([X|L],LFull,NovRez) :-
    brisi_prvo(X,LFull,LSkrateno),
    perm_test(LSkrateno, LSkrateno, LSkratenoRez),
    zalepi_odnapred(X,LSkratenoRez,TempRezRec),
    perm_test(L,LFull,DopL),
    dodadi(TempRezRec,DopL,NovRez).

proveri_dolzina([],0).
proveri_dolzina([_|O],N) :- N>0, N1 is N-1, proveri_dolzina(O,N1).

izmeri_dolzina([],0).
izmeri_dolzina([_|O],N) :- izmeri_dolzina(O,N1), N is N1+1.


filtriraj_dolzina([],_,[]).
filtriraj_dolzina([X|L], XD, [X|Rez]) :-
    proveri_dolzina(X,XD),
    filtriraj_dolzina(L,XD,Rez).
filtriraj_dolzina([X|L], XD, Rez) :-
    not(proveri_dolzina(X,XD)),
    filtriraj_dolzina(L,XD,Rez).

permutacii(L1,LR) :-
    izmeri_dolzina(L1,L1D),
    perm_test(L1,L1,LRez),
    filtriraj_dolzina(LRez,L1D,LR).
    









dolzina([],0).
dolzina([_|O],N) :- dolzina(O,N1), N is N1+1.

izramni(L1,L2,L1R,L2R) :-
    dolzina(L1,N1),
    dolzina(L2,N2),
    N1>N2,
    Razlika is N1-N2,
    izramni_rec(L2,L2R,Razlika),
    L1R=L1,
    !.

izramni(L1,L2,L1R,L2R) :-
    dolzina(L1,N1),
    dolzina(L2,N2),
    N1<N2,
    Razlika is N2-N1,
    izramni_rec(L1,L1R,Razlika),
    L2R=L2,
    !.

izramni(L1,L2,L1R,L2R) :-
    dolzina(L1,N1),
    dolzina(L2,N2),
    N1=N2,
    L1R=L1,
    L2R=L2,
    !.
    
izramni_rec(L,L,0).
izramni_rec(L,[0|LR],N) :-
    M is N-1,
    izramni_rec(L,LR,M).

sobiranje(L1,L2,LRez) :-
    soberi_dirty(L1,L2,[X|LR]),
    chuvaj_carry(X,LR,LRez).

chuvaj_carry(0,LRez,LRez).
chuvaj_carry(1,LRez,[1|LRez]).

soberi_dirty(L1,L2,[Carry|LR]) :-
    izramni(L1,L2,L1R,L2R),
    soberi_rec(L1R,L2R,LR, Carry).

soberi_rec([],[],[], 0).
soberi_rec([X|L1],[Y|L2],[Z|LR], CarryM) :-
    soberi_rec(L1,L2,LR, Carry),
    Res is X + Y + Carry,
    rezultat_dodeli(Res, Z, CarryM).

rezultat_dodeli(0, 0, 0).
rezultat_dodeli(1, 1, 0).
rezultat_dodeli(2, 0, 1).
rezultat_dodeli(3, 1, 1).

komplement([0],[1]).
komplement([1],[0]).
komplement([X|L],[T|LR]) :-
    komplement(L,LR),
    obratno(X,T).
    
obratno(0,1).
obratno(1,0).


pogolem_broj([1|L],[0|_], [1|L]).
pogolem_broj([0|_],[1|L], [1|L]).
pogolem_broj([], [], []).
pogolem_broj([X|L1],[X|L2], [X|LR]) :- pogolem_broj(L1, L2, LR).
    
odzemanje(L1,L2,LRez) :- 
    izramni(L1,L2,L1R,L2R),
    pogolem_broj(L1R,L2R,L1R),
    komplement(L2R,L2RK),
    sobiranje(L2RK,[1],L2RK2), %vtor komplement
    sobiranje(L1R,L2RK2, LRezTemp),
    dolzina(L1,N1),
    dolzina(LRezTemp,N2),
    trgni_kec_odzemanje(N2,N1,LRezTemp, LRezTempSkrateno),
    izbrishi_preceeding_nuli_odzemanje(LRezTempSkrateno,LRez),!.

odzemanje(L1,L2,LRez) :-
    izramni(L1,L2,L1R,L2R),
    pogolem_broj(L1R,L2R,L2R),
    LRez=[0].

izbrishi_preceeding_nuli_odzemanje([1|L],[1|L]).
izbrishi_preceeding_nuli_odzemanje([0],[0]).
izbrishi_preceeding_nuli_odzemanje([_|L],LRez) :-
    izbrishi_preceeding_nuli_odzemanje(L,LRez).
    
trgni_kec_odzemanje(D1,D2,[_|L], L) :- D1>D2. 
trgni_kec_odzemanje(D1,D2,L, L) :- D1=D2.


mnozenje(L1,[1],L1):-!.
mnozenje(_,[0],[0]):-!.
mnozenje(L1,L2,LR) :-
    odzemanje(L2,[1],LMinus),
    mnozenje(L1,LMinus,LP),
    sobiranje(L1,LP,LR),!.
    
delenje(L,L,[1]):-!.
delenje(L1,L2,[0]) :- izramni(L1,L2,L1R,L2R),pogolem_broj(L1R,L2R,L2R),!.
delenje(L1,L2,LR) :-
    odzemanje(L1,L2,LOdzemeno),
    delenje(LOdzemeno,L2,LM),
    sobiranje(LM,[1],LR),!.








element_na_indeks_smeni_dimenzija(0,0,[X|_],X).
element_na_indeks_smeni_dimenzija(0,I2,[_|L],E) :-
    M is I2-1, element_na_indeks_smeni_dimenzija(0,M,L,E).

element_na_indeks(0,I2,[X|_],E) :- element_na_indeks_smeni_dimenzija(0,I2,X,E).
element_na_indeks(I1,I2,[_|L],E) :-
    M is I1-1, element_na_indeks(M,I2,L,E).

calc_multiply_na_indeks(_,_,_,0,M,M).
calc_multiply_na_indeks(L,I1,I2, E, Brojac, Dolzina) :-
    B is Brojac+1,
    calc_multiply_na_indeks(L,I1,I2, K, B, Dolzina),
    element_na_indeks(I1,Brojac,L,E1),    
    element_na_indeks(I2,Brojac,L,E2),
    E is K + E1*E2.


presmetaj_rec_1d(M,_,_,M,[]).
presmetaj_rec_1d(I1,I2,L, D, [El|R]) :-
    IR is I1 + 1,
    presmetaj_rec_1d(IR, I2, L, D, R),
    calc_multiply_na_indeks(L, I1, I2, El, 0, D).

presmetaj_rec_2d(M,_,M,[]).
presmetaj_rec_2d(I,L,D, [Rez|R]) :-
    IR is I + 1,
    presmetaj_rec_2d(IR, L, D, R),
    presmetaj_rec_1d(0,I,L,D,Rez).

presmetaj(M,R) :-
    dolzina(M,D),
    presmetaj_rec_2d(0,M,D,R),!.








get_lista_so_dolzini([],[]).
get_lista_so_dolzini([X|L],[D|LD]) :-
    get_lista_so_dolzini(L,LD),
    dolzina(X,D),
    !.

sortiraj_1iteracija([X|[]],[XD|[]], [X], [XD]).
sortiraj_1iteracija([X,Y|L], [XD,YD|LD], [B|RL], [BD|RD]) :-
    sortiraj_dve(X, Y, XD, YD, B, BD, S, SD,Ista),
    Ista==0,
    sortiraj_1iteracija([S|L], [SD|LD], RL, RD),
    !.
sortiraj_1iteracija([X,Y|L], [XD,YD|LD], [B|RL], [BD|RD]) :-
    sortiraj_dve(X, Y, XD, YD, B, BD, _, _,Ista),
    Ista==1,
    sortiraj_1iteracija(L, LD, RL, RD),
    !.

sortiraj_dve(X,Y,XD,YD,X,XD,Y,YD,0) :- XD>YD.
sortiraj_dve(X,Y,XD,YD,Y,YD,X,XD,0) :- YD>XD.
sortiraj_dve([E1|X],[E2|Y],XD,XD,[E1|X],XD,[E2|Y],XD,0) :- E1>E2.
sortiraj_dve([E1|X],[E2|Y],XD,XD,[E2|Y],XD,[E1|X],XD,0) :- E1<E2.
sortiraj_dve([],[],_,_,[],L,[],L,1).
sortiraj_dve([E1|X],[E1|Y],XD,XD,[E1|RP],XD,[E1|RS],XD,Ista) :-
    sortiraj_dve(X,Y,XD,XD,RP,XD,RS,XD,Ista).
    
transform(L,LR) :-
    get_lista_so_dolzini(L,LD),
    sortiraj_1iteracija(L,LD,LRTemp,LRD),
    dali_sortirana(LRTemp, LRD),
    LR=LRTemp,!.
transform(L,LR) :-
    get_lista_so_dolzini(L,LD),
    sortiraj_1iteracija(L,LD,LRTemp,_),
	transform(LRTemp,LR).

dali_sortirana([_|[]],_).
dali_sortirana([X,Y|L], [XD,YD|LRD]) :-
    sortiraj_dve(X,Y,XD,YD,R,_,_,_,Ista),
    X=R,
    Ista=0,
    dali_sortirana([Y|L],[YD|LRD]),
    !.
    







e_lista([_|_]).
e_lista([]).

izramni([],[]).
izramni([X|L1],[X|L2]):-not(e_lista(X)),!,izramni(L1,L2).
izramni([X|L1],L2):-izramni(X,LX),izramni(L1,LL1),dodadi(LX,LL1,L2).

deep_dolzina([],0).
deep_dolzina([X|O],N) :- 
    not(e_lista(X)),
    deep_dolzina(O,N1), N is N1+1.
deep_dolzina([X|O],N) :- 
    e_lista(X),
    deep_dolzina(O,N1),
    deep_dolzina(X,N2), N is N1+N2.

    

skrati_od_lista(L,0,L).
skrati_od_lista([_|L],N,LR) :-
    NR is N-1,
    skrati_od_lista(L,NR,LR).

brishi([],[],_).
brishi([X|L],[X|LRez],[P|LPojavuvanja]) :-
    not(e_lista(X)),
    1 is P mod 2,
    brishi(L,LRez,LPojavuvanja).
brishi([X|L],LRez,[P|LPojavuvanja]) :-
    not(e_lista(X)),
    0 is P mod 2,
    brishi(L,LRez,LPojavuvanja).

brishi([X|L],[OutRez|SlRez],LPojavuvanja) :-
    e_lista(X),
    deep_dolzina(X,XDolzina),
    brishi(X,OutRez,LPojavuvanja),
    skrati_od_lista(LPojavuvanja, XDolzina, LPojavuvanjaNew),
   	brishi(L,SlRez,LPojavuvanjaNew).

broj_pojavuvanja_element(_,[],0).
broj_pojavuvanja_element(A,[A|L],Broj) :-
    broj_pojavuvanja_element(A,L,BrOut),
    Broj is BrOut+1.
broj_pojavuvanja_element(A,[_|L],Broj) :-
    broj_pojavuvanja_element(A,L,Broj).

broj_pojavuvanja([],_,[]).
broj_pojavuvanja([X|L], FullL, [TempBroj|OutArr]) :-
    broj_pojavuvanja_element(X,FullL,FullBroj),
    broj_pojavuvanja_element(X,L,MinusBroj),
    TempBroj is FullBroj - MinusBroj,
    broj_pojavuvanja(L,FullL, OutArr).

brisi_sekoe_vtoro(L,LR) :-
    izramni(L,LIzramneta),
    broj_pojavuvanja(LIzramneta,LIzramneta,LPojavuvanja),
    brishi(L,LR,LPojavuvanja),!.



