package Queue;


/*
 *  This interface defines the queue abstract data type. It is not nec-
 *  essary to define a Java interface for an ADT -- I recommend it only
 *  if it is useful for your particular program, e.g., if you will be
 *  creating multiple implementations.
 */
public interface QueueADT {
    public void add (String s);
    public String remove ();
    public boolean isEmpty ();
    public int length ();
}
