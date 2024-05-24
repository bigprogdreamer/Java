package main.java.ru.sgu;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class A {
    public static void main(String[]args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите первую дату в формате год месяц день: ");
            int year1 = scanner.nextInt();
            int month1 = scanner.nextInt();
            int day1 = scanner.nextInt();

            System.out.println("Введите вторую дату в формате год месяц день: ");
            int year2 = scanner.nextInt();
            int month2 = scanner.nextInt();
            int day2 = scanner.nextInt();

            LocalDate date1 = LocalDate.of(year1, month1, day1);
            LocalDate date2 = LocalDate.of(year2, month2, day2);

            long daysBetween = ChronoUnit.DAYS.between(date1, date2);
            System.out.println("Число дней между минимальной и максимальной датой: " + Math.abs(daysBetween));
            scanner.close();
    }
}
