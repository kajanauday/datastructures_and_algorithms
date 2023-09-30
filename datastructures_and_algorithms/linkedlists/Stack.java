package datastructures_and_algorithms.linkedlists;

import java.util.Scanner;

public class Stack {
    private static final int MAX_ELEMENTS = 100;
    private Element top;
    private int count=0;

    class Element{
        int data;
        Element next;

        public Element(int data) {
            this.data = data;
        }
    }
    public static void main(String[] args){
        Stack stack = new Stack();
        boolean isUserWantsToContinue;
        int[] dataArray;
        int index;
        Scanner scanner = new Scanner(System.in);
        int noOfElements;
        do{
            try{
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
                        System.out.println("Popped "+stack.pop());
                        System.out.println("-----------------------------------------------------------");
                    }
                    case 3 -> {
                        System.out.println("-----------------------------------------------------------");
                        System.out.println("top element is "+ stack.top.data);
                        System.out.println("-----------------------------------------------------------");
                    }
                    case 4 -> {
                        System.out.println("-----------------------------------------------------------");
                        System.out.println("Stack is"+(stack.count==0?" Empty!":" not Empty!"));
                        System.out.println("-----------------------------------------------------------");
                    }
                    case 5 -> {
                        System.out.println("Stack is "+(stack.count== MAX_ELEMENTS?"Full!":"not Full!"));
                    }
                    case 6 ->{
                        System.out.println("-----------------------------------------------------------");
                        stack.top = null;
                        stack.count = 0;
                        System.out.println("Stack is cleared!");
                        System.out.println("-----------------------------------------------------------");
                    }
                    default -> System.out.println("---Invalid option selected---");
                }
            } catch(Exception e){
                System.out.println("something went wrong...  press any key to continue");
                e.printStackTrace();
                scanner.next();
            }
            System.out.print("do you want to continue[y/n]:");
            isUserWantsToContinue =  scanner.next().equalsIgnoreCase("Y");
            System.out.println("-----------------------------------------------------------");
        }while (isUserWantsToContinue);
        scanner.close();
    }

    private String pop() {
        if(count==0) {
            return " NaN \nStack is Empty!\n";
        }
        count--;
        int data = top.data;
        top = top.next;
        return String.valueOf(data);
    }

    private void append(int[] dataArray) {
        Element head = null;
        Element temp = null;
        Element tail = null;
        if(dataArray.length>0) {
            head = new Element(dataArray[0]);
            tail = head;
            count++;
        }
        for(int i=1;i<dataArray.length;i++){
            if(count==MAX_ELEMENTS){
                System.out.println("reached the capacity omitting extra elements from the list");
                break;
            }
            temp = new Element(dataArray[i]);
            temp.next = head;
            head = temp;
            count++;
        }
        tail.next = top;
        top=head;
    }

}
