public class Strassen {
    private static void partitionMatrix(int[][] matrix,int[][] subMatrix, int row, int col)
    {
        for(int i = 0, ii = row; i < subMatrix.length; i++, ii++){
            for(int j = 0, jj = col; j < subMatrix.length; j++, jj++){
                subMatrix[i][j] = matrix[ii][jj];
            }
        }
    }
    private static int[][] addMatrix(int[][] A, int[][] B)
    {
        int[][] C = new int[A.length][A.length];

        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < A.length; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }

    private static int[][] subtractMatrix(int[][] A, int[][] B)
    {
        int[][] C = new int [A.length][A.length];

        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < A.length; j++){
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }

    private static void mergeMatrix(int[][] matrix, int[][] subMatrix, int row, int col)
    {
        int n = subMatrix.length;
        for(int i = 0, ii = row; i < n; i++, ii++) {
            for (int j = 0, jj = col; j < n; j++, jj++) {
                matrix[ii][jj] = subMatrix[i][j];
            }
        }
    }

    public static int[][] Strassens(int[][] A, int[][] B)
    {
        int n = A.length;
        int[][] C = new int[n][n];
        if(n == 1)
        {
            C[0][0] = A[0][0] * B[0][0];
        }
        else
        {
            int[][] A11 = new int [n/2][n/2];
            int[][] A12 = new int [n/2][n/2];
            int[][] A21 = new int [n/2][n/2];
            int[][] A22 = new int [n/2][n/2];
            int[][] B11 = new int [n/2][n/2];
            int[][] B12 = new int [n/2][n/2];
            int[][] B21 = new int [n/2][n/2];
            int[][] B22 = new int [n/2][n/2];

            partitionMatrix(A, A11, 0, 0);
            partitionMatrix(A, A12, 0, n/2);
            partitionMatrix(A, A21, n/2, 0);
            partitionMatrix(A, A22, n/2, n/2);
            partitionMatrix(B, B11, 0, 0);
            partitionMatrix(B, B12, 0, n/2);
            partitionMatrix(B, B21, n/2, 0);
            partitionMatrix(B, B22, n/2, n/2);


            /*
             P = (A11 + A22)(B11 + B22)
             Q = (A21 + A22) B11
             R = A11 (B12 - B22)
             S = A22 (B21 - B11)
             T = (A11 + A12) B22
             U = (A21 - A11) (B11 + B12)
             V = (A12 - A22) (B21 + B22)
             */

            int[][] P = Strassens(addMatrix(A11,A22), addMatrix(B11,B22));
            int[][] Q = Strassens(addMatrix(A21,A22), B11);
            int[][] R = Strassens(A11, subtractMatrix(B12,B22));
            int[][] S = Strassens(A22, subtractMatrix(B21,B11));
            int[][] T = Strassens(addMatrix(A11,A12), B22);
            int[][] U = Strassens(subtractMatrix(A21,A11),addMatrix(B11,B12));
            int[][] V = Strassens(subtractMatrix(A12,A22),addMatrix(B21,B22));

            /*
             C11 = P + S - T + V
             C12 = R + T
             C21 = Q + S
             C22 = P + R - Q + U
             */

            int[][] C11 = addMatrix(subtractMatrix(addMatrix(P,S),T),V);
            int[][] C12 = addMatrix(R,T);
            int[][] C21 = addMatrix(Q,S);
            int[][] C22 = addMatrix(subtractMatrix(addMatrix(P,R),Q),U);

            mergeMatrix(C, C11, 0, 0);
            mergeMatrix(C, C12, 0, n/2);
            mergeMatrix(C, C21, n/2, 0);
            mergeMatrix(C, C22, n/2, n/2);
        }
        return C;
    }
}
