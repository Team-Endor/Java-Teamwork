package display;

import javax.swing.*;
import java.awt.*;

public class Display extends Canvas {
    private int    width;
    private int    height;
    private String title;

    private JFrame frame;
    private Canvas canvas;

    public Display(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;

        createFrame();
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    public Frame getFrame() {
        return this.frame;
    }

    private void createFrame() {
        frame = new JFrame(this.title);

        // Jrame settings:
        frame.setSize(this.width, this.height);   // frame size e.g. 800X600
        frame.setResizable(false);                // the frame cannot be resized
        frame.setVisible(true);                   // by default all frames are INVISIBLE
        frame.setLocationRelativeTo(null);        // makes frame in the centre of the monitor
        frame.setFocusable(true);                 // clicking ot the frame gives it a focus
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the game, not to run in background when exited
        frame.requestFocus();

        canvas = new Canvas();

        // Canvas settings:
        canvas.setPreferredSize(new Dimension(this.width, this.height)); // setPreferredSize only works with Dimension class
        canvas.setMaximumSize(new Dimension(this.width, this.height));   // this it to insure Canvas will be in the Frame
        canvas.setMinimumSize(new Dimension(this.width, this.height));   // this it to insure Canvas will be in the Frame

        frame.add(canvas);
        frame.pack();                             // creates a new entity
    }
}
