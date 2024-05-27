package main.java.ru.sgu;

import java.util.*;
import java.util.stream.Collectors;

public class A {
    public static void main(String[] args) {
        List<String> input = Arrays.asList(
                "Ионов Кирилл Игоревич Nvidia 10",
                "Петров Петр Иванович Apple 9",
                "Петров Петр Апетрович Apple 9",
                "Шульдяков Александр Андреевич Telegram 5"
        );
        List<String> sortedOutput = input.stream()
                .distinct()
                .sorted((line1, line2) -> {
                    String[] parts1 = line1.split(" ");
                    String[] parts2 = line2.split(" ");
                    int rating1 = Integer.parseInt(parts1[4]);
                    int rating2 = Integer.parseInt(parts2[4]);

                    if (rating1 != rating2) {
                        return Integer.compare(rating2, rating1);
                    }

                    int lastNameComparison = parts1[0].compareTo(parts2[0]);
                    if (lastNameComparison != 0) {
                        return lastNameComparison;
                    }

                    int firstNameComparison = parts2[1].compareTo(parts1[1]);
                    if (firstNameComparison != 0) {
                        return firstNameComparison;
                    }

                    return parts1[2].compareTo(parts2[2]);
                })
                .toList();

        sortedOutput.forEach(System.out::println);
    }
}