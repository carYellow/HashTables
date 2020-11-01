import java.util.*;

public class SimpleSetPerformanceAnalyzer {
    private static final int OPENHASHSET =0;
    private static final int CLOSEDHASHSET =1;
    private static final int TREESET =2;
    private static final int LINKEDLIST =3;
    private static final int HASHSET =4;
    private static final int THIRD = 33333;
    private static final int TWOTHIRDS = 66666;
    private static final int MILLION = 1000000;
    private static final String dataOnePath = "/Users/moshetannenbaum/Desktop/ex4-mota33/src/data1.txt";
    private static final String dataTwoPath = "/Users/moshetannenbaum/Desktop/ex4-mota33/src/data2.txt";

    private static SimpleSet[] arraySet; // array with all the types of sets

    public static void main(String[] args){
        String testoRun = printMenu();
        if(testoRun.equals("1")){
            runDataOneTests();
        }
        if(testoRun.equals("2")) {
            runDataTwoTests();
        }
        if(testoRun.equals("0")){
            runDataOneTests();
            runDataTwoTests();
        }
    }
    /**
     * initialises arraySet with all the types of sets we want to test
     */
    private static void initArraySet(){
        arraySet = new SimpleSet[5];
        arraySet[OPENHASHSET] = new OpenHashSet();
        arraySet[CLOSEDHASHSET] = new ClosedHashSet();
        arraySet[TREESET] = new CollectionFacadeSet(new TreeSet<String>());
        arraySet[LINKEDLIST] = new CollectionFacadeSet(new LinkedList<String>());
        arraySet[HASHSET] = new CollectionFacadeSet(new HashSet<String>());
    }

    /**
     * inputs given words into set
     * @param words - array of words to add
     * @param set - set to add to
     */
    public static void addWordsToSet(String[] words,SimpleSet set){
        for(int i = 0; i<words.length; i++){
            if(i == THIRD)
                System.out.println("Finished a third ");
            if(i == TWOTHIRDS)
                System.out.println("Finished a two thirds");
            set.add(words[i]);
        }
    }

    /**
     * Prints out Menut to user with diffrent options of tests
     * @return - test choice
     */
    public static String printMenu(){
        System.out.println("Welcome to SimpleSetPerformanceAnalyzer");
        System.out.println("Please select the Tests that you would like to run today: ");
        Scanner in = new Scanner(System.in);
        System.out.println("To run all the tests on with DataOne type 1");
        System.out.println("To run all the tests on with DataTwo type 2");
        System.out.println("To run all the tests on DataOne and on DataTwo type 0");

        String testType = in.nextLine();

        while (!(testType.equals("0") || testType.equals("1") || testType.equals("2"))){
            System.out.println("Invalid input");
             testType = in.nextLine();
        }
        System.out.println("testType = "+testType);

        return testType;
    }


    /**
     * Runs all the tests on DataTwo:
     * 1.Loading data
     * 2.Check if contains "23"
     * 2.Check if contains "hi"
     */
    private static void runDataTwoTests() {
        // Data Two tests
        initArraySet(); // create new sets to test on with Data2
        System.out.println("Time Analysis for  for adding DataTwo");
        printTimeForAddingData(dataTwoPath);
        System.out.println("Time Analysis for if contains 23 in dataTwo");
        printTimeForContain("23"); //perform contains("23") when it’s initialized with data2.txt
        System.out.println("Time Analysis for if contains hi in dataTwo");
        printTimeForContain("hi"); //perform contains("23") when it’s initialized with data2.txt
    }
    /**
     * Runs all the tests on DataOne:
     * 1.Loading data
     * 2.Check if contains "hi"
     * 2.Check if contains "13170890158"
     */
    private static void runDataOneTests() {
        // DataTwo tests
        initArraySet();
        System.out.println("Time Analysis for adding DataOne:");
        printTimeForAddingData(dataOnePath);
        System.out.println("Time Analysis for checking if contains hi in dataOne");
        printTimeForContain("hi"); // check if sets contain "hi" when initialized with data1.txt
        // check if sets contain "-13170890158" when initialized with data1.txt
        System.out.println("Time Analysis for if contains -13170890158 in dataOne");
        printTimeForContain("-13170890158");
    }

    /**
     * Prints out the time (in nanoseconds) it takes find if given val is contained by sets.
     * sets type by index:
     * 0. OpenHashSet
     * 1. ClosedHashSet
     * 2. Java’s TreeSet
     * 3. Java’s LinkedList
     * 4. Java’s HashSet
     * @param val - val to check
     */
    private static void printTimeForContain(String val){
        for (int i = 0; i<arraySet.length; i++){
            if (i != 3) // not linkedList
                checkContains70000times(val, arraySet[i]); // "Warm up"
            System.out.println("Before contain runtime analysis");
            long timeBefore = System.nanoTime();
            checkContains70000times(val, arraySet[i]);
            long difference = System.nanoTime() - timeBefore;
            System.out.println("type of set = "+i);
            System.out.println("timeItTook = "+ difference/70000+" nanoseconds");

        }
    }
    /**
     * Prints out the time (in milliseconds) it takes to add given file to all sets.
     * sets type by index:
     * 0. OpenHashSet
     * 1. ClosedHashSet
     * 2. Java’s TreeSet
     * 3. Java’s LinkedList
     * 4. Java’s HashSet
     * @param path - path of file
     */
    private static void printTimeForAddingData(String path) {

        String [] dataArrray =  Ex4Utils.file2array(path);
        for(int i = 0; i<arraySet.length; i++) {
            System.out.println("Before Adding Data runtime analysis");
            long timeBefore = System.nanoTime();
            addWordsToSet(dataArrray,arraySet[i]);
            long difference = System.nanoTime() - timeBefore;
            System.out.println("type of set = "+i);
            System.out.println("timeItTook = "+ difference/MILLION+ " milliseconds");
        }
    }

    /**
     * Check if value is given Set 70000 times
     * @param val - value to check
     * @param set - set to check
     */
    private static void checkContains70000times(String val, SimpleSet set){
        for(int i = 0; i<=70000; i++){
            set.contains(val);
        }
    }

}
