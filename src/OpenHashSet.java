/**
 * Class implements Open Hash set
 */

public class OpenHashSet extends SimpleHashSet{
    private StringLinkedList [] tableOfLinkedList;

    private int size;
    private int tableLength;
    /**
     * Constructs a new, empty table with the specified load factors,
     * and the default initial capacity (16).
     * @param upperLoadFactor - The upper load factor of the hash table.
     * @param lowerLoadFactor - The lower load factor of the hash table.
     */
    public OpenHashSet(float upperLoadFactor,
                       float lowerLoadFactor){
        super(upperLoadFactor, lowerLoadFactor);
        tableLength = INITIAL_CAPACITY;
        tableOfLinkedList = new StringLinkedList[tableLength];
        size = 0; // Contains no items

    }

    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16),
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public OpenHashSet(){
        super();
        tableLength = INITIAL_CAPACITY;
        tableOfLinkedList = new StringLinkedList[tableLength];
        size = 0;

    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one.
     * Duplicate values should be ignored. The new table has the default values of initial capacity (16),
     * upper load factor (0.75), and lower load factor (0.25).
     * @param data
     */
    public OpenHashSet(java.lang.String[] data){
        super();
        tableLength = INITIAL_CAPACITY;
        tableOfLinkedList = new StringLinkedList[tableLength];
        size = 0;

        for (String val: data)
            this.add(val);

    }


    /**
     * Description copied from interface: supplied.SimpleSet
     * Add a specified element to the set if it's not already in it.
     * @param newValue  - New value to add to the set
     * @return False iff newValue already exists in the set
     */
    public boolean add(java.lang.String newValue){
        // checks if value already in table
        if(this.contains(newValue))
            return false;
        // adding
        if(((double)(this.size+1)/this.tableLength) > this.getUpperLoadFactor()) {
            this.resize(INCREASE);
        }
        int index = clamp(newValue.hashCode());
        initListAtIndex(index);
        tableOfLinkedList[index].add(newValue);
        size ++;
        return true;
    }

    private void initListAtIndex(int index) {
        if(tableOfLinkedList[index]  == null)
            tableOfLinkedList[index] = new StringLinkedList();
    }

    /**
     * Look for a specified value in the set.
     * @param searchVal - Value to search for
     * @return True if searchVal is found in the set
     */
     public boolean contains(java.lang.String searchVal){
        int index = clamp(searchVal.hashCode());
         if(tableOfLinkedList[index] != null)
            return tableOfLinkedList[index].contains(searchVal);
         return false; // Empty List at index, (Does not contain SearchVal)
    }

    /**
     * Remove the input element from the set.
     * @param toDelete - Value to delete
     * @return True if toDelete is found and deleted
     */
    public boolean delete(java.lang.String toDelete){
        int index = clamp(toDelete.hashCode());
       if (tableOfLinkedList[index] == null)
           return false;
        if(tableOfLinkedList[index].remove(toDelete)){
            size --;
            if(((double)(this.size)/this.tableLength) < this.getLowerLoadFactor())
                this.resize(DECREASE);

            return true;
        }
        return false; // List did not contain item
    }

    /**
     * Resizes and rehashes  the table
     * @param factorBy - DECREASE - 0.5
     *               INCREASE - 2.0
     */

    private void resize(double factorBy){
        int newLength = (int) (this.tableLength*factorBy);
        if(newLength<1)
            newLength = 1;
        this.tableLength = newLength;

        StringLinkedList [] newArray = new StringLinkedList[newLength];
        for(StringLinkedList lst: this.tableOfLinkedList){
            if (lst != null)
                for (String val = lst.removeFirst(); val !=null; val = lst.removeFirst()){
                int index = clamp(val.hashCode());
                //TODO: refactor
                if(newArray[index] == null)
                    newArray[index] = new StringLinkedList();

                newArray[index].add(val);
            }
        }
        this.tableOfLinkedList = newArray;
    }


    /**
     * Finds the number of elements currently in the set.
     * @return The number of elements currently in the set
     */
    public int size(){
        return this.size;
    }

    @Override
    public int capacity() {
        return tableLength;
    }

}
