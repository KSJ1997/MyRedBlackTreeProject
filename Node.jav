class Node {
    int data;
    Node parent;
    Node left;
    Node right;
    int color;
}

public class RedBlackTree {
    private Node root;
    private Node TNULL;

    // Прекращение идентификации, чтобы определить конец узлов
    static {
        TNULL = new Node();
        TNULL.color = 0;
        TNULL.left = null;
        TNULL.right = null;
    }

    // Вставка узла
    public void insert(int key) {
        // Обычная вставка BST
        Node node = new Node();
        node.parent = null;
        node.data = key;
        node.left = TNULL;
        node.right = TNULL;
        node.color = 1; // новая нода всегда красная

        Node y = null;
        Node x = this.root;

        while (x != TNULL) {
            y = x;
            if (node.data < x.data) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        node.parent = y;
        if (y == null) {
            root = node;
        } else if (node.data < y.data) {
            y.left = node;
        } else {
            y.right = node;
        }

        // Если вставленный узел является корнем дерева, просто окрасьте его в черный цвет и вернитесь
        if (node.parent == null){
            node.color = 0;
            return;
        }

        // Если дедушка существует и у нас есть узел-предок и текущий узел красные
        if (node.parent != null && node.parent.parent != null) {
            fixInsert(node);
        }

        // Устанавливаем корень в черный цвет
        root.color = 0;
    }

    // Функция фиксации

