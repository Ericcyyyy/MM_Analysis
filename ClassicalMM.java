public class ClassicalMM {

        static int[][] multiplyMatrix(int row1, int column1, int[][] A,
                                      int row2, int column2, int[][] B){
            int[][] C = new int[row1][column2];

            for(int i = 0; i < row1; i++) {
                for (int j = 0; j < column1; j++) {
                    for (int k = 0; k < row2; k++)
                        C[i][j] += A[i][k] * B[k][j];
                }
            }
            return C;
        }
}
