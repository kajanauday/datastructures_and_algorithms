package datastructures_and_algorithms.hashing;

import datastructures_and_algorithms.printer.Printer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class DirectChaining {
    static Printer printer = new Printer();
    LinkedList<String>[] hashTable;

    public DirectChaining() {
        hashTable = new LinkedList[5];
    }


    public static void main(String[] args) {
        DirectChaining directChaining = new DirectChaining();
        Scanner scanner = new Scanner(System.in);
        Printer printer = new Printer();
        List<String> menu = Arrays.asList("1. INSERT", "2. SEARCH", "3. DELETE", "4. TRAVERSE ", "5. EXIT(0)");
        while (true) {
            printer.printMenu("Direct Chain", "-", menu);
            System.out.print("Choose your option :");
            switch (scanner.nextInt()) {
                case 1 -> {
                    System.out.print("Enter number of items to insert :");
                    int time = scanner.nextInt();
                    while (time-- > 0) {
                        System.out.print("Enter string to insert :");
                        directChaining.insertWord(scanner.next());
                    }
                }
                case 2 -> {
                    System.out.print("Enter word to search :");
                    String word = scanner.next();
                    int index = directChaining.searchWord(word);
                    if (index > -1)
                        printer.printSuccessMessage(word + " is present in the hash table at [" + index + "]");
                    else printer.printFailureMessage(word + " is not present in the hash table!");
                }
                case 3 -> {
                    System.out.print("Enter word to delete :");
                    String word = scanner.next();
                    if (directChaining.deleteWord(word))
                        printer.printSuccessMessage(word + "  is deleted from the hash table");
                    else printer.printFailureMessage(word + " is not deleted!");
                }
                case 4 -> {
                    System.out.println("Enter index of hash table to see the entries :");
                    directChaining.showTable(scanner.nextInt());
                }
                case 5 -> System.exit(0);
            }
        }
    }

    private void showTable(int index) {
        for (String word : hashTable[index])
            System.out.println(word + " -> ");
        System.out.print("null");
    }

    private boolean deleteWord(String word) {
        if (searchWord(word) > -1) {
            int index = hashFunction(word);
            hashTable[index].remove(word);
            return true;
        }
        return false;
    }

    private short searchWord(String word) {
        int index = hashFunction(word);
        for (String s : hashTable[index])
            if (s.equals(word)) return (short) index;
        return -1;
    }

    private void insertWord(String word) {
        int newIndex = hashFunction(word);
        if (hashTable[newIndex] == null) hashTable[newIndex] = new LinkedList<String>();
        hashTable[newIndex].add(word);

    }

    protected int hashFunction(String word) {
        int sum = 0;
        for (char ch : word.toCharArray()) {
            sum += ch;
        }
        return sum % hashTable.length;
    }
}
