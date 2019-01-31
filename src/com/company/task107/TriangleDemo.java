package com.company.task107;

public class TriangleDemo {
    public static void main(String[] args) {
        Triangle[] triangles = {
                new Triangle(4, 5, 6),
                new Triangle(13, 14, 15),
                new Triangle(3, 4, 5),
                new Triangle(4, 6, 8),
                new Triangle(8, 5, 6)
        };
        java.util.Arrays.sort(triangles, new TriangleSquareComparator());
        System.out.println(java.util.Arrays.toString(triangles));
        java.util.Arrays.sort(triangles, new TrianglePerimeterComparator());
        System.out.println(java.util.Arrays.toString(triangles));
    }
}
