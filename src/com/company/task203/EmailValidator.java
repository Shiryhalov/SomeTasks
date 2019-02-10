package com.company.task203;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите email-адрес: ");
        String email = scanner.nextLine();
        if (emailValidator(email)) {
            System.out.printf("%n%s - email введен верно.", email);
        } else {
            System.out.printf("%n%s - email введен неверно.", email);
        }
    }

    private static boolean emailValidator(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z]+[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\." +
                "[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
