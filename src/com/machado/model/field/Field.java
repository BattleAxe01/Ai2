package com.machado.model.field;

import com.machado.model.field.line.Line;
import com.machado.model.field.line.LinePoint;
import processing.core.PApplet;

import java.util.Collection;
import java.util.LinkedList;

public class Field {

    public static final int x = 10;
    public static final int y = 10;
    public static final int width = 800;
    public static final int height = 800;
    private static final int amoPoint = 100;

    private PApplet view;
    private Collection<Point> points = new LinkedList<>();
    private Line line;

    public Field(PApplet view) {
        this.view = view;

        line = new Line(view);
        for (int i = 0; i < amoPoint; i++) points.add(new Point(view));
        updatePointState();
    }

    public void draw() {
        view.pushMatrix();
        view.translate(x, y);

        drawMe();
        drawPoints();
        line.draw();

        view.popMatrix();
    }

    private void drawMe() {
        view.stroke(0);
        view.fill(100, 50);
        view.rect(0, 0, width, height);
    }

    private void drawPoints() {
        for (Point p : points) p.draw();
    }

    private void updatePointState() {
        points.forEach(p -> p.setState(line));
    }

    public void mousePressed(float mX, float mY) {
        final float mx = mX - x;
        final float my = mY - y;

        line.mousePressed(mx, my);
    }

    public void mouseDragged(float mX, float mY) {
        final float mx = mX - x;
        final float my = mY - y;

        if (line.isClicked()) {
            line.mouseDragged(mx, my);
            updatePointState();
        }
    }

    public void mouseReleased() {
        if (line.isClicked()) {
            line.mouseReleased();
            updatePointState();
        }
    }

    public boolean inside(float cX, float cY) {
        return Field.x - LinePoint.radius <= cX && cX <= Field.x + Field.width + LinePoint.radius &&
                Field.y - LinePoint.radius <= cY && cY <= Field.y + Field.height + LinePoint.radius;
    }

    public boolean isClicked() {
        return line.isClicked();
    }
}
