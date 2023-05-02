package com.grp.connect.java11.problems.olympiad;

/* 
 * https://www.iarcs.org.in/inoi/online-study-material/topics/prefix-sums-ramus-mango-trees.php
 * Ramu's father has left a farm organized as an N × N grid. 
 * Each square in the grid either has or does not have a mango tree.
 *  He has to divide the farm with his three sisters as follows: 
 *  he will draw one horizontal line and one vertical line to divide the field into four rectangles.
 *  His sisters will choose three of the four smaller fields and he gets the last one.
 * He wants to divide the field so that he gets the maximum number of mangos possible,
 *  assuming that his sisters will pick the best three rectangles.
 * 
 * For example, suppose the field looks as follows: were . is  blank space and # is a mongo tree . 

      . # # . . .
      # . . # # .
      . # . . . .
      . # # . . #
      # . . # # .
      . # . . . .
      
Ramu can ensure that he gets at least 3 mango trees by cutting as follows:

        . # | # . . .
        # . | . # # .
        . # | . . . .
      ------+---------
        . # | # . . #
        # . | . # # .
        . # | . . . . 
        
          now We need to write and test the program for above input and output*/


import java.util.Scanner;

public class MangoFarmProblemUsingDP {
    
    private static int[][] farm;
    private static int n;
    private static int[] rowSum;
    private static int[] colSum;
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        n = 6; // size of the farm (n x n grid)
        farm = new int[][]{
            {0, 1, 1, 0, 0, 0},
            {1, 0, 0, 1, 1, 0},
            {0, 1, 0, 0, 0, 0},
            {0, 1, 1, 0, 0, 1},
            {1, 0, 0, 1, 1, 0},
            {0, 1, 0, 0, 0, 0}
        }; // matrix to store mango trees
        
        calculateMangoSums(); // calculate row and column sums
        
        int maxMangos = 0; // variable to store the maximum number of mangos Ramu can get
        for (int i = 1; i < n; i++) { // loop through all possible horizontal lines
            for (int j = 1; j < n; j++) { // loop through all possible vertical lines
                // calculate the sum of mangos in each of the four rectangles
                int rect1 = rowSum[i-1];
                int rect2 = rowSum[n-1] - rect1;
                int rect3 = colSum[j-1];
                int rect4 = colSum[n-1] - rect3;
                // find the minimum sum of mangos in the three rectangles chosen by Ramu's sisters
                int minSistersSum = Math.min(Math.min(rect1, rect2) + Math.min(rect3, rect4),
                                            Math.min(rect1, rect3) + Math.min(rect2, rect4));
                // calculate the sum of mangos in Ramu's rectangle
                int ramuSum = rowSum[i] - rowSum[i-1] + colSum[j] - colSum[j-1] - farm[i-1][j-1];
                // update maxMangos if the sum of mangos in Ramu's rectangle plus the minimum sum of mangos in the three rectangles chosen by Ramu's sisters is greater than maxMangos
                maxMangos = Math.max(maxMangos, ramuSum + minSistersSum);
            }
        }
        System.out.println(maxMangos); // print the maximum number of mangos Ramu can get
    }
    
    private static void calculateMangoSums() {
        rowSum = new int[n]; // array to store row sums
        colSum = new int[n]; // array to store column sums
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rowSum[i] += farm[i][j]; // calculate row sums
                colSum[j] += farm[i][j]; // calculate column sums
            }
        }
    }
}
