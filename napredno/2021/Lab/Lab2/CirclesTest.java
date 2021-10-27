import java.util.*;

//        Да се дефинира интерфејс Movable што ќе ги дефинира основните својства на еден движечки објект:
//
//        движење нагоре (void moveUp())
//        движење надолу (void moveLeft())
//        движење надесно (void moveRight())
//        движење налево (void moveLeft())
//        пристап до моменталните x,y координати на објектот (int getCurrentXPosition() и int getCurrentYPosition()).
//        Постојат два типа на движечки објекти: движечка точка (MovingPoint) и движечки круг (MovingCircle). Да се дефинираат овие две класи коишто го имплементираат интерфејсот Movable.
//
//        Во класата MovingPoint се чуваат информации за:
//
//        x и y координати (цели броеви)
//        xSpeed и ySpeed : степенот на поместување на движечката точка во x насока и y насока (цели броеви)
//        За класата да се имплементираат:
//
//        конструктор со аргументи: MovablePoint(int x, int y, int xSpeed, int ySpeed),
//        методите наведени во интерфејсот Movable
//        toString метод кој дава репрезентација на објектите во следнот формат Movable point with coordinates (5,35)
//        Во класата MovingCircle се чуваат информации за:
//
//        радиусот на движечкиот круг (цел број)
//        центарот на движечкиот круг (објект од класата MovingPoint).
//        За класата да се имплементираат:
//
//        конструктор со аргументи: MovableCircle(int radius, MovablePoint center)
//        методите наведени во интерфејсот Movable
//        toString метод којшто дава репрезентација на објектите во следниот формат Movable circle with center coordinates (48,21) and radius 3
//        Првите четири методи од Movable (moveUp, modeDown, moveRight, moveLeft) треба да фрлат исклучок од тип ObjectCanNotBeMovedException доколку придвижувањето во соодветната насока не е возможно, односно со придвижувањето се излегува од дефинираниот простор во класата MovablesCollection. Справете се со овие исклучоци на соодветните места. _Погледнете во тест примерите какви пораки треба да се печатат кога ќе се фати исклучок од овој тип и имплементирајте го истото._
//
//        Да се дефинира класа MovablesCollection во која што ќе се чуваат информации за:
//
//        низа од движечки објекти (Movable [] movable)
//        статичка променлива за максималната вредност на координатата X (минималната е предодредена на 0)
//        статичка променлива за максималната вредност на координатата Y (минималната е предодредена на 0)
//        За класата да се имплементираат следните методи:
//
//        конструктор MovablesCollection(int x_MAX, int y_MAX)
//        void addMovableObject(Movable m) - метод за додавање на движечки објект во колекцијата од сите движечки објекти. Пред да се додади објектот, мора да се провери дали истиот е може да се вклопи во дефинираниот простор, односно истиот да не излегува од границите 0-X_MAX за x координатата и 0-Y_MAX за y координатата. Доколку станува збор за движечки круг, потребно е целиот круг да се наоѓа во наведениот интервал на вредности. Доколку движечкиот објект не може да биде вклопен во просторот, да се фрли исклучок од тип MovableObjectNotFittableException. Потребно е да се справите со исклучокот на соодветното место во main методот. _Погледнете во тест примерите какви пораки треба да се печатат кога ќе се фати исклучок од овој тип и имплементирајте го истото._
//        void moveObjectsFromTypeWithDirection (TYPE type, DIRECTION direction) - метод за придвижување на движечките објекти од тип type во насока direction. TYPE и DIRECTION се енумерации кои се задедени во почетниот код. Во зависност од насоката зададена во аргументот, да се повика соодветниот метод за придвижување.
//        toString() -


enum TYPE {
    POINT,
    CIRCLE
}

enum DIRECTION {
    UP,
    DOWN,
    LEFT,
    RIGHT
}

public class CirclesTest {

