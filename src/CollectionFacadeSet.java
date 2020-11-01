import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class CollectionFacadeSet implements SimpleSet {

    private Collection<String> collection;

    /**
     * Creates a new facade wrapping the specified collection.
     * @param collection - The Collection to wrap.
     */
    public CollectionFacadeSet(java.util.Collection<java.lang.String> collection){
      this.collection = collection;

    }
            @Override
            public boolean add(String newValue) {
                if(!this.collection.contains(newValue))
                    return this.collection.add(newValue);
                return false;

        }

            @Override
            public boolean contains(String searchVal) {
            return collection.contains(searchVal);
        }

            @Override
            public boolean delete(String toDelete) {
                return collection.remove(toDelete);
        }

            @Override
            public int size() {
            return this.collection.size();
        }
        }

