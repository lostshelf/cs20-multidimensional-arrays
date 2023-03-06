import java.util.*;

public class Main {
    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        six();
    }

    // Question Two
    static void two() {
        int[][] intArray = new int[4][4];

        for (int i = 0; i < 4; i++)
            for (int j = 0; i < 4; i++)
                intArray[i][j] = (i + 1) * (j + 1);

        for (int[] arr : intArray) {
            for (int num : arr)
                System.out.printf("%d ", num);
            six();
        }
    }

    // Also includes BONUS
    // My favourite problem yet since it was surprisingly challenging
    // Only thing left to do is randomize the direction in which the word goes as currently it only goes left to right
    static void six() {
        System.out.print("Enter desired strings seperated by a space: ");
        String input = scan.nextLine();

        String[] words = input.split(" ");

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

        // This is a lot of duplicate code just for one change. Can I just combined the bonus and the regular question
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

            int y = -5;

            // Variable in lambda expression cannot change
            // Not entirely sure why but IntelliJ wouldn't let me compile if I didn't address this
            final int finalY = y;
            do {
                y = rand.nextInt(11 - word.length());
            } while (Arrays.stream(usedRows).anyMatch(i -> i == finalY));


            for (int ch : word.chars().toArray()) {
                wordSearch[y][x] = (char) (ch - 32);
                x++;
            }

            usedRows[usedRows.length - 1] = y;
        }

        print2DArray(wordSearch);
    }

    static <T> void print2DArray(T[][]arr) {
        for (T[] a : arr) {
            for (T b : a)
                System.out.print(b + " ");

            System.out.println();
        }
    }
}