    public static void main(String[] args) {

        System.out.println("===COLLECTION CONSTRUCTOR AND ADD METHOD TEST===");
        MovablesCollection collection = new MovablesCollection(100, 100);
        Scanner sc = new Scanner(System.in);
        int samples = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < samples; i++) {
            String inputLine = sc.nextLine();
            String[] parts = inputLine.split(" ");

            int x = Integer.parseInt(parts[1]);
            int y = Integer.parseInt(parts[2]);
            int xSpeed = Integer.parseInt(parts[3]);
            int ySpeed = Integer.parseInt(parts[4]);

            try {
                if (Integer.parseInt(parts[0]) == 0) { //point

                    collection.addMovableObject(new MovablePoint(x, y, xSpeed, ySpeed));
                } else { //circle
                    int radius = Integer.parseInt(parts[5]);
                    collection.addMovableObject(new MovableCircle(radius, new MovablePoint(x, y, xSpeed, ySpeed)));
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
           
        }
        System.out.println(collection.toString());

        System.out.println("MOVE POINTS TO THE LEFT");
        try{
            collection.moveObjectsFromTypeWithDirection(TYPE.POINT, DIRECTION.LEFT);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println(collection.toString());

        System.out.println("MOVE CIRCLES DOWN");
        try{
            collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.DOWN);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println(collection.toString());

        System.out.println("CHANGE X_MAX AND Y_MAX");
        MovablesCollection.setxMax(90);
        MovablesCollection.setyMax(90);

        System.out.println("MOVE POINTS TO THE RIGHT");
        try{
            collection.moveObjectsFromTypeWithDirection(TYPE.POINT, DIRECTION.RIGHT);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println(collection.toString());

        System.out.println("MOVE CIRCLES UP");
        try{
            collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.UP);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println(collection.toString());


    }
}

interface Movable{
    void moveUp() throws ObjectCanNotBeMovedException;
    void moveDown() throws ObjectCanNotBeMovedException;
    void moveLeft() throws ObjectCanNotBeMovedException;
    void moveRight() throws ObjectCanNotBeMovedException;
    int getCurrentXPosition();
    int getCurrentYPosition();
}

class MovablePoint implements Movable {

    private int x;
    private int y;
    private int xSpeed;
    private int ySpeed;

    public MovablePoint(int x, int y, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    @Override
    public String toString() {
        return "Movable point with coordinates ("+x+","+y+")";
    }

    @Override
    public void moveUp() throws ObjectCanNotBeMovedException {
        canMove(0,ySpeed);
        y+=ySpeed;
    }

    @Override
    public void moveDown() throws ObjectCanNotBeMovedException {
        canMove(0,-ySpeed);
        y-=ySpeed;
    }

    @Override
    public void moveLeft() throws ObjectCanNotBeMovedException {
        canMove(-xSpeed,0);
        x-=xSpeed;
    }

    @Override
    public void moveRight() throws ObjectCanNotBeMovedException {
        canMove(xSpeed,0);
        x+=xSpeed;
    }

    @Override
    public int getCurrentXPosition() {
        return x;
    }

    @Override
    public int getCurrentYPosition() {
        return y;
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public int getySpeed() {
        return ySpeed;
    }

    public boolean canMove(int deltaX, int deltaY) throws ObjectCanNotBeMovedException {
        if(x+deltaX> MovablesCollection.x_MAX || x+deltaX<0
                || y+deltaY> MovablesCollection.y_MAX || y+deltaY<0){
            throw new ObjectCanNotBeMovedException(String.format("Point (%d,%d) is out of bounds",x+deltaX,y+deltaY));
        }
        return true;
    }
}

class MovableCircle implements Movable{
    private int radius;
    private MovablePoint center;

    public MovableCircle(int radius, MovablePoint center) {
        this.radius = radius;
        this.center = center;
    }

    @Override
    public void moveUp() throws ObjectCanNotBeMovedException {
        center.moveUp();
    }

    @Override
    public void moveDown() throws ObjectCanNotBeMovedException {
        center.moveDown();
    }

    @Override
    public void moveLeft() throws ObjectCanNotBeMovedException {
        center.moveLeft();
    }

    @Override
    public void moveRight() throws ObjectCanNotBeMovedException {
        center.moveRight();
    }

    @Override
    public int getCurrentXPosition() {
        return center.getCurrentXPosition();
    }

    @Override
    public int getCurrentYPosition() {
        return center.getCurrentYPosition();
    }

    public int getRadius() {
        return radius;
    }

    public MovablePoint getCenter() {
        return center;
    }

    @Override
    public String toString() {
        return "Movable circle with center coordinates ("+center.getCurrentXPosition()+","+center.getCurrentYPosition()+") and radius "+radius;
    }
}

class ObjectCanNotBeMovedException extends Exception{
    public ObjectCanNotBeMovedException(String message) {
        super(message);
    }
}
class MovableObjectNotFittableException extends Exception{
    public MovableObjectNotFittableException(String message) {
        super(message);
    }
}

class MovablesCollection{
    private List<Movable> movables;
    public static int x_MAX;
    public static int y_MAX;

    public MovablesCollection(Movable[] movables, int x_MAX, int y_MAX) {
        this.movables = Arrays.asList(movables);
        this.x_MAX = x_MAX;
        this.y_MAX = y_MAX;
    }

    public MovablesCollection(Movable[] movables, int x_MAX) {
        this.movables = Arrays.asList(movables);
        this.x_MAX = x_MAX;
        y_MAX = 100;
    }

    public MovablesCollection(Movable[] movables) {
        this.movables = Arrays.asList(movables);
        y_MAX = 100;
        x_MAX = 100;
    }

    public MovablesCollection( int x_MAX, int y_MAX) {
        this.movables = new ArrayList<Movable>();
        this.y_MAX = x_MAX;
        this.x_MAX = y_MAX;
    }

    public static void setxMax(int x_MAX) {
        MovablesCollection.x_MAX = x_MAX;
    }

    public static void setyMax(int y_MAX) {
        MovablesCollection.y_MAX = y_MAX;
    }

    void addMovableObject(Movable m) throws MovableObjectNotFittableException {
        canExistMovable(m);
        movables.add(m);
    }
    boolean canExistMovable(Movable m) throws MovableObjectNotFittableException {
        if(m.getCurrentXPosition()>x_MAX || m.getCurrentXPosition()<0
                || m.getCurrentYPosition()>y_MAX || m.getCurrentYPosition()<0){
            if( m instanceof MovableCircle){
                throw new MovableObjectNotFittableException(String.format("Movable circle with center (%d,%d) and radius %d can not be fitted into the collection",m.getCurrentXPosition(),m.getCurrentYPosition(),((MovableCircle) m).getRadius()));
            }
            else throw new MovableObjectNotFittableException(String.format("Movable point with center (%d,%d) can not be fitted into the collection",m.getCurrentXPosition(),m.getCurrentYPosition()));
        }

        if(m instanceof MovableCircle){
            MovableCircle mc = (MovableCircle) m;
            if(mc.getCurrentXPosition()+mc.getRadius()>x_MAX || mc.getCurrentXPosition()-mc.getRadius()<0
                    || mc.getCurrentYPosition()+mc.getRadius()>y_MAX || mc.getCurrentYPosition()-mc.getRadius()<0){
                throw new MovableObjectNotFittableException(String.format("Movable circle with center (%d,%d) and radius %d can not be fitted into the collection",m.getCurrentXPosition(),m.getCurrentYPosition(),((MovableCircle) m).getRadius()));
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String str = "Collection of movable objects with size "+movables.size()+":\n";
        for(Movable mv : movables){
            str+=mv.toString()+'\n';
        }
        return str;
    }

    void moveObjectsFromTypeWithDirection (TYPE type, DIRECTION direction) throws ObjectCanNotBeMovedException {
        Movable[] arrayTest = movables.toArray(new Movable[movables.size()]);
        ObjectCanNotBeMovedException ex = null;
        for(Movable mv : arrayTest){
            try {
                if (mv instanceof MovablePoint && type == TYPE.POINT) {
                    if (direction == DIRECTION.DOWN) mv.moveDown();
                    if (direction == DIRECTION.UP) mv.moveUp();
                    if (direction == DIRECTION.LEFT) mv.moveLeft();
                    if (direction == DIRECTION.RIGHT) mv.moveRight();
                } else if (mv instanceof MovableCircle && type == TYPE.CIRCLE) {
                    if (direction == DIRECTION.DOWN) mv.moveDown();
                    if (direction == DIRECTION.UP) mv.moveUp();
                    if (direction == DIRECTION.LEFT) mv.moveLeft();
                    if (direction == DIRECTION.RIGHT) mv.moveRight();
                }
            }catch (ObjectCanNotBeMovedException e){
                ex = e;
            }
        }
        if(ex!=null) throw ex;
    }
}

