
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
     * @param @throws Exception
     */
    public BigNumber(byte[] data) throws Exception {

        for (byte digit : data) {

            if (digit > 9 || digit < -9) {

                throw new Exception("Invalid number entered!");

            }

        }

        this.data = data.clone();

    }

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

        for (byte digit : this.data) {
            out = out + digit;
        }

        return out;
    }

}
