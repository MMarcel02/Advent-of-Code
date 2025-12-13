package Y2025.D01;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Day1
 {
    public static void main(String[] args) {
        // Need to get a file reader that reads line by line 
        // Parses the direction and the value
        // Then its passed into dial
        // Need to make a dial object that stores state of its own dial, and a count of how many times its 0 
        // Getter of dials count
        File part1 = new File("Y2025/D01/DialValues.txt");
        Dial simpleDial = new Dial(50);

        try (BufferedReader br = new BufferedReader(new FileReader(part1))) {
            String line;
            while ((line = br.readLine()) != null) {

                int rotationAmount = Integer.parseInt(line.substring(1));
                if (line.startsWith("L")) {
                    rotationAmount *= -1;
                }

                // Part 1
                // simpleDial.rotateDial(rotationAmount);

                // Part 2
                simpleDial.passwordRotateDial(rotationAmount);
            }
            System.out.println(simpleDial.getCount());
        } catch (IOException e) {
            System.out.println("Can't find file");
        }
    }
}
