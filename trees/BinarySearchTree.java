package trees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BinarySearchTree {
    final private static int LEAF_NODE = 1;
    final private static int ROOT_NODE = 2;
    final private static int LEFT_NODE = 3;
    final private static int RIGHT_NODE = 4;
    int heightOfTheTree = 0;
    BinaryNode root = null;

    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        Scanner scanner = new Scanner(System.in);
        int noOfElements;
        do {
            try {
                binarySearchTree.printMenu();
                System.out.print("Please enter your option:");
                int option = scanner.nextInt();
                switch (option) {
                    case 1 -> {
                        System.out.println("-----------------------------------------------------------");
                        System.out.print("enter number or elements to insert: ");
                        noOfElements = scanner.nextInt();
                        for (int i = 0; i < noOfElements; i++) {
                            System.out.print("Enter [" + (i + 1) + "] Element: ");
                            if (binarySearchTree.root == null) {
                                binarySearchTree.root = new BinaryNode(scanner.nextInt());
                                binarySearchTree.heightOfTheTree = 0;
                            } else binarySearchTree.insert(scanner.nextInt());
                        }
                        System.out.println("------------------------------------------------------------");
                    }
                    case 2 -> {
                        System.out.println("-----------------------------------------------------------");
                        System.out.print("Enter Value to search:");
                        System.out.println(binarySearchTree.searchElement(scanner.nextInt()));
                        System.out.println("-----------------------------------------------------------");
                    }
                    case 3 -> binarySearchTree.getRootNode();
                    case 4 -> {
                        System.out.println("-".repeat(30) + "< inorder >" + "-".repeat(30));
                        binarySearchTree.inOrderTraversal(binarySearchTree.root);
                        System.out.println("-".repeat(60));

                    }
                    case 5 -> {
                        System.out.println("-".repeat(30) + "< preorder >" + "-".repeat(30));
                        binarySearchTree.preOrderTraversal(binarySearchTree.root);
                        System.out.println("-".repeat(60));
                    }
                    case 6 -> {
                        System.out.println("-".repeat(30) + "< post-order >" + "-".repeat(30));
                        binarySearchTree.postOrderTraversal(binarySearchTree.root);
                        System.out.println("-".repeat(60));
                    }
                    case 7 -> {
                        System.out.println("-".repeat(30) + "< levelorder >" + "-".repeat(30));
                        binarySearchTree.levelOrderTraversal(binarySearchTree.root);
                        System.out.println("-".repeat(60));
                    }
                    case 8 -> {
                        System.out.println("-".repeat(30) + "< Height of the tree >" + "-".repeat(30));
                        System.out.println(binarySearchTree.getHeightOfTheTree());
                        System.out.println("-".repeat(60));
                    }
                    case 9 -> {
                        System.out.println("-".repeat(30) + "< delete element >" + "-".repeat(30));
                        System.out.print("Enter element to delete:");
                        int data = scanner.nextInt();
                        binarySearchTree.delete(data);
                        System.out.println("-".repeat(60));
                    }
                    case 10 -> {
                        scanner.close();
                        System.exit(0);
                    }
                    default -> printErrorMessage("---Invalid option selected---");
                }
            } catch (Exception e) {
                printErrorMessage("[ERROR]" + e.getMessage());
                scanner.next();
            }
            System.out.println("-----------------------------------------------------------");
        } while (true);

    }

    private static void printErrorMessage(String s) {
        System.out.println("\u001B[31m" + s + "\u001B[0m");
    }

    protected void delete(int data) {
        if (root == null) {
            System.out.println("BST is Empty");
        } else if (root.data == data) {
            if (root.left != null && root.left.data == data) deleteNode(root, root.left.data);
            else if (root.left == null && root.right == null) {
                root = null;
                heightOfTheTree = 0;
            } else root.data = deleteRootNode(root);
        } else {
            BinaryNode node = root, parent = root;
            while (true) {
                if (node.data == data) {
                    System.out.println("Parent:" + parent.data + " Data:" + node.data);
                    deleteNode(parent, data);
                    break;
                } else if (data < node.data) {
                    if (node.left != null) {
                        parent = node;
                        node = node.left;
                    } else {
                        printErrorMessage("Node not found");
                        break;
                    }
                } else {
                    if (node.right != null) {
                        parent = node;
                        node = node.right;
                    } else {
                        printErrorMessage("Node not found");
                        break;
                    }
                }
            }
        }
    }

    private void deleteNode(BinaryNode parent, int data) {
        BinaryNode node = (parent.left != null && parent.left.data == data) ? parent.left : parent.right;
        switch (getTypeOfNode(node)) {
            case BinarySearchTree.LEAF_NODE -> {
                System.out.println("deleting leaf-node");
                if (parent.data >= data) parent.left = null;
                else parent.right = null;
            }
            case BinarySearchTree.LEFT_NODE -> {
                System.out.println("deleting left-sub-tree-node");
                if (parent.left.left != null) parent.left = parent.left.left;
                else parent.left = parent.left.right;
            }
            case BinarySearchTree.RIGHT_NODE -> {
                System.out.println("deleting right-sub-tree-node");
                if (parent.right.left != null) parent.right = parent.right.left;
                else parent.right = parent.right.right;
            }
            case BinarySearchTree.ROOT_NODE -> {
                System.out.println("deleting root-node");
                node.data = deleteRootNode(node);

            }
            default -> System.out.println("Invalid node type...");
        }
    }

    private int deleteRootNode(BinaryNode node) {
        BinaryNode parent = node.right;
        BinaryNode bn = node.right;
        int retData = bn.data;
        while (bn.left != null) {
            parent = bn;
            bn = bn.left;
        }
        if (parent == bn) {
            deleteNode(node, node.right.data);
        } else {
            retData = parent.left.data;
            deleteNode(parent, parent.left.data);
        }
        return retData;
    }

    private void printMenu() {
        System.out.println("\u001B[34m" + """
                ************ BST ************
                1 Insert
                2 Search
                3 Print Root
                4 Traverse (inorder)
                5 Traverse (preorder)
                6 Traverse (postorder)
                7 Traversal (levelorder)
                8 Find Height
                9 Remove Element
                10 Exit
                ************ BST ************""" + "\u001B[0m");
    }

    private int getTypeOfNode(BinaryNode node) {
        if (node.left != null && node.right != null) return ROOT_NODE;
        else if (node.left == null && node.right == null) return LEAF_NODE;
        else if (node.left != null) return LEFT_NODE;
        else return RIGHT_NODE;
    }

    String searchElement(int data) {
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

    protected void insert(int data) {
        BinaryNode node = root;
        int height = 1;
        while (true) {
            if (data <= node.data) {
                if (node.left != null) node = node.left;
                else {
                    node.left = new BinaryNode(data);
                    break;
                }
            } else {
                if (node.right != null) node = node.right;
                else {
                    node.right = new BinaryNode(data);
                    break;
                }
            }
            height++;
        }
        heightOfTheTree = Math.max(heightOfTheTree, height);
    }

    protected void getRootNode() {
        System.out.println((root != null) ? "Value of root is " + root.data : "root is null / empty BST");
    }

    protected int getHeightOfTheTree() {
        return heightOfTheTree;
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
        Queue<BinaryNode> q = new LinkedList<>();
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
