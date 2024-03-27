package datastructures_and_algorithms.linkedlists;

import java.util.Scanner;

public class Stack {
    SNode top = null;
    SNode temp = null;
    int count = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack stack = new Stack();
        final int MAX_ELEMENTS = 100;
        boolean isUserWantsToContinue;
        int noOfElements = 0;
        int[] dataArray;
        do {
            try {
                System.out.println("************ STACK ************");
                System.out.println("""
                        \t1 Append
                        \t2 Pop
                        \t3 Top
                        \t4 isEmpty
                        \t5 isFull
                        \t6 Clear""");
                System.out.println("************ STACK ************");
                System.out.print("Please enter your option:");
                int option = scanner.nextInt();
                switch (option) {
                    case 1 -> {
                        System.out.println("-----------------------------------------------------------");
                        System.out.print("enter number or elements to Append: ");
                        noOfElements = scanner.nextInt();
                        dataArray = new int[noOfElements];
                        for (int i = 0; i < noOfElements; i++) {
                            System.out.print("Enter [" + (i + 1) + "] Element: ");
                            dataArray[i] = scanner.nextInt();
                        }
                        stack.append(dataArray);
                        System.out.println("------------------------------------------------------------");
                    }
                    case 2 -> {
                        System.out.println("-----------------------------------------------------------");
                        System.out.println("Popped " + stack.pop());
                        System.out.println("-----------------------------------------------------------");
                    }
                    case 3 -> {
                        System.out.println("-----------------------------------------------------------");
                        System.out.println("top element is " + stack.top.data);
                        System.out.println("-----------------------------------------------------------");
                    }
                    case 4 -> {
                        System.out.println("-----------------------------------------------------------");
                        System.out.println("Stack is" + (stack.count == 0 ? " Empty!" : " not Empty!"));
                        System.out.println("-----------------------------------------------------------");
                    }
                    case 5 -> {
                        System.out.println("Stack is " + (stack.count == MAX_ELEMENTS ? "Full!" : "not Full!"));
                    }
                    case 6 -> {
                        System.out.println("-----------------------------------------------------------");
                        stack.top = null;
                        stack.count = 0;
                        System.out.println("Stack is cleared!");
                        System.out.println("-----------------------------------------------------------");
                    }
                    default -> System.out.println("---Invalid option selected---");
                }
            } catch (Exception e) {
                System.out.println("something went wrong...  press any key to continue");
                e.printStackTrace();
                scanner.next();
            }
            System.out.print("do you want to continue[y/n]:");
            isUserWantsToContinue = scanner.next().equalsIgnoreCase("Y");
            System.out.println("-----------------------------------------------------------");
        } while (isUserWantsToContinue);
    }

    private void append(int[] dataArray) {
        for (int i : dataArray) {
            temp = new SNode(i);
            if (top == null) {
                top = temp;
            } else {
                temp.next = top;
                top = temp;
            }
            count++;
        }
    }

    private int pop() {
        if (top == null) {
            return -101;
        }
        count--;
        int ret = top.data;
        top = top.next;
        return ret;
    }
}
