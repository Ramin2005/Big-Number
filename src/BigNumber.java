
/**
 * The BigNumber class is designed to store and compute integers with
 * virtually unlimited length, supporting numbers with millions of digits
 * far beyond standard data type limits. It provides reliable arithmetic operations,
 * precise comparisons, and efficient handling of very large values.
 */
public class BigNumber {

    //non-static variables:
    private byte[] data;

    // non-static methods and constructor:
    /**
     *
     * Default constructor.
     */
    public BigNumber() {

    }

    /**
     *
     * @param data List of digits.
     * @throws Exception If data is invalid.
     */
    public BigNumber(byte[] data) throws Exception {
        int symbol = (data.length == 0) ? 0 : ((data[0] < 0) ? -1 : 1);

        for (byte digit : data) {

            if (digit > 9 || digit < -9 || symbol * digit < 0) {

                throw new Exception("Invalid number entered!");

            }

        }

        this.data = data.clone();

    }

    /**
     * @param number String of the number.
     * @throws Exception If string is invalid or can't cast to digits.
     */
    public BigNumber(String number) throws Exception {

        byte symbol = 1;
        int length = number.length();
        int i = 0;

        if (length > 0) {

            if (number.charAt(0) == '-') {

                symbol = -1;
                --length;
                i = 1;

            }

        }

        this.data = new byte[length];

        for (; i < number.length(); i++) {

            char temp = number.charAt(i);

            if (!Character.isDigit(temp)) {

                this.data = null;

                throw new Exception("Invalid number entered!");
            }

            this.data[i] = (byte) (((byte) (Integer.parseInt(temp + ""))) * symbol);
        }

    }

    /**
     *
     * @param number Integer of the number.
     */
    public BigNumber(int number) {

        int copy = number;
        byte symbol = 1;

        if (copy < 0) {

            symbol = -1;
            copy = -1 * copy;

        }

        int n = (copy + "").length();

        for (int i = (n - 1); i > -1; i--) {

            byte temp = (byte) (copy % 10);

            this.data[i] = (byte) (temp * symbol);

            copy = (copy - temp) / 10;

        }

        this.data = new byte[n];

    }

    /**
     *
     * @param number Long integer of the number.
     */
    public BigNumber(long number) {

        long copy = number;

        byte symbol = 1;

        if (copy < 0) {

            symbol = -1;
            copy = -1 * copy;

        }

        int n = (copy + "").length();

        for (int i = (n - 1); i > -1; i--) {

            byte temp = (byte) (copy % 10);

            this.data[i] = (byte) (temp * symbol);

            copy = (copy - temp) / 10;

        }

        this.data = new byte[n];

    }

    public void shiftL(int n) {

        int oldLength = this.data.length;
        int newLength = this.data.length + n;

        byte[] newData = new byte[newLength];

        for (int i = 0; i < oldLength; i++) {

            newData[i] = this.data[i];

        }

    }

    public void shiftL() {

        this.shiftL(1);

    }

    public void shiftR(int n) {

        int oldLength = this.data.length;
        int newLength = this.data.length - n;

        byte[] newData = new byte[newLength];

        for (int i = 0; i < newLength; i++) {

            newData[i] = this.data[i];

        }
    }

    public void shiftR() {

        this.shiftR(1);

    }

    public String toString() {

        String out = "";

        if (this.data.length != 0) {

            if (this.data[0] < 0) {

                out = "-";

            }

        }

        for (int digit : this.data) {

            out = out + (Math.abs(digit));

        }

        return out;
    }

}
