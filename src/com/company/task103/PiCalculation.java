package com.company.task103;

import com.company.MethodsForWork;

public class PiCalculation {
    public static void main(String[] args) {
        System.out.println("Введите eps: ");
        double eps = MethodsForWork.checkDouble();
        System.out.println("Цикл: Pi = " + piCalculationCycle(eps));
        double result = 3 + piCalculationRecursion(eps);
        System.out.println("Рекурсия: Pi = " + result);
    }

    private static double piCalculationCycle(double eps) {
        double sum = 0;
        double k = 1;
        double z = 3;
        double i = 0;
        while (Math.abs(z) > eps) {
            sum += z;
            i += 2;
            z = (4 * k) / (i * (i + 1) * (i + 2));
            k = -k;
        }
        return sum;
    }

    private static double piCalculationRecursion(double eps, int n) {
        double z = 4.0 / (n * (n + 1) * (n + 2));
        if (z < eps) {
            return z;
        }
        return z - piCalculationRecursion(eps, n + 2);
    }

    private static double piCalculationRecursion(double eps) {
        return piCalculationRecursion(eps, 2);
    }
}
