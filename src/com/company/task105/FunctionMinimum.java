package com.company.task105;

public class FunctionMinimum extends AbstractFunctionMinimum {
    @Override
    public double f(double x) {
        return Math.pow(x, 2) - 4 * x + 2;
    }

    public static void main(String[] args) {
        FunctionMinimum functionMinimum = new FunctionMinimum();
        System.out.println("Function minimum = " +
                functionMinimum.findFunctionMinimum(1, 1.5, 0.000001));
    }
}
