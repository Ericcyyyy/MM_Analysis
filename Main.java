import java.lang.Math;
public class Main {
    public static void printMatrix(int[][] a, int r, int c){
        for(int i = 0; i < r; i++)
        {
            System.out.print("{ ");
            for(int j = 0; j < c; j++){
                System.out.print(a[i][j] + " ");
            }
            System.out.println("}");
        }
    }
    public static void main(String[] args) {
        int k, row, column;
        k = 2;

        row = (int) Math.pow(2, k);
        column = (int) Math.pow(2, k);

        int[][] A = {{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}};

        int[][] B = {{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}};

        int[][] C = new int[A.length][A.length];

        long start = System.nanoTime();
        printMatrix(ClassicalMM.multiplyMatrix(row, column, A,
                row, column, B), row, column);
        long end = System.nanoTime();
        long elapsedTime = end - start;
        System.out.println("Elapsed time for Naive (Classical) is: " + elapsedTime);

        start = System.nanoTime();
        printMatrix(DaC.divideAndConquer(A, B, C, A.length, 0, A.length - 1, 0, A.length - 1,
                0, A.length - 1, 0, A.length - 1), C.length, C.length);
        end = System.nanoTime();
        elapsedTime = end - start;
        System.out.println("Elapsed time for DoC is: " + elapsedTime);

        start = System.nanoTime();
        printMatrix(Strassen.Strassens(A,B),A.length,A.length);
        end = System.nanoTime();
        elapsedTime = end - start;
        System.out.println("Elapsed time for Strassen is: " + elapsedTime);
    }
}
