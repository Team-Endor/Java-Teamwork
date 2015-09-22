package physics;

import java.awt.*;

public class CollisionDetector {
    public static boolean intersects(Rectangle firstBoundingBox, Rectangle secondBoundingBox) {
        if (firstBoundingBox.x + firstBoundingBox.width >= secondBoundingBox.x &&
                firstBoundingBox.y + firstBoundingBox.height >= secondBoundingBox.y &&
                firstBoundingBox.y <= secondBoundingBox.y + secondBoundingBox.height &&
                firstBoundingBox.x <= secondBoundingBox.x + secondBoundingBox.width) {
            return true;
        }

        return false;
    }
}
