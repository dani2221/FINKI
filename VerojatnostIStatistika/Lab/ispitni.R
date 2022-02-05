# Практичен R февруари 2020 Г1
# Задача 1. (2) Во една амбуланта секој час пристигнуваат пациенти со рата 10 на час. Симулирајте 100 вредности од случајната променлива која го опишува овој процес. Која е оценката за очекуваниот број на пациенти? Која е теориската веројатност дека во наредниот час ќе дојдат 15 пациенти?

X = rpois(100,10)
log.l <- function( lambda= 7){
  r=dpois(X, lambda);
  -sum(log(r));
}
library(stats4);
mle(log.l)
teor <- (10^15 * exp(-10)) / factorial(15)
teor
funk <- dpois(15,10)
funk


# Задача 2.  Време на преглед за секој пациент е случајна променлива  Y. Во една амбуланта добиен е следниот примерок за Y
# c(1.13,1.83, 13.14, 41.22, 27.19,  7.13,  6.94, 24.22, 31.77,  1.03,  7.80, 26.90,  2.27,  3.21, 19.01,  5.98,  6.57, 9.47,  1.29,  6.99, 28.12,  6.13, 13.91, 21.37, 12.23,  7.29, 13.93, 40.32,  0.17,  7.23,  4.44,  3.59,  0.16, 22.27, 7.32,  4.82,  6.04,  3.80,  3.23,  6.04)

Y = c(1.13,1.83, 13.14, 41.22, 27.19,  7.13,  6.94, 24.22, 31.77,  1.03,  7.80, 26.90,  2.27,  3.21, 19.01,  5.98,  6.57, 9.47,  1.29,  6.99, 28.12,  6.13, 13.91, 21.37, 12.23,  7.29, 13.93, 40.32,  0.17,  7.23,  4.44,  3.59,  0.16, 22.27, 7.32,  4.82,  6.04,  3.80,  3.23,  6.04)

min(Y);max(Y);mean(Y);median(Y);sd(Y);var(Y);range(Y);IQR(Y);quantile(Y);sum(Y);
hist(Y)

log.m <- function(lambda = 7){
  r = dexp(Y,lambda)
  -sum(log(r))
}
library(stats4);
mle(log.m)
#lambda = 0.087 spored MPO

lambda = 0.087
#pravime Kolmogorov smirnov test za r-ba bidekji Y e od ANT
#h0: Y ima eksp r-ba
#ha: Y nema eksp r-ba
ks.test(Y, "pexp", lambda)
#PVAL=0.4854>0.05;h0 se prifakja; Y ima eksp r-ba

#h0: mu = 20
#h1: mu != 20
t.test(Y, alt="two.sided",mu=20,cong.level=0.95)


#Задача 3. (2) Од Grouper  ги имаме следните податоци: од 12 зделки за кои се продадени над 200 ваучери, 5 се со попусти над 50%, а 7 под 50%. Од 24-те зделки за кои биле продадени од 0 до 199 ваучери, 16 биле со попусти под 50%, а останатите со попуст над 50 %. Ако случајната променлива Х е процент на попуст, а У број на продадени ваучери, со ниво на значајност 0.05, тестирај дали постои зависност меѓу нив. 

#X- br na popust
#Y- br na prodadeni vauceri
pod <- matrix(c(7,16,5,8), 2,2, byrow=T)
dimnames(pod) <- list(c("pod 50", "nad 50"), c(">200", "0-199"))
pod

#so hi kvadrat testirame nez na obelezja
#h0: Xi Y se nez
#ha: X i Y se zavisni
chisq.test(pod, correct=FALSE)
#pval = 0.6236>0.05 ;se prifakja h0; nezavisni se


