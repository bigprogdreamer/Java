package main.java.ru.sgu;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class C{
    static class FoodItem {
        String name;
        int calories;

        public FoodItem(String name, int calories) {
            this.name = name;
            this.calories = calories;
        }
    }

    public static void main(String[] args) {
        BlockingQueue<FoodItem> queue = new ArrayBlockingQueue<>(10);

        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    FoodItem food = new FoodItem("Food" + i, i * 100);
                    queue.put(food);
                    System.out.println("Produced: " + food.name + " (" + food.calories + " calories)");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer1 = new Thread(() -> {
            int totalCalories = 0;
            try {
                while (true) {
                    FoodItem food = queue.take();
                    totalCalories += food.calories;
                    System.out.println("Consumer 1 ate: " + food.name + " (" + food.calories + " calories), Total Calories: " + totalCalories);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer2 = new Thread(() -> {
            int totalCalories = 0;
            try {
                while (true) {
                    FoodItem food = queue.take();
                    totalCalories += food.calories;
                    System.out.println("Consumer 2 ate: " + food.name + " (" + food.calories + " calories), Total Calories: " + totalCalories);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer1.start();
        consumer2.start();

        try {
            producer.join();
            consumer1.join();
            consumer2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
