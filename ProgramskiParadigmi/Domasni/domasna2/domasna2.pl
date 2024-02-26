lice(1,petko,petkovski,m,datum(1,3,1950),kratovo,skopje).
lice(2,marija,petkovska,z,datum(30,5,1954),kumanovo,skopje).
lice(3,ljubica,petkovska,z,datum(29,11,1965),skopje,skopje).
lice(4,vasil,vasilev,m,datum(8,4,1954),bitola,bitola).
lice(5,elena,vasileva,z,datum(19,6,1958),resen,bitola).
lice(6,krste,krstev,m,datum(9,8,1948),veles,veles).
lice(7,biljana,krsteva,z,datum(13,8,1949),veles,veles).
lice(8,igor,krstev,m,datum(26,10,1971),veles,skopje).
lice(9,kristina,krsteva,z,datum(30,5,1974),kumanovo,skopje).
lice(10,julija,petrova,z,datum(30,5,1978),skopje,skopje).
lice(11,bosko,petkovski,m,datum(13,11,1981),skopje,skopje).
lice(12,gjorgji,vasilev,m,datum(15,7,1978),bitola,bitola).
lice(13,katerina,petkovska,z,datum(11,12,1979),bitola,skopje).
lice(14,petar,vasilev,m,datum(21,2,1982),skopje,skopje).
lice(15,andrej,krstev,m,datum(3,8,1998),skopje,skopje).
lice(16,martina,petkovska,z,datum(5,12,2005),skopje,skopje).
familija(1,2,[9,10]).
familija(1,3,[11]).
familija(4,5,[12,13,14]).
familija(6,7,[8]).
familija(8,9,[15]).
familija(11,13,[16]).

rodeni_razlicen_grad(S) :-
    findall(S, roden_razlicen_grad(S), L),
    soberi_lista(L,S).

soberi_lista([],0).
soberi_lista([X|L],K) :-
    soberi_lista(L,KR),
    K is KR + X.

roden_razlicen_grad(S) :-
    familija(T,M,B),
    semejstvo_rodeni(T,M,B,S).

semejstvo_rodeni(_,_,[],0).
semejstvo_rodeni(T,M,[K|B],SO) :-
    semejstvo_rodeni(T,M,B,SO),
    not(uslov(T,M,K)).

semejstvo_rodeni(T,M,[K|B],S) :-
    semejstvo_rodeni(T,M,B,SO),
    uslov(T,M,K),
    S is SO + 1.

uslov(T,M,K) :-
    lice(T,_,_,_,_,TR,_),
    lice(M,_,_,_,_,MR,_),
    lice(K,_,_,_,_,DR,_),
    DR \= MR,
    DR \= TR.






dodadi([],L, L).
dodadi([X|O],L,[X|NL]) :- dodadi(O,L,NL).

predci(X,LR) :-
    predci_site(X,L),
    predci_filter(X,L,LR),
    !.

predci_site(X,[]) :- not(najdi_majka_tatko(X,_,_)).
predci_site(X,L) :-
    najdi_majka_tatko(X,M,T),
    predci_site(M,LM),
    predci_site(T,LT),
    dodadi([M|LM],[T|LT],L).
    
najdi_majka_tatko(X,M,T) :-
    familija(M,T,L),
    najdi_familija(X,L).

najdi_familija(X,[X|_]).
najdi_familija(X,[_|B]) :- najdi_familija(X,B).

predci_filter(_,[],[]).
predci_filter(X, [P|L], [P|LR]) :-
    predci_filter(X,L,LR),
    lice(X,_,_,XPol,datum(XDen,XMesec,_),_,_),
    lice(P,_,_,XPol,datum(PDen,PMesec,_),_,_),
    proveri_raganje(XDen, XMesec, PDen, PMesec).

predci_filter(X, [P|L], LR) :-
    predci_filter(X,L,LR),
    lice(X,_,_,_,_,_,_),
    lice(P,_,_,_,_,_,_).

proveri_raganje(XDen,12,PDen,1) :-
    XVal is XDen,
    PVal is 30 + PDen,
    Diff is PVal - XVal,
    Diff =< 7,
    Diff >= -7.
proveri_raganje(XDen,1,PDen,12) :-
    XVal is 30 + XDen,
    PVal is PDen,
    Diff is PVal - XVal,
    Diff =< 7,
    Diff >= -7.
