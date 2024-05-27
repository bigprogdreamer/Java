package main.java.ru.sgu;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.IntStream;

public class B {

    public void start() {

        int[] numbers = {5, 12, 8, 20, 3, 15, 7, 3, 8, 1, 2, 12, 20};

        Optional<Integer> secondLargest = findSecondLargest(numbers);
        Optional<Integer> thirdSmallest = findThirdSmallest(numbers);

        secondLargest.ifPresent(value -> System.out.println("Второе наибольшее число: " + value));
        thirdSmallest.ifPresent(value -> System.out.println("Третье наименьшее число: " + value));
    }

    public Optional<Integer> findSecondLargest(int[] numbers) {
        return Arrays.stream(numbers)
                .boxed()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst();
    }

    public Optional<Integer> findThirdSmallest(int[] numbers) {
        return Arrays.stream(numbers)
                .boxed()
                .distinct()
                .sorted()
                .skip(2)
                .findFirst();
    }
    public static void main(String[] args) {
        B instance = new B();
        instance.start();
    }
}
