
public class TwoDoubleMatrices {
    private double[][] firstMatrix;
    private double[][] secondMatrix;

    public TwoDoubleMatrices() {

    }

    public TwoDoubleMatrices(double[][] firstMatrix, double[][] secondMatrix) {
        this.firstMatrix = firstMatrix;
        this.secondMatrix = secondMatrix;
    }

    public double[][] getFirstMatrix() {
        return firstMatrix;
    }

    public void setFirstMatrix(double[][] firstMatrix) {
        this.firstMatrix = firstMatrix;
    }

    public double[][] getSecondMatrix() {
        return secondMatrix;
    }

    public void setSecondMatrix(double[][] secondMatrix) {
        this.secondMatrix = secondMatrix;
    }
}
