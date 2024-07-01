package printer;

import java.util.List;

public class Printer {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33;1m";
    public static final String ORANGE = "\u001B[33m";
    public static final String DARK_BLUE = "\u001B[34m";
    public static final String BOLD_WHILTE = "\u001B[1m";

    public static void printHeading(String message) {
        System.out.println(BOLD_WHILTE + "\n"+message +BOLD_WHILTE + RESET);

    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println(RED + errorMessage + RED + RESET);
    }

    public static void printWarningMessage(String warningMessage) {
        System.out.println(ORANGE + warningMessage + ORANGE + RESET);
    }

    public static void printSuccessMessage(String successMessage) {
        System.out.println(GREEN + successMessage + GREEN + RESET);
    }

    public static void printMenu(String title, String chunk, List<String> menu) {
        System.out.println(DARK_BLUE + chunk.repeat(10) + "| " + title + " |" + chunk.repeat(10));
        if (menu != null)
            for (String item : menu)
                System.out.println(" " + item);
        System.out.println(chunk.repeat(title.length() + 24) + RESET);
    }

    public static void printHeader(String title, String chunk) {
        System.out.println(DARK_BLUE + chunk.repeat(10) + "| " + title + " |" + chunk.repeat(10)+RESET);
        // System.out.println(chunk.repeat(title.length() + 24) + RESET);
    }

    public static void printFooter(String chunk, int repeat) {
        System.out.println(DARK_BLUE + chunk.repeat(repeat + 24) + RESET);
    }

    public static void printFailureMessage(String failureMessage) {
        System.out.println(YELLOW + failureMessage + YELLOW + RESET);
    }
}
