# Hash Table 
I implemented a two versions of a Hash Table. An open hash table using chaining, and a closed hash table using quadratic probing. 
 
### File Description

ClosedHashSet.java - implements the Closed Hash set\
CollectionFacadeSet.java - implements a wrapper for sets that we use to compare the efficiency of the
different sets\
OpenHashSet.java - implements the Open Hash set\
SimpleHashSet - an abstract class with basic set features\
SimpleSetPerformanceAnalyzer - implements all the tests to compare running time of the different sets



### Design

Both OpenHashSet and Closed Hash set extend the abstract Class SimpleHashSet that contains all the basic and
common functionality of all HashSets. SimpleHashSet implements SimpleSet.
StringLinkedList is a wrapper class for a string linked list inorder to enable the use of an
array of LinkedLists.

### Implementation Details

ClosedHashSet - Delete: I decided to place a "tombStone" at the index of the deleted item,that way when searching for a given item in the table the contains
function would not run in to a null value and return false.
To further optimise the code,
                         when adding a new item if possible i would placce in pace of a tombStone.
                         Note: TombStone is of static String Type, to  initialise it I used:
                               tombStone = new String("tombStone"); to create unique pointer in the heap,
                               this way even if I try to add a string with the value "tombStone" it would not
                               be "==" to  tombStone.
                               
OpenHashSet  - I decided to use a wrapper class, StringLinkedList, that has a LinkedList<String>
and delegates methods to it. I then created an Array of StringLinkedList. To me this seemed like the most
elegant solution that conforms with the encapsulation principle.
Note: I decided not to initiate LinkedList in the Array automatically, rather they are only initialised
when needed. Do this can save run time and memory.

