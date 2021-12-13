import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

//        Windows Explorer Problem 1 (1 / 5)
//        Потребно е да имплементирате Windows Explorer со помош на дрво. Јазлите треба да ви бидат фолдери/датотеки. Почетно ќе имате само еден фолдер c: и тој ви е тековен фолдер. Ќе ви биде дадена низа од команди што можат да бидат во еден од следните типови:
//
//        CREATE [име на фолдер/датотека] - треба да креирате нов фолдер/датотека во тековниот. Треба да внимавате при додавањето, новиот фолдер/датотека треба да се смести на онаа позиција така што сите фолдери/датотеки во тековниот фолдер ќе бидат подредени лексикографски
//
//        OPEN [име на фолдер/датотека] - го отварате фолдерот во тековниот фолдер и се менува тековниот фолдер
//
//        DELETE [име на фолдер/датотека] - го бришете фолдерот/датотеката
//
//        BACK - се враќате назад во претходниот фолдер
//
//        PATH - се печати патеката на тековниот фолдер на пример
//
//        PRINT - се печати целата структура на датотечниот систем така што секој фолдер/датотека се печати во еден ред со онолку празни места колку што е нивото на тој фолдер/датотека
//
//        Забелешка: Имињата на фолдерите/датотеките ќе бидат составени само од еден збор што содржи мали латинични букви. Сите операции ќе бидат валидни.
//
//        Име на класата: WindowsExplorer

interface Tree<E> {
    ////////////Accessors ////////////

    public Node<E> root();

    public Node<E> parent(Node<E> node);

    public int childCount(Node<E> node);

    ////////////Transformers ////////////
    public void makeRoot(E elem);

    public Node<E> addChild(Node<E> node, E elem);

    public void remove(Node<E> node);

    ////////////Iterator ////////////
    public Iterator<E> children(Node<E> node);

}

interface Node<E> {

    public E getElement();

    public void setElement(E elem);
}


class SLLTree<E> implements Tree<E> {

    public SLLNode<E> root;

    public SLLTree() {
        root = null;
    }

    public Node<E> root() {
        return root;
    }

    public Node<E> parent(Node<E> node) {
        return ((SLLNode<E>) node).parent;
    }

    public int childCount(Node<E> node) {
        SLLNode<E> tmp = ((SLLNode<E>) node).firstChild;
        int num = 0;
        while (tmp != null) {
            tmp = tmp.sibling;
            num++;
        }
        return num;
    }

    public void makeRoot(E elem) {
        root = new SLLNode<E>(elem);
    }

    public Node<E> addChild(Node<E> node, E elem) {
        SLLNode<E> tmp = new SLLNode<E>(elem);
        SLLNode<E> curr = (SLLNode<E>) node;
        //tmp.sibling = curr.firstChild;
        //curr.firstChild = tmp;
        //tmp.parent = curr;
        //return tmp;
        if (curr.firstChild == null || tmp.compareTo(curr.firstChild) < 0) {
            tmp.sibling = curr.firstChild;
            curr.firstChild = tmp;
            tmp.parent = curr;
            return tmp;
        }
        SLLNode<E> node1 = (SLLNode<E>) curr.firstChild;
        SLLNode<E> node2 = (SLLNode<E>) node1.sibling;
        while (node2 != null) {
            if (tmp.compareTo(node2) < 0) {
                node1.sibling = tmp;
                tmp.sibling = node2;
                tmp.parent = curr;
                return tmp;
            }
            node1 = node1.sibling;
            node2 = node2.sibling;
        }
        node1.sibling = tmp;
        tmp.parent = curr;
        return tmp;

    }

    public Node<E> getNode(Node<E> node, E elem) {
        SLLNode<E> tmp = (SLLNode<E>) node;
        tmp = tmp.firstChild;
        while (tmp != null) {
            if (tmp.element.equals(elem)) {
                return tmp;
            }
            tmp = tmp.sibling;
        }
        throw new NoSuchElementException();
    }


