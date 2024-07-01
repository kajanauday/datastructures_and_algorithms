package linkedlists;

import java.util.Scanner;

public class Queue {
    int count = 0;
    SNode front = null;
    SNode back = null;
    SNode temp = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Queue queue = new Queue();
        final int MAX_ELEMENTS = 100;
        boolean isUserWantsToContinue;
        int noOfElements;
        int[] dataArray;
        do {
            try {
                System.out.println("************ QUEUE ************");
                System.out.println("""
                        \t1 Enqueue
                        \t2 Dequeue
                        \t3 Front
                        \t4 isEmpty
                        \t5 isFull
                        \t6 Clear
                        \t7 Exit""");
                System.out.println("************ QUEUE ************");
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
                        queue.Enqueue(dataArray);
                        System.out.println("------------------------------------------------------------");
                    }
                    case 2 -> {
                        System.out.println("-----------------------------------------------------------");
                        System.out.println("Popped " + queue.Dequeue());
                        System.out.println("-----------------------------------------------------------");
                    }
                    case 3 -> {
                        System.out.println("-----------------------------------------------------------");
                        System.out.println("top element is " + queue.front.data);
                        System.out.println("-----------------------------------------------------------");
                    }
                    case 4 -> {
                        System.out.println("-----------------------------------------------------------");
                        System.out.println("Queue is" + (queue.count == 0 ? " Empty!" : " not Empty!"));
                        System.out.println("-----------------------------------------------------------");
                    }
                    case 5 -> System.out.println("Queue is " + (queue.count == MAX_ELEMENTS ? "Full!" : "not Full!"));
                    case 6 -> {
                        System.out.println("-----------------------------------------------------------");
                        queue.front = null;
                        queue.count = 0;
                        System.out.println("Queue is cleared!");
                        System.out.println("-----------------------------------------------------------");
                    }
                    case 7 ->{
                        scanner.close();
                        System.exit(0);
                    }
                    default -> System.out.println("---Invalid option selected---");
                }
            } catch (Exception e) {
                System.out.println("[ERROR]"+e.getMessage());
                scanner.next();
            }
            System.out.print("do you want to continue[y/n]:");
            isUserWantsToContinue = scanner.next().equalsIgnoreCase("Y");
            System.out.println("-----------------------------------------------------------");
        } while (isUserWantsToContinue);
    }

    private void Enqueue(int[] dataArray) {
        for (int i : dataArray) {
            temp = new SNode(i);
            if (front == null) {
                front = back = temp;
            } else {
                back.next = temp;
                back = temp;
            }
            count++;
        }
    }

    private int Dequeue() {
        if (front == null) {
            return -101;
        }
        count--;
        int ret = front.data;
        front = front.next;
        return ret;
    }
}