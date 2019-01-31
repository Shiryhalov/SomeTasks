package com.company.task106;

public class Solver {
    public static double solve(double infinum, double supremum, double eps, Function func) {
        double min = supremum;
        double x = infinum;
        for (double i = infinum; i < supremum + eps; i += eps) {
            if (min > func.f(x)) {
                min = func.f(x);
                x += eps;
            }
        }
        return min;
    }
}