    public void remove(Node<E> node) {
        SLLNode<E> curr = (SLLNode<E>) node;
        if (curr.parent != null) {
            if (curr.parent.firstChild == curr) {
                // The node is the first child of its parent
                // Reconnect the parent to the next sibling
                curr.parent.firstChild = curr.sibling;
            } else {
                // The node is not the first child of its parent
                // Start from the first and search the node in the sibling list and remove it
                SLLNode<E> tmp = curr.parent.firstChild;
                while (tmp.sibling != curr) {
                    tmp = tmp.sibling;
                }
                tmp.sibling = curr.sibling;
            }
        } else {
            root = null;
        }
    }

    class SLLTreeIterator<T> implements Iterator<T> {

        SLLNode<T> start, current;

        public SLLTreeIterator(SLLNode<T> node) {
            start = node;
            current = node;
        }

        public boolean hasNext() {
            return (current != null);
        }

        public T next() throws NoSuchElementException {
            if (current != null) {
                SLLNode<T> tmp = current;
                current = current.sibling;
                return tmp.getElement();
            } else {
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            if (current != null) {
                current = current.sibling;
            }
        }
    }

    public Iterator<E> children(Node<E> node) {
        return new SLLTreeIterator<E>(((SLLNode<E>) node).firstChild);
    }

    void printTreeRecursive(Node<E> node, int level) {
        if (node == null)
            return;
        int i;
        SLLNode<E> tmp;

        for (i = 0; i < level; i++)
            System.out.print(" ");
        System.out.println(node.getElement().toString());
        tmp = ((SLLNode<E>) node).firstChild;
        while (tmp != null) {
            printTreeRecursive(tmp, level + 1);
            tmp = tmp.sibling;
        }
    }

    public void printTree() {
        printTreeRecursive(root, 0);
    }

}

class SLLNode<P> implements Node<P>, Comparable<SLLNode<P>> {

    // Holds the links to the needed nodes
    public SLLNode<P> parent, sibling, firstChild;
    // Hold the data
    public P element;

    public SLLNode(P o) {
        element = o;
        parent = sibling = firstChild = null;
    }

    public P getElement() {
        return element;
    }

    public void setElement(P o) {
        element = o;
    }

    @Override
    public int compareTo(SLLNode<P> arg0) {
        String s1 = (String) element;
        String s2 = (String) arg0.element;
        return s1.compareTo(s2);
    }
}

public class WindowsExplorer {

    public static void path(SLLNode<String> node) {
        if (node == null) {
            return;
        }
        path(node.parent);
        System.out.print(node.element + "\\");
    }

    public static void main(String[] args) throws Exception {
        int i, j, k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String commands[] = new String[N];

        for (i = 0; i < N; i++)
            commands[i] = br.readLine();

        br.close();

        SLLTree<String> tree = new SLLTree<String>();
        tree.makeRoot("c:");
        SLLNode<String> node = tree.root;

        // vasiot kod stoi ovde
        for (String command : commands) {
            String parts[] = command.split("\\s");
            if (parts[0].equals("CREATE")) {
                tree.addChild(node, parts[1]);
            } else if (parts[0].equals("OPEN")) {
                node = (SLLNode<String>) tree.getNode(node, parts[1]);
            } else if (parts[0].equals("DELETE")) {
                SLLNode<String> toDelete = node.firstChild;
                while (toDelete != null) {
                    if (toDelete.element.equals(parts[1])) {
                        tree.remove(toDelete);
                    }
                    toDelete = toDelete.sibling;
                }
            } else if (parts[0].equals("BACK")) {
                node = node.parent;
            } else if (parts[0].equals("PRINT")) {
                tree.printTree();
            } else if (parts[0].equals("PATH")) {
                SLLNode<String> tmp = node;
                path(tmp);
                System.out.print("\n");
            }
        }


    }

}