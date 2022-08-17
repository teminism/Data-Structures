package BinarySearchTree;

/*
 *  A simple implementation of non-binary trees, using the left-child-right-sibling
 *  representation. I've given each node a String label, and provided put() and
 *  get() methods that take an array of Strings to specify the node in the tree. For
 *  example, the array {"A", "B"} would correspond to starting at the root, going to
 *  the child with label "A", then to its child with label "B".
 */
public class NonBinaryTree {
    /*
     *  Each node stores a String label, an Object for data, and references to its
     *  first child and next sibling. A more comprehensive implementation might
     *  also store a node's parent. The children are essentially stored as a linked
     *  list; depending on how you wanted to access the tree, it might also make
     *  sense to store the children as a doubly linked list and/or to store a
     *  reference to the last child as well as, or instead of the first. Basically,
     *  anything you can do with a linked list, you can do here.
     */
    private class Node {
        String label;
        Object data;
        Node firstChild;
        Node nextSibling;

        private Node (String label, Object data) {
            this.label = label;
            this.data = data;
        }

        private Node (String label, Object data, Node nextSibling) {
            this (label, data);
            this.nextSibling = nextSibling;
        }

        /*
         *  Return the Node storing the child with a specified label, or null if
         *  no such child exists.
         */
        private Node getChildWithLabel (String label) {
            Node cur = firstChild;
            while (cur != null) {
                if (cur.label.equals (label))
                    break;
                cur = cur.nextSibling;
            }
            return cur;
        }
    }

    private Node root;

    /*
     *  Create a new, empty tree. In this case, I'm not storing anything in the
     *  root or giving it a label, but you could do that if it made sense in your
     *  application.
     */
    public NonBinaryTree () {
        root = new Node ("", null);
    }

    /*
     *  Return the data accessed by following a given sequence of labels from the
     *  root. Giving an empty array would retrieve the data stored at the root.
     */
    public Object get (String[] labels) {
        Node cur = root;

        for (String label : labels) {
            cur = cur.getChildWithLabel (label);
            if (cur == null)
                return null;
        }
        return cur.data;
    }

    /*
     *  Stores data in the location given by a sequence of labels. Any nodes that
     *  don't exist are created and given null data. When a new child is created,
     *  it is made to be its parent's first child. This was done for simplicity.
     *  Depending on your application, you might want to make the new child be the
     *  last child (in which case, it would make sense to store each node's last
     *  child, as well as the first), or you might want to sort the children by
     *  label.
     */
    public void put (String[] labels, Object data) {
        Node cur = root;

        for (String label : labels) {
            Node child = cur.getChildWithLabel(label);
            if (child == null)
                cur.firstChild = new Node (label, null, cur.firstChild);
            cur = cur.firstChild;
        }
        cur.data = data;
    }

    /*
     *  Prints out the contents of the tree. Quiz: which traversal is being used
     *  here? (Answer at the bottom of the file.)
     */
    public void print() {
        print (root, "");
    }

    private void print (Node node, String indent) {
        System.out.println(indent + node.label + ": " + node.data);
        for (Node cur = node.firstChild; cur != null; cur = cur.nextSibling)
            print (cur, indent + "  ");
    }

    /*
     *  Test code. We build a tree to store populations of geographical areas.
     *  The labels are arranged hierarchically (e.g., country -> county/state ->
     *  city). Areas in the hierarchy may or may not have population data
     *  stored. For example, when we create UK->Essex->Colchester, we store a
     *  population for Colchester, but not for the UK or Essex. We later add the
     *  population of the UK (the root already has a child with label "UK" so
     *  this doesn't create a new node), but leave Essex without a stated popu-
     *  lation.
     */
    public static void main (String[] args) {
        NonBinaryTree population = new NonBinaryTree();

        population.put (new String[]{"UK", "Essex", "Colchester"}, 122_000);
        population.put (new String[]{"UK", "Essex", "Chelmsford"}, 112_000);
        population.put (new String[]{"UK", "Essex", "Southend"}, 183_000);
        population.put (new String[]{"UK", "Essex", "Basildon"}, 107_000);
        population.put (new String[]{"UK"}, 66_800);      // "oops"
        population.put (new String[]{"UK"}, 66_800_000);  // Correction.
        population.put (new String[]{"UK", "Suffolk", "Ipswich"}, 137_000);
        population.put (new String[]{"UK", "Hertfordshire"}, 1_190_000);
        population.put (new String[]{"USA", "California"}, 39_400_000);
        population.put (new String[]{"USA", "California", "San Francisco"}, 805_000);

        population.print();
    }
}

/*
 *  Quiz answer: it's a pre-order traversal, because we process (in this case,
 *  print) each node before processing its children. Note that the concept of
 *  in-order traversal really requires a binary tree, because it depends on the
 *  concept of having left and right children.
 */

