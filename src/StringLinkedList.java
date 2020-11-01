import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Class that Wraps a linkeList Gives us the ability to create a Array Of linkedLists
 */
public class StringLinkedList {
    private LinkedList<String> lst;
    private int counter;

    StringLinkedList(){
        this.lst = new LinkedList<String>();
        counter  = 0;
    }

    /**
     * adds new String to List,
     * @param newVal - value to add
     * @return true if Value did not exist in array
     */
    public boolean add(String newVal){
        if(!lst.contains(newVal)){
            lst.add(newVal);
            return true;
        }

        return false; // value already in List
    }

    /**
     * Removes Given String from List,
     * @param toRemove - String to
     * @return true if an element was removed as a result of this call
     */
    public boolean remove(String toRemove){
        return this.lst.remove(toRemove);
    }

    /**
     * checks size of list.
     * @return returns size of list
     */
    public int size(){
        return this.lst.size();
    }

    /**
     * Removes and returns the last element from this list.
     * @return - the last element from this list
     *         - if list is empty returns null
     */
    public String removeFirst(){
        try {
            return this.lst.removeFirst();
        }
        catch (NoSuchElementException exception){
            return null;
        }


    }
    /**
     * Checks if given String is in list.
     * @param toCheck - val to check
     * @return - true if this list contains the specified element
     */
    public boolean contains(String toCheck){
        return this.lst.contains(toCheck);
    }


}
