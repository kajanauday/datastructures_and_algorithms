package trees;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import printer.Printer;

public class TrieTree {
    static Printer printer = new Printer();
    public TrieNode root = new TrieNode();
    private boolean deletionNeeded = true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TrieTree tree = new TrieTree();
        int iterations;
        List<String> menu = Arrays.asList("1. INSERT", "2. SEARCH", "3. DELETE", "4. TRAVERSE ", "5. EXIT(0)");
        do {
            try {
                Printer.printMenu("TRIE", "-", menu);
                System.out.print("Please enter your option:");
                switch (scanner.nextInt()) {
                    case 1 -> {
                        System.out.print("Enter number of strings you want to Insert :");
                        iterations = scanner.nextInt();
                        while (iterations-- > 0) {
                            System.out.print("Enter String to Insert: ");
                            tree.insertString(scanner.next());
                        }
                    }
                    case 2 -> {
                        System.out.print("Enter String to Search: ");
                        String string = scanner.next();
                        boolean found = tree.searchString(string);
                        if (found)
                            Printer.printSuccessMessage("'" + string + "' is in the tree!");
                        else
                            Printer.printFailureMessage("'" + string + "' is not in the tree!");
                    }
                    case 3 -> {
                        tree.deleteString(scanner.next());
                        tree.deletionNeeded = true;
                    }
                    case 4 -> tree.traverseTree();
                    case 5 -> {
                        System.out.println("Have a good day!");
                        scanner.close();
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid selection\nEnter any key to continue....");
                }
            } catch (Exception e) {
                Printer.printErrorMessage(e.getMessage());
            }
        } while (true);
    }

    private void insertString(String string) {
        if (!root.children.containsKey(string.charAt(0)))
            root.children.put(string.charAt(0), null);
        TrieNode node = root;
        for (int i = 0; i < string.length() - 1; i++) {
            if (node.children.get(string.charAt(i)) == null) {
                node.children.put(string.charAt(i), new HashMap<>());
                node.children.get(string.charAt(i)).put(string.charAt(i + 1), new TrieNode());
            } else if (!node.children.get(string.charAt(i)).containsKey(string.charAt(i + 1))) {
                node.children.get(string.charAt(i)).put(string.charAt(i + 1), new TrieNode());
            }
            node = node.children.get(string.charAt(i)).get(string.charAt(i + 1));
        }
    }

    private boolean searchString(String string) {
        TrieNode node = root;
        for (int i = 0; i < string.length() - 1; i++) {
            char currentChar = string.charAt(i);
            char nextChar = string.charAt(i + 1);
            if (node.children.containsKey(currentChar) && node.children.get(currentChar).containsKey(nextChar)) {
                node = node.children.get(currentChar).get(nextChar);
            } else
                return false;
        }
        return true;
    }

    private void traverseTree() {
        Scanner scanner = new Scanner(System.in);
        char c, d;
        TrieNode node = root;
        while (node != null) {
            System.out.print("Choose from " + node.children.keySet() + ":");
            c = scanner.next().charAt(0);
            if (c == '$'){
                return;
            }
            if (node.children.containsKey(c)) {
                System.out.print("Choose from " + node.children.get(c).keySet() + ":");
                d = scanner.next().charAt(0);
                if (node.children.get(c).containsKey(d))
                    node = node.children.get(c).get(d);
            }
        }
        scanner.close();
    }

    private void deleteString(String string) {
        if (searchString(string)) {
            deleteEntry(string, 0, root);
            if (root.children.get(string.charAt(0)).isEmpty())
                root.children.remove(string.charAt(0));
            Printer.printSuccessMessage("[" + string + "] is deleted from the Trie...");
        } else
            Printer.printFailureMessage("[" + string + "] not found in Trie! aborting the deletion...");
    }

    private void deleteEntry(String string, int index, TrieNode node) {
        if (node == null || index == string.length() - 1)
            return;
        deleteEntry(string, index + 1, node.children.get(string.charAt(index)).get(string.charAt(index + 1)));
        if (deletionNeeded) {
            deletionNeeded = !(node.children.get(string.charAt(index)).size() > 1);
            node.children.get(string.charAt(index)).remove(string.charAt(index + 1));
        }
    }
}
