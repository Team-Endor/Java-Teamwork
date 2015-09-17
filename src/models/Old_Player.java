package models;

import gfx.Assets;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Old_Player {         // TODO think whether to create class Entity

    private static final int velocitySpeed = 3; // Player moves with 2 px left/right/up/down
    private static final int boundingBoxWidth = 95;
    private static final int boundingBoxHeight = 130;

    public int x;
    public int y;
    public int health;
    private int velocity;

    public static boolean movingUp;
    public static boolean movingDown;
    public static boolean movingLeft;
    public static boolean movingRight;

    private BufferedImage playerImage;
    private Rectangle boundingBox;                  // used in collisions

    public Old_Player(int x, int y, int health) {
        this.x = x;
        this.y = y;
        this.health = health;

        this.velocity = velocitySpeed;
        movingDown = false;
        movingLeft = false;
        movingRight = false;
        movingUp = false;

//        this.playerImage = Assets.player1;

        this.boundingBox = new Rectangle(this.x, this.y);
    }

    public boolean intersects(Rectangle r) {         // check for collision
        if (this.boundingBox.x + this.boundingBox.width >= r.x &&
                this.boundingBox.y + this.boundingBox.height >= r.y &&
                this.boundingBox.y <= r.y + r.height &&
                this.boundingBox.x <= r.x + r.width){
            return true;
        }
        return false;

//        if( r.contains(this.boundingBox) &&
//                this.boundingBox.contains(r))
//        {
//            return true;
//        }
//        return false;
    }

    public void tick() {
        this.boundingBox.setBounds(this.x, this.y, boundingBoxWidth, boundingBoxHeight);

        //this.x += this.velocity;                          // velocity of player, when used in one direction

        if (movingUp) {
            this.y -= velocity;
        }
        if (movingDown) {
            this.y += velocity;
        }
        if (movingLeft) {
            this.x -= velocity;
        }
        if (movingRight) {
            this.x += velocity;
        }

    }

    public void render(Graphics g) {
        g.drawImage(this.playerImage, this.x, this.y, null);
    }
}
