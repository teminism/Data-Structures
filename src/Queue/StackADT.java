package Queue;

/*
 *  This interface defines the stack abstract datatype. As explained in
 *  the lectures, an ADT is more than just a Java interface -- it also
 *  includes behavioural descriptions of the operations and their
 *  running times. It is not necessary to define a Java interface for
 *  your ADTs. I did it here because I want to consider multiple imple-
 *  mentations. In most cases, you'll just have one implementation, so
 *  there's no need for the Java interface.
 */
public interface StackADT {
    public void push (String s);
    public String pop ();
    public boolean isEmpty ();
    public int length ();
}