package edu.grinnell.csc207.texteditor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import net.jqwik.api.*;
import net.jqwik.api.constraints.*;

public class GapBufferTests {

    @Test
    public void testAddEmptyDeleteBeginning() {
        GapBuffer buffer = new GapBuffer();
        buffer.insert('a');
        assertEquals(1, buffer.getCursorPosition());
        assertEquals("a", buffer.toString());
        buffer.delete();
        assertEquals(0, buffer.getCursorPosition());
        assertEquals("", buffer.toString());
    }

    @Test
    public void testAddEndDeleteEnd() {
        GapBuffer buffer = new GapBuffer();
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
        GapBuffer buffer = new GapBuffer();
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
        GapBuffer buffer = new GapBuffer();
        buffer.insert('a');
        buffer.insert('b');
        buffer.insert('c');
        assertEquals('b', buffer.getChar(1));
        assertEquals(3, buffer.getSize());
        buffer.delete();
        assertEquals(2, buffer.getSize());
    }

    @Test
    public void testDoubleArray() {
        GapBuffer buffer = new GapBuffer();
        buffer.insert('a');
        buffer.insert('b');
        buffer.insert('c');
        buffer.insert('d');
        buffer.insert('e');
        assertEquals("abcde", buffer.toString());
        assertEquals(5, buffer.getCursorPosition());
        buffer.moveLeft();
        buffer.moveLeft();
        buffer.moveLeft();
        assertEquals(2, buffer.getCursorPosition());
        buffer.insert('x');
        assertEquals("abxcde", buffer.toString());
    }

    @Property
    public boolean testCursorMovement(@ForAll @IntRange(min = 0, max = 10) int sz) {
        GapBuffer buffer = new GapBuffer();
        for (int i = 0; i < sz; i++) {
            buffer.insert('a');
        }
        return buffer.getCursorPosition() == sz;
    }
}
