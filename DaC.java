public class DaC {
    public static int[][] divideAndConquer(int[][] A, int[][] B, int[][] C, int n,
                                           int startRowA, int lastRowA, int startColA, int lastColA,
                                           int startRowB, int lastRowB, int startColB, int lastColB) {
        if(n == 1)
        {
            if (lastRowA < n && lastColA >= n && lastRowB >= n && lastColB < n)
                C[lastRowA][lastColB] = (A[lastRowA][lastColA] * B[lastRowB][lastColB]) + C[lastRowA][lastColB];
            else if (lastRowA < n && lastColA >= n && lastRowB >= n)
                C[lastRowA][lastColB] = (A[lastRowA][lastColA] * B[lastRowB][lastColB]) + C[lastRowA][lastColB];
            else if (lastRowA >= n && lastColA >= n && lastRowB >= n && lastColB < n)
                C[lastRowA][lastColB] = (A[lastRowA][lastColA] * B[lastRowB][lastColB]) + C[lastRowA][lastColB];
            else if (lastRowA >= n && lastColA >= n && lastRowB >= n)
                C[lastRowA][lastColB] = (A[lastRowA][lastColA] * B[lastRowB][lastColB]) + C[lastRowA][lastColB];
            else
                C[lastRowA][lastColB] = A[lastRowA][lastColA] * B[lastRowB][lastColB];
        }
        else
        {
            n /= 2;
            // Handles first row of matrices multiplication
            divideAndConquer(A, B, C, n, startRowA, lastRowA - n, startColA, lastColA - n,
                    startRowB, lastRowB - n, startColB, lastColB - n);
            divideAndConquer(A, B, C, n, startRowA, lastRowA - n, startColA + n, lastColA,
                    startRowB + n, lastRowB, startColB, lastColB - n);

            divideAndConquer(A, B, C, n, startRowA, lastRowA - n, startColA, lastColA - n,
                    startRowB, lastRowB - n, startColB + n, lastColB);
            divideAndConquer(A, B, C, n, startRowA, lastRowA - n, startColA + n, lastColA,
                    startRowB + n, lastRowB, startColB + n, lastColB);

            divideAndConquer(A, B, C, n, startRowA + n, lastRowA, startColA, lastColA - n,
                    startRowB, lastRowB - n, startColB, lastColB - n);
            divideAndConquer(A, B, C, n, startRowA + n, lastRowA, startColA + n, lastColA,
                    startRowB + n, lastRowB, startColB, lastColB - n);

            divideAndConquer(A, B, C, n, startRowA + n, lastRowA, startColA, lastColA - n,
                    startRowB, lastRowB - n, startColB + n, lastColB);
            divideAndConquer(A, B, C, n, startRowA + n, lastRowA, startColA + n, lastColA,
                    startRowB + n, lastRowB, startColB + n, lastColB);
        }
        return C;
    }
}
