package com.company.task106;

public class InterfaceDemo {
    public static void main(String[] args) {
        System.out.println("Явный класс: " + Solver.solve(1, 1.5, 0.000001, new MyFunction()));
        System.out.println("Безымянный класс: " + Solver.solve(1, 1.5, 0.000001, new Function() {
            public double f(double x) {
                return Math.pow(x, 2) - 4 * x + 2;
            }
        }));
        System.out.println("Лямбда-выражение: " +
                Solver.solve(1, 1.5, 0.000001, x -> Math.pow(x, 2) - 4 * x + 2));
    }
}