# juni 2017
# 1. (2+3) а) Вп кутија има еднп белп и 10 црни тппчиоа. Еден експеримент се спстпи пд влечеое на
# еднп тппче сп враќаое, се’ дпдека не се извлече белптп тппче. Нека X е случајната прпменлива
# кпја гп дава брпјпт на влечеоа вп еден експеримент.
# а) Да се направат 50 симулации на експериментпт и статистички да се пбрабптат.
# б) Од дпбиенипт примерпк да се најде интервал на дпверба за прпсечнипт брпј на влечеоа вп
# еден експеримент. Да се пдгпвпри дали теприската вреднпст ЕХ се напда вп интервалпт на
# дпверба. Да се тестира хипптезата дека прпсечнипт брпј на влечеоа дп ппјавуваое на белптп
# тппче е 5.

x <- rgeom(50,1/11)
min(x);max(x);mean(x);median(x);sd(x);var(x);range(x);IQR(x);quantile(x);sum(x);
interval = function(t,alpha){
  m = mean(t)
  s = sd(t)
  n = length(t)
  z = qnorm(1-alpha/2)
  c(m-z*s/sqrt(n),m+z*s/sqrt(n))
}
interval(x,0.95)
mean(x)

#h0: mu = 5
#ha: mu!=5
t.test(x,alt="two.sided",mu=5,conf.level=0.95)
# se prifakja ha

#2. (1/2.5) На еден сервер пристигнуваат ппраки на случаен начин, при штп вп ист мпмент не
# пристигнува ппвеќе пд една ппрака. Дадени се времиоата на чекаое ппмеду ппследпвателнп
# пристигнати ппраки.
# x=c(0.053, 0.246, 0.528, 0.233, 0.161, 1.749,0.690, 0.097, 0.839, 0.122, 0.093, 0.525, 0.952, 1.601,1.059,
#   0.395, 1.286, 0.0143,0.009, 0.268, 0.0334, 0.807, 0.102, 0.043, 0.795, 0.280, 0.067, 0.747, 0.449, 0.060,
#   0.395, 0.079, 0.365, 1.379, 0.042, 0.560, 0.205, 1.099, 1.444, 0.832)

X <- c(0.053, 0.246, 0.528, 0.233, 0.161, 1.749,0.690, 0.097, 0.839, 0.122, 0.093, 0.525, 0.952, 1.601,1.059,0.395, 1.286, 0.0143,0.009, 0.268, 0.0334, 0.807, 0.102, 0.043, 0.795, 0.280, 0.067, 0.747, 0.449, 0.060,0.395, 0.079, 0.365, 1.379, 0.042, 0.560, 0.205, 1.099, 1.444, 0.832)
hist(X)
# momenti
l = mean(X)
1/l

# maksimalna podobnost
log.l <- function( lambda= 1){
  r=dexp(X, lambda);
  -sum(log(r));
}
library(stats4);
mle(log.l)

#3. (2.5) Завпдпт за статистика сака да направи испитуваое дали зарабптувачката на младите е
# независна пд степенпт на пбразпвание. Сп испитуваое на случаен примерпк пд 300 млади на
# впзраст пд 30 гпдини ја дпбиле следната табела
# Штп заклучиле вп Завпдпт за статистика?

X = matrix(c(82,74,48,96),2,2,byrow = T)
chisq.test(x,correct = T)
# p-val > alfa, h0 se prifakja


# sept 2020
# 1. На една автобуска станица минуваат автобусите 3,5, 15 и пристигнуваат независно еден по друг со веројатности 0.25, 0.4 и 0.35 соодветно. Се разгледува поминувањето на 1000 автобуси.
# a) Симулирајте една реализација на експериментот
# б) Пресметајте ги дескриптивните статистики и нацртајте хистограм
# в) Пресметајте ја релативната фреквенција на појавување на автобусот 3-ка од симулацијата под а)

#a)
x = sample(c(3,5,15),1000,replace=T,prob=c(0.25,0.4,0.35))
#b)
sum(x);var(x);sd(x);mean(x);min(x);max(x);range(x);IQR(x);quantile(x);hist(x);
#v)
length(x[x==3])/length(x)


# 2.На една постојка поминуваат само автобусите 3-ка и 15-ка. Се разгледува поминувањето на автобусот 3-ка се додека не помине 3-ка точно три пати. Да се оцени непознатиот параметар со МПО. 

