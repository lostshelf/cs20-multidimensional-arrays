import java.util.*;

public class Main {
    static Random rand = new Random();
    public static void main(String[] args) {
        six();
    }

    static void six() {
        Character[][] wordSearch = new Character[10][10];
        String[] words = { "heap", "stack", "buffer" };

        for (int i = 0; i < wordSearch.length; i++)
            for (int j = 0; j < wordSearch[0].length; j++)
                wordSearch[i][j] = (char) (rand.nextInt(26) + 65 + 32);

        for (String word : words) {

            // Starting positions for the word
            int x = rand.nextInt(11 - word.length());
            int y = rand.nextInt(11 - word.length());

            for (int ch : word.chars().toArray() ) {
                wordSearch[y][x] = (char) (ch - 32);
                x++;
            }
        }

        print2DArray(wordSearch);
    }

    static <T> void print2DArray(T[][] arr) {
        for (T a[] : arr) {
            for (T b : a)
                System.out.print(b + " ");

            System.out.println();
        }
    }
}