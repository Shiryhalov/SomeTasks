package com.company.task105;

public abstract class AbstractFunctionMinimum {

    public abstract double f(double x);

    public double findFunctionMinimum(double infinum, double supremum, double eps) {
        double min = supremum;
        double x = infinum;
        for (double i = infinum; i < supremum + eps; i += eps) {
            if (min > f(x)) {
                min = f(x);
                x += eps;
            }
        }
        return min;
    }
}
