package sol.chap6;

/**
 * AKA - Diagonal/Lattice/Vedic Multiplication
 *
 * Multiplying two m*n digit numbers, answer = m+n (max) digits in m+n-1 steps.
 *
 * @author Dhval
 *
 * References :- http://en.wikipedia.org/wiki/Lattice_multiplication
 * http://math66.com/how-to-multiply/
 */
public class StringMultiply {

    private static char[] s1 = {'7', '6', '5'};
    private static char[] s2 = {'7', '6', '5'};

    private static int carryover = 0;
    private static int len = s1.length;
    private static int maxIndex = s1.length - 1;

    /*
     Diagonal multiple
     */
    private static int mulStep(char[] s1, char[] s2, int i, int j) {
        int sum = carryover;
        for (; i <= j; i++, j--) {
            sum += Integer.parseInt("" + s1[maxIndex - i]) * Integer.parseInt("" + s2[maxIndex -j]);
            if (i != j) {
                sum += Integer.parseInt("" + s1[maxIndex - j]) * Integer.parseInt("" + s2[maxIndex - i]);
            }
        }
        carryover = sum / 10;
        return sum % 10;
    }

    public static void main(String[] s) {
        String ans = "";
        int step = 0;
        for (int j = 0; j < len; j++) {
            ans = mulStep(s1, s2, 0, j) + ans;
            System.out.println("STEP=" + step + " i=" + 0 + ", j=" + j + " carry=" + carryover + " ans=" + ans);
        }
        for (int i = 1; i < len; i++) {
            ans = mulStep(s1, s2, i, maxIndex) + ans;
            System.out.println("STEP=" + step + " i=" + i + ", j=" + maxIndex + " carry=" + carryover + " ans=" + ans);
        }
        System.out.println("Final Answer: " + (carryover + ans));
    }

}
