
/**
 * The BigNumber class is designed to store and compute integers with
 * virtually unlimited length, supporting numbers with millions of digits
 * far beyond standard data type limits. It provides reliable arithmetic operations,
 * precise comparisons, and efficient handling of very large values.
 */
public class BigNumber {

    // non-static variables:
    private byte[] data;
    private int length;
    private boolean isPositive = true;

    // non-static methods and constructor:
    /**
     *
     * Default constructor.
     */
    public BigNumber() {
        this.length = 0;
    }

    /**
     *
     * @param data List of digits.
     * @throws RuntimeException If data is invalid.
     */
    public BigNumber(byte[] data) throws RuntimeException {

        int symbol = (data.length == 0) ? 0 : ((data[0] < 0) ? -1 : 1);

        for (byte digit : data) {

            if (digit > 9 || digit < -9 || symbol * digit < 0) {

                throw new RuntimeException("Invalid number entered!");

            }

        }

        this.data = data.clone();
        this.length = data.length;
        this.isPositive = symbol >= 0;

    }

    /**
     *
     * @param number String of the number.
     * @throws RuntimeException If string is invalid or can't cast to digits.
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

        byte[] temp = new byte[length];

        for (; i < stringLength; i++) {

            char tempChar = number.charAt(i);

            if (!Character.isDigit(tempChar)) {

                throw new RuntimeException("Invalid number entered!");
            }

            temp[i] = (byte) (((byte) (Integer.parseInt(tempChar + ""))) * symbol);
        }

        this.data = temp.clone();
        this.length = stringLength - 1;
        this.isPositive = symbol > 0;

    }

    /**
     *
     * @param number Integer of the number.
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
     * @param number Long integer of the number.
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

    /**
     *
     * Shift the number n digits to the left(multiply number to 10^n).
     *
     * @param n Number of shift digits.
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
     * @param n Number of shift digits.
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

    /**
     *
     * @return Negative BigNumber of number.
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
     * @return String of number.
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

}
