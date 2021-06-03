
import java.util.Scanner;

public class UserInterFace {

    private final Scanner scanner;

    public UserInterFace(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() {
        int choice;

        while (true) {
            printMenu();

            System.out.print("Your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    sum();
                    break;
                case 2:
                    scalarMultiply();
                    break;
                case 3:
                    multiplyMatrices();
                    break;
                case 4:
                    transposeMatrix();
                    break;
                case 5:
                    determinant();
                    break;
                case 6:
                    inverseMatrix();
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }

    private void inverseMatrix() {
        double[][] matrix = readOneMatrix();

        double[][] result;

        double det = MatrixOperations.determinant(matrix);
        if (det == 0) {
            System.out.println("This matrix doesn't have an inverse.");
        } else {
            result = MatrixOperations.inverseMatrix(matrix, det);
            System.out.println("The result is: ");
            printMatrix(result);
        }
    }

    private void determinant() {
        double[][] matrix = readOneMatrix();

        System.out.println("The result is: ");
        System.out.println(MatrixOperations.determinant(matrix));
    }

    private void printMenu() {
        System.out.println();
        System.out.println("1. Add matrices");
        System.out.println("2. Multiply matrix by a constant");
        System.out.println("3. Multiply matrices");
        System.out.println("4. Transpose matrix");
        System.out.println("5. Calculate a determinant");
        System.out.println("6. Inverse matrix");
        System.out.println("0. Exit");
    }

    private void sum() {
        TwoDoubleMatrices matrices = readTwoMatrices();

        double[][] result;

        try {
            result = MatrixOperations.sum(matrices.getFirstMatrix(), matrices.getSecondMatrix());
        } catch (IllegalArgumentException e) {
            System.out.println("The operation cannot be performed");
            return;
        }

        System.out.println("The result is: ");
        printMatrix(result);
    }

    private void scalarMultiply() {
        double[][] matrix = readOneMatrix();

        System.out.print("Enter constant: ");
        double toMultiply = scanner.nextDouble();

        System.out.println("The result is: ");
        printMatrix(MatrixOperations.scalarMultiply(matrix, toMultiply));
    }

    private void multiplyMatrices() {
        TwoDoubleMatrices matrices = readTwoMatrices();

        double[][] result;

        try {
            result = MatrixOperations.multiplyMatrices(matrices.getFirstMatrix(), matrices.getSecondMatrix());
        } catch (IllegalArgumentException exception) {
            System.out.println("The operation cannot be performed.");
            return;
        }

        System.out.println("The result is:");
        printMatrix(result);
    }

    private double[][] readOneMatrix() {
        double[][] matrix;

        System.out.println("Enter size of the matrix: ");
        int rows = scanner.nextInt();
        int columns = scanner.nextInt();
        System.out.println("Enter matrix");
        matrix = readMatrix(rows, columns);

        return matrix;
    }

    private TwoDoubleMatrices readTwoMatrices() {
        TwoDoubleMatrices twoDoubleMatrices = new TwoDoubleMatrices();

        System.out.print("Enter the size of the first matrix: ");
        int rows = scanner.nextInt();
        int columns = scanner.nextInt();
        System.out.println("Enter first matrix: ");
        twoDoubleMatrices.setFirstMatrix(readMatrix(rows, columns));

        System.out.print("Enter the size of the second matrix: ");
        rows = scanner.nextInt();
        columns = scanner.nextInt();
        System.out.println("Enter second matrix: ");
        twoDoubleMatrices.setSecondMatrix(readMatrix(rows, columns));

        return twoDoubleMatrices;
    }

    private double[][] readMatrix(int rows, int columns) {
        double[][] toReturn = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                toReturn[i][j] = scanner.nextDouble();
            }
        }

        return toReturn;
    }

    private void printMatrix(double[][] matrix) {
        boolean isDouble = isMatrixDouble(matrix);

        for (double[] row : matrix) {
            for (double element : row) {
//                if (isDouble) {
//                    System.out.printf("%.2f ", element);
//                } else {
//                    System.out.printf("%.0f ", element);
//                }
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    private boolean isMatrixDouble(double[][] matrix) {
        for (double[] tensor : matrix) {
            for (double element : tensor) {
                if (element != (int) element) {
                    return true;
                }
            }
        }

        return false;
    }

    private void transposeMatrix() {
        System.out.println("1. Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");

        System.out.print("Your choice: ");

        int option = scanner.nextInt();

        switch (option) {
            case 1:
                mainDiagonal();
                break;
            case 2:
                sideDiagonal();
                break;
            case 3:
                verticalLine();
                break;
            case 4:
                horizontalLine();
                break;
        }
    }

    private void mainDiagonal() {
        double[][] matrix = readOneMatrix();

        System.out.println("The result is: ");
        printMatrix(MatrixOperations.mainDiagonal(matrix));
    }

    private void sideDiagonal() {
        double[][] matrix = readOneMatrix();

        System.out.println("The result is: ");
        printMatrix(MatrixOperations.sideDiagonal(matrix));
    }

    private void verticalLine() {
        double[][] matrix = readOneMatrix();

        System.out.println("The result is: ");
        printMatrix(MatrixOperations.verticalLine(matrix));
    }

    private void horizontalLine() {
        double[][] matrix = readOneMatrix();

        System.out.println("The result is: ");
        printMatrix(MatrixOperations.horizontalLine(matrix));
    }
}
