package main.java.ru.sgu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class C {
    public void start(String[] args) {
        // Укажите путь к вашему файлу
        String cons = "parser.txt";
        File file = new File(cons);

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String word = scanner.next();
                if (isWord(word)) {
                    System.out.print(word + " ");
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден: " + e.getMessage());
        }
    }

    public boolean isWord(String word) {
        // Проверяем, что каждый символ в слове - это русская буква
        for (char ch : word.toCharArray()) {
            if (!((ch >= 'а' && ch <= 'я') || (ch >= 'А' && ch <= 'Я') || ch == 'ё' || ch == 'Ё')) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        C instance = new C();
        instance.start(args);
    }
}
