/**
 * Class impliments Closed Hash set
 */
public class ClosedHashSet extends SimpleHashSet {

    private int tableLength;
    private int size; // amount of Strings in array not including tombStone
    private String[] tableArray; // Hash table
    // to create unique pointer in heap
    private static final String tombStone = new String("tombStone");
    private static final int CONSTANT  = 2;

    /**
     * Constructs a new, empty table with the specified load factors,
     * and the default initial capacity (16).
     * @param upperLoadFactor - The upper load factor of the hash table.
     * @param lowerLoadFactor - The lower load factor of the hash table.
     */
    public ClosedHashSet(float upperLoadFactor,
                         float lowerLoadFactor){
        super(upperLoadFactor, lowerLoadFactor);
        initData();
    }



    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16),
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public ClosedHashSet(){
        super();
        initData();
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one.
     * Duplicate values should be ignored. The new table has the default values of initial capacity (16),
     * upper load factor (0.75), and lower load factor (0.25).
     * @param data - Values to add to the set.
     */
    public ClosedHashSet(java.lang.String[] data){
        super();
        initData();
        for (String val:data){
            this.add(val);
        }
    }

    /**
     * initialises data Members in class
     */
    private void initData() {
        tableLength = INITIAL_CAPACITY;
        tableArray = new String[tableLength];
        size = 0;

    }

    /**
     * The number of elements currently in the set.
     * @return the number of elements currently in the set
     */
    @Override
    public int size(){
        return this.size;
    }

    /**
     * Finds the current capacity (number of cells) of the table.
     * @return The current capacity (number of cells) of the table.
     */
    @Override
    public int capacity() {
        return this.tableLength;
    }


    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue - New value to add to the set
     * @return - False if newValue already exists in the set
     */
    @Override
    public boolean add(String newValue) {
        if (this.contains(newValue))
            return false; // value already in hashTable

        //Since the capacity is always to the power of two
        // as long the table is not full, a place for a new value will
        // be found during the first capacity attempts.

        for(int i = 0; i<tableLength; i++){
            if(((double)(this.size+1)/this.tableLength) > this.getUpperLoadFactor())
                this.resize(INCREASE);
            int index = clamp(newValue.hashCode() + (i + i*i)/2);

            if(tableArray[index] == null || tableArray[index] == tombStone){
                tableArray[index] = newValue;
                size ++;
                return true;
            }
        }
        return false;
    }

    /**
     * Looks for a specified value in the set.
     * @param searchVal - Value to search for
     * @return True if searchVal is found in the set
     */
    @Override
    public boolean contains(String searchVal) {

        for(int i =0; i<tableLength; i++){
            int index = clamp(searchVal.hashCode() + (i + i*i)/CONSTANT);
            if(tableArray[index]==null)
                return false; // value not in hash table
            if(tableArray[index].equals(searchVal))
                return true;
        }
        return false;
    }

    /**
     * Remove the input element from the set.
     * @param toDelete - Value to delete
     * @return True if toDelete is found and deleted
     */
    @Override
    public boolean delete(String toDelete) {
        for(int i =0; i<tableLength; i++){
            int index = clamp(toDelete.hashCode() + (i + i*i)/CONSTANT);
            if(tableArray[index]==null)
                return false; // value not in HashTable
            if(tableArray[index].equals(toDelete)){
                tableArray[index] = tombStone;
                size --;
                // check if need to refactor
                if(((double)(this.size)/this.tableLength) < this.getLowerLoadFactor())
                    this.resize(DECREASE);
                return true;
            }
        }
        return false;
    }

    /**
     * Resizes and rehashes  the table
     * @param factorBy - smaller or bigger
     */
    private void resize(double factorBy){
          int  newLength = (int) (this.tableLength *factorBy);
        if(newLength<1)
            newLength =1;

        this.tableLength = newLength;
        this.size = 0; // reset to get rid of tombstone count
        // Building new array
        String [] newArray = new String[this.tableLength];
        for(String val: this.tableArray){
            if(val != tombStone && val != null){
                rehash(val, newArray);
                this.size ++;
            }
        }
        this.tableArray = newArray;
    }

    /**
     * rehashes given value, (Does not check for duplicates)
     * @param val
     */
    private void rehash(String val, String [] newArray){
        for (int i =0; i<=newArray.length; i++){
            int index = clamp( val.hashCode() + (i + i * i) / 2);
            if (newArray[index] == null){
                newArray[index] = val;
                return;
            }
        }

    }

}

