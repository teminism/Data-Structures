package BinarySearchTree;

public class BinarySearchTree {
    private class Node {
        Node parent;
        Node left, right;
        int value;

        Node (Node parent, int value) {
            this.parent = parent;
            this.value = value;
            this.left = this.right = null;
        }

        /*
         *  When deleting, we want to know how many children a node has.
         */
        int numChildren () {
            return (left == null ? 0 : 1) + (right == null ? 0 : 1);
        }

        /*
         *  Convert a node to a string using an in-order traversal.
         */
        public String toString () {
            String s = "";
            if (left != null) s = left + ", ";
            s += value;
            if (right != null) s += ", " + right;
            return s;
        }
    }

    private Node root = null;
    private int size = 0;

    public boolean isEmpty () {
        return root != null;
    }

    public boolean contains (int i) {
        return getNode (i) != null;
    }

    private Node getNode (int i) {
        Node cur = root;
        while (cur != null) {
            if (i == cur.value)
                return cur;
            else if (i < cur.value)
                cur = cur.left;
            else
                cur = cur.right;
        }
        return null;
    }

    public void insert (int i) {
        Node cur = root;
        if (root == null) {
            root = new Node (null, i);
            size = 1;
            return;
        }

        while (true) {
            if (i == cur.value)
                return;
            else if (i < cur.value) {
                if (cur.left == null) {
                    cur.left = new Node (cur, i);
                    size++;
                    return;
                } else
                    cur = cur.left;
            } else {
                if (cur.right == null) {
                    cur.right = new Node (cur, i);
                    size++;
                    return;
                } else
                    cur = cur.right;
            }
        }
    }

    /*
     *  Delete a node from the tree. If the node has one child or none,
     *  delete it as if it, its parent (if it exists) and its child (if
     *  it has one) are a doubly linked list. Otherwise, replace the value
     *  in the node to be deleted with the minimum element of its right
     *  subtree and delete the node that originally contained that element.
     *  Note that, by construction, that node has at most one child (it has
     *  no left child because a left child would contain a smaller value).
     */
    public void delete (int i) {
        Node node = getNode (i);
        if (node == null)
            return;

        if (node.numChildren() < 2) {
            simpleDelete (node);
        } else {
            Node min = getMinNode(node.right);
            simpleDelete (min);
            node.value = min.value;
        }
        size--;
    }

    /*
     *  Delete a node that has one child or none. We treat this node,
     *  its parent (if it exists) and its child (if it has one) as a doubly
     *  linked list and delete accordingly. The code is fiddly because of
     *  the special cases. We might be deleting the root (parent == null)
     *  and/or we might be deleting an node with no child, with just a left
     *  child or just a right child.
     */
    private void simpleDelete (Node node) {
        Node child = node.left != null ? node.left : node.right;

        if (node == root) {
            root = child;
            if (root != null)
                root.parent = null;
        } else {
            if (node == node.parent.left)
                node.parent.left = child;
            else
                node.parent.right = child;
            if (child != null)
                child.parent = node.parent;
        }
    }

    /*
     *  Return the node containing the smallest value in the subtree
     *  rooted at the given node. This is found by following the left
     *  child reference until we get to a node that has no left child.
     */
    private Node getMinNode (Node node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

    /*
     *  Convert to a String: "null" if there's no root; convert the
     *  root if non-null. Lists the values stored in the tree.
     */
    public String toString () {
        if (root == null)
            return "null";
        else
            return root.toString();
    }

    /*
     *  Test code. Performs a sequence of additions and deletions to
     *  test that everything is working.
     */
    public static void main (String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        /*
         *  Declare an array of numbers to be inserted/deleted. This is a
         *  bit of a hack but positive numbers are added to the tree and
         *  -x means "delete x".
         */
        int[] values = {6, 6, /* test duplicate insert */
                2, 1, 4, 7, 3, 5, 8,
                -7, /* delete non-root with one child */
                -8, /* delete non-root leaf */
                -6, /* delete root with one child */
                -4, /* delete non-root with two children */
                -2, /* delete root with two children */
                -3, -1, -5 /* delete last node */};

        for (int value: values) {
            if (value >0) {
                System.out.print("Insert " + value);
                bst.insert(value);
            } else {
                value = -value;
                System.out.print("Delete " + value);
                bst.delete(value);
            }
            System.out.println(": " + bst);
        }
    }
}
