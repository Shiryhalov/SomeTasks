package com.company.task104;

import java.util.Arrays;
import java.util.Objects;

public class Matrix implements Cloneable {
    private int[][] array;
    private int rows;
    private int columns;

    public Matrix(int[][] array) {
        setArray(array);
    }

    public Matrix() {
    }

    public int[][] getArray() {
        return array.clone();
    }

    public void setArray(int[][] array) {
        this.array = array.clone();
        this.rows = array.length;
        this.columns = array[0].length;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public void print() {
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                System.out.print(this.getArray()[i][j] + " ");
            }
            System.out.println();
        }
    }

    public Matrix multMatrix(Matrix secMatrix) {
        if (getRows() == secMatrix.getColumns() && getColumns() == secMatrix.getRows()) {
            int[][] secArray = secMatrix.getArray();
            int[][] compArray = new int[array.length][secArray[0].length];
            Matrix comp = new Matrix(new int[array.length][secArray[0].length]);
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < secArray[0].length; j++) {
                    for (int k = 0; k < secArray.length; k++) {
                        compArray[i][j] += array[i][k] * secArray[k][j];
                    }
                }
            }
            comp.setArray(compArray);
            return comp;
        } else {
            System.out.println("Размерности матриц не совпадают с требуемыми для их перемножения");
            return this;
        }
    }

    @Override
    public Matrix clone() {
        try {
            return (Matrix) super.clone();
        } catch (CloneNotSupportedException e) {
            e.getMessage();
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix = (Matrix) o;
        return rows == matrix.rows &&
                columns == matrix.columns &&
                Arrays.equals(array, matrix.array);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(rows, columns);
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }

    @Override
    public String toString() {
        return "Matrix{" +
                "array=" + Arrays.toString(array) +
                ", rows=" + rows +
                ", columns=" + columns +
                '}';
    }
}
