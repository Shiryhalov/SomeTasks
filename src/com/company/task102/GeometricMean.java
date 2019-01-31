package com.company.task102;

import com.company.MethodsForWork;

public class GeometricMean {
    public static void main(String[] args) {
        double firstNum, secondNum, result;
        System.out.println("Введите первое число: ");
        firstNum = MethodsForWork.checkDouble();
        System.out.println("Введите второе число: ");
        secondNum = MethodsForWork.checkDouble();
        result = Math.sqrt(firstNum * secondNum);
        System.out.println("Среднее геометрическое = " + result);
    }
}
