package leetcode.problems;

import java.util.HashMap;
import java.util.Scanner;

/*
this class will hold the methods for solving leet-code problems
 one method for one leet-code solutions
 these methods will be called from Dispatcher class.
*/
public class Solution {
    Scanner scanner;
    Toolkit toolkit = new Toolkit();


    public void rotateArray(int[][] a, int k) {
        k = k % 4;
        int[][] b = new int[a[0].length][a.length];
        System.out.println("leetcode.problems.Solution.rotateArray");
        System.out.println(a.length + " " + a[0].length);
        System.out.println(k);
        while (k-- > 0) {
            for (int i = 0; i < a[0].length; i++) {
                for (int j = a.length - 1; j >= 0; j--) {
                    System.out.print(a[j][i] + " ");
                }
                System.out.println();
            }
        }//https://leetcode.com/problems/rotate-array/description/
    }

    public int finalValueAfterOperations(String[] operations) {
        int x =0;
        for (int i = 0;i < operations.length;i++){
            if(operations[i].charAt(1)=='-')
                x--;
            else
                x++;
        }
        return x; //https://leetcode.com/problems/final-value-of-variable-after-performing-operations/
    }

    public String defangIPaddr(String address) {
        int last = 0;
        StringBuilder finalString = new StringBuilder();
        StringBuilder add = new StringBuilder(address);
        for (int i = 0; i < address.length(); i++) {
            if (address.charAt(i) == '.') {
                finalString.append(add.substring(last, i)).append("[.]");
                last = i+1;
            }
        }
        finalString.append(add.substring(last));
        return String.valueOf(finalString); //https://leetcode.com/problems/defanging-an-ip-address/
    }
    public int numJewelsInStones(String jewels, String stones) {
        HashMap<Character, Integer> map = new HashMap<>();
        for(char i : jewels.toCharArray())
            map.put(i,0);
        for(char i:stones.toCharArray())
            if(map.containsKey(i))
                map.put(i, map.get(i)+1);
        int sum=0;
        for(int i:map.values())
            sum = sum + i;
        return sum;//https://leetcode.com/problems/jewels-and-stones/
    }
}