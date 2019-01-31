package com.company;

import java.util.Scanner;

public class MethodsForWork {
    private static Scanner sc = new Scanner(System.in);

    public static int checkInt() {
        int i = 0, b = 0;
        while (i == 0) {
            if (sc.hasNextInt()) {
                b = sc.nextInt();
                i++;
            } else {
                System.out.println("Введите целое число");
            }
        }
        return b;
    }

    public static double checkDouble() {
        double i = 0, b = 0;
        while (i == 0) {
            Scanner sc = new Scanner(System.in);
            if (sc.hasNextDouble()) {
                b = sc.nextDouble();
                i++;
            } else {
                System.out.println("Введите вещественное число");
            }
        }
        return b;
    }
}
