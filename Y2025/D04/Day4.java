package Y2025.D04;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

public class Day4 {
    private static int totalObjectsRemoved = 0;
    private static boolean moreObjectsToRemove = true;
    public static void main(String[] args) {
        List<int[]> warehouseLayoutList = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(new File("Y2025/D04/WarehouseLayout.txt")))) {
            String line;
            while ((line = br.readLine()) != null) {
                int[] warehouseRow = new int[line.length()];
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '@') {
                        warehouseRow[i] = 1;
                    } else {
                        warehouseRow[i] = 0;
                    }
                }
                warehouseLayoutList.add(warehouseRow);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        int[][] warehouseArray = warehouseLayoutList.toArray(new int[0][]);

        while (moreObjectsToRemove) {
            warehouseArray = removePossibleObjects(warehouseArray);
        }

        System.out.println(totalObjectsRemoved);
    }


    public static int[][] removePossibleObjects(int[][] warehouseArray) {
        int currentRemovedObjectCount = 0;
        for (int row = 0; row < warehouseArray.length; row++) {
            for (int col = 0; col < warehouseArray[row].length; col++) {
                if (warehouseArray[row][col] == 0) continue;

                int adjecentSpotsTakenCount = 0;

                int rowAbove = row - 1;
                int rowBelow = row + 1;
                int colLeft = col - 1;
                int colRight = col + 1;

                boolean reachRowAbove = rowAbove >= 0;
                boolean reachRowBelow = rowBelow <= warehouseArray.length - 1;
                boolean reachColLeft = colLeft >= 0;
                boolean reachColRight = colRight <= warehouseArray[row].length - 1;

                
                if (reachRowAbove) {
                    if (warehouseArray[rowAbove][col] == 1) adjecentSpotsTakenCount++;

                    if (reachColLeft) {
                        if (warehouseArray[rowAbove][colLeft] == 1) adjecentSpotsTakenCount++;
                    }
                    if (reachColRight) {
                        if (warehouseArray[rowAbove][colRight] == 1) adjecentSpotsTakenCount++;
                    }
                }
                
                if (reachColLeft) {
                    if (warehouseArray[row][colLeft] == 1) adjecentSpotsTakenCount++;
                }
                if (reachColRight) {
                    if (warehouseArray[row][colRight] == 1) adjecentSpotsTakenCount++;
                }

                if (reachRowBelow) {
                    if (warehouseArray[rowBelow][col] == 1) adjecentSpotsTakenCount++;

                    if (reachColLeft) {
                        if (warehouseArray[rowBelow][colLeft] == 1) adjecentSpotsTakenCount++;
                    }
                    if (reachColRight) {
                        if (warehouseArray[rowBelow][colRight] == 1) adjecentSpotsTakenCount++;
                    }
                }

                if (adjecentSpotsTakenCount < 4) {
                    warehouseArray[row][col] = 0;
                    currentRemovedObjectCount++;
                }
            }
        }
        if (currentRemovedObjectCount == 0) {
            moreObjectsToRemove = false;
        }

        totalObjectsRemoved += currentRemovedObjectCount;
        return warehouseArray;
    }

    
}
