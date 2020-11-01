public abstract class SimpleHashSet implements SimpleSet {
    protected static final float DEFAULT_HIGHER_CAPACITY = 0.75f;
    protected static final float DEFAULT_LOWER_CAPACITY = 0.25f;
    protected static final int INITIAL_CAPACITY = 16;
    protected  static final double DECREASE = 0.5;
    protected  static final int INCREASE = 2;

    private float upperLoadFactor;
    private float lowerLoadFactor;
    private int initialCapacity; //TODO: get rid of this

    protected SimpleHashSet(){
        this.upperLoadFactor = DEFAULT_HIGHER_CAPACITY;
        this.lowerLoadFactor = DEFAULT_LOWER_CAPACITY;
        // TODO:Check if this is needed
        this.initialCapacity = INITIAL_CAPACITY;

    }

    protected SimpleHashSet(float upperLoadFactor,
                            float lowerLoadFactor){
        this.upperLoadFactor = upperLoadFactor;
        this.lowerLoadFactor = lowerLoadFactor;
        this.initialCapacity = INITIAL_CAPACITY;
    }

    /**
     *
     * @return The current capacity (number of cells) of the table.
     */
    public abstract int capacity();

    /**
     * Clamps hashing indices to fit within the current table capacity
     * @param index  - the index before clamping
     * @return - an index properly clamped
     */

     protected int clamp(int index){
         return index & (this.capacity()-1);
    }

    /**
     *
     * @return The lower load factor of the table.
     */
    protected float getLowerLoadFactor(){
        return this.lowerLoadFactor;
    }

    /**
     *
     * @return - The higher load factor of the table.
     */
    protected float getUpperLoadFactor(){
        return this.upperLoadFactor;
    }
    @Override
    abstract public boolean add(String newValue);

    @Override
    abstract public boolean contains(String searchVal);

    @Override
    abstract public boolean delete(String toDelete);


    @Override
    abstract public int size();
}
