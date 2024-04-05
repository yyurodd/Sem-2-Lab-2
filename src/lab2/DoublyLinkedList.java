package lab2;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class DoublyLinkedList {
    Node head;
    Node tail;

    DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void insertFirst(int data) {
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

    public void insertLast(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
        }
        tail = newNode;

    }

    public void insertAnyPos(int data, int position){
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

    public void fillListWithRandNumbs(DoublyLinkedList list){
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

    public int getListLength(){
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

    public void removeByIndex(int position){
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

    public void removeByValue(int value) {
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

    public void removeFirstByValue(int value) {
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

    public Node getNodeByIndex(int index) {
        Node current = head;
        int currentIndex = 0;

        while (current != null && currentIndex < index) {
            current = current.next;
            currentIndex++;
        }

        return current;
    }
    public void swapByIndex(int index1, int index2) {
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

    public int getElementByIndex(int index) {
        Node current = head;
        int currentIndex = 0;
        long start = System.nanoTime();
        while (current != null && currentIndex < index) {
            current = current.next;
            currentIndex++;
        }

        if (current != null) {
            long end = System.nanoTime();
            System.out.println("Значение по индексу: " + current.data);
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
        int currentDataIndex = 0;
        int currentIndex = 0;

        while (current != null) {
            if (current.data == value) {
                indexes[currentIndex] = currentDataIndex;
                currentIndex++;
            }
            currentDataIndex++;
            current = current.next;
        }
        long end = System.nanoTime();
        if (count == 0) {
            System.out.println("Элемент со значением " + value + " не найден.");
        } else {
            System.out.println("Индексы элементов: " + Arrays.toString(indexes));
            System.out.println("Время получения индексов по значению: " + (end - start)/1000000.0 + " мс");
        }
        return indexes;

    }

    public void clearAll(){
        head = null;
        tail = null;
        System.gc();
    }

    public void display() {
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
