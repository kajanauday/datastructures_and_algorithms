package leetcode.problems;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/*
this class will hold the methods for solving leet-code problems
 one method for one leet-code solutions
 these methods will be called from Dispatcher class.
*/
public class Solution {
    Scanner scanner;
    Toolkit toolkit = new Toolkit();
    public int balancedStringSplit(String s) {
        int R=0,L=0;
        for(int i = 0;i<s.length();i++){
            if(s.charAt(i)=='L')
                L++;
            else
                R++;
        }
        int charCount = (L+R)/2;

        return 0;
    }
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        int i = 0, f = 0, j = 0, s = 0;
        boolean areSame=true,isUpdated=false;
        char firstChar = '1', secondChar = '1';
        while (f < word1.length) {
            if (i < word1[f].length())
                firstChar = word1[f].charAt(i);
            else
                i++;
            System.out.println(firstChar);
            i = i % (word1[f].length());
        }
        return true;
    }
    public String truncateSentence(String s, int k) {
        int count = 0, i = 0;
        for(i = 0;i<s.length();i++){
            if(s.charAt(i)==' '){
                count++;
                if(count==k){
                    break;
                }
            }
        }
        //Runtime
        //Details
        //0ms
        //Beats 100.00%of users with Java
        //Memory
        //Details
        //40.76MB
        //Beats 62.56%of users with Java
        //https://leetcode.com/problems/truncate-sentence/
        return s.substring(0,i);
    }
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int count=0, index = -1;
        if(ruleKey.equals("type"))
            index = 0;
        else if(ruleKey.equals("color"))
            index = 1;
        else
            index = 2;
            for(int i = 0;i<items.size();i++) {
                if (items.get(i).get(index).equals(ruleValue)) {
                    count++;
                }
            }
            //(CMT) 99.45% , 87.55% , 3ms , https://leetcode.com/problems/count-items-matching-a-rule/
            return count;
    }
    public int mostWordsFound(String[] sentences) {
        int index=-1;
        int count = 0;
        int maxCount = -1;
        for(int i = 0;i< sentences.length;i++){
            do{
            index = sentences[i].substring(index+1).indexOf( " ");
            if(index>-1)
                count++;
        }while(index>-1);
            if(maxCount<=count)
                maxCount = count;
            count=0;
    }
        return maxCount;
    }
    public String interpret(String command) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i< command.length();i++){
            if(command.charAt(i)=='G' || command.charAt(i)=='a' || command.charAt(i)=='l'){
                sb.append(command.charAt(i));
            }
            else if(command.charAt(i)=='(' && i!=command.length()-1 && command.charAt(i+1)==')'){
                    sb.append('o');

            }
        }
//        Runtime    1ms Beats 66.00%of users with Java
//        Memory 40.11MB Beats 93.14%of users with Java
//        https://leetcode.com/problems/goal-parser-interpretation
        return String.valueOf(sb);
    }
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