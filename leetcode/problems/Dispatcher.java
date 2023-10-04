package leetcode.problems;

import java.util.Scanner;

//this class will call the method which solves a leet-code problem
public class Dispatcher {
    public static void main(String[] args){
        Scanner scanner;
        Solution solution   = new Solution();
        Toolkit toolkit = new Toolkit();
        solution.rotateArray(toolkit.sequentialIntegerArray(16,5)); //this method will rotate array in clock wise direction by n times//
    }

}

