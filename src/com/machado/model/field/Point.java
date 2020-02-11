package com.machado.model.field;

import com.machado.model.field.line.Line;
import processing.core.PApplet;
import processing.core.PConstants;

public class Point {

    public static final float radius = 10;

    protected float x;
    protected float y;
    State state;

    protected PApplet view;

    public Point(PApplet view) {
        this.view = view;

        this.x = (float) (Math.random() * Field.width);
        this.y = (float) (Math.random() * Field.height);
    }

    protected void drawConfig() {
        if (state == State.UP) view.fill(255, 0, 0);
        else if (state == State.DOWN) view.fill(0, 255, 0);
        else view.fill(0);

        view.ellipseMode(PConstants.RADIUS);
    }

    public void draw() {
        drawConfig();
        view.circle(x, y, radius);
    }

    public boolean inside(float cX, float cY) {
        return Math.sqrt(Math.pow(cX - x, 2) + Math.pow(cY - y, 2)) <= radius;
    }

    public void setState(Line line) {
        if (line.getA() * x + line.getB() < y) state = State.UP;
        else if (line.getA() * x + line.getB() > y) state = State.DOWN;
        else state = State.ON;
    }
}
