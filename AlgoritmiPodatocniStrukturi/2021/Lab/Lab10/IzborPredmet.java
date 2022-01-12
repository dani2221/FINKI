import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

//        Избор на предмет на факултет Problem 1 (0 / 0)
//        На еден факултет се поставени предмети кои задолжително треба да се слушаат и изборни предмети. За секој предмет е даден предуслов: кои предмети треба да се положени за да може да се слуша избраниот предмет. Ваша задача е да за даден последен положен предмет, да најдете кој е следен предмет кој може да го слуша студентот.
//        Влез: Во првиот ред е даден број n - број на предмети Во следните n редови се дадени шифрите на предметите. Во следнииот ред е даден број m - број на зависности Во следните m реда се дадени шифри на предмети, разделени со празно место. Првата шифра го означува предметот за кој се дефинира зависност, а следните шифри се предметите кои треба да се положени за да се слуша предметот даден со првата шифра. Во последниот ред е дадена шифрата на последниот слушан предмет.
//        Излез: Се печати шифрата на следниот предмет кој треба да се слуша.

// uncomment this ->


//class GraphNode<E> {
//    private int index;//index (reden broj) na temeto vo grafot
//    private E info;
//    private LinkedList<GraphNode<E>> neighbors;
//
//    public GraphNode(int index, E info) {
//        this.index = index;
//        this.info = info;
//        neighbors = new LinkedList<GraphNode<E>>();
//    }
//
//    boolean containsNeighbor(GraphNode<E> o){
//        return neighbors.contains(o);
//    }
//
//    void addNeighbor(GraphNode<E> o){
//        neighbors.add(o);
//    }
//
//
//    void removeNeighbor(GraphNode<E> o){
//        if(neighbors.contains(o))
//            neighbors.remove(o);
//    }
//
//
//    @Override
//    public String toString() {
//        String ret= "INFO:"+info+" SOSEDI:";
//        for(int i=0;i<neighbors.size();i++)
//            ret+=neighbors.get(i).info+" ";
//        return ret;
//
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        @SuppressWarnings("unchecked")
//        GraphNode<E> pom = (GraphNode<E>)obj;
//        return (pom.info.equals(this.info));
//    }
//
//    public int getIndex() {
//        return index;
//    }
//
//    public void setIndex(int index) {
//        this.index = index;
//    }
//
//    public E getInfo() {
//        return info;
//    }
//
//    public void setInfo(E info) {
//        this.info = info;
//    }
//
//    public LinkedList<GraphNode<E>> getNeighbors() {
//        return neighbors;
//    }
//
//    public void setNeighbors(LinkedList<GraphNode<E>> neighbors) {
//        this.neighbors = neighbors;
//    }
//
//
//
//}
//
//class Graph<E> {
//
//    int num_nodes;
//    GraphNode<E> adjList[];
//
//    @SuppressWarnings("unchecked")
//    public Graph(int num_nodes, E[] list) {
//        this.num_nodes = num_nodes;
//        adjList = (GraphNode<E>[]) new GraphNode[num_nodes];
//        for (int i = 0; i < num_nodes; i++)
//            adjList[i] = new GraphNode<E>(i, list[i]);
//    }
//
//    @SuppressWarnings("unchecked")
//    public Graph(int num_nodes) {
//        this.num_nodes = num_nodes;
//        adjList = (GraphNode<E>[]) new GraphNode[num_nodes];
//        for (int i = 0; i < num_nodes; i++)
//            adjList[i] = new GraphNode<E>(i, null);
//    }
//
//    int adjacent(int x, int y) {
//        // proveruva dali ima vrska od jazelot so
//        // indeks x do jazelot so indeks y
//        return (adjList[x].containsNeighbor(adjList[y])) ? 1 : 0;
//    }
//
//    void addEdge(int x, int y) {
//        // dodava vrska od jazelot so indeks x do jazelot so indeks y
//        if (!adjList[x].containsNeighbor(adjList[y])) {
//            adjList[x].addNeighbor(adjList[y]);
//        }
//    }
//
//    void deleteEdge(int x, int y) {
//        adjList[x].removeNeighbor(adjList[y]);
//    }
//
//    /************************TOPOLOGICAL SORT*******************************************************************/
//
//    void dfsVisit(Stack<Integer> s, int i, boolean[] visited){
//        if(!visited[i]){
//            visited[i] = true;
//            Iterator<GraphNode<E>> it = adjList[i].getNeighbors().iterator();
//
//            while(it.hasNext()){
//                dfsVisit(s, it.next().getIndex(), visited);
//            }
//            s.push(i);
//        }
//    }
//
//    E topological_sort_dfs(int check){
//        boolean visited[] = new boolean[num_nodes];
//        for(int i=0;i<num_nodes;i++){
//            visited[i] = false;
//        }
//
//        Stack<Integer> s = new Stack<Integer>();
//
//        for(int i=0;i<num_nodes;i++){
//            dfsVisit(s,i,visited);
//        }
//        while(!s.isEmpty()){
//            if(s.pop().equals(check)){
//                return adjList[s.pop()].getInfo();
//            }
//        }
//        return null;
//    }
//
//    /***********************************************************************************************************/
//
//    @Override
//    public String toString() {
//        String ret = new String();
//        for (int i = 0; i < this.num_nodes; i++)
//            ret += i + ": " + adjList[i] + "\n";
//        return ret;
//    }
//
//}
//
//
//public class IzborPredmet {
//    private static int indexOf(String[] arr, String el){
//        for(int i=0;i<arr.length;i++){
//            if(arr[i].equals(el)) return i;
//        }
//        return -1;
//    }
//    public static void main(String[] args) throws IOException {
//        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        int N = Integer.parseInt(bf.readLine());
//        String[] courses = new String[N];
//        for(int i=0;i<N;i++){
//            courses[i] = bf.readLine();
//        }
//        Graph<String> graph = new Graph<String>(N,courses);
//        int M = Integer.parseInt(bf.readLine());
//        for(int i=0;i<M;i++){
//            String[] els = bf.readLine().split("\\s+");
//            for(int j=1;j<els.length;j++){
//                graph.addEdge(indexOf(courses,els[j]),indexOf(courses,els[0]));
//            }
//        }
//        System.out.println(graph.topological_sort_dfs(indexOf(courses,bf.readLine())));
//    }
//}
