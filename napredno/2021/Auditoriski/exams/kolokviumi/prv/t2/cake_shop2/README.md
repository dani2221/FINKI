Да се дефинира класа `CakeShopApplication` во која ќе се чуваат информации за сите тековни нарачки на торти и пити. Дополнително, во класата се чува и информација за најмалиот број на производи кои може да се нарачаат во рамки на една нарачка.

За класата да се дефинира: 

 - `CakeShopApplication(int minOrderItems)` - конструктор
 - `void readCakeOrders(InputStream inputStream)` - метод кој од влезен поток на податоци ќе прочита информации за повеќе нарачки. Во секој еден ред се наоѓа информација за една нарачка во формат: `orderId item1Name item1Price item2Name item2Price .... itemNName itemNPrice`, каде што `orderId` е ID на нарачката, а по неа следуваат паровите нарачан производ и цена. Дополнително, првата буква од името на производот го дава неговиот вид `(C = cake, P = pie)`.
     - Потребно е да се спречи додавање на нарачка која има помалку производи од минимално дозволените. Истото треба да се направи со фрлање на исклучок од тип `InvalidOrderException`. Справете се со исклучокот така што нема да се попречи вчитувањето на останатите нарачки, а ќе се испечати порака во формат: `The order with id [orderId] has less items than the minimum allowed.`
 - `void printAllOrders(OutputStream outputStream)` - метод кој на излезен поток ќе ги испечати информациите за сите нарачки. Нарачките треба да бидат сортирани во опаѓачки редослед според сумата на сите нарачани продукти во рамки на нарачката. Притоа, потребно е да се внимава, бидејќи има покачување на цената на сите пити, за 50 денари. Форматот за печатење на нарачка е: `orderId totalOrderItems totalPies totalCakes totalAmount`.

----------

Define a class `CakeShopApplication` which stores information for all current orders of cakes and pies. Additionally, the class stores information about the smallest number of products that can be ordered within one order.

For the class you need to define and implement:

 - `CakeShopApplication(int minOrderItems)` - constructor
 - `void readCakeOrders(InputStream inputStream)` - method which reads the data for multiple orders from input stream. Each line has data for an order in the following format: orderId item1Name item1Price item2Name item2Price .... itemNName itemNPrice, where `orderId` is the ID of the order, after which pairs of ordered products and price follow. Additionally, the first letter of the name of the product gives its type `(C = cake, P = pie)`. 
     - The addition of an order that has less products than the number allowed is not permitted. This should be implemented with an exception `InvalidOrderException`. The exception should be handled in a way that does not disturb the reading of other orders. Additionally, a message should be printed in the following format `The order with id [orderId] has less items than the minimum allowed.`
 - `void printAllOrders(OutputStream outputStream)` - method that prints the data for all orders on an output stream. The orders should be sorted in an descending order considering the sum of all products within the order. Take special care about the price of the pies, because there was an increase of their price by 50den. All orders should be printed in the following format: `orderId totalOrderItems totalPies totalCakes totalAmount`.

