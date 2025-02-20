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
    
    public void GapBuffer() {
        gapBeg = 0;
        afterGapBeg = length - 1;
    }
    
    private void doubleArray() {
       char[] temp = arr;
       length = length * 2;
       arr = new char[length];
       
       for(int i = 0; i < gapBeg; i++) {
           arr[i] = temp[i];
       }
       
       for(int i = gapBeg; i < length / 2 + gapBeg; i++) {
           arr[i] = '\0';
       }
       
       for(int i = gapBeg + length / 2; i < length; i++) {
           arr[i] = temp[i - length / 2];
       }
    }
    
    public void insert(char ch) {
        if (gapBeg > afterGapBeg) {
            doubleArray();
        }
        arr[gapBeg] = ch;
        gapBeg++;
        size++;
        
    }

    public void delete() {
       if(gapBeg != 0) {
        arr[gapBeg - 1] = '\0';
        gapBeg--;
        size--;
        }
    }

    /**
     * Returns the position of the cursor in the array.
     * @return int : The position of the cursor in the array 
     */
    public int getCursorPosition() {
        return gapBeg;
    }

    /**
     * Moves the cursor one position to the left
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
     * Moves the cursor one position to the right
     */
    public void moveRight() {
        if (gapBeg < length && gapBeg < afterGapBeg) {
            arr[gapBeg] = arr[afterGapBeg + 1];
            arr[afterGapBeg + 1] = '\0';
            gapBeg++;
            afterGapBeg++;
        } else if (gapBeg < length) {
            gapBeg++;
            afterGapBeg++;
        }
    }

    /**
     * Returns the size of the array
     * @return int : Returns the size of the array
     */
    public int getSize() {
        return size;
    }

    public char getChar(int i) {
       if(i > size) {
             throw new IndexOutOfBoundsException();
        } 
        int j = 0;
        for(int k = 0; k < length; k++) {    
            if(j == i) {
                return arr[k];
            }
            if(arr[k] != '\0') {
                j++;
            }
        }
        return '\0';
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < length; i++) {
            if(arr[i] != '\0') {
                s = s + arr[i];
            }
            
        }
        return s;
    }
}
