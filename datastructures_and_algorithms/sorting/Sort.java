package datastructures_and_algorithms.sorting;

import datastructures_and_algorithms.printer.Printer;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Sort {
    static Printer printer = new Printer();
    int[] melements;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Sort sort = new Sort();
        int selection;
        List<String> menu = Arrays.asList("1. BUBBLE", "2. SELECTION", "3. INSERTION", "4. MERGE", "5. QUICK ", "6. EXIT");
        while (true) {
            printer.printMenu("SORT", "=", menu);
            System.out.print("Choose your option :");
            selection = scanner.nextInt();
            printer.printHeader(menu.get(selection - 1), "-");
            System.out.print("Enter elements :");
            scanner.nextLine();
            String input = scanner.nextLine();
            String[] elements = input.split("[,\\s]+");
            int[] nums = new int[elements.length];
            int i = 0;
            for (String s : elements)
                nums[i++] = Integer.parseInt(s);
            sort.printArray("BEFORE :", nums);
            switch (selection) {
                case 1 -> sort.bubbleSort(nums);
                case 2 -> sort.selectionSort(nums);
                case 3 -> sort.insertionSort(nums);
                case 4 -> {
                    sort.melements = nums;
                    sort.mergeSort(0, sort.melements.length - 1);
                    System.out.println("AFTER :" + Arrays.toString(Arrays.stream(sort.melements).toArray()));
                }
                case 5 -> {
                    sort.melements = nums;
                    sort.quickSort(0, sort.melements.length - 1);
                    sort.printArray("AFTER  :", sort.melements);
                }
                case 6 -> System.exit(0);
            }
            printer.printFooter("-", menu.get(selection - 1).length());
        }
    }

    private void quickSort(int start, int end) {
        if (start >= end) return;
        if ((end - start == 2) && melements[end] < melements[start]) swap(start, end);
        int i = start, j = end - 1;
        while ((i < end) && melements[i] < melements[end]) i++;
        while ((j > i) && melements[j] > melements[end]) j--;

    }

    private void swap(int i, int j) {
        System.out.println("SWAP :" + melements[i] + "[" + i + "]" + " and " + melements[j] + "[" + j + "]");
        int temp = melements[i];
        melements[i] = melements[j];
        melements[j] = temp;
        System.out.println(Arrays.toString(Arrays.stream(melements).toArray()));
    }

    private void bubbleSort(int[] elements) {
        for (int i = 0; i < elements.length - 1; i++) {
            for (int j = 0; j < elements.length - i - 1; j++) {
                if (elements[j] > elements[j + 1]) {
                    elements[j] += elements[j + 1];
                    elements[j + 1] = elements[j] - elements[j + 1];
                    elements[j] = elements[j] - elements[j + 1];
                }
            }
        }
        printArray("AFTER  :", elements);
    }

    private void selectionSort(int[] elements) {
        int min, index;
        for (int i = 0; i < elements.length; i++) {
            min = elements[i];
            index = i;
            for (int j = i + 1; j < elements.length; j++) {
                if (min > elements[j]) {
                    min = elements[j];
                    index = j;
                }
            }
            if (min < elements[i]) {
                elements[i] += elements[index];
                elements[index] = elements[i] - elements[index];
                elements[i] = elements[i] - elements[index];
            }
        }
        printArray("AFTER  :", elements);
    }

    private void insertionSort(int[] elements) {
        for (int i = 1; i < elements.length; i++) {
            int temp = elements[i], j = i;
            while (j > 0 && temp < elements[j - 1]) {
                elements[j] = elements[j - 1];
                j--;
            }
            elements[j] = temp;
        }
        printArray("AFTER  :", elements);
    }

    private void mergeSort(int l, int h) {
        if (l < h) {
            int mid = (l + h) / 2;
            mergeSort(l, mid);
            mergeSort(mid + 1, h);
            merge(l, mid, h);
        }
    }

    private void merge(int l, int mid, int h) {
        int[] a = new int[h - l + 1];
        int i = l, j = mid + 1;
        int k = 0;
        while (i <= mid && j <= h) {
            if (melements[i] < melements[j]) {
                a[k++] = melements[i++];
            } else {
                a[k++] = melements[j++];
            }
        }
        while (i <= mid) {
            a[k++] = melements[i++];
        }
        while (j <= h) {
            a[k++] = melements[j++];
        }
        k = 0;
        for (i = l; i <= h; i++) {
            melements[i] = a[k++];
        }
    }

    private void printArray(String status, int[] nums) {
        System.out.print(status);
        System.out.print(Arrays.toString(Arrays.stream(nums).toArray()));
        System.out.println();
    }
}
