package Y2025.D03;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day3 {
    public static void main(String[] args) {
        ArrayList<Integer> maxJoltsPerBank = new ArrayList<>();
        long total = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(new File("Y2025/D03/BatteryBanks.txt")))) {
            String line;
            while ((line = br.readLine()) != null) {
                int indexOfLargestTens  = indexOfLargestNumberInString(line, true);

                char largestTens = line.charAt(indexOfLargestTens);
                String lineAfterLargestTens = line.substring(indexOfLargestTens + 1);

                int indexOfLargestUnit = indexOfLargestNumberInString(lineAfterLargestTens, false);
                char largestUnit = lineAfterLargestTens.charAt(indexOfLargestUnit);

                Integer largestCombined = (largestTens - '0')*10 + (largestUnit - '0');
                maxJoltsPerBank.add(largestCombined);
            }

            for (Integer maxJolts : maxJoltsPerBank) {
                total += maxJolts;
            }

            System.out.println(total);


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cant find file");
        }
    }

    public static int indexOfLargestNumberInString (String line, boolean checkNumberIsNotLast) {
        for (char i = '9'; i > '0'; i--) {
            int indexOfLargestNumber = line.indexOf(i);
            // We make sure that the largest number is not the last one in the list for the tens, can be for the singles
            if (indexOfLargestNumber != -1 && (!checkNumberIsNotLast || (indexOfLargestNumber < line.length() - 1))) {
                return indexOfLargestNumber;
            }
        }
        // Input is guaranteed to have only numbers 1-9 so dont need to worry about this case
        return -1;
    }
}