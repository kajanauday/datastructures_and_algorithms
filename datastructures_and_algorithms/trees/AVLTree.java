package datastructures_and_algorithms.trees;

import java.util.*;

public class AVLTree {
    final private static int LEAF_NODE = 1;
    final private static int ROOT_NODE = 2;
    final private static int LEFT_NODE = 3;
    final private static int RIGHT_NODE = 4;
    Queue<BinaryNode> q = new LinkedList<>();
    List<BinaryNode> seq = new ArrayList<>();

    private BinaryNode root;

    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
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
                            if (avlTree.root == null) {
                                avlTree.root = new BinaryNode(scanner.nextInt());
                            } else avlTree.insert(scanner.nextInt());
                        }
                        System.out.println("------------------------------------------------------------");
                    }
                    case 2 -> {
                        System.out.println("-----------------------------------------------------------");
                        System.out.print("Enter Value to search:");
                        System.out.println(avlTree.searchElement(scanner.nextInt()));
                        System.out.println("-----------------------------------------------------------");
                    }
                    case 3 -> avlTree.getRootNode();
                    case 4 -> {
                        System.out.println("-".repeat(30) + "< inorder >" + "-".repeat(30));
                        avlTree.inOrderTraversal(avlTree.root);
                        System.out.println("-".repeat(60));

                    }
                    case 5 -> {
                        System.out.println("-".repeat(30) + "< preorder >" + "-".repeat(30));
                        avlTree.preOrderTraversal(avlTree.root);
                        System.out.println("-".repeat(60));
                    }
                    case 6 -> {
                        System.out.println("-".repeat(30) + "< post-order >" + "-".repeat(30));
                        avlTree.postOrderTraversal(avlTree.root);
                        System.out.println("-".repeat(60));
                    }
                    case 7 -> {
                        System.out.println("-".repeat(30) + "< levelorder >" + "-".repeat(30));
                        avlTree.levelOrderTraversal(avlTree.root);
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
                        avlTree.delete(data);
                        System.out.println("-".repeat(60));
                    }
                    case 10 -> System.exit(0);
                    case 11 -> System.out.println(avlTree.getNodeOf(scanner.nextInt()));
                    default -> avlTree.printErrorMessage("---Invalid option selected---");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                avlTree.printErrorMessage("[ERROR]" + e.getMessage());
                scanner.next();
            }
            System.out.println("-----------------------------------------------------------");
        } while (true);
    }

    private void printErrorMessage(String errorMessage) {
        System.out.println("\u001B[31m" + errorMessage + "\u001B[0m");
    }

    private void printMenu() {
        System.out.println("\u001B[34m" + """
                ************ AVL Tree ************
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
                11 Get Height of
                ************ AVL Tree ************""" + "\u001B[0m");
    }

    protected void delete(int data) {
        if (root == null) {
            System.out.println("BST is Empty");
        } else if (root.data == data) {
            if (root.left != null && root.left.data == data) deleteNode(root, root.left.data);
            else if (root.left == null && root.right == null) {
                root = null;
            } else root.data = deleteRootNode(root);
        } else {
            BinaryNode node = root, parent = root;
            while (true) {
                if (node.data == data) {
                    System.out.println("Parent:" + parent.data + " Data:" + node.data);
                    BinaryNode dNode = deleteNode(parent, data);
                    if(getHeightsOfNode(dNode,0)>1){

                    }

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

    private void doBalanceTree(){
        BinaryNode node = root;

    }

    private BinaryNode deleteNode(BinaryNode parent, int data) {
        BinaryNode node = (parent.left != null && parent.left.data == data) ? parent.left : parent.right;
        switch (getTypeOfNode(node)) {
            case AVLTree.LEAF_NODE -> {
                System.out.println("deleting leaf-node");
                if (parent.data >= data) parent.left = null;
                else parent.right = null;
            }
            case AVLTree.LEFT_NODE -> {
                System.out.println("deleting left-sub-tree-node");
                if (parent.left.left != null) parent.left = parent.left.left;
                else parent.left = parent.left.right;
            }
            case AVLTree.RIGHT_NODE -> {
                System.out.println("deleting right-sub-tree-node");
                if (parent.right.left != null) parent.right = parent.right.left;
                else parent.right = parent.right.right;
            }
            case AVLTree.ROOT_NODE -> {
                System.out.println("deleting root-node");
                node.data = deleteRootNode(node);

            }
            default -> System.out.println("Invalid node type...");
        }
        return node;
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

    private int getTypeOfNode(BinaryNode node) {
        if (node.left != null && node.right != null) return ROOT_NODE;
        else if (node.left == null && node.right == null) return LEAF_NODE;
        else if (node.left != null) return LEFT_NODE;
        else return RIGHT_NODE;
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

    protected void insert(int data) {
        BinaryNode node = root;
        seq.clear();
        seq.add(node);
        while (true) {
            if (data <= node.data) {
                if (node.left != null) {
                    seq.add(0, node.left);
                    node = node.left;
                } else {
                    node.left = new BinaryNode(data);
                    break;
                }
            } else {
                if (node.right != null) {
                    seq.add(0, node.right);
                    node = node.right;
                } else {
                    node.right = new BinaryNode(data);
                    break;
                }
            }
        }
        for (int i = 0; i < seq.size(); i++) {
            int leftHeight = seq.get(i).left == null ? 0 : getHeightsOfNode(seq.get(i).left, 0);
            int rightHeight = seq.get(i).right == null ? 0 : getHeightsOfNode(seq.get(i).right, 0);
            if (Math.abs(leftHeight - rightHeight) > 1) {
                System.out.println("Insertion made the tree imbalanced.. making the tree balanced");
                BinaryNode imbalancedNode, parentNode, childNode, grandChildNode;
                parentNode = (i == seq.size() - 1) ? null : seq.get(i + 1);
                imbalancedNode = seq.get(i);
                childNode = seq.get(i - 1);
                grandChildNode = seq.get(i - 2);
                if (childNode.data <= imbalancedNode.data) {
                    if (grandChildNode.data <= childNode.data) rotateRight(imbalancedNode, parentNode);
                    else {
                        rotateLeft(childNode, imbalancedNode);
                        rotateRight(imbalancedNode, parentNode);
                    }
                } else {
                    if (grandChildNode.data > childNode.data) rotateLeft(imbalancedNode, parentNode);
                    else {
                        rotateRight(childNode, imbalancedNode);
                        rotateLeft(imbalancedNode, parentNode);
                    }
                }
                break;
            }
        }
        seq.clear();
        levelOrderTraversal(root);
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

    protected void getRootNode() {
        System.out.println((root != null) ? "Value of root is " + root.data : "root is null / empty BST");
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
