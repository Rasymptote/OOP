package ru.nsu.babich;

/**
 * Represents a dynamic matrix of integers.
 */
public class Matrix {
    private int rows;
    private int cols;
    private int[][] data;

    /**
     * Constructs a matrix with specified dimensions.
     *
     * @param rows Number of rows.
     * @param cols Number of columns.
     */
    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new int[rows][cols];
    }

    /**
     * Constructs an empty matrix.
     */
    public Matrix() {
        this(0, 0);
    }

    public int getWidth() {
        return this.cols;
    }

    public int getHeight() {
        return this.rows;
    }

    public int get(int row, int col) {
        return data[row][col];
    }

    public void set(int row, int col, int value) {
        data[row][col] = value;
    }

    public void addRow() {
        int[][] newData = new int[rows + 1][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(data[i], 0, newData[i], 0, cols);
        }
        data = newData;
        rows++;
    }

    public void removeRow(int rowIndex) {
        int[][] newData = new int[rows - 1][cols];
        for (int i = 0, newRow = 0; i < rows; i++) {
            if (i != rowIndex) {
                System.arraycopy(data[i], 0, newData[newRow], 0, cols);
                newRow++;
            }
        }
        data = newData;
        rows--;
    }

    public void addCol() {
        int[][] newData = new int[rows][cols + 1];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(data[i], 0, newData[i], 0, cols);
        }
        data = newData;
        cols++;
    }

    public void removeCol(int colIndex) {
        int[][] newData = new int[rows][cols - 1];
        for (int i = 0; i < rows; i++) {
            for (int j = 0, newCol = 0; j < cols; j++) {
                if (j != colIndex) {
                    newData[i][newCol] = data[i][j];
                    newCol++;
                }
            }
        }
        data = newData;
        cols--;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sb.append(String.format("%s ", data[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
