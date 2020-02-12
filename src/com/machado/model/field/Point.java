package com.machado.model.field;

import com.machado.controller.Brain;
import com.machado.model.Neuron;
import com.machado.model.field.line.Line;
import processing.core.PApplet;
import processing.core.PConstants;

import java.awt.*;

public class Point {

    public static final float radius = 10;

    protected float x;
    protected float y;
    private State color;
    private double iaGuess = 0.5;

    protected PApplet view;

    public Point(PApplet view) {
        this.view = view;

        this.x = (float) (Math.random() * Field.width);
        this.y = (float) (Math.random() * Field.height);
    }

    protected void drawConfig() {
        if (color == State.UP) view.fill(255, 0, 0);
        else if (color == State.DOWN) view.fill(0, 255, 0);
        else view.fill(0);

        view.ellipseMode(PConstants.RADIUS);
    }

    public void draw() {
        drawConfig();
        if (Brain.drawIaGuess) {
            if (color == State.UP) view.fill(view.lerpColor(Color.green.getRGB(), Color.red.getRGB(), (float) iaGuess));
            else if (color == State.DOWN)
                view.fill(view.lerpColor(Color.red.getRGB(), Color.green.getRGB(), (float) iaGuess));
        }
        view.circle(x, y, radius);
    }

    public void setColor(Line line) {
        if (line.getA() * x + line.getB() < y) color = State.UP;
        else if (line.getA() * x + line.getB() > y) color = State.DOWN;
        else color = State.ON;
    }

    public State getColor() {
        return color;
    }

    public void mousePressed() {
        Neuron.displayData = getData();
    }

    public boolean inside(float cX, float cY) {
        return Math.sqrt(Math.pow(cX - x, 2) + Math.pow(cY - y, 2)) <= radius;
    }

    public void setIaGuess(double iaGuess) {
        this.iaGuess = iaGuess;
    }

    public double[] getData() {
        return new double[]{PApplet.map(x, 0, 800, 0, 1), PApplet.map(y, 0, 800, 0, 1)};
    }
}
