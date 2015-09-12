package game;

import display.Display;
import javafx.scene.input.KeyCode;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener{

    public InputHandler(Display display){
        display.getCanvas().addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(keyCode == KeyEvent.VK_UP){
            Player.movingUp = true;
        }
        if(keyCode == KeyEvent.VK_DOWN){
            Player.movingDown = true;
        }
        if(keyCode == KeyEvent.VK_LEFT){
            Player.movingLeft = true;
        }
        if(keyCode == KeyEvent.VK_RIGHT){
            Player.movingRight = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(keyCode == KeyEvent.VK_UP){
            Player.movingUp = false;
        }
        if(keyCode == KeyEvent.VK_DOWN){
            Player.movingDown = false;
        }
        if(keyCode == KeyEvent.VK_LEFT){
            Player.movingLeft = false;
        }
        if(keyCode == KeyEvent.VK_RIGHT){
            Player.movingRight = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
