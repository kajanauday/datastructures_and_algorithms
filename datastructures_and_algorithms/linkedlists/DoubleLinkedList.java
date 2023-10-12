package datastructures_and_algorithms.linkedlists;
import java.util.Scanner;
class DNode {
    int data;
    DNode prev;
    DNode next;
    public DNode(int data){
        this.data = data;
        next = null;
        prev = null;
    }
}
public class DoubleLinkedList {
    private int count;
    DNode head = null;
    DNode tail = null;
    DNode temp = null;

    public static void main(String[] args){
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        boolean isUserWantsToContinue;
        int[] dataArray;
        int index;
        Scanner scanner = new Scanner(System.in);
        int noOfElements;
        do{
            try{
                System.out.println("****************************** DOUBLY LINKED LIST ******************************");
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
                System.out.println("****************************** DOUBLY LINKED LIST ******************************");
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
                        doubleLinkedList.insertData(dataArray, doubleLinkedList.count);
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
                        doubleLinkedList.insertData(dataArray, index);
                        System.out.println("------------------------------------------------------------");
                    }
                    case 3 -> {
                        System.out.println("-----------------------------------------------------------");
                        System.out.print("Enter data to delete:");
                        System.out.println(doubleLinkedList.searchAndDelete(scanner.nextInt(),true));
                        System.out.println("-----------------------------------------------------------");
                    }
                    case 4 -> {
                        System.out.println("-----------------------------------------------------------");
                        System.out.print("Enter index to delete:");
                        doubleLinkedList.deleteDataAt(scanner.nextInt());
                        System.out.println("-----------------------------------------------------------");
                    }
                    case 5 -> {
                        System.out.println("-----------------------------------------------------------");
                        System.out.print("Enter element to search:");
                        System.out.println(doubleLinkedList.searchAndDelete(scanner.nextInt(),false));
                        System.out.println("-----------------------------------------------------------");
                    }
                    case 6 -> System.out.println("Number of Elements in list:"+doubleLinkedList.count);
                    case 7 -> {
                        System.out.println("-----------------------------------------------------------");
                        doubleLinkedList.traverse();
                        System.out.println("-----------------------------------------------------------");
                    }
                    case 8 -> {
                        System.out.println("-----------------------------------------------------------");
                        doubleLinkedList.reverseTraverse();
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
    }

    private String searchAndDelete(int data, boolean deleteRequired) {
        for(DNode dnode = head; dnode!=null;dnode=dnode.next) {
            if (dnode.data == data) {
                if (deleteRequired) {
                    if (dnode == head) {
                        head = head.next;
                        head.prev = null;
                    } else if (dnode == tail) {
                        tail = tail.prev;
                        tail.next = null;
                    } else {
                        if (dnode.prev != null) {
                            dnode.prev.next = dnode.next;
                        }
                        if (dnode.next != null) {
                            dnode.next.prev = dnode.prev;
                        }
                    }
                    count--;
                    return "Delete Done!!";
                }
                return "Data Found!!";
            }
        }
        return "Data not Found!!";
    }

    private void deleteDataAt(int index) {
        temp = head;
        if (index < 2 && head != null) {
            head = head.next;
            head.prev = null;
            System.out.println("Head node deleted");
        } else if (index <= count) {
            for (int i = 1; i < index; i++) {
                temp = temp.next;
            }
            if(temp.next!=null)
                temp.next.prev= temp.prev;
            if(temp.prev!=null)
                temp.prev.next = temp.next;
            System.out.println("Node deleted");
        }
        else {
            System.out.println("Invalid index");
            return;
        }
        count--;
    }

    private void insertData(int[] data, int index) {
        if(index>count)
            index=count;
        DNode hold = null;
        DNode startNode = null;
        DNode endNode = null;
        int noOfElements = 0;
        temp = head;
        for (int d : data) {
            DNode dnode = new DNode(d);
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
            tail.next = null;
        } else if (index < 2) {
            endNode.next = head;
            head.prev = endNode;
            head = startNode;
        } else if (index < count) {
            for (int i = 1; i < index - 1; i++) {
                temp = temp.next;
            }
            endNode.next = temp.next;
            temp.next.prev = endNode;
            temp.next = startNode;
        } else if (index == count) {
            tail.next = startNode;
            startNode.prev = tail;
            tail = endNode;
            tail.next = null;
        }
        count = count + noOfElements;
    }
    private void traverse(){
        for(DNode dnode = head; dnode!=null; dnode = dnode.next){
            System.out.print(dnode.data+"-->");
        }
        System.out.println("null");
    }
    private void reverseTraverse(){
        for(DNode dnode = tail; dnode!=null; dnode = dnode.prev){
            System.out.print(dnode.data+"-->");
        }
        System.out.println("null");
    }

}
