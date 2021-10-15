Да се дефинира класа `ShapesApplication` чување на податоци  за повеќе прозорци на кои и се сцртуваат геометриски слики во различна форма (квадрати и кругови)..

За класата да се дефинира:

- `ShapesApplication(double maxArea)` - конструктор, каде maxArea е најголемата дозволена плоштина на секоја форма поединечно, која може да биде исцртана на прозорците.
- `void readCanvases (InputStream inputStream)` - метод којшто од влезен поток на податоци ќе прочита информации за повеќе прозорци на кои се исцртуваат различните геометриски слики. Во секој ред се наоѓа информација за еден прозорец во формат: `canvas_id type_1 size_1 type_2 size_2 type_3 size_3 …. type_n size_n`
каде што canvas_id е ИД-то на прозорецот, a после него следуваат информации за секоја форма во прозорецот. Секоја форма е означена со карактер што го означува типот на геометриската слика  (`S` = square, `C` = circle) и со големината на страната на квадратот, односно радиусот на кругот. 
  - При додавањето на геометриските слики на прозорецот треба да се спречи креирање и додавање на прозорец во кој има форма што има плоштина поголема од максимално дозволената. Како механизам за спречување треба да се користи исклучок од тип `IrregularCanvasException` (фрлањето на исклучокот не треба да го попречи вчитувањето на останатите прозорци и геометриски слики. Да се испечати порака `Canvas [canvas_id] has a shape with area larger than [max_area]`.
- `void printCanvases (OutputStream os)`  - метод којшто на излезен поток ќе ги испечати информациите за сите прозорци во апликацијата. Прозорците да се сортирани во опаѓачки редослед според сумата на плоштините на геометриските слики во нив. Секој прозорец да е испечатен во следниот формат: `ID total_shapes total_circles total_squares min_area max_area average_area`.

За вредноста на PI користете ja константата Math.PI. За постигнување на точност со тест примерите користете double за сите децимални променливи.

-- 

Define a class `ShapesApplication` whre you'll keep information about multiple windows on which geometric images (in different shape - square and circle) are drawn. 

For the class you need to define and implement:

- `ShapesApplication(double maxArea)` - constructor with one argument which represents the maximum allowed area of a shape that can be drawn on the windows.
- `void readCanvases (InputStream inputStream)` - method that will read info about multiple windows from input stream. Each line of the data stream represents one window and it's in the format `canvas_id type_1 size_1 type_2 size_2 type_3 size_3 …. type_n size_n` where canvas_id is the ID of the window and after the ID there are unknown number of pairs of data for the shapes. Each pair has its type (character `S` = square, `C` = circle) and the side of the side of the square or the size of the radius of the circle. 
 - When adding the geometric images on the window, the creation and addition of a window which contains a shape with area greater than the maximum area, should not be allowed. This should be done via exception of type `InvalidCanvasException`. Throwing an exception of this type should not stop the reading of the data from the input stream. When catching the exception, the following message should be printed: `Canvas [canvas_id] has a shape with area larger than [max_area]`.
- `void printCanvases(OutputStream os)` - method that will print to output stream the information for all the windows in the application. The windows should be sorted in descending order by the sum of the areas of the geometric shapes in them. Each window should be printed in the following format:  `ID total_shapes total_circles total_squares min_area max_area average_area`.

For the value of PI use Math.PI. Use double for better precision of the decimal numbers.
