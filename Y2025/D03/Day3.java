package Y2025.D03;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day3 {
    public static void main(String[] args) {
        ArrayList<Long> maxJoltsPerBank = new ArrayList<>();
        long total = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(new File("Y2025/D03/BatteryBanks.txt")))) {
            String line;
            while ((line = br.readLine()) != null) {
                long largestCombined = 0;
                int indexOfLargest = -1;
                String lineAfterLargest = line;

                // i = 12 Because trying to find largest combined 12 digit number for this assignment
                for (int i = 12; i > 0; i--) {
                    lineAfterLargest = lineAfterLargest.substring(indexOfLargest + 1);
                    
                    indexOfLargest  = indexOfLargestNumberInString(lineAfterLargest, i);
                    
                    char largest = lineAfterLargest.charAt(indexOfLargest);
                    largestCombined = largestCombined*10 + (largest - '0');
                }

                maxJoltsPerBank.add(largestCombined);

            }

            for (Long maxJolts : maxJoltsPerBank) {
                total += maxJolts;
            }

            System.out.println(total);


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cant find file");
        }
    }

    public static int indexOfLargestNumberInString (String line, int currentDigit) {
        for (char i = '9'; i > '0'; i--) {
            int indexOfLargestNumber = line.indexOf(i);
            if (indexOfLargestNumber != -1 && (line.length() - indexOfLargestNumber >= currentDigit)) {
                return indexOfLargestNumber;
            }
        }
        // Input is guaranteed to have only numbers 1-9 so dont need to worry about this case
        return -1;
    }
}