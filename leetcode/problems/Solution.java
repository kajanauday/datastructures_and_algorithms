package leetcode.problems;

import java.util.Scanner;

/*
this class will hold the methods for solving leet-code problems
 one method for one leet-code solutions
 these methods will be called from Dispatcher class.
*/
public class Solution {
    Scanner scanner;
    Toolkit toolkit = new Toolkit();
    //https://leetcode.com/problems/rotate-array/description/
    public void rotateArray(int[][] a) {
        int[][] b = new int[a[0].length][a.length];
        toolkit.printArray(b);
        System.out.println("leetcode.problems.Solution.rotateArray");
        System.out.println(a.length+" "+a[0].length);
        for(int i=0;i<a[0].length;i++){
            for(int j=a.length-1;j>=0;j--){
                System.out.print(a[j][i]+" ");
            }
            System.out.println();
        }
    }
}
