package lab2;
import javax.rmi.ssl.SslRMIClientSocketFactory;
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
    }

    void fillListWithRandNums(DoublyLinkedList list){
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        System.out.println("Введите количество элементов в списке, которые будут случайными числами от 0 до 99: ");
        int num = scanner.nextInt();
        for (int i = 0; i < num; i++){
            list.insertLast(rand.nextInt(100));
        }
    }

    void getListLength(){
        
    }

    void removeByIndex(int position){
        if (position < 0){
            System.err.println("Неверный индекс.");
        } else {
            if (position >= size) {
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
            System.out.println("Время удаления по значению: " + (end - start)/1000000.0 + " мс");
        }
    }

    void removeByValue(int value) {
        Node current = head;
        long start = System.nanoTime();
        while (current != null) {
            if (current.data == value) {
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

                current = current.next;
            } else {
                current = current.next;
            }
        }
        long end = System.nanoTime();
        System.out.println("Время удаления по значению: " + (end - start)/1000000.0 + " мс");
    }

    void swapElements(int index1, int index2){

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
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        DoublyLinkedList list = new DoublyLinkedList();
        System.out.println("Введите индекс куда хотите вставить элемент: ");
        int position = scanner.nextInt();
        System.out.println("Введите элемент: ");
        int data = scanner.nextInt();
        list.insertAnyPos(data, position);
        list.insertAnyPos(data, position);

        list.display();
        list.fillListWithRandNums(list);
        list.insertLast(111);
        list.insertLast(111);
        list.display();
        list.removeByValue(111);
        list.display();
    }
}