Y <- c(5,  7 , 5,  8 , 9,  7,  5,  6 , 4 , 8) #Y~NB(3, p)
log.l <- function(p=0.5){
  k=3;
  r = dnbinom(Y-k , k, p);
  -sum(log(r));
}
library(stats4);
mle(log.l)


# 3. Пресметан е просечниот број на новозаболени од ковид19? Во 15 последователни дена. По отварањето на границите повторно е пресметан просечниот број на новозаболени. Дали можеме да заклучиме дека просечниот борј на новозаболени е поголем по отварањето на границите? Да предпоставиме дека двата примероци имаат исти дисперзии.

before <- c(121, 125, 130, 145, 160, 180, 145, 184, 178, 169, 178 ,179 ,154,
            155, 165) #n=15
after <- c(125, 132, 135, 169, 132, 188, 198, 200, 202,
           145, 154, 178, 141, 122, 131, 128, 118, 145, 185, 158) #n=20
# ista disperzija - mal primerok se koristi t test za dva primeroka
# h0: ist mean, Ha: razlicen
t.test(before,after,alt="greater",var.equal = T)# p>alfa -> h0 se prifakja


# 4. Дадена беше табела со податоци со студенти и насоките на кои шо се запишале на факс.

x = matrix(c(35,55,85,80,135,145,115,120),4,2,byrow=T)
x
chisq.test(x,correct=T)
# p> alfa se prifakja h0 se nezavisi


# januarti 2020 g2


# Задача 1. (2) Време на работа со патници на еден граничен премин е случајна променлива Х (со рата 15 мин). Симулирајте 100 вредности од  Х. Која е оценката за очекуваното време на работа со патници? Која е теориската веројатност дека наредниот патник ќе се задржи на преминот  барем 10 минути?

x = rexp(100,15)
x
ex = 1 /mean(x)
ex

1 - dexp(10,1/15)

# Задача 2. На граничниот премин пристигнуваат автомобили (независно еден од друг). Нека У е сл. променлива која го дава бројот на автомобили во еден час. Даден е следниот примерок од У 
# c(7,  8, 11,  8, 12, 13, 14,  7, 10, 12,  6, 11,  7,  9,  6,  3,  8, 12, 10,  4, 11, 10,  7,  8, 14, 13,  7,  9, 11,  9,  8, 16, 12, 12, 5, 13,  9, 10, 11,  9)
# а) (1) Да се обработат статистички овие податоци (дескриптивни статистики, хистограм).
# б) (1+2) Да се направи претпоставка за распределбата од која доаѓаат податоците. Да се направи оценка за  параметарот со метод на максимална подобност. Да се тестира дали податоците доаѓаат од претпоставената распределба (
# в) (2) Да се тестира хипотезата дека просечниот брон на автомобили е 15, наспроти алтернативната хипотеза дека е поголем  од 15 .

#a)
Y = c(7,  8, 11,  8, 12, 13, 14,  7, 10, 12,  6, 11,  7,  9,  6,  3,  8, 12, 10,  4, 11, 10,  7,  8, 14, 13,  7,  9, 11,  9,  8, 16, 12, 12, 5, 13,  9, 10, 11,  9)
sum(Y);var(Y);sd(Y);mean(Y);min(Y);max(Y);range(Y);IQR(Y);quantile(Y);hist(Y);

#b)
# Y~ pois
log.L = function(lam = 5){
  r = dpois(Y,lam)
  -sum(log(r))
}
library(stats4)
mle(log.L)

chisq.test(Y,dpois(Y,9.54))

#v)
t.test(Y,mu=15,alt="greater")

# Задача 3. (2) Од Grouper  ги имаме следните податоци: од 12 зделки за кои се продадени над 200 ваучери, 5 се со попусти над 50%, а 7 под 50%. Од 24-те зделки за кои биле продадени од 0 до 199 ваучери, 16 биле со попусти под 50%, а останатите со попуст над 50 %. Ако случајната променлива Х е процент на попуст, а У број на продадени ваучери, со ниво на значајност 0.05, тестирај дали постои зависност меѓу нив. 

