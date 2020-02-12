package com.machado.model.field.line;

import com.machado.model.field.Field;
import processing.core.PApplet;

public class Line {

    LinePoint p1, p2;

    private PApplet view;

    public Line(PApplet view) {
        this.view = view;

        p1 = new LinePoint(view);
        p2 = new LinePoint(view);
        p2.x += Field.width;
    }

    public void draw() {
        view.stroke(0);
        view.strokeWeight(3);
        view.fill(0, 50);
        view.line(p1.x, p1.y, p2.x, p2.y);

        view.fill(0);
        p1.draw();
        p2.draw();
    }

    public void mousePressed(float mX, float mY) {
        if (p1.inside(mX, mY)) p1.mousePressed();
        if (p2.inside(mX, mY)) p2.mousePressed();
    }

    public void mouseDragged(float mX, float mY) {
        if (p1.isClicked()) p1.mouseDragged(mX, mY);
        if (p2.isClicked()) p2.mouseDragged(mX, mY);
    }

    public void mouseReleased() {
        if (p1.isClicked()) p1.mouseReleased();
        if (p2.isClicked()) p2.mouseReleased();
    }

    public boolean isClicked() {
        return p1.isClicked() || p2.isClicked();
    }

    public float getA() {
        return (p1.y - p2.y) / (p1.x - p2.x);
    }

    public float getB() {
        return p1.y;
    }
}
