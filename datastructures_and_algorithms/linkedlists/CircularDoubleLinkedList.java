package datastructures_and_algorithms.linkedlists;

import java.util.Scanner;

class CDNode {
    int data;
    CDNode prev;
    CDNode next;

    public CDNode(int data) {
        this.data = data;
        next = null;
        prev = null;
    }
}

public class CircularDoubleLinkedList {
    CDNode head = null;
    CDNode tail = null;
    CDNode temp = null;
    private int count;

    public static void main(String[] args) {
        CircularDoubleLinkedList circularDoubleLinkedList = new CircularDoubleLinkedList();
        boolean isUserWantsToContinue;
        int[] dataArray;
        int index;
        Scanner scanner = new Scanner(System.in);
        int noOfElements;
        do {
            try {
                System.out.println("****************************** CIRCULAR DOUBLY LINKED LIST ******************************");
                System.out.print("""
                        Select transaction:
                        \t1 Insert
                        \t2 Insert|Index
                        \t3 Delete
                        \t4 Delete|Index
                        \t5 Search
                        \t6 Count
                        \t7 Traverse
                        \t8 Reverse Traverse
                        Please enter your option:""");
                int option = scanner.nextInt();
                System.out.println("****************************** CIRCULAR DOUBLY LINKED LIST ******************************");
                switch (option) {
                    case 1 -> {
                        System.out.println("-----------------------------------------------------------");
                        System.out.print("enter number or elements to insert: ");
                        noOfElements = scanner.nextInt();
                        dataArray = new int[noOfElements];
                        for (int i = 0; i < noOfElements; i++) {
                            System.out.print("Enter [" + (i + 1) + "] Element: ");
                            dataArray[i] = scanner.nextInt();
                        }
                        circularDoubleLinkedList.insertData(dataArray, circularDoubleLinkedList.count);
                        System.out.println("------------------------------------------------------------");
                    }
                    case 2 -> {
                        System.out.println("-----------------------------------------------------------");
                        System.out.print("enter number or elements to insert: ");
                        noOfElements = scanner.nextInt();
                        dataArray = new int[noOfElements];
                        for (int i = 0; i < noOfElements; i++) {
                            System.out.print("Enter [" + (i + 1) + "] Element: ");
                            dataArray[i] = scanner.nextInt();
                        }
                        System.out.println("Enter index: ");
                        index = scanner.nextInt();
                        circularDoubleLinkedList.insertData(dataArray, index);
                        System.out.println("------------------------------------------------------------");
                    }
                    case 3 -> {
                        System.out.println("-----------------------------------------------------------");
                        System.out.print("Enter data to delete:");
                        System.out.println(circularDoubleLinkedList.searchAndDelete(scanner.nextInt(), true));
                        System.out.println("-----------------------------------------------------------");
                    }
                    case 4 -> {
                        System.out.println("-----------------------------------------------------------");
                        System.out.print("Enter index to delete:");
                        circularDoubleLinkedList.deleteDataAt(scanner.nextInt());
                        System.out.println("-----------------------------------------------------------");
                    }
                    case 5 -> {
                        System.out.println("-----------------------------------------------------------");
                        System.out.print("Enter element to search:");
                        System.out.println(circularDoubleLinkedList.searchAndDelete(scanner.nextInt(), false));
                        System.out.println("-----------------------------------------------------------");
                    }
                    case 6 -> System.out.println("Number of Elements in list:" + circularDoubleLinkedList.count);
                    case 7 -> {
                        System.out.println("-----------------------------------------------------------");
                        circularDoubleLinkedList.traverse();
                        System.out.println("-----------------------------------------------------------");
                    }
                    case 8 -> {
                        System.out.println("-----------------------------------------------------------");
                        circularDoubleLinkedList.reverseTraverse();
                        System.out.println("-----------------------------------------------------------");
                    }
                    default -> System.out.println("---Invalid option selected---");
                }
            } catch (Exception e) {
                System.out.println("invalid transaction...  press any key to continue");
                scanner.next();
            }
            System.out.print("do you want to continue[y/n]:");
            isUserWantsToContinue = scanner.next().equalsIgnoreCase("Y");
            System.out.println("-----------------------------------------------------------");
        } while (isUserWantsToContinue);
    }

    private String searchAndDelete(int data, boolean deleteRequired) {
        CDNode cdnode = head;
        do {
            if (cdnode.data == data) {
                if (deleteRequired) {
                    if (cdnode == head) {
                        head = head.next;
                        head.prev = tail;
                        tail.next = head;
                    } else if (cdnode == tail) {
                        tail = tail.prev;
                        tail.next = head;
                        head.prev = tail;
                    } else {
                        if (cdnode.prev != null) {
                            cdnode.prev.next = cdnode.next;
                        }
                        if (cdnode.next != null) {
                            cdnode.next.prev = cdnode.prev;
                        }
                    }
                    count--;
                    return "Delete Done!!";
                }
                return "Data Found!!";
            }
        } while (temp != head);
        return "Data not Found!!";
    }

    private void deleteDataAt(int index) {
        temp = head;
        if (index < 2 && head != null) {
            head = head.next;
            head.prev = tail;
            System.out.println("Head node deleted");
        } else if (index <= count) {
            for (int i = 1; i < index; i++) {
                temp = temp.next;
            }
            if (temp.next != null)
                temp.next.prev = temp.prev;
            if (temp.prev != null)
                temp.prev.next = temp.next;
            System.out.println("Node deleted");
        } else {
            System.out.println("Invalid index");
            return;
        }
        count--;
    }

    private void insertData(int[] data, int index) {
        if (index > count)
            index = count;
        CDNode hold = null;
        CDNode startNode = null;
        CDNode endNode = null;
        int noOfElements = 0;
        temp = head;
        for (int d : data) {
            CDNode dnode = new CDNode(d);
            if (startNode == null) {
                startNode = dnode;
                endNode = dnode;
                noOfElements++;
                continue;
            }
            endNode.next = dnode;
            dnode.prev = endNode;
            endNode = dnode;
            noOfElements++;
        }
        if (head == null && tail == null) {
            head = startNode;
            tail = endNode;
            tail.next = head;
            head.prev = tail;
        } else if (index < 2) {
            endNode.next = head;
            head.prev = tail = endNode;
            head = startNode;
        } else if (index < count) {
            for (int i = 1; i < index - 1; i++) {
                temp = temp.next;
            }
            endNode.next = temp.next;
            temp.next.prev = endNode;
            temp.next = startNode;
            startNode.prev = temp;
        } else if (index == count) {
            tail.next = startNode;
            startNode.prev = tail;
            tail = endNode;
            tail.next = head;
            head.prev = tail;
        }
        tail.next = head;
        count = count + noOfElements;
    }

    private void traverse() {
        temp = head;
        do {
            System.out.print(temp.data + "-->");
            temp = temp.next;
        } while (temp != head);
        System.out.println("[end]");
    }

    private void reverseTraverse() {
        temp = tail;
        do {
            System.out.print(temp.data + "-->");
            temp = temp.prev;
        } while (temp != tail);
        System.out.println("[end]");
    }
}
