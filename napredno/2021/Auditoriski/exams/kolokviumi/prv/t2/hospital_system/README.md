Еден медицински систем дава податоци за пациентите, во рамки на болницата каде истиот се користи. За секој пациент се чуваат информации за `скриен код (long)`, `име (String)`, `години (int)`, `пол (enum)` и `име на матичен лекар (String)`.

За потребите на системот треба да се дефинира генерички интерфејс `PatientService` кој ќе има еден метод `ArrayList patientsInformation (ArrayList patients, String filter)`. Методот како влезни аргументи ја добива листата на пациенти за која треба да даде информации, како и `filter` кој се користи при извлекување на информациите. Интерфејсот `PatientService` ви е даден и за истиот треба да ги пополните само генеричките параметри.

Дадена ви е класа `HospitalSystem` во која се чува листа од сите пациенти за кои болницата води евиденција. За класата да се дефинираат соодветните генерички параметри, да се имплементира конструктор `HospitalSystem(ArrayList patients)`, како и да се комплетира методот `void printResults()`.

Во овој метод потребно е да креирате три конкретни сервиси кои даваат информации за пациентите (со помош на ламбда изрази):

 1. Сервис кој ќе ги врати сите пациенти кои имаат матичен лекар со име `filter`. 
 2. Сервис кој ќе ги врати сите пациенти кои не се постари од 60 години и се од полот `filter`. 
 3. Сервис кој ќе ги врати сите пациенти со име `filter` сортирани во растечки редослед според нивниот скриен код. 


----------

One medical system gives information about the patients within a hospital, where it is implemented. For each patient the following data is stored: `hidden code (long)`, `name (String)`, `age (int)`, `gender (enum)` and `name of doctor (String)`.

For the needs of this system you need to define a generic interface `PatientService` which has one method `ArrayList patientsInformation (ArrayList patients, String filter)`. The method accepts two arguments: the list of patients for which information is needed and the `filter` which will be used in the process of information extraction. The interface `PatientService` is already given in the starter code. You need to complete it, by adding its generic parameters. 

Additionally, the class `HospitalSystem` is given in starter code. This class stores a list of all the patients that belong to a hospital. You need to define the appropriate generic parameters for this class, implement the constructor `HospitalSystem(ArrayList patients)` and complete the method `void printResults()`.

In this method you need to create three service implementations that give information about the patients (using lambda expressions):

 1. Service that returns all the patients that have a doctor who is named `filter`.
 2. Service that returns all the patients that are not older than 60 and are of gender `filter`.
 3. Service that returns all the patients named `filter`, sorted in ascending order considering their hidden code.

 
