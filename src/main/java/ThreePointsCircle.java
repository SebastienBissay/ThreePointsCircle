import processing.core.PApplet;
import processing.core.PVector;

import static parameters.Parameters.*;
import static save.SaveUtil.saveSketch;

public class ThreePointsCircle extends PApplet {

    private final Point[] points = new Point[3];

    public static void main(String[] args) {
        PApplet.main(ThreePointsCircle.class);
    }

    @Override
    public void settings() {
        size(WIDTH, HEIGHT);
        randomSeed(SEED);
    }

    @Override
    public void setup() {
        background(BACKGROUND_COLOR.red(), BACKGROUND_COLOR.green(), BACKGROUND_COLOR.blue());
        stroke(STROKE_COLOR.red(), STROKE_COLOR.green(), STROKE_COLOR.blue(), STROKE_COLOR.alpha());
        noFill();
        frameRate(FRAMERATE);

        for (int i = 0; i < points.length; i++) {
            points[i] = new Point(this);
        }
    }

    @Override
    public void draw() {
        if (frameCount % FADE_DELAY == 0) {
            blendMode(MULTIPLY);
            fill(BACKGROUND_COLOR.red(), BACKGROUND_COLOR.green(), BACKGROUND_COLOR.blue(), FADE_ALPHA);
            rect(0, 0, WIDTH, HEIGHT);
            noFill();
            blendMode(LIGHTEST);
        }

        for (Point point : points) {
            point.move();
        }

        Matrix mat = new Matrix();
        mat.setValue(0, 0, points[0].getNormSquared());
        mat.setValue(0, 1, points[0].getY());
        mat.setValue(0, 2, 1);
        mat.setValue(1, 0, points[1].getNormSquared());
        mat.setValue(1, 1, points[1].getY());
        mat.setValue(1, 2, 1);
        mat.setValue(2, 0, points[2].getNormSquared());
        mat.setValue(2, 1, points[2].getY());
        mat.setValue(2, 2, 1);

        float sx = 0.5f * mat.getDeterminant();

        mat.setValue(0, 0, points[0].getX());
        mat.setValue(0, 1, points[0].getNormSquared());
        mat.setValue(1, 0, points[1].getX());
        mat.setValue(1, 1, points[1].getNormSquared());
        mat.setValue(2, 0, points[2].getX());
        mat.setValue(2, 1, points[2].getNormSquared());

        float sy = 0.5f * mat.getDeterminant();

        mat.setValue(0, 1, points[0].getY());
        mat.setValue(1, 1, points[1].getY());
        mat.setValue(2, 1, points[2].getY());

        float a = mat.getDeterminant();

        mat.setValue(0, 2, points[0].getNormSquared());
        mat.setValue(1, 2, points[1].getNormSquared());
        mat.setValue(2, 2, points[2].getNormSquared());

        float b = mat.getDeterminant();

        PVector s = new PVector(sx / a, sy / a);
        float radius = sqrt(b / a + (sq(sx) + sq(sy)) / sq(a));

        circle(s.x, s.y, 2 * radius);
    }

    @Override
    public void keyPressed() {
        if (key == ' ') {
            saveSketch(this);
        }
    }
}
