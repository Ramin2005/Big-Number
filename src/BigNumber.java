
/**
 * The BigNumber class is designed to store and compute integers with virtually
 * unlimited length, supporting numbers with millions of digits far beyond
 * standard data type limits. It provides reliable arithmetic operations,
 * precise comparisons, and efficient handling of very large values.
 */
public class BigNumber {

    // non-static variables:
    private byte[] data;
    private int length;
    private boolean isPositive = true;

    // non-static methods and constructors:
    // non-static constructors:
    /**
     *
     * Default constructor.
     */
    public BigNumber() {
        this.length = 0;
    }

    /**
     *
     * @param data List of digit
     * @throws RuntimeException If data is invalid
     */
    public BigNumber(byte[] data) throws RuntimeException {

        int symbol = (data.length == 0) ? 0 : ((data[0] < 0) ? -1 : 1);

        for (byte digit : data) {

            if (digit > 9 || digit < -9 || symbol * digit < 0) {

                throw new RuntimeException("Invalid number entered!");

            }

        }

        int i = 0;
        int j = data.length;

        while (i < j) {

            if (data[i] == 0) {

                ++i;

            } else {

                break;

            }

        }

        byte[] temp = new byte[j - i];

        for (int k = 0; k < j - i; k++) {

            temp[k] = data[k + i];

        }

        this.data = temp.clone();
        this.length = j - i + 1;
        this.isPositive = symbol >= 0;
    }

    /**
     *
     * @param number String of the number
     * @throws RuntimeException If string is invalid or can't cast to digits
     */
    public BigNumber(String number) throws RuntimeException {

        byte symbol = 1;
        int stringLength = number.length();
        int i = 0;

        if (stringLength > 0) {

            if (number.charAt(0) == '-') {

                symbol = -1;
                i = 1;

            }

        }

        if (stringLength - i > 0) {

            while (i < stringLength) {

                if (number.charAt(i) == '0') {

                    ++i;

                } else {

                    break;

                }

            }

        }

        byte[] temp = new byte[stringLength - i];

        for (int k = 0; k < stringLength - i; k++) {

            char tempChar = number.charAt(i + k);

            if (!Character.isDigit(tempChar)) {

                throw new RuntimeException("Invalid number entered!");
            }

            temp[k] = (byte) (((byte) (Integer.parseInt(tempChar + ""))) * symbol);
        }

        this.data = temp.clone();
        this.length = temp.length;
        this.isPositive = symbol > 0;

    }

    /**
     *
     * @param number Integer of the number
     */
    public BigNumber(int number) {

        String stringNumber = number + "";
        byte symbol = 1;
        int stringLength = stringNumber.length();
        int i = 0;

        if (stringLength > 0) {

            if (stringNumber.charAt(0) == '-') {

                symbol = -1;
                i = 1;

            }

        }

        byte[] temp = new byte[length];

        for (; i < stringLength; i++) {

            char tempChar = stringNumber.charAt(i);
            temp[i] = (byte) (((byte) (Integer.parseInt(tempChar + ""))) * symbol);

        }

        this.data = temp.clone();
        this.length = stringLength - 1;
        this.isPositive = symbol > 0;
    }

    /**
     *
     * @param number Long integer of the number
     */
    public BigNumber(long number) {

        String stringNumber = number + "";
        byte symbol = 1;
        int stringLength = stringNumber.length();
        int i = 0;

        if (stringLength > 0) {

            if (stringNumber.charAt(0) == '-') {

                symbol = -1;
                i = 1;

            }

        }

        byte[] temp = new byte[length];

        for (; i < stringLength; i++) {

            char tempChar = stringNumber.charAt(i);
            temp[i] = (byte) (((byte) (Integer.parseInt(tempChar + ""))) * symbol);

        }

        this.data = temp.clone();
        this.length = stringLength - 1;
        this.isPositive = symbol > 0;

    }

    // non-static methods:
    /**
     * Returns copy of data array;
     *
     * @return byte array - Copy of data
     */
    public byte[] getData() {
        return this.data.clone();
    }

    /**
     * Returns true if number is positive, zero or null else returns false;
     *
     * @return boolean
     */
    public boolean isPositive() {
        return this.isPositive;
    }

    /**
     *
     * @return Negative BigNumber of number
     */
    public BigNumber getNegative() throws RuntimeException {
        byte[] tempData = this.data.clone();

        for (int i = 0; i < tempData.length; i++) {
            tempData[i] *= -1;
        }

        BigNumber negative = new BigNumber(tempData);

        return negative;
    }

    /**
     *
     * @return String of number
     */
    @Override
    public String toString() {

        String out = "";

        if (this.length != 0) {

            if (!this.isPositive) {

                out = "-";

            }

        }

        for (int digit : this.data) {

            out = out + (Math.abs(digit));

        }

        return out;
    }

    /**
     *
     * Shift the number n digits to the left(multiply number to 10^n).
     *
     * @param n Number of shift digits
     */
    public void shiftL(int n) {

        int oldLength = this.length;
        int newLength = oldLength + n;
        byte[] newData = new byte[newLength];

        System.arraycopy(this.data, 0, newData, 0, oldLength);

        this.data = newData.clone();
        this.length = newLength;

    }

    /**
     *
     * Shift the number one digit to the left(multiply number to 10).
     */
    public void shiftL() {

        this.shiftL(1);

    }

    /**
     *
     * Shift the number n digits to the right(divide number by 10^n).
     *
     * @param n Number of shift digits
     */
    public void shiftR(int n) {

        int oldLength = this.length;
        int newLength = oldLength - n;

        if (newLength < 1) {

            this.data = null;
            this.length = 0;
            this.isPositive = true;

        } else {

            byte[] newData = new byte[newLength];

            System.arraycopy(this.data, 0, newData, 0, newLength);

            this.data = newData.clone();
            this.length = newLength;

        }

    }

    /**
     *
     * Shift the number one digits to the right(divide number by 10).
     */
    public void shiftR() {

        this.shiftR(1);

    }

    // static methods and constructor:
    /**
     *
     * Calculate sum of number one and number two(a + b).
     *
     * @param a BigNumber number one
     * @param b BigNumber number two
     * @return Sum of BigNumbers one & two
     */
    public static BigNumber sum(BigNumber a, BigNumber b) {
        boolean symbolA = a.isPositive();
        byte[] dataA = a.getData();

        boolean symbolB = b.isPositive();
        byte[] dataB = b.getData();

        return null;
    }
}
