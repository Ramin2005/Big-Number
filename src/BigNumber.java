
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
    public BigNumber(boolean isPositive, byte[] data) throws RuntimeException {

        // validation list
        for (byte digit : data) {

            // checking is digit has be between -9 and 9
            if (digit > 9 || digit < 0) {

                // throw exception if a digit not be between -9 and 9
                throw new RuntimeException("Invalid number entered!");

            }

        }

        // pointer and counters for restoring start and end position
        int i = 0;
        int j = data.length;

        // moving pointer for remove zero digits from the of list 
        while (i < j) {

            if (data[i] == 0) {

                // moving pointer
                ++i;

            } else {

                // break while statement
                break;

            }

        }

        // temp list for copying data
        byte[] temp = new byte[j - i];

        // copying list data
        for (int k = 0; k < j - i; k++) {

            // copying data
            temp[k] = data[k + i];

        }

        // cloning and setting list data, length and symbol
        // clone data list
        this.data = temp.clone();
        // set number length
        this.length = j - i + 1;
        // set symbol
        this.isPositive = isPositive;

    }

    /**
     *
     * @param number String of the number
     * @throws RuntimeException If string is invalid or can't cast to digits
     */
    public BigNumber(String number) throws RuntimeException {

        byte symbol = 1;

        // pointer and counters for restoring start and end position
        int stringLength = number.length();
        int i = 0;

        // checking for number is not empty or null
        if (stringLength > 0) {

            // if number is negative moving pointer and set symbol negative
            if (number.charAt(0) == '-') {

                // set symbol
                symbol = -1;

                // moving pointer 
                i = 1;

            }

        }

        // moving pointer for remove zero digits from the of list 
        // checking for number is not empty or null
        if (stringLength - i > 0) {

            while (i < stringLength) {

                // get character at index i
                char tempChar = number.charAt(i);

                // checking char to be digit
                if (!Character.isDigit(tempChar)) {

                    // throw exception
                    throw new RuntimeException("Invalid number entered!");

                }
                // checking 
                if (tempChar == '0') {

                    // moving pointer
                    ++i;

                } else {

                    // break while statement
                    break;

                }

            }

        }

        // temp list for copying data
        byte[] temp = new byte[stringLength - i];

        // extract and copying list data
        for (int k = 0; k < stringLength - i; k++) {

            // get character at index i + k
            char tempChar = number.charAt(i + k);

            // checking char to be digit
            if (!Character.isDigit(tempChar)) {

                // throw exception
                throw new RuntimeException("Invalid number entered!");

            }

            // extracting and copying data
            temp[k] = (byte) Integer.parseInt(tempChar + "");
        }

        // cloning and setting list data, length and symbol
        // clone data list
        this.data = temp.clone();
        // set number length
        this.length = temp.length;
        // set symbol
        this.isPositive = symbol > 0;

    }

    /**
     *
     * @param number Integer of the number
     */
    public BigNumber(int number) {

        // converting integer to string for extracting data
        String stringNumber = number + "";
        byte symbol = 1;

        // pointer and counters for restoring start and end position
        int stringLength = stringNumber.length();
        int i = 0;

        // checking for number is not empty or null
        if (stringLength > 0) {

            // if number is negative moving pointer and set symbol negative
            if (stringNumber.charAt(0) == '-') {

                // set symbol
                symbol = -1;

                // moving pointer 
                i = 1;

            }

        }

        // temp list for copying data
        byte[] temp = new byte[length];

        // extract and copying list data
        for (; i < stringLength; i++) {

            // get character at index i
            char tempChar = stringNumber.charAt(i);

            // extracting and copying data
            temp[i] = (byte) Integer.parseInt(tempChar + "");

        }

        // cloning and setting list data, length and symbol
        // clone data list
        this.data = temp.clone();
        // set number length
        this.length = stringLength - 1;
        // set symbol
        this.isPositive = symbol > 0;
    }

    /**
     *
     * @param number Long integer of the number
     */
    public BigNumber(long number) {

        // converting long to string for extracting data
        String stringNumber = number + "";
        byte symbol = 1;

        // pointer and counters for restoring start and end position
        int stringLength = stringNumber.length();
        int i = 0;

        // checking for number is not empty or null
        if (stringLength > 0) {

            // if number is negative moving pointer and set symbol negative
            if (stringNumber.charAt(0) == '-') {

                // set symbol
                symbol = -1;

                // moving pointer 
                i = 1;

            }

        }

        // temp list for copying data
        byte[] temp = new byte[length];

        // extract and copying list data
        for (; i < stringLength; i++) {

            // get character at index i
            char tempChar = stringNumber.charAt(i);

            // extracting and copying data
            temp[i] = (byte) Integer.parseInt(tempChar + "");

        }

        // cloning and setting list data, length and symbol
        // clone data list
        this.data = temp.clone();
        // set number length
        this.length = stringLength - 1;
        // set symbol
        this.isPositive = symbol > 0;

    }

    // non-static methods:
    /**
     * Returns copy of data array;
     *
     * @return byte array - Copy of data
     */
    public byte[] getData() {

        // returning clone of list
        return this.data.clone();

    }

    /**
     * Returns true if number is positive, zero or null else returns false;
     *
     * @return boolean
     */
    public boolean isPositive() {

        // returning positive 
        return this.isPositive;

    }

    /**
     *
     * @return Negative BigNumber of number
     */
    public BigNumber getNegative() throws RuntimeException {

        // get clone of data
        byte[] tempData = this.data.clone();

        // setting negative each digit
        for (int i = 0; i < tempData.length; i++) {

            // setting negative
            tempData[i] *= -1;

        }

        // get BigNumber of negative list
        BigNumber negative = new BigNumber(this.isPositive, tempData);

        // returning negative obj
        return negative;

    }

    /**
     *
     * @return String of number
     */
    @Override
    public String toString() {

        // declare out string
        String out = "";

        // checking for number is null
        if (this.length != 0) {

            // checking symbol
            if (!this.isPositive) {

                // add '-' to end of string
                out = "-";

            }

        }

        // adding each digit to right of out string
        for (int digit : this.data) {

            // adding absolute of digit to right of out string
            out = out + (Math.abs(digit));

        }

        // returning out string
        return out;

    }

    // self shift methods: 
    /**
     *
     * Shift the number n digits to the left(multiply number to 10^n).
     *
     * @param n Number of shift digits
     */
    public void selfShiftL(int n) {

        // get old length
        int oldLength = this.length;
        // new length
        int newLength = oldLength + n;
        // declare new data list 
        byte[] newData = new byte[newLength];

        // copying all list data to the left of new list
        System.arraycopy(this.data, 0, newData, 0, oldLength);

        // set data list
        this.data = newData.clone();
        // set new size
        this.length = newLength;

    }

    /**
     *
     * Shift the number one digit to the left(multiply number to 10).
     */
    public void selfShiftL() {

        // call self shift left method with argument 1 to shift the number one digit to the left
        this.selfShiftL(1);

    }

    /**
     *
     * Shift the number n digits to the right(divide number by 10^n).
     *
     * @param n Number of shift digits
     */
    public void selfShiftR(int n) {

        // get old length
        int oldLength = this.length;
        // new lengths
        int newLength = oldLength - n;

        // checking for new length must be greater than zero
        if (newLength < 1) {

            // set data list null
            this.data = null;
            // set length zero
            this.length = 0;
            // set positive symbol true
            this.isPositive = true;

        } else {

            // declare new data list 
            byte[] newData = new byte[newLength];

            // copying data 
            System.arraycopy(this.data, 0, newData, 0, newLength);

            // set data list
            this.data = newData.clone();
            // set new size
            this.length = newLength;

        }

    }

    /**
     *
     * Shift the number one digits to the right(divide number by 10).
     */
    public void selfShiftR() {

        // call self shift right method with argument 1 to shift the number one digit to the right
        this.selfShiftR(1);

    }

    // returning shift methods: 
    /**
     *
     * Shift the number n digits to the left(multiply number to 10^n).
     *
     * @param n Number of shift digits
     */
    public BigNumber shiftL(int n) {
        // get old length
        int oldLength = this.length;
        // new length
        int newLength = oldLength + n;
        // declare new data list 
        byte[] newData = new byte[newLength];

        // copying all list data to the left of new list
        System.arraycopy(this.data, 0, newData, 0, oldLength);

        return new BigNumber(this.isPositive, newData);

    }

    /**
     *
     * Shift the number one digit to the left(multiply number to 10).
     */
    public BigNumber shiftL() {

        // call shift left method with argument 1 to return one digit shifted to the left number
        return this.shiftL(1);

    }

    /**
     *
     * Shift the number n digits to the right(divide number by 10^n).
     *
     * @param n Number of shift digits
     */
    public BigNumber shiftR(int n) {

        // get old length
        int oldLength = this.length;
        // new lengths
        int newLength = oldLength - n;

        // checking for new length must be greater than zero
        if (newLength < 1) {

            // returning null BigNumber
            return new BigNumber();

        } else {

            // declare new data list 
            byte[] newData = new byte[newLength];

            // copying data 
            System.arraycopy(this.data, 0, newData, 0, newLength);

            // returning BigNumber with new data list
            return new BigNumber(this.isPositive, newData);

        }

    }

    /**
     *
     * Shift the number one digits to the right(divide number by 10).
     */
    public BigNumber shiftR() {

        // call shift right method with argument 1 to return one digit shifted to the right number
        return this.shiftR(1);

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
