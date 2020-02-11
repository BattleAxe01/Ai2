package com.machado.view;

import com.machado.model.field.Field;
import processing.core.PApplet;

public class MainLoop extends PApplet {

    Field field;

    @Override
    public void settings() {
        final int height = 820;
        size(16 * height / 9, height);
    }

    @Override
    public void setup() {
        field = new Field(this);
    }

    @Override
    public void draw() {
        background(255);

        field.draw();
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
