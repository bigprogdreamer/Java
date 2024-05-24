package main.java.ru.sgu;

import java.util.Scanner;
public class B {
    // Перечисление для дней недели
    enum DayOfWeek {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

        public DayOfWeek getNextDay() {
            DayOfWeek[] days = DayOfWeek.values();
            int currentIndex = this.ordinal();
            int nextIndex = (currentIndex + 1) % days.length;
            return days[nextIndex];
        }
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите день недели (например, 'sunday'): ");
        String inputDay = scanner.nextLine().toUpperCase();

        System.out.print("Введите количество дней: ");
        int numberOfDays = scanner.nextInt();
        scanner.nextLine();

        DayOfWeek startDay;
        try {
            startDay = DayOfWeek.valueOf(inputDay);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input for day of week.");
            return;
        }

        DayOfWeek endDay = calculateEndDay(startDay, numberOfDays);

        System.out.println("Через " + numberOfDays + " дней будет " + endDay);

        scanner.close();
    }

    private DayOfWeek calculateEndDay(DayOfWeek startDay, int numberOfDays) {
        DayOfWeek endDay = startDay;
        for (int i = 0; i < numberOfDays; i++) {
            endDay = endDay.getNextDay();
        }
        return endDay;
    }
    public static void main(String[] args) {
        B instance = new B();
        instance.start();
    }
}

