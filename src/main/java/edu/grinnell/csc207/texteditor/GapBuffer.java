package edu.grinnell.csc207.texteditor;

/**
 * A gap buffer-based implementation of a text buffer.
 */
public class GapBuffer {

    int length = 5;

    int size = 0;

    int gapBeg = 0;

    int afterGapBeg = length - 1;

    char[] arr = new char[length];

    /**
     * Creates baseline for GapBuffer.
     */
    public void gapBuffer() {
        gapBeg = 0;
        afterGapBeg = length - 1;
    }

    /**
     * Private helper method that doubles the storage of the array for
     * GapBuffer. This function also organizes all values originally in the
     * array to the correct location after the change in size.
     */
    private void doubleArray() {
        char[] temp = arr;
        length = length * 2;
        arr = new char[length];

        for (int i = 0; i < gapBeg; i++) {
            arr[i] = temp[i];
        }

        for (int i = gapBeg; i < length / 2 + gapBeg; i++) {
            arr[i] = '\0';
        }

        for (int i = gapBeg + length / 2; i < length; i++) {
            arr[i] = temp[i - length / 2];
        }
        
        afterGapBeg = length - (length / 2 - afterGapBeg);
    }

    /**
     * Inserts the given char into the array at the location of the cursor and
     * moves the cursor forward one.
     *
     * @param ch : A character to be inserted in the array.
     */
    public void insert(char ch) {
        if (gapBeg > afterGapBeg) {
            doubleArray();
        }
        arr[gapBeg] = ch;
        gapBeg++;
        size++;

    }

    /**
     * Deletes an element of the array at the cursor and moves the cursor back
     * one unless the cursor is at the beginning of the array.
     */
    public void delete() {
        if (gapBeg != 0) {
            arr[gapBeg - 1] = '\0';
            gapBeg--;
            size--;
        }
    }

    /**
     * Returns the position of the cursor in the array.
     *
     * @return int : The position of the cursor in the array
     */
    public int getCursorPosition() {
        return gapBeg;
    }

    /**
     * Moves the cursor one position to the left and changes characters in the
     * array around if necessary.
     */
    public void moveLeft() {
        if (gapBeg > 0 && gapBeg < afterGapBeg) {
            arr[afterGapBeg] = arr[gapBeg - 1];
            arr[gapBeg - 1] = '\0';
            gapBeg--;
            afterGapBeg--;
        } else if (gapBeg > 0) {
            gapBeg--;
            afterGapBeg--;
        }
    }

    /**
     * Moves the cursor one position to the right and changes characters in the
     * array around if necessary.
     */
    public void moveRight() {
        if (gapBeg < length - 1 && gapBeg < afterGapBeg) {
            arr[gapBeg] = arr[afterGapBeg + 1];
            arr[afterGapBeg + 1] = '\0';
            gapBeg++;
            afterGapBeg++;
        } else if (gapBeg < length - 1) {
            gapBeg++;
            afterGapBeg++;
        }
    }

    /**
     * Returns the size of the array
     *
     * @return int : Returns the size of the array
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the char at the input int i or throws an index out of bounds
     * exception if i is greater than the size of the gapBuffer.
     *
     * @param i : int at which char is gotten from
     * @return ch : the char that is at the index i given by the user.
     */
    public char getChar(int i) {
        if (i > size) {
            throw new IndexOutOfBoundsException();
        }
        int j = 0;
        for (int k = 0; k < length; k++) {
            if (j == i) {
                return arr[k];
            }
            if (arr[k] != '\0') {
                j++;
            }
        }
        return '\0';
    }

    /**
     * Returns the current array in string form.
     *
     * @return String : Returns the current array in string form.
     */
    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < length; i++) {
            if (arr[i] != '\0') {
                s = s + arr[i];
            }

        }
        return s;
    }
}
