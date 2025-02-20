package edu.grinnell.csc207.texteditor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import net.jqwik.api.*;
import net.jqwik.api.constraints.*;

public class SimpleStringBufferTests {

    @Test
    public void testAddEmptyDeleteBeginning() {
        SimpleStringBuffer buffer = new SimpleStringBuffer();
        buffer.insert('a');
        assertEquals(1, buffer.getCursorPosition());
        assertEquals("a", buffer.toString());
        buffer.delete();
        assertEquals("", buffer.toString());
    }

    @Test
    public void testAddEndDeleteEnd() {
        SimpleStringBuffer buffer = new SimpleStringBuffer();
        buffer.insert('a');
        buffer.insert('b');
        assertEquals(2, buffer.getCursorPosition());
        buffer.insert('c');
        assertEquals("abc", buffer.toString());
        buffer.delete();
        assertEquals("ab", buffer.toString());
    }

    @Test
    public void testMoveCursorAddMiddleDeleteMiddle() {
        SimpleStringBuffer buffer = new SimpleStringBuffer();
        buffer.insert('a');
        buffer.insert('b');
        buffer.insert('c');
        assertEquals(3, buffer.getCursorPosition());
        buffer.moveLeft();
        buffer.moveLeft();
        assertEquals(1, buffer.getCursorPosition());
        buffer.moveRight();
        assertEquals(2, buffer.getCursorPosition());
        buffer.delete();
        assertEquals("ac", buffer.toString());
    }

    @Test
    public void testGetCharGetSize() {
        SimpleStringBuffer buffer = new SimpleStringBuffer();
        buffer.insert('a');
        buffer.insert('b');
        buffer.insert('c');
        assertEquals('b', buffer.getChar(1));
        assertEquals(3, buffer.getSize());
    }

    @Property
    public boolean testCursorMovement(@ForAll @IntRange(min = 0, max = 1000) int sz) {
        SimpleStringBuffer buffer = new SimpleStringBuffer();
        for (int i = 0; i < sz; i++) {
            buffer.insert('a');
        }
        return buffer.getCursorPosition() == sz;
    }

}
