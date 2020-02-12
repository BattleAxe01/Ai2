package com.machado.view;

import com.machado.controller.Brain;
import com.machado.model.field.Field;
import processing.core.PApplet;

public class MainLoop extends PApplet {

    private static final int fps = 5;

    Field field;
    Brain brain;

    @Override
    public void settings() {
        final int height = 820;
        size(16 * height / 9, height);
    }

    @Override
    public void setup() {
        frameRate(fps);

        field = new Field(this);
        brain = new Brain(this);
    }

    @Override
    public void draw() {
        background(255);

        field.draw();
        brain.draw();

        brain.train(field.getPoints());
    }

    @Override
    public void mousePressed() {
        if (field.inside(mouseX, pmouseY)) field.mousePressed(mouseX, mouseY);
    }

    @Override
    public void mouseDragged() {
        if (field.isClicked()) field.mouseDragged(mouseX, mouseY);
    }

    @Override
    public void mouseReleased() {
        if (field.isClicked()) field.mouseReleased();
    }
}
