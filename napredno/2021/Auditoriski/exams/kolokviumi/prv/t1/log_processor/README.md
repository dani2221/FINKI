Потребно е да се развие систем за процесирање на логови. За секој лог треба да се чува пораката од логот (String), типот на логот (String) и временски печат (long). За таа цел комплетирајте го интерфејсот `ILog`.

За да се процесираат логовите ќе се користи генеричкиот интерфејс `LogProcessor`. Овој интерфејс има само еден метод со потпис: `ArrayList processLogs (ArrayList logs)`. Методот добива влезен аргумент логови коишто треба да ги процесира, а враќа резултат процесирани логови. Интерфејсот ви е даден и за истиот треба да ги пополните само генеричките параметри.

Дадена ви е класата `LogSystem` во којашто се чува листа на логови. За класата да се дефинираат соодветните генерички параметри, да се имплементира конструктор `LogSystem(ArrayList elements)`, како и да се комплетира методот `printResults()`. 

Во овој метод потребно е да креирате три конкретни процесори на логови (со помош на ламбда изрази):

1. Процесор којшто ќе ги врати само логовите коишто се од тип INFO
2. Процесор којшто ќе ги врати само логовите чиишто пораки се пократки од 100 карактери
3. Процесор којшто ќе ги врати логовите сортирани според временскиот печат во растечки редослед (од најстар кон најнов лог).

-- 

You are asked to develop a system for log processing. For each log you need to keep the message from the log (String), the type of the log (String) and a timestamp (long). For that purpose complete the missing parts of the interface `ILog`. 

The generic interface `LogProcessor` should be used for log processing. This interface has only one method with signature `ArrayList processLogs (ArrayList logs)` The methods has one arguments - the logs that should to be processed and the results is a list of processed logs. You can find this interface in the starter code and you only need to define the generic parameters for it. 

The class `LogSystem` is also given in the start code. You need to keep a list of logs in this class. For the class you need to define the generic parameters, to implement the constructor `LogSystem(ArrayList elements)` and to complete the method `printResults()`.

In this method you need to create three concrete log processor (with lambda expressions): 

1. Log processor that will return only the logs which are from type INFO
2. Log processor that will return only the logs whose messages are with length smaller that 100 characters.
3. Log processor that will return the logs sorted by their timestamp in ascending order.


