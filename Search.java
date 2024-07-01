import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import printer.Printer;

public class Search {
    int[] nums;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Search search = new Search();
        int selection;
        List<String> menu = Arrays.asList("1. Linear", "2. Binary", "6. EXIT");
        while (true) {
            Printer.printMenu("SEACH", "=", menu);
            System.out.print("Choose your option :");
            selection = scanner.nextInt();
            Printer.printHeader(menu.get(selection - 1), "-");
            System.out.print(search.nums != null ? "Do you want to continue with Existing Array[y/n]:" : "");
            if (search.nums == null || Character.toLowerCase(scanner.next().charAt(0)) == 'y') {
                System.out.print("Enter elements :");
                scanner.nextLine();
                String input = scanner.nextLine();
                String[] elements = input.split("[,\\s]+");
                search.nums = new int[elements.length];
                int i = 0;
                for (String s : elements)
                    search.nums[i++] = Integer.parseInt(s);
            }
            System.out.print("Enter Element to search: ");
            switch (selection) {
                case 1 -> search.linearSearch(scanner.nextInt());
                case 2 -> search.binarySearch(scanner.nextInt());
                case 6 -> {
                    scanner.close();
                    System.exit(0);
                }
            }
            Printer.printFooter("-", menu.get(selection - 1).length());
        }
    }

    protected void linearSearch(int searchItem) {
        for (int i = 0; i <= nums.length; i++) {
            if (nums[i] == searchItem) {
                System.out.println(searchItem + "found at index [" + (i + 1) + "]");
                return;
            }
        }
        System.out.println("No such element found!");
    }

    protected void binarySearch(int searchItem) {
        int start = 0, end = nums.length - 1, mid = 0;
        while (start < end) {
            mid = (start + end) / 2;
            if (nums[mid] == searchItem) {
                System.out.print(searchItem + " found at index [" + (mid + 1) + "]\n");
                return;
            }
            if (searchItem > nums[mid])
                start = mid + 1;
            else
                end = mid - 1;
        }
        System.out.println("No such element found!");
    }
}
