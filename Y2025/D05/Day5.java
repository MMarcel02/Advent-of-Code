package Y2025.D05;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day5 {
    public static void main(String[] args) {
        ArrayList<long[]> idRanges = new ArrayList<>();
        ArrayList<Long> idsToCheck = new ArrayList<>();
        

        try (BufferedReader br = new BufferedReader(new FileReader(new File("Y2025/D05/IngredirentIDRanges.txt")))) {
            boolean readingRanges = true;
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    // Now we switch to reading IDs not ranges
                    readingRanges = false;
                    continue;
                }

                if (readingRanges) {
                    String[] rangesSplit = line.split("-");
                    long min = Long.parseLong(rangesSplit[0]);
                    long max = Long.parseLong(rangesSplit[1]);

                    long[] rangePair = {min, max};
                    idRanges.add(rangePair);
                } else {
                    idsToCheck.add(Long.parseLong(line));
                }

            }
    
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // For Part 1
        int totalFresh = 0;
        for (Long id : idsToCheck) {
            for (long[] idRange : idRanges) {
                if (idRange[0] <= id && id <= idRange[1]) {
                    totalFresh++;
                    break;
                }
            }
        }
        
        //For Part 2
        long totalTheoreticalFresh = 0;
        idRanges.sort((a,b) -> Long.compare(a[0], b[0]));
        ArrayList<long[]> mergedRanges = new ArrayList<>();
        long[] currentIDRange = idRanges.get(0);
        for (int i = 1; i < idRanges.size(); i++) {
            long[] nextIDRange = idRanges.get(i);
            if (currentIDRange[1] + 1 >= nextIDRange[0]) {
                currentIDRange[1] = Math.max(currentIDRange[1], nextIDRange[1]);

            } else {
                mergedRanges.add(currentIDRange);
                currentIDRange = nextIDRange;
            }
        }
        mergedRanges.add(currentIDRange);



        for (long[] range : mergedRanges) {
            totalTheoreticalFresh += range[1] - range[0] + 1;
        }

        System.out.println("Part 1 - Find total fresh IDs: " + totalFresh);
        System.out.println("Part 2 - Find total possible fresh IDs: " + totalTheoreticalFresh);
    }
}    

