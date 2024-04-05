package lab2;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class DynamicArray {
    private Integer[] array;
    int size;
    private static final int INITIAL_CAPACITY = 10;

    DynamicArray() {
        this.array = new Integer[INITIAL_CAPACITY];
        this.size = 0;
    }

    public void resizeIfNeeded() {
        if(size == array.length) {
            Integer[] newArray = new Integer[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
    }

    public void insertFirst(Integer data) {
        resizeIfNeeded();
        System.arraycopy(array, 0, array, 1, size);
        array[0] = data;
        size++;
    }

    public void insertLast(Integer data) {
        resizeIfNeeded();
        array[size++] = data;
    }

    public void fillWithRandNumbs(DynamicArray arr){
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        System.out.println("Введите количество элементов в массиве, которые будут случайными числами от 0 до 99: ");
        int num = scanner.nextInt();
        long start = System.nanoTime();
        for (int i = 0; i < num; i++){
            arr.insertLast(rand.nextInt(100));
        }
        long end = System.nanoTime();
        System.out.println("Время заполнения массива: " + (end - start)/1000000.0 + " мс.");
    }

    public void removeByIndex(int index){
        if (index < 0 || index >= size){
            System.err.println("Неверный индекс.");
        } else {
            long start = System.nanoTime();
            for (int i = index; i < size - 1; i++) {
                array[i] = array[i + 1];
            }
            array[size - 1] = null;
            size--;
            long end = System.nanoTime();
            System.out.println("Время удаления по индексу: " + (end - start)/1000000.0 + " мс");
        }
    }

    public void removeElementByValue(int value) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (array[i] != null && array[i].equals(value)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            removeByIndex(index);
        } else {
            System.out.println("Элемент не найден.");
        }
    }

    public void removeAllElementsByValue(int value) {
        for (int i = 0; i < size; i++) {
            if (array[i] != null && array[i].equals(value)) {
                removeByIndex(i);
                i--;
            }
        }
    }

    public Integer getElement(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Неверный индекс");
            return null;
        }

        return array[index];
    }



    public void display() {
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public void clear() {
        array = new Integer[INITIAL_CAPACITY];
        size = 0;
    }


    public void insertAnyPos(int data, int index) {
        if (index < 0 || index > size) {
            System.err.println("Неверный индекс.");
        }
        long start = System.nanoTime();
        if (index == 0) {
            insertFirst(data);
        } else if (index == size) {
            insertLast(data);
        } else {
            resizeIfNeeded();
            for (int i = size - 1; i >= index; i--) {
                array[i + 1] = array[i];
            }
            array[index] = data;
            size++;
        }
        long end = System.nanoTime();
        System.out.println("Время вставки: " + (end - start) / 1000000.0 + " мс.");
    }

    public void swapByIndex(int index1, int index2) {
        long start = System.nanoTime();
        int tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
        long end = System.nanoTime();
        System.out.println("Время обмена элементов: " + (end - start) / 1000000.0 + " мс.");
    }

    public int getFirstIndexByValue(int value) {
        int index = 0;
        long start = System.nanoTime();
        while (index < array.length) {
            if (array[index] == value) {
                long end = System.nanoTime();
                System.out.println("Элемент найден по индексу " + index);
                System.out.println("Время получения первого вхождения значения по индексу: " + (end - start)/1000000.0 + " мс");
                return index;
            }
            index++;
        }

        System.out.println("Элемент со значением " + value + " не найден.");
        return -1;
    }

    public int[] getIndexesByValue(int value) {
        long start = System.nanoTime();
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (array[i].equals(value)) {
                count++;
            }
        }
        int[] indexes = new int[count];
        int currentIndex = 0;
        for (int i = 0; i < size; i++) {
            if (array[i].equals(value)) {
                indexes[currentIndex] = i;
                currentIndex++;
            }
        }
        long end = System.nanoTime();
        if (count == 0) {
            System.out.println("Элемент со значением " + value + " не найден.");
        } else {
            System.out.println("Индексы элементов: " + Arrays.toString(indexes));
            System.out.println("Время получения индексов по значению: " + (end - start) / 1000000.0 + " мс");
        }

        return indexes;
    }
}
