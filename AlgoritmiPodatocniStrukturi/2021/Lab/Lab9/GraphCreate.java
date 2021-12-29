import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Stack;

//        Креирање на граф Problem 1 (1 / 1)
//        Ваша задача е да креирате неориентиран нетежински граф со матрица на соседство, каде темињата како информација содржат буква. Графот го креирате според наредбите кои се добиваат. Ќе ви биде дадена низа од команди што можат да бидат од следните типови:
//
//        CREATE [број] - треба да креирате нов граф со дадениот број на темиња. Вредностите во темињата ќе бидат буквите од англиската азбука, според нивниот редослед. Така ако имате 3 темиња буквите ќе бидат: A, B и C. ADDEDGE [број1] [број2] - треба да креирате ребро меѓу темињата со реден број број1 и реден број број2. DELETEEDGE [број1] [број2] - треба да го избришете реброто меѓу темињата со реден број број1 и реден број број2. ADЈACENT [број1] [број2] - треба да испечатите 1 доколку темињата со реден број број1 и реден број број2 се соседни, во спротивност 0. PRINTMATRIX - Треба да ја испечатите матрицата на соседство PRINTNODE [број] - Треба да ја испечатите информацијата (т.е. буквата) за дадениот реден број на теме
//
//        Во првата линија на влезот е даден бројот на команди кои ќе следуваат.
//
//        Име на класа: GraphCreate


// uncomment this ->

//class Graph<E> {
//
//    int num_nodes; // broj na jazli
//    E nodes[];    // informacija vo jazlite - moze i ne mora?
//    int adjMat[][];  // matrica na sosednost
//
//    @SuppressWarnings("unchecked")
//    public Graph(int num_nodes) {
//        this.num_nodes = num_nodes;
//        nodes = (E[]) new Object[num_nodes];
//        adjMat = new int[num_nodes][num_nodes];
//
//        for(int i=0;i<this.num_nodes;i++)
//            for(int j=0;j<this.num_nodes;j++)
//                adjMat[i][j]=0;
//    }
//
//
//
//    public Graph(int num_nodes, E[] nodes) {
//        this.num_nodes = num_nodes;
//        this.nodes = nodes;
//        adjMat = new int[num_nodes][num_nodes];
//
//        for(int i=0;i<this.num_nodes;i++)
//            for(int j=0;j<this.num_nodes;j++)
//                adjMat[i][j]=0;
//    }
//
//
//
//    int adjacent(int x,int y)
//    {  // proveruva dali ima vrska od jazelot so indeks x do jazelot so indeks y
//        return (adjMat[x][y]!=0)?1:0;
//    }
//
//    void addEdge(int x,int y)
//    {  // dodava vrska megu jazlite so indeksi x i y
//        adjMat[x][y]=1;
//        adjMat[y][x]=1;
//    }
//
//    void deleteEdge(int x,int y)
//    {
//        // ja brise vrskata megu jazlite so indeksi x i y
//        adjMat[x][y]=0;
//        adjMat[y][x]=0;
//    }
//
//    // Moze i ne mora?
//    E get_node_value(int x)
//    {  // ja vraka informacijata vo jazelot so indeks x
//        return nodes[x];
//    }
//
//    // Moze i ne mora?
//    void set_node_value(int x, E a)
//    {  // ja postavuva informacijata vo jazelot so indeks na a
//        nodes[x]=a;
//    }
//
//    public int getNum_nodes() {
//        return num_nodes;
//    }
//
//    public void setNum_nodes(int num_nodes) {
//        this.num_nodes = num_nodes;
//    }
//
//    void dfsSearch(int node) {
//        boolean visited[] = new boolean[num_nodes];
//        for (int i = 0; i < num_nodes; i++)
//            visited[i] = false;
//        dfsRecursive(node, visited);
//    }
//
//    void dfsRecursive(int node, boolean visited[]) {
//        visited[node] = true;
//        System.out.println(node + ": " + nodes[node]);
//
//        for (int i = 0; i < this.num_nodes; i++) {
//            if(adjacent(node, i)==1){
//                if (!visited[i])
//                    dfsRecursive(i, visited);
//            }
//        }
//    }
//
//    void dfsNonrecursive(int node) {
//        boolean visited[] = new boolean[num_nodes];
//        for (int i = 0; i < num_nodes; i++)
//            visited[i] = false;
//        visited[node] = true;
//        System.out.println(node + ": " + nodes[node]);
//        Stack<Integer> s = new Stack<Integer>();
//        s.push(node);
//
//        int pom;
//
//        while (!s.isEmpty()) {
//            pom = s.peek();
//            int pom1 = pom;
//            for (int i = 0; i < num_nodes; i++) {
//                if(adjacent(pom,i)==1){
//                    pom1 = i;
//                    if(!visited[i])
//                        break;
//                }
//            }
//            if(!visited[pom1]){
//                visited[pom1] = true;
//                System.out.println(pom1 + ": " + nodes[pom1]);
//                s.push(pom1);
//            }
//            else
//                s.pop();
//        }
//    }
//
//
//    @Override
//    public String toString() {
//        String ret="  ";
//        for(int i=0;i<num_nodes;i++)
//            ret+=nodes[i]+" ";
//        ret+="\n";
//        for(int i=0;i<num_nodes;i++){
//            ret+=nodes[i]+" ";
//            for(int j=0;j<num_nodes;j++)
//                ret+=adjMat[i][j]+" ";
//            ret+="\n";
//        }
//        return ret;
//    }
//
//
//}
//
//
//public class GraphCreate {
//    public static void main(String[] args) throws IOException {
//        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        int N = Integer.parseInt(bf.readLine());
//        Graph<Character> graph = null;
//        for(int i=0;i<N;i++){
//            String[] line = bf.readLine().split("\\s+");
//            if(line[0].equals("CREATE")){
//                Character[] letters = new Character[Integer.parseInt(line[1])];
//                for(int j=0;j<letters.length;j++) letters[j] = (char)('A'+j);
//                graph = new Graph<Character>(Integer.parseInt(line[1]),letters);
//            }
//            if(line[0].equals("ADDEDGE")){
//                graph.addEdge(Integer.parseInt(line[1]),Integer.parseInt(line[2]));
//            }
//            if(line[0].equals("DELETEEDGE")){
//                graph.deleteEdge(Integer.parseInt(line[1]),Integer.parseInt(line[2]));
//            }
//            if(line[0].equals("ADJACENT")){
//                System.out.println(graph.adjacent(Integer.parseInt(line[1]),Integer.parseInt(line[2])));
//            }
//            if(line[0].equals("PRINTMATRIX")){
//                for(int j=0;j<graph.adjMat.length;j++){
//                    for(int k=0;k<graph.adjMat.length;k++){
//                        System.out.print(graph.adjMat[j][k]+" ");
//                    }
//                    System.out.println();
//                }
//            }
//            if(line[0].equals("PRINTNODE")){
//                System.out.println(graph.get_node_value(Integer.parseInt(line[1])));
//            }
//        }
//    }
//}
