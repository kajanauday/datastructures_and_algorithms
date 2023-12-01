package datastructures_and_algorithms.linkedlists;

import java.util.Scanner;

class BinaryNode {
    BinaryNode left;
    BinaryNode right;
    int data;
    int height;

    public BinaryNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }


}

public class BinarySearchTree {
    BinaryNode root = null;

    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        Scanner scanner = new Scanner(System.in);
        boolean isUserWantsToContinue;
        int noOfElements = 0;
        int[] dataArray;
        do {
            try {
                System.out.println("************ BST ************");
                System.out.println("""
                        \t1 Insert
                        \t2 Search
                        \t3 Traverse(inorder)
                        \t4 Traverse(preorder)
                        \t5 Traverse(postorder)
                        \t4 Clear""");
                System.out.println("************ BST ************");
                System.out.print("Please enter your option:");
                int option = scanner.nextInt();
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
                        if(binarySearchTree.root==null)
                            binarySearchTree.root = new BinaryNode(dataArray[0]);
                        for (int i=0; i<dataArray.length;i++) {
                            binarySearchTree.insert(binarySearchTree.root, dataArray[i]);
                        }
                        System.out.println("------------------------------------------------------------");
                    }
                    case 2 -> {
                        System.out.println("-----------------------------------------------------------");
                        System.out.println("Search");
                        System.out.println("-----------------------------------------------------------");
                    }
                    case 3,4,5 -> {
                        System.out.println("-----------------------------------------------------------");
                        if(option==4) {
                            System.out.println("inorder --> ");
                            binarySearchTree.inOrderTraversal(binarySearchTree.root);
                        }
                        else if(option == 5){
                            System.out.println("preorder --> ");
                            binarySearchTree.preOrderTraversal(binarySearchTree.root);
                        } else{
                            System.out.println("postorder 9++sszz --> ");
                            binarySearchTree.postOrderTraversal(binarySearchTree.root);
                        }
                        System.out.println("-----------------------------------------------------------");
                    }
                    case 6 -> {
                        System.out.println("-----------------------------------------------------------");
                        System.out.println(binarySearchTree.root.data + " " + binarySearchTree.root.right.data + " " + binarySearchTree.root.right.right.data);
                        System.out.println("-----------------------------------------------------------");
                    }
                    case 7 -> {
                        System.out.println("Stack is ");
                    }
                    case 8 -> {
                        System.out.println("-----------------------------------------------------------");
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


    private void insert(BinaryNode bn, int data) {
        if (bn.data > data) {
            if (bn.left != null) insert(bn.left, data);
            else bn.left = new BinaryNode(data);
        } else if (bn.right != null)
            insert(bn.right, data);
        else bn.right = new BinaryNode(data);

    }

    private void inOrderTraversal(BinaryNode bn) {
        if (bn.left != null)
            inOrderTraversal(bn.left);
        System.out.println(bn.data);
        if (bn.right != null)
            inOrderTraversal(bn.right);
    }
    private void preOrderTraversal(BinaryNode bn) {
        System.out.println(bn.data);
        if (bn.left != null)
            preOrderTraversal(bn.left);
        if (bn.right != null)
            preOrderTraversal(bn.right);
    }

    private void postOrderTraversal(BinaryNode bn) {
        if (bn.left != null)
            postOrderTraversal(bn.left);
        if (bn.right != null)
            postOrderTraversal(bn.right);
        System.out.println(bn.data);
    }
}
