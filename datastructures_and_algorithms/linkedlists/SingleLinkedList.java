package datastructures_and_algorithms.linkedlists;
import java.util.Scanner;
class SNode{
    int data;
    SNode next;
    SNode(int data){
        this.data = data;
        this.next = null;
    }
}
public class SingleLinkedList {

    SNode head = null;
    SNode tail = null;
    SNode temp = null;
    int count = 0;

    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        boolean isUserWantsToContinue;
        int[] dataArray;
        int index;
        Scanner scanner = new Scanner(System.in);
        int noOfElements;
        do{
            try{
                System.out.println("****************************** SINGLY LINKED LIST ******************************");
                System.out.print("""
                        Select transaction:
                        \t2 Insert|index
                        \t1 Insert
                        \t3 Delete
                        \t4 Delete|index
                        \t5 Search
                        \t6 Count
                        \t7 TraversePlease enter your option:""");
                int option = scanner.nextInt();
                System.out.println("****************************** SINGLY LINKED LIST ******************************");
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
                        singleLinkedList.insertData(dataArray, singleLinkedList.count);
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
                        singleLinkedList.insertData(dataArray, index);
                        System.out.println("------------------------------------------------------------");
                    }
                    case 3 -> {
                        System.out.println("-----------------------------------------------------------");
                        System.out.print("Enter data to delete:");
                        singleLinkedList.deleteData(scanner.nextInt());
                        System.out.println("-----------------------------------------------------------");
                    }
                    case 4 -> {
                        System.out.println("-----------------------------------------------------------");
                        System.out.print("Enter index to delete:");
                        singleLinkedList.deleteDataAt(scanner.nextInt());
                        System.out.println("-----------------------------------------------------------");
                    }
                    case 5 -> {
                        System.out.println("-----------------------------------------------------------");
                        System.out.print("Enter element to search:");
                        System.out.println(singleLinkedList.searchData(scanner.nextInt()));
                        System.out.println("-----------------------------------------------------------");
                    }
                    case 6 -> System.out.println("Number of Elements in list:"+singleLinkedList.count);
                    case 7 -> {
                        System.out.println("-----------------------------------------------------------");
                        singleLinkedList.traverseCircularLinkedList(singleLinkedList.head);
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

    private void deleteData(int data) {
        boolean nodeFound = false;
        if(head!=null && head.data == data){
            if(head.next==null)
                head=tail=null;
            else head=head.next;
            nodeFound = true;
        }
        else {
            SNode snode = head;
            while (snode!= null) {
                if (snode.next.data == data) {
                    if (snode.next == tail) {
                        tail = snode;
                    }
                    snode.next = snode.next.next;
                    nodeFound = true;
                    break;
                }
                snode = snode.next;
            }
        }
        if (nodeFound) {
            System.out.println("Element deleted");
            count--;
        } else {
            System.out.println("No such element in list");
        }
    }

    private boolean searchData(int data) {
        for(SNode snode = head;snode!=null;snode = snode.next){
            if(snode.data==data){
                return true;
            }
        }
        return false;
    }

    private void deleteDataAt(int index) {
        temp = head;
        if (index < 2 && head != null) {
            head = head.next;
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
        else {
            System.out.println("Invalid index");
            return;
        }
        count--;
    }

    private void traverseCircularLinkedList(SNode head) {
        for(SNode snode = head; snode!=null;snode=snode.next){
            System.out.print(snode.data+"-->");
        }
        System.out.println("null");
    }

    private void insertData(int[] data, int index) {
        if(index>count)
            index=count;
        SNode hold = null;
        SNode startNode = null;
        SNode endNode = null;
        int noOfElements = 0;
        temp = head;
        for (int d : data) {
            SNode snode = new SNode(d);
            if (startNode == null) {
                startNode = snode;
                endNode = snode;
            }
            endNode.next = snode;
            endNode = snode;
            noOfElements++;
        }
        if (head == null && tail == null) {
            head = startNode;
            tail = endNode;
            tail.next = null;
        } else if (index < 2) {
            endNode.next = head;
            head = startNode;
        } else if (index < count) {
            for (int i = 1; i < index - 1; i++) {
                temp = temp.next;
            }
            endNode.next = temp.next;
            temp.next = startNode;
        } else if (index == count) {
            tail.next = startNode;
            tail = endNode;
            tail.next = null;
        }
        count = count + noOfElements;
    }
}
