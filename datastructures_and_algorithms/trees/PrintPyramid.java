package datastructures_and_algorithms.trees;

public class PrintPyramid {
    public static void main(String[] args){
        int height =5;
        int noOfElements = 0;
        String[][] holder= new String[height][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < height; j++) {
                holder[i][j] = "    ";
            }
        }
        for(int i=0;i<height;i++){
            noOfElements = (int) Math.pow(2,i);
            System.out.println("Level["+i+"]-->"+(height/(noOfElements+1)));
        }
    }
}
