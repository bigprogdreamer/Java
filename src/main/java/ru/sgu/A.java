package main.java.ru.sgu;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class A {

    public static void main(String[] args) {

        Stream<Integer> numberStream = Stream.of(5, 12, 8, 20, 3, 15, 7);

        Predicate<Integer> filterCondition = num -> num > 6;

        List<Integer> filteredNumbers = filterStream(numberStream, filterCondition);

        filteredNumbers.forEach(System.out::println);
    }

    public static List<Integer> filterStream(Stream<Integer> stream, Predicate<Integer> condition) {
        return stream.filter(condition)
                .collect(Collectors.toList());
    }
}