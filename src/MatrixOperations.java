
public class MatrixOperations {

    public static double[][] sum(double[][] matrix1, double[][] matrix2) {
        if (matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length) {
            throw new IllegalArgumentException("Matrices should have the same size.");
        }

        double[][] result = new double[matrix1.length][matrix1[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[i].length; j++) {
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }

        return result;
    }

    public static double[][] scalarMultiply(double[][] matrix, double scalar) {
        double[][] result = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result[i][j] = matrix[i][j] * scalar;
            }
        }

        return result;
    }

    public static double[][] multiplyMatrices(double[][] matrix1, double[][] matrix2) {
        if (matrix2.length != matrix1[0].length) {
            throw new IllegalArgumentException("Second's rows must be the same as first's columns.");
        }

        double[][] result = new double[matrix1.length][matrix2[0].length];

        for (int row = 0; row < result.length; row++) {
            for (int column = 0; column < result[row].length; column++) {
                for (int from = 0; from < matrix2.length; from++) {
                    result[row][column] += matrix1[row][from] * matrix2[from][column];
                }
            }
        }

        return result;
    }

    public static double[][] mainDiagonal(double[][] matrix) {
        double[][] result = new double[matrix[0].length][matrix.length];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = matrix[j][i];
            }
        }
        return result;
    }

    public static double[][] sideDiagonal(double[][] matrix) {
        double[][] result = new double[matrix[0].length][matrix.length];
        int inputMatrixRows = matrix.length;
        int inputMatrixColumns = matrix[0].length;

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = matrix[inputMatrixColumns - 1 - j][inputMatrixRows - 1 - i];
            }
        }
        return result;
    }

    public static double[][] verticalLine(double[][] matrix) {
        double[][] result = new double[matrix[0].length][matrix.length];
        int inputMatrixColumns = matrix[0].length;

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = matrix[i][inputMatrixColumns - 1 - j];
            }
        }
        return result;
    }

    public static double[][] horizontalLine(double[][] matrix) {
        double[][] result = new double[matrix[0].length][matrix.length];
        int inputMatrixRows = matrix.length;

        for (int i = 0; i < result.length; i++) {
            System.arraycopy(matrix[inputMatrixRows - 1 - i], 0, result[i], 0, result[0].length);
        }
        return result;
    }

    public static double determinant(double[][] matrix) {
        double[][] subMatrix;
        double result = 0;
        if (matrix.length == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        } else {
            for (int i = 0; i < matrix.length; i++) {
                subMatrix = getSubMatrix(matrix, 0, i);
                result = result + determinant(subMatrix) * Math.pow(-1, 2 + i) * matrix[0][i];
            }
            return result;
        }
    }

    public static double[][] getSubMatrix(double[][] matrix, int row, int column) {
        int newLength = matrix.length - 1;
        double[][] subMatrix = new double[newLength][newLength];

        for (int i = 0; i < subMatrix.length; i++) {
            for (int j = 0; j < subMatrix.length; j++) {
                subMatrix[i][j] = matrix[i >= row ? i + 1 : i][j >= column ? j + 1 : j];
            }
        }

        return subMatrix;
    }

    public static double[][] inverseMatrix(double[][] matrix, double det) {
        double[][] result = new double[matrix.length][matrix.length];
        if (matrix.length == 2) {
            result[0][0] = matrix[1][1];
            result[0][1] = -matrix[0][1];
            result[1][0] = -matrix[1][0];
            result[1][1] = matrix[0][0];

            result = scalarMultiply(result, Math.pow(det, -1));
        } else {

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    result[i][j] = determinant(getSubMatrix(matrix, i, j)) * Math.pow(-1, 2 + i + j);
                }
            }

            result = mainDiagonal(result);

            result = scalarMultiply(result, Math.pow(det, -1));
        }
        return result;
    }
}
