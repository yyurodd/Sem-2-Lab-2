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
class DynamicMassive<T> {
    private Object[] array;
    int size;
    private static final int INITIAL_CAPACITY = 10;

    public DynamicMassive() {
        this.array = new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    private void resizeIfNeeded() {
        if(size == array.length) {
            Object[] newArray = new Object[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
    }

    public void insertFirst(T data) {
        resizeIfNeeded();
        System.arraycopy(array, 0, array, 1, size);
        array[0] = data;
        size++;
    }

    public void insertLast(T data) {
        resizeIfNeeded();
        array[size++] = data;
    }
    public T getElement(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Index out of bounds");
            return null;
        }
        return (T) array[index];
    }

}

class DoublyLinkedList {
    Node head;
    Node tail;

    DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    void insertFirst(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }

    void insertLast(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
        }
        tail = newNode;

    }

    void insertAnyPos(int data, int position){
        if (position < 0){
            System.err.println("Введена неверная позиция");
            return;
        }
        Node newNode = new Node(data);
        long start = System.nanoTime();
        if (position == 0){
            insertFirst(data);
        }
        else {
            Node current = head;
            int index = 0;
            while (current != null && index < position - 1){
                current = current.next;
                index++;
            }
            if (current == null){
                System.err.println("Неверная позиция");
                return;
            }
            newNode.next = current.next;
            newNode.prev = current;
            if(current.next != null){
                current.next.prev = newNode;
            }
            current.next = newNode;
            if(newNode.next == null){
                tail = newNode;
            }
        }
        long end = System.nanoTime();
        System.out.println("Время вставки: " + (end - start)/1000000.0 + " мс.");
    }

    void fillListWithRandNums(DoublyLinkedList list){
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        System.out.println("Введите количество элементов в списке, которые будут случайными числами от 0 до 99: ");
        int num = scanner.nextInt();
        long start = System.nanoTime();
        for (int i = 0; i < num; i++){
            list.insertLast(rand.nextInt(100));
        }
        long end = System.nanoTime();
        System.out.println("Время заполнения списка: " + (end - start)/1000000.0 + " мс.");
    }

    int getListLength(){
        if(head == null){
            return 0;
        }

        Node current = head;
        int count = 1;

        while(current.next != null){
            current = current.next;
            count++;
        }
        return count;
    }

    void removeByIndex(int position){
        if (position < 0){
            System.err.println("Неверный индекс.");
        } else {
            if (position >= getListLength()) {
                System.err.println("Неверный индекс.");
                return;
            }

            Node current = head;
            int index = 0;
            long start = System.nanoTime();

            while(index < position){
                if (current == null) {
                    System.err.println("Неверный индекс.");
                    return;
                }

                current = current.next;
                index++;
            }

            if(current == head){
                head = current.next;
            }
            if(current == tail){
                tail = current.prev;
            }
            if(current.prev != null){
                current.prev.next = current.next;
            }
            if(current.next != null){
                current.next.prev = current.prev;
            }

            long end = System.nanoTime();
            System.out.println("Время удаления по индексу: " + (end - start)/1000000.0 + " мс");
        }
    }

    void removeByValue(int value) {
        Node current = head;
        boolean valueRemoved = false;
        long start = System.nanoTime();
        while (current != null) {
            if (current.data == value) {
                valueRemoved = true;
                if (current == head) {
                    head = current.next;
                }

                if (current == tail) {
                    tail = current.prev;
                }

                if (current.prev != null) {
                    current.prev.next = current.next;
                }

                if (current.next != null) {
                    current.next.prev = current.prev;
                }
            }

            current = current.next;
        }
        long end = System.nanoTime();
        if (!valueRemoved) {
            System.out.println("Значение " + value + " не найдено в списке.");
        } else {
            System.out.println("Время удаления по значению: " + (end - start)/1000000.0 + " мс");
        }
    }

    void removeFirstByValue(int value) {
        Node current = head;
        boolean valueRemoved = false;
        long start = System.nanoTime();

        while (current != null) {
            if (current.data == value) {
                valueRemoved = true;
                if (current == head) {
                    head = current.next;
                }

                if (current == tail) {
                    tail = current.prev;
                }

                if (current.prev != null) {
                    current.prev.next = current.next;
                }

                if (current.next != null) {
                    current.next.prev = current.prev;
                }

                break;
            }

            current = current.next;
        }

        long end = System.nanoTime();

        if (!valueRemoved) {
            System.out.println("Значение " + value + " не найдено в списке.");
        } else {
            System.out.println("Первое вхождение удалено. Время удаления: " + (end - start) + " нс");
        }
    }

    Node getNodeByIndex(int index) {
        Node current = head;
        int currentIndex = 0;

        while (current != null && currentIndex < index) {
            current = current.next;
            currentIndex++;
        }

        return current;
    }
    void swapByIndex(int index1, int index2) {
        long start = System.nanoTime();
        if (index1 == index2) {
            return;
        }

        Node node1 = getNodeByIndex(index1);
        if (node1 == null) {
            System.out.println("Элемент с индексом " + index1 + " не найден.");
            return;
        }

        Node node2 = getNodeByIndex(index2);
        if (node2 == null) {
            System.out.println("Элемент с индексом " + index2 + " не найден.");
            return;
        }


        int temp = node1.data;
        node1.data = node2.data;
        node2.data = temp;
        long end = System.nanoTime();
        System.out.println("Элементы с индексами " + index1 + " и " + index2 + " успешно поменялись местами.");
        System.out.println("Время обмена элементов по индексам: " + (end - start)/1000000.0 + " мс");
    }

    int getElementByIndex(int index) {
        Node current = head;
        int currentIndex = 0;
        long start = System.nanoTime();
        while (current != null && currentIndex < index) {
            current = current.next;
            currentIndex++;
        }

        if (current != null) {
            long end = System.nanoTime();
            System.out.println("Индекс элемента: " + current.data);
            System.out.println("Время получения элемента по индексу: " + (end - start)/1000000.0 + " мс");
            return current.data;
        } else {
            System.out.println("Элемент с индексом " + index + " не найден.");
            return -1;
        }
    }

    public int getFirstIndexByValue(int value) {
        Node current = head;
        int currentIndex = 0;
        long start = System.nanoTime();
        while (current != null) {
            if (current.data == value) {
                long end = System.nanoTime();
                System.out.println("Элемент найден по индексу " + currentIndex);
                System.out.println("Время получения первого вхождения значения по индексу: " + (end - start)/1000000.0 + " мс");
                return currentIndex;
            }
            current = current.next;
            currentIndex++;
        }

        System.out.println("Элемент со значением " + value + " не найден.");
        return -1;
    }

    public int[] getIndexesByValue(int value) {
        long start = System.nanoTime();
        int count = 0;
        Node current = head;

        while (current != null) {
            if (current.data == value) {
                count++;
            }
            current = current.next;
        }

        int[] indexes = new int[count];
        current = head;
        int currentIndex = 0;

        while (current != null) {
            if (current.data == value) {
                indexes[currentIndex] = currentIndex;
                currentIndex++;
            }
            current = current.next;
        }
        long end = System.nanoTime();
        if (count == 0) {
            System.out.println("Элемент со значением " + value + " не найден.");
        } else {
            System.out.println("Индексы элементов" + Arrays.toString(indexes));
            System.out.println("Время получения индексов по значению: " + (end - start)/1000000.0 + " мс");
        }
        return indexes;
    }

    void display() {
        Node current = head;
        if(head == null){
            System.out.println("Двусвязный список пуст");
            return;
        }
        System.out.print("Список: ");
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DoublyLinkedList list = new DoublyLinkedList();
        int number;
        while (true) {
            System.out.println("""
                              Меню                \s
                    1 - Введите количество элементов в списке, который будет автоматически заполняться случайными числами (0 до 99).                 \s
                    2 - Ввести элементы в список вручную.                    \s
                    3 - Вставка, удаление, обмена и получение элемента двусвязного списка.
                    4 - Вывод списка на экран.
                    0 - Завершить программу.
                    """);
            number = scanner.nextInt();
            if (number == 0) {
                break;
            }
            switch (number) {
                case 1:
                    list.fillListWithRandNums(list);
                    break;
                case 2:
                    System.out.println("""
                            Куда вы хотите добавлять каждый последующий элемент?
                            1 - в начало списка
                            2 - в конец списка
                            """);
                    int choice = scanner.nextInt();

                    DynamicMassive times = new DynamicMassive(); // Создаем массив здесь

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
                            times.insertLast(timeInNanos);
                        } catch (NumberFormatException e) {
                            System.out.println("Ошибка: введено не целое число");
                        }
                    }

                    long sum = 0;
                    for (int i = 0; i < times.size; i++) {
                        long time = (long) times.getElement(i); // Приведение к long
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
                        case 8:
                            System.out.println("Введите значение: ");
                            value = scanner.nextInt();
                            list.getIndexesByValue(value);
                    }
                case 4:
                    list.display();
                    break;
            }
        }
    }
}
