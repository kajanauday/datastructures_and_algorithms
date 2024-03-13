package datastructures_and_algorithms;

public class PrintUtils {
    public final String RESET = "\u001B[0m";
    public final String RED = "\u001B[31m";
    public final String GREEN = "\u001B[32m";
    public final String ORANGE = "\u001B[33m";
    public final String PINK = "\u001B[35m";
    public final String DARK_BLUE = "\u001B[34m";

    public void printErrorMessage(String errorMessage) {
        System.out.println(RED + errorMessage + RED + RESET);
    }

    public void printWarningMessage(String warningMessage) {
        System.out.println(ORANGE + warningMessage + ORANGE + RESET);
    }

/*    private void printAbstacleMessage(String abstacleMessage) {
        System.out.println(YELLOW + abstacleMessage + YELLOW + RESET);
    }*/

    private void printSuccessMessage(String successMessage) {
        System.out.println(GREEN + successMessage + GREEN + RESET);
    }

    public void userGuidingMessage() {
        System.out.println(DARK_BLUE + """
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
                **********************************""" + DARK_BLUE + RESET);
    }
}