proveri_raganje(XDen, XMesec, PDen, PMesec) :-
    XVal is XMesec*30 + XDen,
    PVal is PMesec*30 + PDen,
    Diff is PVal - XVal,
    Diff =< 7,
    Diff >= -7.
    






telefon(111111,petko,petkovski,[povik(222222,250),povik(101010,125)]).
telefon(222222,marija,petkovska,[povik(111111,350),povik(151515,113),povik(171717,122)
]).
telefon(333333,ljubica,petkovska,[povik(555555,150),povik(101010,105)]).
telefon(444444,vasil,vasilev,[povik(171717,750)]).
telefon(555555,elena,vasileva,[povik(333333,250),povik(101010,225)]).
telefon(666666,krste,krstev,[povik(888888,75),povik(111111,65),povik(141414,50),povik(
161616,111)]).
telefon(777777,biljana,krsteva,[povik(141414,235)]).
telefon(888888,igor,krstev,[povik(121212,160),povik(101010,225)]).
telefon(999999,kristina,krsteva,[povik(666666,110),povik(111111,112),povik(222222,55)]
).
telefon(101010,julija,petrova,[]).
telefon(121212,bosko,petkovski,[povik(444444,235)]).
telefon(131313,gjorgji,vasilev,[povik(141414,125),povik(777777,165)]).
telefon(141414,katerina,petkovska,[povik(777777,315),povik(131313,112)]).
telefon(151515,petar,vasilev,[]).
telefon(161616,andrej,krstev,[povik(666666,350),povik(111111,175),povik(222222,65),povik(101010,215)]).
telefon(171717,martina,petkovska,[povik(222222,150)]).
sms(111111,[222222,999999,101010]).
sms(444444,[333333,121212,161616]).
sms(111111,[777777]).
sms(666666,[888888]).
sms(444444,[555555,121212,131313,141414]).
sms(666666,[777777,888888]).
sms(888888,[999999,151515]).
sms(171717,[131313,161616]).

dolzina([],0).
dolzina([_|O],N) :- dolzina(O,N1), N is N1+1.

clen(X,[X|_]) :- ! .
clen(X,[_|L]) :- clen(X,L).

otstrani_duplikati([ ],[ ]).
otstrani_duplikati([X|L1],L2):- clen(X,L1) , ! ,otstrani_duplikati(L1,L2).
otstrani_duplikati([X|L1],[X|L2]) :- otstrani_duplikati(L1,L2).

najbroj(X,Y):-
    findall([B,BP], broj_povikuvanja(B,BP), L),
    modificiran_maksimum(L,[B,_]),
    telefon(B,X,Y,_).

broj_povikuvanja(T,B):- 
    broj_povikuvanja_pojdovni(T,BP),
    broj_povikuvanja_dojdovni(T,BD),
    dodadi(BP,BD,BTemp),
    otstrani_duplikati(BTemp, BOut),
    dolzina(BOut, B).

broj_povikuvanja_pojdovni(T, LR) :-
    telefon(T,_,_,L),
    mapiraj_vo_broj(L,LR).

mapiraj_vo_broj([],[]).
mapiraj_vo_broj([povik(BR,_)|L],[BR|LR]) :- mapiraj_vo_broj(L,LR).

broj_povikuvanja_dojdovni(T, L) :-
    findall(B, broj_povikuvanja_dojdovni_posebno(T,B), L).

broj_povikuvanja_dojdovni_posebno(T,BT) :-
    telefon(BT,_,_,L),
    modificiran_clen(T,L).

modificiran_clen(X,[povik(X,_)|_]) :- ! .
modificiran_clen(X,[_|L]) :- modificiran_clen(X,L).

modificiran_maksimum([X|O],M) :- modificiran_maks(O,X,M),!.
modificiran_maks([[_,X]|O],[YBr,Y],M) :- X<Y, modificiran_maks(O,[YBr,Y],M).
modificiran_maks([[XBr,X]|O],[_,Y],M) :- X>=Y, modificiran_maks(O,[XBr,X],M).
modificiran_maks([ ],M,M).




omilen(X,Y) :-
    findall([YTemp,Sum], omilen_posebno(X,YTemp,Sum), LOut),
    modificiran_maksimum(LOut, [Y,_]).

omilen_posebno(X,Y,Sum) :-
    telefon(Y, _, _, _),
    omilen_pojdoven(X,Y,PSum),
    omilen_dojdoven(X,Y,DSum),
    findall(S,omilen_sms_sender(X,Y,S),LOut),
    soberi(LOut, SSSum),
    findall(Sk,omilen_sms_reciever(X,Y,Sk),LkOut),
    soberi(LkOut, SRSum),
    Sum is PSum + DSum + SSSum + SRSum.

