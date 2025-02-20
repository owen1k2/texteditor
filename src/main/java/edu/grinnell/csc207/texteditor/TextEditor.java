package edu.grinnell.csc207.texteditor;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The driver for the TextEditor Application.
 */
public class TextEditor {

    /**
     * Private helper function that updates the screen with the letter just
     * typed.
     *
     * @param buf the GapBuffer that is being used to store the characters
     * typed.
     * @param screen the screen that is being used to display the GapBuffer
     * @throws IOException
     */
    private static void drawBuffer(GapBuffer buf, Screen screen) throws IOException {
        buf.toString();
        screen.refresh();
    }

    /**
     * The main entry point for the TextEditor application.
     *
     * @param args command-line arguments.
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        DefaultTerminalFactory factory = new DefaultTerminalFactory();
        Screen screen = factory.createScreen();
        GapBuffer buffer = new GapBuffer();

        if (args.length != 1) {
            System.err.println("Usage: java TextEditor <filename>");
            System.exit(1);
        }

        // TODO: fill me in with a text editor TUI!
        String path = args[0];
        if (Files.exists(Paths.get(path))) {
            if (Files.isRegularFile(Paths.get(path))) {
                System.out.format("Loading %s...\n", Files.readString(Paths.get(path)));
            }
        }

        screen.startScreen();
        boolean isRunning = true;

        while (isRunning) {
            KeyStroke stroke = screen.readInput();
            if (null != stroke.getKeyType()) {
                switch (stroke.getKeyType()) {
                    case Character:
                        buffer.insert(stroke.getCharacter());
                        screen.setCharacter(buffer.getCursorPosition(), 0,
                                new TextCharacter(stroke.getCharacter()));
                        screen.setCursorPosition(
                                new TerminalPosition(buffer.getCursorPosition(), 0));
                        break;
                    case ArrowLeft:
                        buffer.moveLeft();
                        screen.setCursorPosition(
                                new TerminalPosition(buffer.getCursorPosition(), 0));
                        break;
                    case ArrowRight:
                        buffer.moveRight();
                        screen.setCursorPosition(
                                new TerminalPosition(buffer.getCursorPosition(), 0));
                        break;
                    case Backspace:
                        buffer.delete();
                        screen.setCursorPosition(
                                new TerminalPosition(buffer.getCursorPosition(), 0));
                        break;
                    default:
                        isRunning = false;
                        break;
                }
            }
            drawBuffer(buffer, screen);
        }

        screen.stopScreen();
        Files.writeString(Paths.get(path), buffer.toString());
    }
}
