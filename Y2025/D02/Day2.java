package Y2025.D02;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day2
 {
    public static void main(String[] args) {
        ArrayList<Long> invalidIDs = new ArrayList<>();
        long total = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(new File("Y2025/D02/IDRanges.txt")))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] idRanges = line.split(",");
                for (String idRange : idRanges) {
                    String[] minMaxValues = idRange.split("-");
                    long minValue = Long.parseLong(minMaxValues[0]);
                    long maxValue = Long.parseLong(minMaxValues[1]);
                    for (long i = minValue; i <= maxValue; i++) {
                        if (isMadeOfRepeatedSequence(String.valueOf(i))) {
                            invalidIDs.add(i);
                        }
                    }
                }
            }
            // Goal is to get sum of the idNumbers not just a count
            for (Long idNumber : invalidIDs) {
                total += idNumber;
            }

            System.out.println(total);


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cant find file");
        }
    }

    public static boolean isMadeOfRepeatedSequence(String s) {
        int length = s.length();
        int midpoint = length/2;
        
        for (int seqLen = 1; seqLen <= midpoint; seqLen++) {
            if (length % seqLen != 0) continue;

            boolean valid = true;

            for (int i = seqLen; i < length; i++) {
                if (s.charAt(i) != s.charAt(i % seqLen)) {
                    valid = false;
                    break;
                }
            }

            if (valid) return true;
        }
        
        return false;
    }
}


