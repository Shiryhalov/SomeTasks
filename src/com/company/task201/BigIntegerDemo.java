package com.company.task201;

import com.company.MethodsForWork;

import java.math.BigInteger;
import java.util.Random;

public class BigIntegerDemo {
    public static void main(String[] args) {
        System.out.println("Enter integer pow: ");
        int power = MethodsForWork.checkInt();
        BigInteger number1 = new BigInteger(100, new Random());
        BigInteger result = number1.pow(power);
        System.out.println(number1 + " (pow) = " + result);
        result = number1;
        for (int i = 0; i < power - 1; i++) {
            result = result.multiply(number1);
        }
        System.out.println(number1 + " (multiply)  = " + result);
    }
}
