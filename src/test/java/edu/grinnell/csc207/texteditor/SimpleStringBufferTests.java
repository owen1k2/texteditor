package edu.grinnell.csc207.texteditor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import net.jqwik.api.*;
import net.jqwik.api.constraints.*;

public class SimpleStringBufferTests {

    private SimpleStringBuffer makeBufferWith(String s) {
        SimpleStringBuffer buf = new SimpleStringBuffer();
        for (int i = 0; i < s.length(); i++) {
            buf.insert(s.charAt(i));
        }
        return buf;
    }

    @Test
    @DisplayName("Gap: delete front")
    public void cursorDeleteFrontTest() {
        SimpleStringBuffer buf = makeBufferWith("abc");
        for (int i = 0; i < 3; i++) {
            buf.moveLeft();
        }
        buf.delete();
        assertEquals(3, buf.getSize(), "size");
        assertEquals(0, buf.getCursorPosition(), "cursor");
        assertEquals("abc", buf.toString(), "contents");
    }


    @Test
    public void testAddEmptyDeleteBeginning() {
        SimpleStringBuffer buffer = new SimpleStringBuffer();
        buffer.insert('a');
        buffer.insert('b');
        buffer.insert('c');
        buffer.insert('d');
        assertEquals(4, buffer.getCursorPosition());
        assertEquals("abcd", buffer.toString());
        buffer.moveLeft();
        buffer.moveLeft();
        buffer.delete();
        assertEquals(1, buffer.getCursorPosition());
        buffer.delete();
        assertEquals(0, buffer.getCursorPosition());
        assertEquals("cd", buffer.toString());
        assertEquals(2 , buffer.getSize());
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
