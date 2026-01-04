package Y2025.D06;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.ArrayList;


public class Day6 {
    public static void main(String[] args) {
        
        ArrayList<int[]> parsedRows = new ArrayList<>();
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
                            if (i == charLine.length - 1) {
                                maxLengths[index] = spaces;
                            }   
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

        int index = -1;
        for (int maxLen : maxLengths) {
            // System.out.println(maxLen);
            
            // For each column starting from the right
            int[] parsedNumArr = new int[maxLen];
            int k = 0;
            for (int j = index + maxLen; j > index; j--) {
                // We rewrite the vertical number horizontally
                int parsedNum = 0;
                for (char[] row : rowsCharList) {
                    if (row[j] > '0' && row[j] <= '9') {
                        parsedNum = parsedNum*10 + (row[j] - '0');
                    }
                }
                parsedNumArr[k] = parsedNum;
                k++;
            }
            parsedRows.add(parsedNumArr);
            index += maxLen+1;
        }

        long total = 0;
        
        for (int i = 0; i < symbolArr.length; i++ ) {
            long colTotal = 0; 
            if (symbolArr[i] == '+') {
                for (int num : parsedRows.get(i)) {
                    colTotal += num;
                }
            }
            if (symbolArr[i] == '*') {
                colTotal = 1;
                for (int num : parsedRows.get(i)) {
                    colTotal *= num;
                }
            }
            total += colTotal;
        }

        System.out.println(total);

        

        

    }
}
