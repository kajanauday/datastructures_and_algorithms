package printer;


import java.util.List;

public class Printer {
    public final String RESET = "\u001B[0m";
    public final String RED = "\u001B[31m";
    public final String GREEN = "\u001B[32m";
    public final String YELLOW = "\u001B[33;1m";
    public final String ORANGE = "\u001B[33m";
    public final String DARK_BLUE = "\u001B[34m";

    public void printErrorMessage(String errorMessage) {
        System.out.println(RED + errorMessage + RED + RESET);
    }

    public void printWarningMessage(String warningMessage) {
        System.out.println(ORANGE + warningMessage + ORANGE + RESET);
    }

    public void printSuccessMessage(String successMessage) {
        System.out.println(GREEN + successMessage + GREEN + RESET);
    }

    public void printMenu(String title, String chunk, List<String> menu) {
        System.out.println(DARK_BLUE + chunk.repeat(10) + "| " + title + " |" + chunk.repeat(10));
        if (menu != null)
            for (String item : menu) System.out.println(" " + item);
        System.out.println(chunk.repeat(title.length() + 24) + RESET);
    }

    public void printHeader(String title, String chunk) {
        System.out.println(DARK_BLUE + chunk.repeat(10) + "| " + title + " |" + chunk.repeat(10));
        //System.out.println(chunk.repeat(title.length() + 24) + RESET);
    }

    public void printFooter(String chunk, int repeat) {
        System.out.println(DARK_BLUE + chunk.repeat(repeat + 24) + RESET);
    }

    public void printFailureMessage(String failureMessage) {
        System.out.println(YELLOW + failureMessage + YELLOW + RESET);
    }
}
