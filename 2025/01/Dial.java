
public class Dial {
    int dialValue;
    int count;

    public Dial(int dialValue) {
        this.dialValue = dialValue;
        this.count = 0;
    }

    // Part 1
    // Count each time it ends on 0
    public void rotateDial(int rotation) {
        int remainder = rotation % 100;
        int newDialValue = dialValue + remainder;

        if (newDialValue > 99) {
            dialValue = newDialValue - 100;
        } else if ( newDialValue < 0) {
            dialValue = newDialValue + 100;
        } else {
            dialValue += remainder;
        }

        if (dialValue == 0) {
            count++;
        }
    }

    // Different logic for part 2
    // Count each time it crosses 0
    public void passwordRotateDial(int rotation) {
        int fullRotations = rotation / 100;
        int remainder = rotation % 100;
        int newDialValue = dialValue + remainder;

        count += Math.abs(fullRotations);

        if (newDialValue > 99) {
            newDialValue -= 100;
            if (dialValue != 0 && newDialValue != 0) count++;
            dialValue = newDialValue;
        } else if ( newDialValue < 0) {
            newDialValue += 100;
            if (dialValue != 0 && newDialValue != 0) count++;
            dialValue = newDialValue;
        } else {
            dialValue += remainder;
        }

        if (dialValue == 0) {
            count++;
        }

    }

    public int getCount() {
        return count;
    }
}
