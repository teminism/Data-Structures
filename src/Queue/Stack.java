package Queue;

import java.util.*;

/**
 *  A class implementing the stack ADT to provide a stack of strings. This
 *  implementation stores the data in an array, which initially has length
 *  10 and which is doubled every time the stack fills up.
 */
public class Stack implements StackADT {
    /*
     *  The entries of the stack and the number of items currently in the
     *  stack. Note that the fields are private, so the stack can only be
     *  accessed through the methods of the public interface. This prevents
     *  users from corrupting the data structure.
     */
    private String[] entries = new String[10];
    private int size = 0;

    /**
     *  push adds a string to the top of the stack.
     */
    public void push (String s) {
        /*
         *  Java arrays are indexed from zero so, if there are, e.g., five
         *  strings on the stack, they will be in positions 0-4. The next
         *  string to be added will go in position 5 and then the size will
         *  be 6.
         */
        entries[size] = s;
        size++;

        /*
         *  If the array is now full, we grow it so the next call to push()
         *  won't fail with an ArrayIndexOutOfBoundsException. We grow the
         *  array by doubling its length -- specifically, by making a new
         *  array that's twice as long, copying across the entries and then
         *  throwing away the old array. Instead of doubling the array, we
         *  could add, e.g., 10 more cells each time. This would use less
         *  memory but would result in much more copying: adding n strings
         *  to the stack would, on average, require copying each one about
         *  n/2 times; with doubling, each one is copied only once, on
         *  average. See the slides for details.
         *
         *  Arrays.copyOf (A, n) creates a new array of length n. If
         *  n <= A.length, it copies the first n entries of A into the new
         *  array. If n > A.length, it copies the whole of A into the start
         *  of the new array and fills the rest with a default value of the
         *  appropriate type -- 0, false or null. Here, we have an array of
         *  Strings, so the default value is null.
         */
        if (size >= entries.length)
            entries = Arrays.copyOf (entries, entries.length * 2);
    }

    /**
     *  pop() removes the top string from the stack and returns it. If the
     *  stack is empty, it returns null; it would probably be better to throw
     *  an exception, in production code, since trying to pop() from an empty
     *  stack usually indicates an error somewhere in the code.
     *
     *  Note that we don't actually remove the string from the array, but it
     *  will be overwritten the next time that array cell is used. Production
     *  code might want to set entries[size] to null so that the String
     *  object can be freed by the garbage collector.
     */
    public String pop () {
        if (size == 0)
            return null;
        else {
            size--;
            return entries[size];
        }
    }

    /*
     *  A convenient method to test whether the stack is empty.
     */
    public boolean isEmpty () { return size == 0; }

    /*
     *  Return the current number of entries in the stack. Note that size
     *  itself is private, so cannot be accessed directly from outside the
     *  class.
     */
    public int length () { return size;      }

    /*
     *  Test code. We push the numbers 0-99 onto the stack and then pop
     *  them off again. Since we are within the Stack class, we can access
     *  the private fields such as entries, and use this to detect when the
     *  stack has grown.
     */
    public static void main (String[] args) {
        Stack s = new Stack ();

        System.out.println ("Pushing data onto stack:");
        System.out.print ("    ");
        int capacity = s.entries.length;
        for (int i = 0; i < 100; i++) {
            System.out.print (i + " ");
            s.push(Integer.toString(i));
            if (capacity != s.entries.length) {
                capacity = s.entries.length;
                System.out.println ();
                System.out.println ("Stack capacity increased to " + capacity);
                System.out.print ("    ");
            }
        }
        System.out.println ();
        System.out.println ();

        System.out.println ("Popping data off stack:");
        System.out.print ("    ");
        do {
            System.out.print (s.pop() + " ");
        } while (!s.isEmpty());
        System.out.println ();
    }
}
