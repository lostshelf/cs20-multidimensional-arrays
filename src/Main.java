import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class Main {
    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        two();
        printSeparator();
        four();
        printSeparator();
        six();
        printSeparator();
        eight();
    }

    // Question Two (BONUS included)
    static void two() {
        Integer[][] intArray = new Integer[4][4];

        // Keep track of which row it's at
        for (int i = 0; i < 4; i++)
            // Keep track of which column it's at
            for (int j = 0; j < 4; j++)
                // Choose a random number between 1 and 10 to fill the current element
                intArray[i][j] = rand.nextInt(10) + 1;

        print2DArray(intArray);
        System.out.println("Total: ");

        int total = 0;
        for (Integer[] rows : intArray)
            for (Integer vals : rows)
                total += vals;

        System.out.println(total + '\n');
    }

    static void four() {
        System.out.print("How many rows will the array have?: ");
        int rows = scan.nextInt();
        scan.nextLine();
        System.out.print("How many column will the array have?: ");
        int columns = scan.nextInt();
        scan.nextLine();

        Integer[][] arr = new Integer[rows][columns];

        int counter = 1;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                arr[i][j] = counter++;

        print2DArray(arr);
    }

    // Also includes BONUS
    // My favourite problem yet since it was surprisingly challenging
    // Only thing left to do is randomize the direction in which the word goes as currently it only goes left to right
    static void six() {
        System.out.print("Enter desired strings seperated by a space: ");
        String input = scan.nextLine();

        // Array of words from the user input
        String[] words = input.split(" ");

        // Array to store the invalid array location in
        int[] usedRows = new int[10];

        // Convert words into a stream of strings
        int max = Arrays.stream(words)
                // Create a stream of all the lengths of the elements in the word array
                .map(String::length)
                // Get the max value by running the compareTo function on all array elements
                .max(Integer::compareTo)
                // Extract actual value out of the Optional one
                .get();

        // Minimum size of the crossword puzzle
        if (max <= 10)
            max = 10;

        Character[][] wordSearch = new Character[max][max];

        // This is a lot of duplicate code just for one change. Can I just combine the bonus and the regular question
        // into one function?

        // Loop through the rows
        for (int i = 0; i < wordSearch.length; i++)
            // Loop through the columns in each row
            for (int j = 0; j < wordSearch[0].length; j++)
                // Set each one to random letters
                wordSearch[i][j] = (char) (rand.nextInt(26) + 65); // Random integers from 65 -> 91 (A -> Z on the ASCII table | source: https://asciitable.com)

        for (String word : words) {
            // Starting positions for the word
            int x = rand.nextInt(11 - word.length());

            // Can't initialize with 0 or else the program breaks
            int y = -5;

            // Variable in lambda expression must not change
            // Not entirely sure why but IntelliJ wouldn't let me compile if I didn't address this
            final int finalY = y;

            // loop until `y` doesn't already have a word in it
            do {
                y = rand.nextInt(11 - word.length());
            }

            // Loop until there is no match for Y in the usedRows array
            while (Arrays.stream(usedRows).anyMatch(i -> i == finalY));

            // Loop through each character in the word
            for (int ch : word.toUpperCase().chars().toArray()) {
                // Place it in the array with its corresponding X and Y values
                wordSearch[y][x] = (char) ch; // Have to convert back to char since looping through it turns it into
                                              // an int for some reason?

                // Increase `x` to move the cursor to the left
                x++;
            }

            // Add `y` to usedRows, so it is not overwritten
            usedRows[usedRows.length - 1] = y;
        }

        // Finally print the array out
        print2DArray(wordSearch);
    }

    // A lot more annoying than the last one, but it was still pretty interesting
    // I haven't had to use 2D arrays a single time since I started coding, so it was a fun and new experience
    // Not sure about how efficient this is. I feel like there's a much cleaner and more efficient way to do this
    // but I'm happy with its current state.
    static void eight() {
        System.out.print("How many bell numbers would you like?: ");
        int rows = scan.nextInt();
        // Need to empty `System.in` or else weird formatting bugs will happen
        scan.nextLine();

        // Array to complete all the computations in
        Integer[][] bellNumbers = new Integer[rows][];

        // Can't use the shortcut here unfortunately since it hasn't even been allocated yet
        bellNumbers[0] = new Integer[1];
        bellNumbers[0][0] = 1;

        // No point in calculating the length over and over again, so I made another variable for it
        for (int i = 1; i < rows; i++) {
            // Allocate the next array with one extra spacer than the last array
            bellNumbers[i] = new Integer[bellNumbers[i - 1].length + 1];

            // Set the first element of this array to the last element of the array before
            bellNumbers[i][0] = bellNumbers[i - 1][(bellNumbers[i - 1]).length - 1];

            // Loop through this array and fill all the elements up by adding the one before it and the one before and
            // above it
            for (int j = 1; j < (bellNumbers[i]).length; j++) {
                bellNumbers[i][j] = bellNumbers[i][j - 1] + bellNumbers[i - 1][j - 1];
            }
        }

        // Print out the last element of each array which are the wanted bell numbers
        for (Integer[] arr : bellNumbers) {
            System.out.println(arr[arr.length - 1]);
        }
    }

    // Makes my life easier, just need to pass in the array instead of rewriting the print logic for each function
    static <T> void print2DArray(T[][]arr) {
        // Loop through rows
        for (T[] rows : arr) {
            // Loop through columns
            for (T columns : rows)
                // Print out the number along with a space after for readability
                System.out.print(columns + " ");

            // Add extra line to make it look better
            System.out.println();
        }
    }

    static void printSeparator() {
        System.out.println("------------------------------");
    }
}