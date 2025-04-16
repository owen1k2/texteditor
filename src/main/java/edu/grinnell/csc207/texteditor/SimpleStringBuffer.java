package edu.grinnell.csc207.texteditor;

/**
 * A naive implementation of a text buffer using a <code>String</code>.
 */
public class SimpleStringBuffer {

    String s = "";
    
    int cursor;

    /**
     * Creates a new and empty SimpleStringBuffer.
     */
    public void simpleStringBuffer() {
        cursor = 0;
        s = "";
    }

    /**
     * Inserts the given character at the cursors given position and moves the
     * cursor forward one space.
     *
     * @param ch : The character to be inserted
     */
    public void insert(char ch) {
        if (s == null) {
            s = ch + "";
        } else if (cursor == s.length()) {
            s = s + ch;
        } else if (cursor == 0) {
            s = ch + s;
        } else {
            s = s.substring(0, cursor) + ch + s.substring(cursor, s.length());
        }
        cursor++;
    }

    /**
     * Deletes the character at the cursors given position and moves the cursor
     * back one space if not already there.
     */
    public void delete() {
        if (cursor == s.length()) {
            s = s.substring(0, s.length() - 1);
            cursor--;
        } else if (cursor == 0) {
            s = s.substring(0, s.length());
        } else {
            s = s.substring(0, cursor - 1) + s.substring(cursor, s.length());
            cursor--;
        }
    }

    /**
     * Returns the current position of the cursor.
     *
     * @return int : The position of the cursor.
     */
    public int getCursorPosition() {
        return cursor;
    }

    /**
     * Moves the cursor one position to the left.
     */
    public void moveLeft() {
        if (cursor > 0) {
            cursor--;
        }
    }

    /**
     * Moves the cursor one position to the right.
     */
    public void moveRight() {
        if (cursor < s.length()) {
            cursor++;
        }
    }

    /**
     * Returns the size of the SimpleStringBuffer
     *
     * @return int : the size of SimpleStringBuffer.
     */
    public int getSize() {
        return s.length();
    }

    /**
     * Returns the char from SimpleStringBuffer at index i
     *
     * @param i : int, the index of which the char is returned from
     * @return char : The char in the string SimpleStringBuffer at index i.
     */
    public char getChar(int i) {
        if (i > s.length()) {
            throw new IndexOutOfBoundsException();
        }
        return s.charAt(i);
    }

    /**
     * Returns the contents of SimpleStringBuffer
     *
     * @return String : Returns the string that is the SimpleStringBuffer
     */
    @Override
    public String toString() {
        return s;
    }
}
