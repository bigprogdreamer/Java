package main.java.ru.sgu;

import java.math.RoundingMode;
import java.util.Scanner;
import java.math.BigInteger;
import java.math.BigDecimal;
public class B {
    public void summer(){
        Scanner in = new Scanner(System.in);
        BigDecimal first;
        BigDecimal second;
        String message =                 """
                        Выберите операцию для исполнения
                        ADD - Сложение
                        SUB - Вычитание,
                        MULT - Умножение,
                        DIV - Деление,
                        REM - Остаток после деления
                        POW - Возведение в степень.""";

        BigDecimal result = BigDecimal.ZERO;
        int scale = 10;
        RoundingMode roundingMode = RoundingMode.HALF_UP;
        String operation;
        boolean check = true;
        try {
            while (check) {
                System.out.print("Введите 1 число: ");
                first = in.nextBigDecimal();
                System.out.print("Введите 2 число: ");
                second = in.nextBigDecimal();
                in.nextLine();
                System.out.println(message + "\nВведите операцию: ");
                operation = in.nextLine();
                switch (operation) {
                    case ("ADD"):
                        result = first.add(second);
                        check = false;
                        break;
                    case ("SUB"):
                        result = first.subtract(second);
                        check = false;
                        break;
                    case ("MULT"):
                        result = first.multiply(second);
                        check = false;
                        break;
                    case ("DIV"):
                        result = first.divide(second, scale, roundingMode);
                        check = false;
                        break;
                    case ("REM"):
                        result = first.remainder(second);
                        check = false;
                        break;
                    case ("POW"):
                        if ((second.compareTo(BigDecimal.valueOf((Integer.MAX_VALUE))) > 0) ||
                                second.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) != 0){
                            System.out.println("Операция pow с этим 2 числом не поддерживается");
                            continue;
                        }
                        result = first.pow(second.intValue());
                        check = false;
                        break;
                    default:
                        System.out.println("Операция введена некорректно, попробуйте еще раз");
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage()); }

        System.out.println(result);


    }

    public void start(String[]args){
        summer();
    }
    public static void main(String[] args) {
        B instance = new B();
        instance.start(args);
    }
}