omilen_pojdoven(X,Y,PSum) :-
    telefon(X,_,_,L),
    omilen_povik_vnatre_lista(L,Y,PSum),
    !.

omilen_dojdoven(X,Y,DSum) :-
    telefon(Y,_,_,L),
    omilen_povik_vnatre_lista(L,X,DSum),
    !.

omilen_povik_vnatre_lista([],_,0).
omilen_povik_vnatre_lista([povik(Y,Val)|_], Y, Val).
omilen_povik_vnatre_lista([povik(_,_)|L], Y, Val) :- 
    omilen_povik_vnatre_lista(L,Y,Val).

omilen_sms_sender(X, Y, SSSum) :-
    sms(X,L),
    omilen_sms_vnatre_lista(L,Y, SSSum).
    
soberi([],0).
soberi([X|L],Sum) :-
    soberi(L,SumO),
    Sum is SumO + X.

omilen_sms_reciever(X, Y, SSSum) :-
    sms(Y,L),
    omilen_sms_vnatre_lista(L,X, SSSum).

omilen_sms_vnatre_lista([],_,0).
omilen_sms_vnatre_lista([Y|_], Y, 100).
omilen_sms_vnatre_lista([_|L], Y, Val) :- 
    omilen_sms_vnatre_lista(L,Y,Val).
    
    











klient(1,petko,petkov,[usluga(a,b,50,datum(12,12,2015),23),usluga(c,a,50,datum(7,12,2015),34),usluga(c,f,40,datum(7,11,2015),23)]).
klient(2,vasil,vasilev,[usluga(a,e,50,datum(25,12,2015),12),usluga(c,g,40,datum(17,11,2015),56),usluga(g,d,50,datum(17,12,2015),45),usluga(e,a,40,datum(24,12,2015),34)]).
klient(3,krste,krstev,[usluga(c,b,60,datum(31,12,2015),56),usluga(e,f,60,datum(31,12,2015),34)]).
klient(4,petar,petrov,[usluga(a,f,50,datum(25,12,2015),23),usluga(f,d,50,datum(25,12,2015),34)]).
klient(5,ivan,ivanov,[usluga(d,g,50,datum(7,12,2015),56),usluga(g,e,40,datum(25,12,2015),34)]).
klient(6,jovan,jovanov,[usluga(c,f,50,datum(5,12,2015),12),usluga(f,d,50,datum(27,12,2015),45)]).
klient(7,ana,aneva,[usluga(e,d,50,datum(11,12,2015),12),usluga(d,g,50,datum(11,12,2015),12)]).
klient(8,lidija,lideva,[usluga(e,g,50,datum(29,12,2015),45),usluga(f,b,50,datum(29,12,2015),34)]).

rastojanie(a,b,4).
rastojanie(a,c,7).
rastojanie(b,c,5).
rastojanie(b,d,3).
rastojanie(c,d,4).
rastojanie(b,e,6).
rastojanie(c,e,2).
rastojanie(b,f,8).
rastojanie(e,f,5).
rastojanie(f,g,3).

izbroj_lokacija(X,Br) :-
    findall(N, izbroj_lokacija_posebno(X,N), LOut),
    soberi_lista(LOut, Br).


izbroj_lokacija_posebno(X,Br) :- 
    klient(_,_,_,Uslugi),
    izbroj_pojavuvanja(X,Uslugi, Br).

izbroj_pojavuvanja(_,[],0).
izbroj_pojavuvanja(X, [usluga(X,_,_,_,_) | L], Br) :-
    izbroj_pojavuvanja(X, L, BrO),
    Br is BrO + 1, !.
izbroj_pojavuvanja(X, [usluga(_,X,_,_,_) | L], Br) :-
    izbroj_pojavuvanja(X, L, BrO),
    Br is BrO + 1, !.
izbroj_pojavuvanja(X, [usluga(_,_,_,_,_) | L], BrO) :-
    izbroj_pojavuvanja(X, L, BrO), !.

najmnogu_kilometri(X,Y) :-
    findall([Id, Km],pominati_km_po_user(Id, Km), LOut),
    maksimum_km(LOut,[IdOut,_]),
    klient(IdOut, X, Y, _).

pominati_km_po_user(Id, Km) :-
    klient(Id, _, _, Uslugi),
    suma_od_km(Uslugi, Km).

