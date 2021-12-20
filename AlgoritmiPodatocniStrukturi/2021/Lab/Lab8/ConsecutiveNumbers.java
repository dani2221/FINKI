import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;


//        Последователни броеви Problem 1
//        Со помош на нанижано бинарно дрво потребно е да проверите дали при inorder изминување на дрвото важи дека секој следен елемент има вредност за 1 поголема од претходниот (треба да се испечати true или false). Притоа доколку ви е потребно можете да користите дополнителни функции, но не смеете да користите рекурзија и дополнителни структури.
//
//        Име на класата во Java: ConsecutiveNumbers

// uncomment this ->

//public class ConsecutiveNumbers {
//    public static void main(String[] args) throws IOException {
//        String line, action;
//        int i,index;
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//
//        int N = Integer.parseInt(br.readLine());
//        BNode<Integer> nodes[] = new BNode[N];
//        BTree<Integer> tree = new BTree<Integer>();
//
//        for (i = 0; i < N; i++)
//            nodes[i] = new BNode<Integer>();
//
//        for (i = 0; i < N; i++) {
//            line = br.readLine();
//            st = new StringTokenizer(line);
//            index = Integer.parseInt(st.nextToken());
//            nodes[index].info = Integer.parseInt(st.nextToken());
//            action = st.nextToken();
//            if (action.equals("LEFT")) {
//                tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.LEFT, nodes[index]);
//            } else if (action.equals("RIGHT")) {
//                tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.RIGHT, nodes[index]);
//            } else {
//                tree.makeRootNode(nodes[index]);
//            }
//        }
//
//        BNode<Integer> curr = tree.root;
//        int value = Integer.MAX_VALUE;
//        Stack<BNode<Integer>> s = new ArrayStack<BNode<Integer>>(N);
//        while (curr != null || !s.isEmpty()) {
//            while (curr !=  null) {
//                s.push(curr);
//                curr = curr.left;
//            }
//            curr = s.pop();
//            if(value==Integer.MAX_VALUE) value = curr.info;
//            else {
//                if(curr.info==value+1) value++;
//                else {
//                    System.out.println("false");
//                    return;
//                }
//            }
//            curr = curr.right;
//        }
//        System.out.println("true");
//
//    }
//}
//
//interface Stack<E> {
//
//    // Elementi na stekot se objekti od proizvolen tip.
//
//    // Metodi za pristap:
//
//    public boolean isEmpty ();
//    	// Vrakja true ako i samo ako stekot e prazen.
//
//    public E peek ();
//    	// Go vrakja elementot na vrvot od stekot.
//
//    // Metodi za transformacija:
//
//    public void clear ();
//    	// Go prazni stekot.
//
//    public void push (E x);
//    	// Go dodava x na vrvot na stekot.
//
//    public E pop ();
//    	// Go otstranuva i vrakja elementot shto e na vrvot na stekot.
//}
//
//class ArrayStack<E> implements Stack<E> {
//    private E[] elems;
//    private int depth;
//
//    @SuppressWarnings("unchecked")
//    public ArrayStack (int maxDepth) {
//        // Konstrukcija na nov, prazen stek.
//        elems = (E[]) new Object[maxDepth];
//        depth = 0;
//    }
//
//
//    public boolean isEmpty () {
//        // Vrakja true ako i samo ako stekot e prazen.
//        return (depth == 0);
//    }
//
//
//    public E peek () {
//        // Go vrakja elementot na vrvot od stekot.
//        if (depth == 0)
//            throw new NoSuchElementException();
//        return elems[depth-1];
//    }
//
//
//    public void clear () {
//        // Go prazni stekot.
//        for (int i = 0; i < depth; i++)  elems[i] = null;
//        depth = 0;
//    }
//
//
//    public void push (E x) {
//        // Go dodava x na vrvot na stekot.
//        elems[depth++] = x;
//    }
//
//
//    public E pop () {
//        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
//        if (depth == 0)
//            throw new NoSuchElementException();
//        E topmost = elems[--depth];
//        elems[depth] = null;
//        return topmost;
//    }
//}
//
//
//
//class BNode<E> {
//
//    public E info;
//    public BNode<E> left;
//    public BNode<E> right;
//
//    static int LEFT = 1;
//    static int RIGHT = 2;
//
//    public BNode(E info) {
//        this.info = info;
//        left = null;
//        right = null;
//    }
//
//    public BNode() {
//        this.info = null;
//        left = null;
//        right = null;
//    }
//
//    public BNode(E info, BNode<E> left, BNode<E> right) {
//        this.info = info;
//        this.left = left;
//        this.right = right;
//    }
//
//}
//
//class BTree<E extends Comparable<E>> {
//
//    public BNode<E> root;
//
//    public BTree() {
//        root = null;
//    }
//
//    public BTree(E info) {
//        root = new BNode<E>(info);
//    }
//
//    public void makeRoot(E elem) {
//        root = new BNode(elem);
//    }
//
//    public void makeRootNode(BNode<E> node) {
//        root = node;
//    }
//
//    public BNode<E> addChild(BNode<E> node, int where, E elem) {
//
//        BNode<E> tmp = new BNode<E>(elem);
//
//        if (where == BNode.LEFT) {
//            if (node.left != null)  // veke postoi element
//                return null;
//            node.left = tmp;
//        } else {
//            if (node.right != null) // veke postoi element
//                return null;
//            node.right = tmp;
//        }
//
//        return tmp;
//    }
//
//    public BNode<E> find(BNode<E> node, E nodeValue) {
//        BNode<E> result = null;
//        if (node == null)
//            return null;
//        if (node.info.compareTo(nodeValue) == 0)
//            return node;
//        if (node.left != null)
//            result = find(node.left, nodeValue);
//        if (result == null)
//            result = find(node.right, nodeValue);
//        return result;
//    }
//
//    public BNode<E> addChildNode(BNode<E> node, int where, BNode<E> tmp) {
//
//        if (where == BNode.LEFT) {
//            if (node.left != null)  // veke postoi element
//                return null;
//            node.left = tmp;
//        } else {
//            if (node.right != null) // veke postoi element
//                return null;
//            node.right = tmp;
//        }
//
//        return tmp;
//    }
//
//}