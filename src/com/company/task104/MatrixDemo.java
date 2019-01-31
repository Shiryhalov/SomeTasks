package com.company.task104;

public class MatrixDemo {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(new int[][]{{1, 2, 3}, {4, 5, 6}});
        Matrix matrix2 = new Matrix(new int[][]{{2, 1, 4}, {3, 6, 10}});
        Matrix matrix3 = new Matrix();

        System.out.println("Количество строк: " + matrix1.getRows());
        System.out.println("Количество столбцов: " + matrix1.getColumns());

        matrix3.setArray(new int[][]{{1, 2}, {4, 6}, {2, 3}});
        System.out.println("Количество строк: " + matrix3.getRows());
        System.out.println("Количество столбцов: " + matrix3.getColumns());

        matrix1.print();
        System.out.println("*");
        matrix3.print();
        System.out.println("=");
        matrix1.multMatrix(matrix3).print();
        System.out.println("---------------Проверка на размерность---------------");
        matrix3 = matrix2.clone();
        System.out.println("Количество строк: " + matrix1.getRows());
        System.out.println("Количество столбцов: " + matrix1.getColumns());
        System.out.println("Количество строк: " + matrix3.getRows());
        System.out.println("Количество столбцов: " + matrix3.getColumns());
        matrix1.print();
        System.out.println("*");
        matrix3.print();
        System.out.println("=");
        matrix1.multMatrix(matrix2).print();

    }
}
