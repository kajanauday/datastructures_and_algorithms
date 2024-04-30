package leetcode.problems;

import java.util.Random;

public class Toolkit {
    final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    Random random = new Random();
    public int[][] randomIntegerArray(int noOfRows, int noOfCols){
        int[][] array = new int[noOfRows][noOfCols];
        for(int i=0;i<noOfRows;i++){
            for(int j=0;j<noOfCols;j++){
             array[i][j] = random.nextInt(Math.max(noOfCols, noOfRows));
            }
        }
        System.out.println("leetcode.problems.Toolkit.randomIntegerArray");
        return array;
    }
    public int[][] sequentialIntegerArray(int noOfRows, int noOfCols){
        int seqNum =0;
        int[][] array = new int[noOfRows][noOfCols];
        for(int i=0;i<noOfRows;i++){
            for(int j=0;j<noOfCols;j++){
             array[i][j] = seqNum++;
            }
        }
        System.out.println("leetcode.problems.Toolkit.sequentialIntegerArray");
        return array;
    }
    public void printArray(int[][] a){
        System.out.println("leetcode.problems.Toolkit.printArray");
        for (int[] ints : a) {
            for (int anInt : ints)
                System.out.print(anInt + " ");
            System.out.println();
        }
    }
}
