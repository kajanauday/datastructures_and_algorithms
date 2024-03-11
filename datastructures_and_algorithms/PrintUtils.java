package datastructures_and_algorithms;

import javax.swing.text.StyledEditorKit;

public class PrintUtils {
    public final String RESET = "\u001B[0m";
    public final String RED = "\u001B[31m";
    public final String GREEN = "\u001B[32m";
    public final String YELLOW = "\u001B[33m";
    public final String PINK = "\u001B[35m";
    public final String DARK_BLUE = "\u001B[34m";
    public final String ORANGE = "\u001B[31m";

    public void printErrorMessage(String errorMessage) {
        System.out.println(RED + errorMessage + RED);
    }

    private void printWarningMessage(String warningMessage) {
        System.out.println(ORANGE + warningMessage + ORANGE);
    }

    private void printAbstacleMessage(String abstacleMessage){
        System.out.println(YELLOW + abstacleMessage + YELLOW);
    }

    private void printSuccessMessage(String successMessage){
        System.out.println(GREEN + successMessage + GREEN);
    }

    private void printUserGuidingMessage(String guidingMessage){
        System.out.println(DARK_BLUE + guidingMessage + DARK_BLUE);
    }
}
