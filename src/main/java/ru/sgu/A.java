package main.java.ru.sgu;

public class A {
    private static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                synchronized (A.class) {
                    counter++;
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                synchronized (A.class) {
                    counter++;
                }
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println("Final Counter Value: " + counter);
    }
}
