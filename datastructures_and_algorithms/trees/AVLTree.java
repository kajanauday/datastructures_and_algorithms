package datastructures_and_algorithms.trees;

import datastructures_and_algorithms.PrintUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class AVLTree {
    private static BinaryNode root;
    PrintUtils printUtils = new PrintUtils();
    Queue<BinaryNode> q = new LinkedList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AVLTree avlTree = new AVLTree();
        PrintUtils printUtils = new PrintUtils();
        int noOfElements;
        do {
            try {
                printUtils.userGuidingMessage();
                System.out.print("Please enter your option:");
                int option = scanner.nextInt();
                switch (option) {
                    case 1 -> {
                        System.out.println("-".repeat(30) + "< INSERTION >" + "-".repeat(30));
                        System.out.print("enter number or elements to insert: ");
                        noOfElements = scanner.nextInt();
                        for (int i = 0; i < noOfElements; i++) {
                            System.out.print("Enter [" + (i + 1) + "] Element: ");
                            if (root == null) {
                                root = new BinaryNode(scanner.nextInt());
                            } else avlTree.insert(scanner.nextInt());
                        }
                        avlTree.levelOrderTraversal(root);
                    }
                    case 2 -> {
                        System.out.println("-".repeat(30) + "< SEARCH >" + "-".repeat(30));
                        System.out.print("Enter Value to search:");
                        System.out.println(avlTree.searchElement(scanner.nextInt()));
                    }
                    case 3 -> {
                        System.out.println("-".repeat(30) + "< ROOT OF THE AVL >" + "-".repeat(30));
                        System.out.println((root != null) ? "Value of root is " + root.data : "root is null / empty AVL tree");
                    }
                    case 4 -> {
                        System.out.println("-".repeat(30) + "< IN-ORDER TRAVERSAL >" + "-".repeat(30));
                        avlTree.inOrderTraversal(root);
                    }
                    case 5 -> {
                        System.out.println("-".repeat(30) + "< PRE-ORDER TRAVERSAL >" + "-".repeat(30));
                        avlTree.preOrderTraversal(root);
                    }
                    case 6 -> {
                        System.out.println("-".repeat(30) + "< LEVEL-ORDER TRAVERSAL >" + "-".repeat(30));
                        avlTree.levelOrderTraversal(root);
                    }
                    case 7 -> {
                        System.out.println("-".repeat(30) + "< POST-ORDER TRAVERSAL >" + "-".repeat(30));
                        avlTree.postOrderTraversal(root);
                    }
                    case 8 -> {
                        System.out.println("-".repeat(30) + "< HEIGHT OF THE TREE >" + "-".repeat(30));
                        System.out.println(0);
                    }
                    case 9 -> {
                        System.out.println("-".repeat(30) + "< DELETE NODE >" + "-".repeat(30));
                        System.out.print("Enter element to delete:");
                        int data = scanner.nextInt();
                        avlTree.deleteNode(data);
                    }
                    case 10 -> System.exit(0);
                    case 11 -> System.out.println(avlTree.getNodeOf(scanner.nextInt()));
                    case 12 -> avlTree.getElement(scanner.nextInt());
                    default -> printUtils.printErrorMessage("---Invalid option selected---");
                }
            } catch (Exception e) {
                e.printStackTrace();
                printUtils.printErrorMessage(e.getMessage());
                scanner.next();
            }
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
        analyseThePath(seq);
        seq.clear();
    }

    private void deleteNode(int data) {
        BinaryNode temp;
        ArrayList<BinaryNode> seq = new ArrayList<>();
        for (BinaryNode node = root, parent = root; node != null; ) {
            if (node.data == data) {
                System.out.println("Node found, deleting.....");
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
                    if (parent == node) root = null;
                    else{
                        if (data <= parent.data) parent.left = null;
                        else parent.right = null;
                    }
                }
                for (int i = 0; i < seq.size(); i++) {
                    int lh = getHeightsOfNode(seq.get(i).left, 0);
                    int rh = getHeightsOfNode(seq.get(i).right, 0);
                    if (Math.abs(lh - rh) > 1) {
                        System.out.println("L->" + getHeightsOfNode(seq.get(i).left, 0) + " R->" + getHeightsOfNode(seq.get(i).right, 0));
                        analyseThePath(seq);
                        break;
                    }
                }
                printUtils.printWarningMessage("Node [" + data + "] deleted!");
                seq.clear();
                break;
            } else if (node.left != null && node.data >= data) {
                seq.add(0, node);
                parent = node;
                node = node.left;
            } else if (node.right != null && node.data < data) {
                seq.add(0, node);
                parent = node;
                node = node.right;
            } else {
                System.out.println("No such data exists in the AVL tree.");
                seq.clear();
                break;
            }
        }
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
                        System.out.println("Right Rotation [" + grandChildNode.data + ", " + childNode.data + ", " + imbalancedNode.data + "]");
                        rotateRight(imbalancedNode, parentNode);
                    } else {
                        System.out.println("Left Rotation [" + childNode.data + ", " + imbalancedNode.data + "]");
                        rotateLeft(childNode, imbalancedNode);
                        System.out.println("Right Rotation [" + imbalancedNode.data + ", " + childNode.data + ", " + grandChildNode.data + "]");
                        rotateRight(imbalancedNode, parentNode);
                    }
                } else {
                    if (grandChildNode.data > childNode.data) {
                        System.out.println("Left Rotation [" + imbalancedNode.data + ", " + childNode.data + ", " + grandChildNode.data + "]");
                        rotateLeft(imbalancedNode, parentNode);
                    } else {
                        System.out.println("Right Rotation [" + childNode.data + ", " + grandChildNode.data + "]");
                        rotateRight(childNode, imbalancedNode);
                        System.out.println("Left Rotation [" + grandChildNode.data + ", " + childNode.data + ", " + imbalancedNode.data + "]");
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

    protected void getElement(int data) {
        BinaryNode tmp = root;
        int level = 0;
        while (tmp != null) {
            level++;
            if (data < tmp.data) tmp = tmp.left;
            else if (data > tmp.data) tmp = tmp.right;
            else break;
        }
        System.out.println(tmp == null ? "null" : (tmp.data + "[P] ->" +
                (tmp.left == null ? "null" : tmp.left.data)) + "[L] ->" +
                (tmp.right == null ? "null" : tmp.right.data) + "[R]");
    }

    private void initializeRotation(boolean isLeft, BinaryNode node, BinaryNode parent) {
        System.out.println("Initializing.....");
        if (isLeft) {
            if (node.left.left != null) {
                System.out.println("Right Rotation [" + node.data + ", " + (parent == null ? null : parent.data) + "]");
                rotateRight(node, parent);
            } else {
                System.out.println("Left Rotation [" + node.left.data + ", " + node.data + "]");
                rotateLeft(node.left, node);
                System.out.println("Right Rotation [" + node.data + ", " + (parent == null ? null : parent.data) + "]");
                rotateRight(node, parent);
            }
        } else {
            if (node.right.right != null) {
                System.out.println("Left Rotation [" + node.data + ", " + (parent == null ? null : parent.data) + "]");
                rotateLeft(node, parent);
            } else {
                System.out.println("Right Rotation [" + node.right.data + ", " + node.data + "]");
                rotateRight(node.right, node);
                System.out.println("Left Rotation [" + node.data + ", " + (parent == null ? null : parent.data) + "]");
                rotateLeft(node, parent);

            }
        }
    }

    private void analyseThePath(ArrayList<BinaryNode> seq) {
        for (int i = 0; i < seq.size(); i++) {
            int leftHeight = getHeightsOfNode(seq.get(i).left, 0), rightHeight = getHeightsOfNode(seq.get(i).right, 0);
            if (Math.abs(leftHeight - rightHeight) > 1) {
                initializeRotation(leftHeight > rightHeight, seq.get(i), (i != seq.size() - 1) ? seq.get(i + 1) : null);
            }
        }
    }


    protected int getNodeOf(int data) {
        BinaryNode tmp = root;
        while (tmp != null) {
            if (data < tmp.data) tmp = tmp.left;
            else if (data > tmp.data) tmp = tmp.right;
            else return Math.abs(getHeightsOfNode(tmp.left, 0) - getHeightsOfNode(tmp.right, 0));
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
