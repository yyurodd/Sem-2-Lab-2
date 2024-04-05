package lab2;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


class Node {
    int data;
    Node prev;
    Node next;

    Node(int data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }

}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DoublyLinkedList list = new DoublyLinkedList();
        DynamicArray arr = new DynamicArray();
        int number;
        while (true) {
            System.out.println("""
                             Меню                \s
                    -----------------------------------------------------------------------------------------------------------------
                            Двусвязный список
                    -----------------------------------------------------------------------------------------------------------------
                    1 - Введите количество элементов в списке, который будет автоматически заполняться случайными числами (0 до 99).                 \s
                    2 - Ввести элементы в список вручную.(Введите "stop" для остановки ввода)                    \s
                    3 - Вставка, удаление, обмена и получение элемента двусвязного списка.
                    4 - Вывод списка на экран.
                    5 - Очистить список.
                    -----------------------------------------------------------------------------------------------------------------
                            Динамический массив
                    -----------------------------------------------------------------------------------------------------------------        
                    6 - Введите количество элементов в массиве, который будет автоматически заполняться случайными числами (0 до 99).                 \s
                    7 - Ввести элементы в массив вручную.(Введите "stop" для остановки ввода)                    \s
                    8 - Вставка, удаление, обмен и получение элемента динамического массива.
                    9 - Вывод массива на экран.
                    10 - Очистить массив.
                    0 - Завершить программу.
                    """);
            number = scanner.nextInt();
            if (number == 0) {
                break;
            }
            switch (number) {
                case 1:
                    list.fillListWithRandNumbs(list);
                    break;
                case 2:
                    System.out.println("""
                            Куда вы хотите добавлять каждый последующий элемент?
                            1 - в начало списка
                            2 - в конец списка
                            """);
                    int choice = scanner.nextInt();

                    DynamicArray times = new DynamicArray();

                    while (true) {
                        String input = scanner.next();
                        if (input.equals("stop")) {
                            break;
                        }

                        try {
                            int num = Integer.parseInt(input);
                            long start, end;
                            if (choice == 1) {
                                start = System.nanoTime();
                                list.insertFirst(num);
                                end = System.nanoTime();
                            } else {
                                start = System.nanoTime();
                                list.insertLast(num);
                                end = System.nanoTime();
                            }
                            long timeInNanos = end - start;
                            times.insertLast((int) timeInNanos);
                        } catch (NumberFormatException e) {
                            System.out.println("Ошибка: введено не целое число");
                        }
                    }

                    long sum = 0;
                    for (int i = 0; i < times.size; i++) {
                        long time = (long) times.getElement(i);
                        sum += time;
                    }

                    long average = sum / times.size;
                    System.out.println("Среднее время добавления элемента: " + average/1000000.0 + " миллисекунд");
                    break;
                case 3:
                    System.out.println("""
                            Какую операцию вы хотите выполнить?
                            1 - Вставка элемента в любую позицию.
                            2 - Удаление элемента по индексу.
                            3 - Удаление первого вхождения элемента по значению.
                            4 - Удаление всех элементов по значению.
                            5 - Обмен двух элементов по их индексам.
                            6 - Получение элемента по индексу.
                            7 - Получение индекса элемента по его значению(только первое вхождение).
                            8 - Получение индексов элементов по значению(все вхождения).
                            0 - Отмена выполнения.
                            """);
                    choice = scanner.nextInt();
                    switch (choice) {
                        case 0:
                            break;
                        case 1:
                            System.out.println("Введите индекс, куда вы хотите вставить элемент: ");
                            int index = scanner.nextInt();
                            System.out.println("Введите значение элемента: ");
                            int data = scanner.nextInt();
                            list.insertAnyPos(data, index);
                            break;
                        case 2:
                            System.out.println("Введите индекс по которому вы хотите удалить элемент: ");
                            index = scanner.nextInt();
                            list.removeByIndex(index);
                            break;
                        case 3:
                            System.out.println("Введите значение: ");
                            int value = scanner.nextInt();
                            list.removeFirstByValue(value);
                            break;
                        case 4:
                            System.out.println("Введите значение: ");
                            value = scanner.nextInt();
                            list.removeByValue(value);
                            break;
                        case 5:
                            System.out.println("Введите индексы элементов: ");
                            int index1 = scanner.nextInt();
                            int index2 = scanner.nextInt();
                            list.swapByIndex(index1, index2);
                            break;
                        case 6:
                            System.out.println("Введите индекс элемента: ");
                            index = scanner.nextInt();
                            list.getElementByIndex(index);
                            break;
                        case 7:
                            System.out.println("Введите значение: ");
                            value = scanner.nextInt();
                            list.getFirstIndexByValue(value);
                            break;
                        case 8:
                            System.out.println("Введите значение: ");
                            value = scanner.nextInt();
                            list.getIndexesByValue(value);
                            break;
                    }
                break;
                case 4:
                    list.display();
                    break;
                case 5:
                    list.clearAll();
                    break;
                case 6:
                    arr.fillWithRandNumbs(arr);
                    break;
                case 7:
                    System.out.println("""
                            Куда вы хотите добавлять каждый последующий элемент?
                            1 - в начало списка
                            2 - в конец списка
                            """);
                    choice = scanner.nextInt();

                    DynamicArray timesArr = new DynamicArray();

                    while (true) {
                        String input = scanner.next();
                        if (input.equals("stop")) {
                            break;
                        }

                        try {
                            int num = Integer.parseInt(input);
                            long start, end;
                            if (choice == 1) {
                                start = System.nanoTime();
                                arr.insertFirst(num);
                                end = System.nanoTime();
                            } else {
                                start = System.nanoTime();
                                arr.insertLast(num);
                                end = System.nanoTime();
                            }
                            long timeInNanos = end - start;
                            timesArr.insertLast((int) timeInNanos);
                        } catch (NumberFormatException e) {
                            System.out.println("Ошибка: введено не целое число");
                        }
                    }

                    sum = 0;
                    for (int i = 0; i < timesArr.size; i++) {
                        long time = (long) timesArr.getElement(i);
                        sum += time;
                    }

                    average = sum / timesArr.size;
                    System.out.println("Среднее время добавления элемента: " + average/1000000.0 + " миллисекунд");
                    break;
                case 8:
                    System.out.println("""
                            Какую операцию вы хотите выполнить?
                            1 - Вставка элемента в любую позицию.
                            2 - Удаление элемента по индексу.
                            3 - Удаление первого вхождения элемента по значению.
                            4 - Удаление всех элементов по значению.
                            5 - Обмен двух элементов по их индексам.
                            6 - Получение элемента по индексу.
                            7 - Получение индекса элемента по его значению(только первое вхождение).
                            8 - Получение индексов элементов по значению(все вхождения).
                            0 - Отмена выполнения.
                            """);
                    choice = scanner.nextInt();
                    switch (choice) {
                        case 0:
                            break;
                        case 1:
                            System.out.println("Введите индекс, куда вы хотите вставить элемент: ");
                            int index = scanner.nextInt();
                            System.out.println("Введите значение элемента: ");
                            int data = scanner.nextInt();
                            arr.insertAnyPos(data, index);
                            break;
                        case 2:
                            System.out.println("Введите индекс по которому вы хотите удалить элемент: ");
                            index = scanner.nextInt();
                            arr.removeByIndex(index);
                            break;
                        case 3:
                            System.out.println("Введите значение: ");
                            int value = scanner.nextInt();
                            arr.removeElementByValue(value);
                            break;
                        case 4:
                            System.out.println("Введите значение: ");
                            value = scanner.nextInt();
                            arr.removeAllElementsByValue(value);
                            break;
                        case 5:
                            System.out.println("Введите индексы элементов: ");
                            int index1 = scanner.nextInt();
                            int index2 = scanner.nextInt();
                            arr.swapByIndex(index1, index2);
                            break;
                        case 6:
                            System.out.println("Введите индекс элемента: ");
                            index = scanner.nextInt();
                            arr.getElement(index);
                            break;
                        case 7:
                            System.out.println("Введите значение: ");
                            value = scanner.nextInt();
                            arr.getFirstIndexByValue(value);
                            break;
                        case 8:
                            System.out.println("Введите значение: ");
                            value = scanner.nextInt();
                            arr.getIndexesByValue(value);
                            break;
                    }
                    break;
                case 9:
                    arr.display();
                    break;
                case 10:
                    arr.clear();
                    break;
            }
        }
    }
}