x = matrix(c(5,7,8,16),2,2,byrow = T)
colnames(x)=c("pod 50","nad 50")
rownames(x)=c("200","<200")
x

chisq.test(x,correct = F)

# tekstot na zadacive go ima vo fb grupata vis

# 2014/2015 
# zadaca 1
#a)
x = rexp(100,1/6)
min(x);max(x);mean(x);sd(x);IQR(x);var(x);quantile(x);sum(x);range(x);hist(x);
#b)
1 - pexp(1/4,1/6)
#v)
Y=c(0.139949895, 0.067040492, 0.870743696, 0.028843486, 0.129958534, 0.002069600,
    0.148733045, 0.435783069, 0.016266783, 0.439330805, 0.248336550, 0.141990803,
    0.115508388, 0.088883181, 0.602650303, 0.203305696, 0.302216169, 0.006328214,
    0.011383270, 0.004067436);
ks.test(rexp(20,1/6),Y)
# se prifakja Ha - ne se ista raspredelba
# nova h0 : poasonova
chisq.test(rpois(20,1/6),Y,correct = F)
# h0 se prifakja
#g)
# momenti
1/mean(x)
# mle
log.L = function(lam = 0.16){
  r = dexp(Y,lam)
  -sum(log(r))
}
library(stats4)
mle(log.L)


# zadaca 2

x = matrix(c(26,18,12,20),2,2,byrow = T)
chisq.test(x,correct = F)
#p>alfa h0 se prifakja tie se nezavisni


# 02.03.2015
# zadaca 1
#a)
x = rpois(40,8)
min(x);max(x);mean(x);sd(x);IQR(x);var(x);quantile(x);sum(x);range(x);hist(x);
#b)
1 - ppois(4,8)
#v)
pred=c(8, 12, 9, 11, 15, 11, 12, 13, 11, 9);
potoa=c(8, 8, 13, 12, 12, 11, 10, 7, 13, 11, 12, 9, 13, 12, 11);

log.L = function(lambda = 10){
  r = dpois(potoa,lambda)
  -sum(log(r))
}
library(stats4)
mle(log.L)

chisq.test(rbind(pred,potoa))
# p>alfa H0 se prifakja

t.test(potoa,mu=10,alt="greater",conf.level = 0.99)
# p>alfa h0 se prifakja ne se zgolemila


# 02.03.2015 gr2

# zadaca 1
#a)
X = rbinom(30,20,0.4)
X;min(X);max(X);mean(X);sd(X);var(X);range(X);quantile(X);sum(X);IQR(X);hist(x);
#b)
qbinom(0.5,20,0.4)
#v)
pred=c(8,12,7,4,8,8,6,8,9,7,6,12,7,10,7);
potoa=c(13,16,14,8,12,14,16,11,13,10,12,12,14,5,15,9,11,13,8,12,9,15);

log.L = function(p = 0.5){
  r = dbinom(potoa,22,p)
  -sum(log(r))
}
library(stats4)
mle(log.L)

chisq.test(rbind(pred,potoa),correct = T)

t.test(potoa,mu=10,alt="greater",conf.level = 0.98)


# 06.2015
# zadaca 1

x = rbinom(40,30,0.5)
pbinom(10,30,0.5)

# zadaca 2
Log.L = function(lam = 5){
  r = dexp(x,lam)
  -sum(log(r))
}
library(stats4)
mle(Log.L)


# 09.2015
# zadaca 1
#a)
x = rgeom(100,0.01)
min(x);max(x);mean(x);sd(x);IQR(x);var(x);quantile(x);sum(x);range(x);hist(x);
#b)
pgeom(5,0.01)

# zadaca 2
X=c(6,5,10,7,7,8,6,5,2,2);
mean(X)
log.L = function(lam=6){
  r = dpois(X,lam)
  -sum(log(r))
}
library(stats4)
mle(log.L)

