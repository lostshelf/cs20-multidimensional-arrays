public class Main {
    public static void main(String[] args) {
        two();
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

            System.out.println();
        }
    }
}