suma_od_km([],0).
suma_od_km([usluga(A,B,_,_,_) | L], Km) :-
    suma_od_km(L, KmOut),
    findall(Dolzina,presmetaj_dolzina(A,B,Dolzina,[]),LOut),
    najdi_najkratko(LOut,100000,Min),
    Km is Min + KmOut.

presmetaj_dolzina(A, B, Km, _) :- rastojanie(A, B, Km).
presmetaj_dolzina(A, B, Km, _) :- rastojanie(B, A, Km).
presmetaj_dolzina(A,B, Km, Videno) :- 
    not(clen([A,B], Videno)),
    rastojanie(A,C,KmD),
    A \=B,
    presmetaj_dolzina(C,B,KmO, [[A,B]| Videno]),
    Km is KmD + KmO.
presmetaj_dolzina(A,B, Km, Videno) :- 
    not(clen([A,B], Videno)),
    rastojanie(C,A,KmD),
    A \=B,
    presmetaj_dolzina(C,B,KmO, [[A,B]| Videno]),
    Km is KmD + KmO.

najdi_najkratko([],Min,Min).
najdi_najkratko([X|L], Min, MinOut) :-
    X < Min,
    najdi_najkratko(L, X, MinOut).
najdi_najkratko([X|L], Min, MinOut) :-
    X >= Min,
    najdi_najkratko(L, Min, MinOut).


maksimum_km([[Id,Broj]|O],M) :- maks_km(O,[Id,Broj],M),!.
maks_km([[_,Broj1]|O],[Id2,Broj2],M) :- Broj1=<Broj2, maks_km(O,[Id2,Broj2],M).
maks_km([[Id1,Broj1]|O],[_,Broj2],M) :- Broj1>Broj2, maks_km(O,[Id1,Broj1],M).
maks_km([ ],M,M).


najmnogu_zarabotil(X) :-
    najdi_site_taksinja(L),
    najdi_najvekje_zarabotil(L,LOut),
    modificiran_maksimum(LOut, [X,_]).
    
najdi_site_taksinja(L) :-
    findall(LV, taksi_po_klient(LV), LOut),
    izramni(LOut, LIzramneto),
    otstrani_duplikati(LIzramneto, L).

taksi_po_klient(L) :-
    klient(_,_,_,LUslugi),
    mapiraj_taksi(LUslugi, L).

mapiraj_taksi([],[]).
mapiraj_taksi([usluga(_,_,_,_,T)|LUslugi], [T|L]) :-
    mapiraj_taksi(LUslugi, L).

izramni([],[]).
izramni([X|L1],[X|L2]):-not(e_lista(X)),!,izramni(L1,L2).
izramni([X|L1],L2):-izramni(X,LX),izramni(L1,LL1),dodadi(LX,LL1,L2).


e_lista([_|_]).
e_lista([]).


najdi_najvekje_zarabotil([],[]).
najdi_najvekje_zarabotil([X|L], [[X,Z]|LOut]) :-
    najdi_zarabotuvacka(X, Z),
    najdi_najvekje_zarabotil(L,LOut).

najdi_zarabotuvacka(X, Z) :-
    findall(ZK,najdi_zarabotuvacka_po_klient(X,ZK),LOut),
    soberi(LOut, Z).

najdi_zarabotuvacka_po_klient(X, Z) :-
    klient(_,_,_,LUslugi),
    soberi_pari_od_uslugi_cut(X,LUslugi, Z).

soberi_pari_od_uslugi_cut(X,LUslugi,Z) :-
    soberi_pari_od_uslugi(X,LUslugi,Z),
    !.

soberi_pari_od_uslugi(_,[],0).
soberi_pari_od_uslugi(X, [usluga(_,_,_,_,Y) | L], Pari) :-
    X \= Y,
    soberi_pari_od_uslugi(X, L, Pari).
soberi_pari_od_uslugi(X, [usluga(Od,Do,Cena,datum(_,12,2015),X) | L], Pari) :-
    soberi_pari_od_uslugi(X,L,PariRec),
    findall(Dolzina,presmetaj_dolzina(Od,Do,Dolzina,[]),LOut),
    najdi_najkratko(LOut,100000,Min),
    Pari is PariRec + (Min*Cena).
soberi_pari_od_uslugi(X, [usluga(_,_,_,_,X) | L], Pari) :-
    soberi_pari_od_uslugi(X, L, Pari),
    !.
    








    
    