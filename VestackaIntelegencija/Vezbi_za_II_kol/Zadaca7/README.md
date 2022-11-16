Дадено ни е податочно множество за соларен одблесок. Сите атрибути кои ги содржи се од категориски тип. Ваша задача е да истренирате класификатор - дрво на одлука кој ќе предвидува класи на соларен одблесок. Од стандарден влез се чита бројот на примероци X за кои треба да се направи предвидувањето. Последните X примероци се земаат за тестирање, додека сите останати примероци се за тренирање (на пр. ако X=6, последните 6 примероци се тест примероци, а останатите примероци се дел од тренирачкото множество). 

Во почетниот код имате дадено податочно множество, како и објект од моделот DecisionTreeClassifier. Ваша задача е да го поделите првичното податочно множество на множество за тренирање и множество за тестирање. Потоа, истренирајте го моделот. Пресметајте точност и прецизност на моделот со тестирачкото множество и вредностите испечатете ги на стандарден излез. Напомена: Освен тоа што се бара не е потребно да имплементирате ништо друго!

точност = (TP + TN) / (TP + FP + TN + FN)
прецизност = TP / (TP + FP)


TP - број на точно предвидени позитивни класи

FP - број на грешно предвидени позитивни класи

TN - број на точно предвидени негативни класи

FN - број на грешно предвидени негативни класи



ЗАБЕЛЕШКА: Ако TP + FP е 0, тогаш вредноста за прецизноста е 0.

За да ги добиете истите резултати како и во тест примерите, при креирање на класификаторот поставете random_state=0