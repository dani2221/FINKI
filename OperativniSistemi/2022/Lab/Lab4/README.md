# Лаб. вежба 4 / Lab. 4
- Opened: Friday, 1 April 2022, 4:00 PM
- Due: Sunday, 17 April 2022, 11:59 PM

## Задача 1: TCP client (75%)

Да се имплементира чет клиент кој ќе се поврзе со централен сервер. Серверот го користи TCP протоколот и слуша за дојдовни конекции на порта 9753 на следната адреса: 194.149.135.49

Вашата прва порака која ќе треба да ја испратите да се логирате на серверот треба да биде: login:123456 доколку 123456 е вашиот индекс. За успешно логирање, ќе добиете повратна порака од серверот во остварената конекција преку socket. Доколку ова е неуспешно, потребно е да ја терминирате конекцијата и да пробате од ново да се конектирате.


По добивањето на потврда дека сте успешно поврзани, следна порака која е испратена до серверот треба да биде hello:123456 доколку 123456 е вашиот индекс. По добивање на потврда после оваа порака може да комуницирате со ваши колеги кои во тој момент се истотака логирани на серверот. За да испратите порака на друг колега, (пример, со индекс 111111) таа треба да биде во формат: 111111: nekoja poraka, . Услов за успешно праќање на пораката е колегата во тоа време да е логиран на чет серверот. За тестирање можете да си испраќате и ехо пораки на самите себе (каде примачот ќе биде вашиот индекс)


Помош: за непречена комуникација користете посебна нитка за праќање, а посебна за примање на пораки од серверот.

Напомена: не заборавајте секоја испратена порака да ја терминирате со карактер за нова линија "\n" !

## Задача 2: UDP клиент. (25%)

Да се имплементира UDP клиент кој праќа текстуална порака на серверот кој слуша на порта 9753 на следната адреса: 194.149.135.49. Пораката која ќе ја испратите треба да содржи само стринг од 6 карактери- вашиот број на индекс. За успешно остварена конекција серверот ќе ви врати повратна информација од истиот UDP socket.

-------------------------------------------------

Напомена:
Овде треба да се прикачат решенијата на задачите. Се прикачува само јава код. Во имињата на фајловите треба да стои и вашиот број на индекс.

Дополнително, ќе биде оценета и успешноста на поврзувањето со серверот.

ПЛАГИЈАТИТЕ НАЈСТРОГО ЌЕ БИДАТ КАЗНЕТИ!

---------------------------------------------------------------------------------------------------------------------------------------------

## Task 1: TCP client (75%)

Implement a chat client that is able to connect to a central server. The server uses the TCP protocol and listens for inbound connections on port 9753 on the following address: 194.149.135.49

Your first login message to the server should be: "login:123456" if 123456 is your index number. On a successful login, you will receive a confirmation from the server trough the established socket. If your login attempt is unsuccessful, you should terminate the connection and try again from start.


After receiving a confirmation of a successful login, the next message to the server should be: "hello:123456" if 123456 is your index number. After receiving a confirmation on this you can communicate with your colleagues that are active at the same time on the server. The messages sent (for example, if your colleague has an id of 111111) should be in the format "111111: some message" . The message will be delivered only if the recipient is active on the server at that time.


Hint: use separate threads for inbound and outbound communication with the server.

Note: do not forget to terminate each message with a new line character "\n" !

## Task 2: UDP client. (25%)

Implement a UDP client that sends a single message to a server that listens on port 9753 on the following address: 194.149.135.49. The single message sent should contain one single 6-character string- your index number. If your message was successfully sent, the server will echo back a confirmation on the same UDP socket.

------------------------------------------------------------------
Note:
Place for uploading the solutions. Attach Java source code only, where your ID number is contained in the filenames.

The success of the communication with the server will be graded as well. 

PLAGIARISM WILL NOT BE TOLERATED!