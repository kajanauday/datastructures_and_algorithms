package datastructures_and_algorithms.linkedlists;

import java.util.Scanner;
class CNode{
    int data;
    CNode next;
    CNode prev;
    CNode(int data){
        this.data = data;
        this.next = null;
    }
}
public class CircularLinkedList {
    CNode head = null;
    CNode tail = null;
    CNode temp = null;
    int count = 0;
    boolean nodeFound;

    public static void main(String[] args) {
        CircularLinkedList circularLinkedList = new CircularLinkedList();
        boolean isUserWantsToContinue;
        int[] dataArray;
        int index;
        Scanner scanner = new Scanner(System.in);
        int noOfElements;
        do{
        try{
            System.out.println("****************************** CIRCULAR LINKED LIST ******************************");
            System.out.print("""
                   Select transaction:
                    \t1 Insert
                    \t2 Insert|index
                    \t3 Traverse
                    \t4 Delete
                    \t5 Search
                    \t6 Delete|index
                  Please enter your option:""");
            int option = scanner.nextInt();
            System.out.println("****************************** CIRCULAR LINKED LIST ******************************");
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
                    circularLinkedList.insertData(dataArray, circularLinkedList.count);
                    System.out.println("------------------------------------------------------------");
                }
                case 2 -> {
                    System.out.println("-----------------------------------------------------------");
                    System.out.println("enter number or elements to insert: ");
                    noOfElements = scanner.nextInt();
                    dataArray = new int[noOfElements];
                    for (int i = 0; i < noOfElements; i++) {
                        System.out.print("Enter [" + (i + 1) + "] Element: ");
                        dataArray[i] = scanner.nextInt();
                    }
                    System.out.println("Enter index: ");
                    index = scanner.nextInt();
                    circularLinkedList.insertData(dataArray, index);
                    System.out.println("------------------------------------------------------------");
                }
                case 3 -> {
                    System.out.println("-----------------------------------------------------------");
                    circularLinkedList.traverseCircularLinkedList(circularLinkedList.head);
                    System.out.println("-----------------------------------------------------------");
                }
                case 4 -> {
                    System.out.println("-----------------------------------------------------------");
                    System.out.print("Enter data to delete:");
                    circularLinkedList.deleteData(scanner.nextInt());
                    System.out.println("-----------------------------------------------------------");
                }
                case 5 -> {
                    System.out.println("-----------------------------------------------------------");
                    System.out.print("Enter element to search:");
                    System.out.println(circularLinkedList.searchData(scanner.nextInt()));
                    System.out.println("-----------------------------------------------------------");

                }
                case 6 -> {
                    System.out.println("-----------------------------------------------------------");
                    System.out.print("Enter index to delete:");
                    circularLinkedList.deleteDataAt(scanner.nextInt());
                    System.out.println("-----------------------------------------------------------");
                }
                default -> System.out.println("---Invalid option selected---");
            }
        } catch(Exception e){
            System.out.println("invalid transaction...  press any key to continue");
            scanner.next();
        }
            System.out.print("do you want to continue[y/n]:");
            isUserWantsToContinue =  scanner.next().equalsIgnoreCase("Y");
            System.out.println("-----------------------------------------------------------");
        }while (isUserWantsToContinue);
        scanner.close();
    }

    private void deleteDataAt(int index) {
        temp = head;
        if (index < 2 && head != null) {
            head = head.next;
            tail.next = head;
        } else if (index <= count) {
            for (int i = 1; i < index - 1; i++) {
                temp = temp.next;
            }
            if (temp.next != null) {
                temp.next = temp.next.next;
            } else {
                temp.next = null;
            }
        }
        count--;
    }

    private void deleteData(int data1) {
        nodeFound = false;
        if(head!=null && head.data == data1){
            if(head.next==null)
                head=tail=null;
            else tail.next=head=head.next;
            nodeFound = true;
        }
        else {
            CNode cnode = head;
            do{
                if (cnode.next.data == data1) {
                    if (cnode.next == tail) {
                        tail = cnode;
                    }
                    cnode.next = cnode.next.next;
                    nodeFound = true;
                    break;
                }
                cnode = cnode.next;
            }while (cnode!= head);
        }
        if (nodeFound) {
            System.out.println("Element deleted");
            count--;
        } else {
            System.out.println("No such element in list");
        }
    }

    private boolean searchData(int data){
        CNode cnode = head;
        do{
            if(cnode.data==data){
                nodeFound = false;
                return true;
            }
            cnode = cnode.next;
        }while(cnode!=head);
        return false;
    }

    private void insertData(int[] data, int index) {
        CNode hold = null;
        CNode startNode = null;
        CNode endNode = null;
        int noOfElements = 0;
        temp = head;
        for (int d : data) {
            CNode cnode = new CNode(d);
            if (startNode == null) {
                startNode = cnode;
                endNode = cnode;
            }
            endNode.next = cnode;
            endNode = cnode;
            noOfElements++;
        }
        if (head == null && tail == null) {
            head = startNode;
            tail = endNode;
            tail.next = head;
        } else if (index < 2) {
            endNode.next = head;
            tail.next = head = startNode;
        } else if (index < count) {
            for (int i = 1; i < index - 1; i++) {
                temp = temp.next;
            }
            hold = temp.next;
            temp.next = startNode;
            tail = endNode;
            tail.next = hold;
        } else if (index == count) {
            tail.next = startNode;
            tail = endNode;
            tail.next = head;
        }
        count = count + noOfElements;
    }

    private void traverseCircularLinkedList(CNode temp) {
        for (int i = 0; i < count; i++) {
            System.out.print(temp.data + "--> ");
            temp = temp.next;
        }
        System.out.println("null");
    }
}
