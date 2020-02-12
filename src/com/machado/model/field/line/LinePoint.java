package com.machado.model.field.line;

import com.machado.model.field.Field;
import com.machado.model.field.Point;
import processing.core.PApplet;

public class LinePoint extends Point {

    public static float radius = 5;

    float x;
    float y = super.y;
    private boolean clicked = false;

    public LinePoint(PApplet view) {
        super(view);

        this.x = 0;
    }

    @Override
    public void draw() {
        super.drawConfig();
        view.circle(x, y, radius);
    }

    @Override
    public void mousePressed() {
        radius *= 2;
        clicked = true;
    }

    public void mouseDragged(float mX, float mY) {
        if (0 <= mY && mY <= Field.height) y = mY;
    }

    public void mouseReleased() {
        radius /= 2;
        clicked = false;
    }

    @Override
    public boolean inside(float cX, float cY) {
        return Math.sqrt(Math.pow(cX - x, 2) + Math.pow(cY - y, 2)) <= radius;
    }

    public boolean isClicked() {
        return clicked;
    }
}
