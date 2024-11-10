import processing.core.PApplet;
import processing.core.PVector;

import static parameters.Parameters.HEIGHT;
import static parameters.Parameters.WIDTH;
import static processing.core.PApplet.cos;
import static processing.core.PApplet.sin;
import static processing.core.PConstants.PI;
import static processing.core.PConstants.TWO_PI;

public class Point {
    private final PVector position;
    private final float velocity;
    private float angle;

    public Point(PApplet pApplet) {
        position = new PVector(pApplet.random(WIDTH), pApplet.random(HEIGHT));
        velocity = pApplet.random(1, 3);
        angle = pApplet.random(TWO_PI);
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getNormSquared() {
        return position.magSq();
    }

    public void move() {
        position.x += velocity * cos(angle);
        if (position.x < 0 || position.x > WIDTH) {
            angle = PI - angle;
            position.x += velocity * cos(angle);
        }
        position.y += velocity * sin(angle);
        if (position.y < 0 || position.y > HEIGHT) {
            angle *= -1;
            position.y += velocity * sin(angle);
        }
    }
}
