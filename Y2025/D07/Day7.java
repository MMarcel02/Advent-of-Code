package Y2025.D07;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.ArrayList;


public class Day7 {
    public static ArrayList<ArrayList<Integer>> indexOfBeams = new ArrayList<>();
    public static void main(String[] args) {
        int total = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(new File("Y2025/D07/TrachyonDiagram.txt")))) {
            String line;

            String firstLine = br.readLine();
            ArrayList<Integer> firstBeam = new ArrayList<>();
            firstBeam.add(firstLine.indexOf("S"));
            indexOfBeams.add(firstBeam);

            int index = 0;
            while ((line = br.readLine()) != null) {
                total += calculateSplits(indexOfBeams.get(index), line);
                index++;
            }

            System.out.println(total);
        } catch (IOException e) {
            System.err.println("Can't find file: " + e.getMessage());
        }


    }

    public static int calculateSplits(ArrayList<Integer> indexOfOldBeams, String splitters) {
        int totalSplits = 0;

        ArrayList<Integer> indexOfSplitters = new ArrayList<>();
        ArrayList<Integer> indexOfNewBeams = new ArrayList<>();

        for (int i = 0; i < splitters.length(); i++) {
            if (splitters.charAt(i) == '^') {
                indexOfSplitters.add(i);
            }
        }

        for (Integer indexOfOldeBeam : indexOfOldBeams) {
            boolean split = false;
            for (Integer indexOfSplitter : indexOfSplitters) {
                if (indexOfOldeBeam.equals(indexOfSplitter)) {
                    totalSplits++;
                    split = true;
                    if (!(indexOfNewBeams.contains(indexOfOldeBeam + 1))) {
                        indexOfNewBeams.add(indexOfOldeBeam + 1);
                    }
                    if (!(indexOfNewBeams.contains(indexOfOldeBeam - 1))) {
                        indexOfNewBeams.add(indexOfOldeBeam - 1);
                    }
                }
            }
            if (!split) {
                if (!indexOfNewBeams.contains(indexOfOldeBeam)) {
                    indexOfNewBeams.add(indexOfOldeBeam);
                }
            }
        }

        indexOfBeams.add(indexOfNewBeams);
        return totalSplits;
    }

}
