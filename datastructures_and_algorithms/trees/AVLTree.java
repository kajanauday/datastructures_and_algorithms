package datastructures_and_algorithms.trees;

import datastructures_and_algorithms.PrintUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class AVLTree {
    private static BinaryNode root;
    Queue<BinaryNode> q = new LinkedList<>();

    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        PrintUtils printUtils = new PrintUtils();
        Scanner scanner = new Scanner(System.in);
        int noOfElements;
        do {
            try {
                avlTree.printMenu();
                System.out.print("Please enter your option:");
                int option = scanner.nextInt();
                switch (option) {
                    case 1 -> {
                        System.out.println("-----------------------------------------------------------");
                        System.out.print("enter number or elements to insert: ");
                        noOfElements = scanner.nextInt();
                        for (int i = 0; i < noOfElements; i++) {
                            System.out.print("Enter [" + (i + 1) + "] Element: ");
                            if (root == null) {
                                root = new BinaryNode(scanner.nextInt());
                            } else avlTree.insert(scanner.nextInt());
                        }
                        avlTree.levelOrderTraversal(root);
                        System.out.println("------------------------------------------------------------");
                    }
                    case 2 -> {
                        System.out.println("-----------------------------------------------------------");
                        System.out.print("Enter Value to search:");
                        System.out.println(avlTree.searchElement(scanner.nextInt()));
                        System.out.println("-----------------------------------------------------------");
                    }
                    case 3 ->
                            System.out.println((root != null) ? "Value of root is " + root.data : "root is null / empty BST");
                    case 4 -> {
                        System.out.println("-".repeat(30) + "< inorder >" + "-".repeat(30));
                        avlTree.inOrderTraversal(root);
                        System.out.println("-".repeat(60));

                    }
                    case 5 -> {
                        System.out.println("-".repeat(30) + "< preorder >" + "-".repeat(30));
                        avlTree.preOrderTraversal(root);
                        System.out.println("-".repeat(60));
                    }
                    case 6 -> {
                        System.out.println("-".repeat(30) + "< levelorder >" + "-".repeat(30));
                        avlTree.levelOrderTraversal(root);
                        System.out.println("-".repeat(60));
                    }
                    case 7 -> {
                        System.out.println("-".repeat(30) + "< post-order >" + "-".repeat(30));
                        avlTree.postOrderTraversal(root);
                        System.out.println("-".repeat(60));
                    }
                    case 8 -> {
                        System.out.println("-".repeat(30) + "< Height of the tree >" + "-".repeat(30));
                        System.out.println(0);
                        System.out.println("-".repeat(60));
                    }
                    case 9 -> {
                        System.out.println("-".repeat(30) + "< delete element >" + "-".repeat(30));
                        System.out.print("Enter element to delete:");
                        int data = scanner.nextInt();
                        avlTree.deleteNode(data);
                        System.out.println("-".repeat(60));
                    }
                    case 10 -> System.exit(0);
                    case 11 -> System.out.println(avlTree.getNodeOf(scanner.nextInt()));
                    default -> printUtils.printErrorMessage("---Invalid option selected---");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                printUtils.printErrorMessage(e.getMessage());
                scanner.next();
            }
            System.out.println("-----------------------------------------------------------");
        } while (true);
    }

    protected void insert(int data) {
        BinaryNode node = root;
        ArrayList<BinaryNode> seq = new ArrayList<>();
        seq.add(node);
        while (true) {
            if (data <= node.data) {
                if (node.left != null) {
                    node = node.left;
                } else {
                    node.left = new BinaryNode(data);
                    seq.add(0, node.left);
                    break;
                }
            } else {
                if (node.right != null) {
                    node = node.right;
                } else {
                    node.right = new BinaryNode(data);
                    seq.add(0, node.right);
                    break;
                }
            }
            seq.add(0, node);
        }
        makeBalanced(seq);
        seq.clear();
    }

    private void deleteNode(int data) {
        BinaryNode temp;
        for (BinaryNode node = root, parent = root; node != null; ) {
            if (node.data == data) {
                System.out.println("Element " + data + "Found, deleting it....");
                if (node.right != null) {
                    temp = node.right;
                    if (temp.left != null) {
                        while (temp.left.left != null) {
                            temp = temp.left;
                        }
                        node.data = temp.left.data;
                        temp.left = temp.left.right;
                    } else {
                        node.data = temp.data;
                        node.right = temp.right;
                    }
                } else if (node.left != null) {
                    temp = node.left;
                    if (temp.right != null) {
                        while (temp.right.right != null) {
                            temp = temp.right;
                        }
                        node.data = temp.right.data;
                        temp.right = temp.right.left;
                    } else {
                        node.data = temp.data;
                        node.left = temp.left;
                    }
                } else {
                    if (parent == root) root = null;
                    else {
                        if (data <= parent.data) parent.left = null;
                        else parent.right = null;
                    }
                }
                levelOrderTraversal(root);
                break;
            } else if (node.left != null && node.data >= data) {
                parent = node;
                node = node.left;
            } else if (node.right != null && node.data < data) {
                parent = node;
                node = node.right;
            } else {
                System.out.println("No such data exists in the AVL tree.");
                break;
            }
        }
    }

    private void printMenu() {
        System.out.println("\u001B[34m" + """
                ************ AVL Tree ************
                1 Insert
                2 Search
                3 Print Root
                4 Traverse (in-order)
                5 Traverse (pre-order)
                6 Traversal (level-order)
                7 Traverse (post-order)
                8 Find Height
                9 Remove Element
                10 Exit
                11 Get Height
                ************ AVL Tree ************""" + "\u001B[0m");
    }

    private void makeBalanced(ArrayList<BinaryNode> seq) {
        for (int i = 2; i < seq.size(); i++) {
            if (Math.abs((getHeightsOfNode(seq.get(i).left, 0)) - getHeightsOfNode(seq.get(i).right, 0)) > 1) {
                BinaryNode imbalancedNode, parentNode, childNode, grandChildNode;
                imbalancedNode = seq.get(i);
                parentNode = (i == seq.size() - 1) ? null : seq.get(i + 1);
                childNode = seq.get(i - 1);
                grandChildNode = seq.get(i - 2);
                if (childNode.data <= imbalancedNode.data) {
                    if (grandChildNode.data <= childNode.data) {
                        System.out.println(parentNode == null ? ("Right Rotation [" + grandChildNode.data + ", " + childNode.data + ", " + imbalancedNode.data + "]") : ("Right Rotation [" + childNode.data + ", " + imbalancedNode.data + ", " + parentNode.data + "]"));
                        rotateRight(imbalancedNode, parentNode);
                    } else {
                        System.out.println(parentNode == null ? ("Right Rotation [" + grandChildNode.data + ", " + childNode.data + ", " + imbalancedNode.data + "]") : ("Right Rotation [" + childNode.data + ", " + imbalancedNode.data + ", " + parentNode.data + "]"));
                        rotateLeft(childNode, imbalancedNode);
                        rotateRight(imbalancedNode, parentNode);
                    }
                } else {
                    if (grandChildNode.data > childNode.data) {
                        System.out.println(parentNode == null ? ("Right Rotation [" + grandChildNode.data + ", " + childNode.data + ", " + imbalancedNode.data + "]") : ("Right Rotation [" + childNode.data + ", " + imbalancedNode.data + ", " + parentNode.data + "]"));
                        rotateLeft(imbalancedNode, parentNode);
                    } else {
                        System.out.println(parentNode == null ? ("Right Rotation [" + grandChildNode.data + ", " + childNode.data + ", " + imbalancedNode.data + "]") : ("Right Rotation [" + childNode.data + ", " + imbalancedNode.data + ", " + parentNode.data + "]"));
                        rotateRight(childNode, imbalancedNode);
                        rotateLeft(imbalancedNode, parentNode);
                    }
                }
                break;
            }
        }
    }

    protected String searchElement(int data) {
        BinaryNode tmp = root;
        int level = 0;
        while (tmp != null) {
            level++;
            if (data < tmp.data) tmp = tmp.left;
            else if (data > tmp.data) tmp = tmp.right;
            else break;
        }
        return tmp != null ? "Element " + data + " found at level " + level : "Element " + data + " Not found!";
    }

    protected void rotateLeft(BinaryNode imbalancedNode, BinaryNode parent) {
        BinaryNode r = imbalancedNode.right;
        BinaryNode temp = imbalancedNode.right.left;
        imbalancedNode.right.left = imbalancedNode;
        imbalancedNode.right = temp;
        if (parent != null) if (imbalancedNode.data <= parent.data) parent.left = r;
        else parent.right = r;
        else root = r;
    }

    protected void rotateRight(BinaryNode imbalancedNode, BinaryNode parent) {
        BinaryNode r = imbalancedNode.left;
        BinaryNode temp = imbalancedNode.left.right;
        imbalancedNode.left.right = imbalancedNode;
        imbalancedNode.left = temp;
        if (parent != null) if (imbalancedNode.data <= parent.data) parent.left = r;
        else parent.right = r;
        else root = r;
    }

    protected int getNodeOf(int data) {
        BinaryNode tmp = root;
        while (tmp != null) {
            if (data < tmp.data) tmp = tmp.left;
            else if (data > tmp.data) tmp = tmp.right;
            else return getHeightsOfNode(tmp, 0);
        }
        return -1;
    }

    protected int getHeightsOfNode(BinaryNode bn, int height) {
        if (bn == null) return 0;
        return Math.max(getHeightsOfNode(bn.left, height + 1), getHeightsOfNode(bn.right, height + 1)) + 1;
    }

    protected void preOrderTraversal(BinaryNode bn) {
        System.out.println(bn.data);
        if (bn.left != null) preOrderTraversal(bn.left);
        if (bn.right != null) preOrderTraversal(bn.right);
    }

    protected void inOrderTraversal(BinaryNode bn) {
        if (bn == null) return;
        inOrderTraversal(bn.left);
        System.out.println(bn.data);
        inOrderTraversal(bn.right);
    }

    protected void postOrderTraversal(BinaryNode bn) {
        if (bn.left != null) postOrderTraversal(bn.left);
        if (bn.right != null) postOrderTraversal(bn.right);
        System.out.println(bn.data);
    }

    protected void levelOrderTraversal(BinaryNode bn) {
        if (bn == null) return;
        q.add(bn);
        int levelSize = q.size();
        while (!q.isEmpty()) {
            for (int i = 0; i < levelSize; i++) {
                BinaryNode node = q.remove();
                if (node != null) {
                    System.out.print(node.data + ",");
                    if (node.left != null) q.add(node.left);
                    if (node.right != null) q.add(node.right);
                }
            }
            System.out.println();
            levelSize = q.size();
        }
    }
}
