package main.java.ru.sgu;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FibonacciCalculator extends RecursiveTask<Long> {
    private final int n;

    public FibonacciCalculator(int n) {
        this.n = n;
    }

    @Override
    protected Long compute() {
        if (n <= 1) {
            return (long) n;
        } else {
            FibonacciCalculator f1 = new FibonacciCalculator(n - 1);
            f1.fork();
            FibonacciCalculator f2 = new FibonacciCalculator(n - 2);
            return f2.compute() + f1.join();
        }
    }

    public static void main(String[] args) {
        int n = 10;
        ForkJoinPool pool = new ForkJoinPool();
        long result = pool.invoke(new FibonacciCalculator(n));
        System.out.println("Fibonacci number at position " + n + ": " + result);
    }
}
