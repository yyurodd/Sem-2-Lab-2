package lab2;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;
import java.util.Random;


public class Main {

    public static void fillRandNums(LinkedList<Integer> list){
        Scanner scanner = new Scanner(System.in);

        Random rand = new Random();
        System.out.println("Введите количество элементов в списке, которые будут случайными числами от 0 до 99: ");
        int num = scanner.nextInt();
        for (int i = 0; i < num; i++){
            list.add(rand.nextInt(100));
        }
        for (int x : list){
            System.out.print(x + " ");
        }
    }

    public static void fillInsertedNums(LinkedList<Integer> list){
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                        \nВведите новые элементы в список.
                        Чтобы прекратить ввод введите "stop"
                        """);
        String input = scanner.nextLine();
        while (!input.equals("stop")){
            try {
                int num = Integer.parseInt(input);
                list.add(num);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введено не целое число");
            }
            input = scanner.nextLine();
        }

    }

    public static void measureTime(LinkedList<Integer> list){
        long start = System.currentTimeMillis();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedList<Integer> List = new LinkedList<Integer>();
        fillRandNums(List);
        fillInsertedNums(List);
        for (int x : List){
            System.out.print(x + " ");
        }


    }
}
