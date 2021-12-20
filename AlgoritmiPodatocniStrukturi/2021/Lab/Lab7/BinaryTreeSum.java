import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.StringTokenizer;

//        Збир на елементи во бинарно дрво Problem 3 (0 / 0)
//        Дадено ви е бинарно дрво. Потоа дадена ви е вредноста на некој јазол во дрвото. Испечатете го збирот на елементите во неговото лево поддрво кои се помали од него и збирот на елементите во неговото десно поддрво кои се поголеми од него.
//
//        Име на класата (Java): BinaryTreeSum.

// uncomment this ->

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
//
//public class BinaryTreeSum {
//
//    public static int sumCondition(BNode<Integer> node, Comparator<Integer> comparator, int value) {
//        if (node == null) return 0;
//        if (comparator.compare(node.info, value) > 0)
//            return node.info + sumCondition(node.right, comparator, value) + sumCondition(node.left, comparator, value);
//        else return sumCondition(node.right, comparator, value) + sumCondition(node.left, comparator, value);
//    }
//
//
//    public static void main(String[] args) throws Exception {
//        int i, j, k;
//        int index;
//        String action;
//
//        String line;
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//
//        int N = Integer.parseInt(br.readLine());
//
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
//                // this node is the root
//                tree.makeRootNode(nodes[index]);
//            }
//        }
//
//        int baranaVrednost = Integer.parseInt(br.readLine());
//
//        br.close();
//
//        BNode<Integer> root = tree.find(tree.root, baranaVrednost);
//        System.out.printf("%d %d",
//                sumCondition(root.left, (x, y) -> Integer.compare(y, x), baranaVrednost),
//                sumCondition(root.right, Integer::compare, baranaVrednost)
//        );
//
//
//    }
//}
