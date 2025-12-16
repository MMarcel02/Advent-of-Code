package Y2025.D06;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.ArrayList;


public class Day6 {
    public static void main(String[] args) {
        
        ArrayList<int[]> rowsList = new ArrayList<>();
        ArrayList<char[]> rowsCharList = new ArrayList<>();
        char[] symbolArr = null;
        int[] maxLengths = null;
        
        try (BufferedReader br = new BufferedReader(new FileReader(new File("Y2025/D06/MathHomework.txt")))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] lineSplit = line.split("\s+");
                char[] charLine = line.toCharArray();
                if (lineSplit[0].equals("*") || lineSplit[0].equals("+")) {
                    symbolArr = new char[lineSplit.length];
                    
                    for (int i = 0; i < lineSplit.length; i++) {
                        symbolArr[i] = lineSplit[i].charAt(0);
                    }
                    
                    maxLengths = new int[lineSplit.length];
                    int spaces = 0;
                    int index = 0;
                    for (int i = 1; i < charLine.length; i++) {
                        if (charLine[i] == ' ') {
                            spaces++;
                        } else {
                            maxLengths[index] = spaces;
                            index++;
                            spaces = 0;
                        }
                    }
                } else {
                    char[] charNumArr = line.toCharArray();
                    rowsCharList.add(charNumArr);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int index = 0;
        for (int maxLen : maxLengths) {
            // System.out.println(maxLen);

            // For each column starting from the right
            for (int j = index + maxLen-1; j >= 0; j--) {
                // We rewrite the vertical number horizontally
                int parsedNum = 0;
                for (char[] row : rowsCharList) {
                    if (row[j] > '0' && row[j] <= '9') {
                        parsedNum = parsedNum*10 + (row[j] - '0');
                    }
                }
                System.out.println(parsedNum);
            }


        }

        

        // we have array of 

        // int[][] numbers2DArr = new int[rowsList.get(0).length][rowsList.size()];
        // char[][] char2D = new

        // int column = 0;
        // for (int[] row : rowsList) {
        //     for (int i = 0; i < row.length; i++) {
        //         numbers2DArr[i][column] = row[i];
        //     }
        //     column++;
        // }

        // long totalAllRows = 0;
        // for (int i = 0; i < symbolArr.length; i++) {
        //     long totalInRow = 0;

        //     if (symbolArr[i] == '*') {
        //         totalInRow = 1;
        //     }
        //     for (int number : numbers2DArr[i]) {
        //         if (symbolArr[i] == '+') {
        //             totalInRow += number;
        //         } else if (symbolArr[i] == '*') {
        //             totalInRow *= number;
        //         }
        //     }

        //     totalAllRows += totalInRow;
        // }

        // System.out.println("Part 1: " + totalAllRows);

        // // Part 2: Cephalopod Numbers - reading the problems right-to-left one column at a time

        // int[][] cephalopodNum2DArr = new int[rowsList.get(0).length][rowsList.size()];
        // for (int[] row : numbers2DArr) {

        // }

    }
}