# zadaca 3
x = matrix(c(42,10,9,39),2,2,byrow = T)
colnames(x) = c("kuce","mace")
rownames(x) = c("maski","zenski")
x
chisq.test(x,correct = F)
#p<alfa -> Ha se prifakja, ne se nezavisni


# 09.2017
# zadaca 1
# a)
x = rbinom(100,10,0.6)
x
# b)
min(x);max(x);mean(x);sd(x);IQR(x);var(x);quantile(x);sum(x);range(x);hist(x);
# v)
dbinom(3,10,0.6)
length(x[x==3])/length(x)

# zadaca 2
x=c(5,3,2,1,2,7,4,1,3,1,2,4,1,2,4);
log.L = function(p = 0.5){
  r = dgeom(x,p)
  -sum(log(r))
}
library(stats4)
mle(log.L)

# zadaca 3
A=c(3.40,3.50,3.57,3.74,3.61,4.27,4.20,5.22);
B=c(4.09,5.50,4.08,3.93,4.74,4.36,3.32,4.86);
t.test(A,B,alt="less", var.equal=TRUE)

# zadaca 4
x = matrix(c(200,240,180,200),2,2,byrow=T)
chisq.test(x,correct = F)


# 09.2017 gr2
# zadaca 1
#a)
x = rbinom(100,15,0.7)
x
# b)
min(x);max(x);mean(x);sd(x);IQR(x);var(x);quantile(x);sum(x);range(x);hist(x);
# v)
length(x[x==5])/length(x)
dbinom(5,15,0.7)

# zadaca 2
X=c(4, 2, 1, 1, 4, 8, 3, 1, 2, 3, 8, 6, 2, 2, 3);

Log.L = function(p = 0.5){
  r = dgeom(X,p)
  -sum(log(r))
}
library(stats4)
mle(Log.L)

# zadaca 3
Model1=c(6.25, 4.61, 4.09, 7.24, 6.06, 4.83, 6.28);
Model2=c(7.64, 4.01, 4.68, 4.62, 6.28, 4.84, 5.60);
t.test(Model1,Model2,alt='less',var.equal=T)
# p>alfa h0 se prifakja, imaat isto vreme do rasipuvanje

#zadaca 4
x = matrix(c(180,150,60,40),nrow=2,ncol=2,byrow = T)
chisq.test(x,correct = F)
# p>alfa h0 se prifajka, se nezavisni


# 01.2018
# zadaca 1
x = rnbinom(100,5,0.7) + 5
min(x);max(x);mean(x);sd(x);IQR(x);var(x);quantile(x);sum(x);range(x);hist(x);
length(x[x==7])/length(x)

#zadaca 2
X=c(6, 5, 10, 7, 7, 8, 6, 5, 2, 2);
log.L=function(lambda=5)
{
  r=dpois(X, lambda);
  -sum(log(r));
}
library(stats4)
mle(log.L)

# zadaca 3
prva=c(9.3, 10.2, 10.9, 18.9, 17.8, 16, 19.3, 13.9, 16.2, 11.4);
vtora=c(19, 12, 13.5, 12, 14, 10.5, 12.8);
t.test(prva,vtora,var.equal=T, alt='less')
#p>alfa h0 se , isti mu

# zadaca 4
frequencies=c(0,1,2,3,4,5,6,7,8,9);
probabilities=c(1010,990,990,960,1020,1000,1000,1030,1020,980)/10000;
chisq.test(frequencies, probabilities);


# 6.2018
# zadaca 1
x = rbinom(100,5,0.5)
min(x);max(x);mean(x);sd(x);IQR(x);var(x);quantile(x);sum(x);range(x);hist(x);
length(x[x==3])/length(x)
dbinom(3,5,0.5)

# zadaca 2
domasni=c(5,3,4,2,2,1,3,4,3,3,1,4,2,4,2,1);
log.L=function(lambda=2)
{
  r=dpois(domasni,lambda);
  -sum(log(r));
}
library(stats4);
mle(log.L);

verojatnosti=c();
for(i in 1:16)
{
  verojatnosti[i]=dpois(i,2.75)
}
verojatnosti
chisq.test(domasni,verojatnosti)


