package com.machado.model;

import com.machado.model.field.Field;
import processing.core.PApplet;
import processing.core.PConstants;

public class Neuron {

    private final int size;
    public double bias;
    public double[] weight;

    public static double[] displayData = new double[]{0.5, 0.5};

    public Neuron(int size) {
        this.bias = Math.random();
        this.size = size;

        weight = new double[size];
        for (int i = 0; i < size; i++) weight[i] = Math.random();
    }

    public double guess(double[] input) {
        double sum = bias;
        for (int i = 0; i < size; i++) {
            sum += weight[i] * input[i];
        }
        return sig(sum);
    }

    private double sig(double d) {
        return 1 / (1 + Math.exp(-d));
    }

    public void draw(PApplet view) {
        view.pushMatrix();

        final int width = view.width - Field.width - Field.x * 3;
        final int height = view.height - 20 - 50;
        final int radius = 100;
        final int y = 100;
        final int txtBorder = 5;

        view.translate(Field.width + 20, 10 + 50);

        view.fill(255);
        view.noStroke();

        view.translate(width / 2F, y);

        String data0 = String.format("%.2f", displayData[0]);
        String data1 = String.format("%.2f", displayData[1]);
        String bs = String.format("%.3f", bias);
        String w0 = String.format("%.3f", weight[0]);
        String w1 = String.format("%.3f", weight[1]);
        String guess = String.format("%.3f", guess(displayData));

        view.stroke(0);
        view.strokeWeight(2);
        view.line(-width / 2F + view.textWidth(data0) + txtBorder, -radius / 2F, 0, -radius / 2F);
        view.line(-width / 2F + view.textWidth(data1) + txtBorder, radius / 2F, 0, radius / 2F);
        view.line(radius, 0, width / 2F - view.textWidth(guess) - txtBorder, 0);

        view.textAlign(PConstants.LEFT, PConstants.CENTER);
        view.textSize(22);

        view.strokeWeight(1);
        view.fill(0);
        view.text(data0, -width / 2F, -radius / 2F);
        view.text(data1, -width / 2F, radius / 2F);

        view.fill(0, 0, 255);
        view.textAlign(PConstants.CENTER, PConstants.BOTTOM);
        view.text(w0, (-width / 2F) / 2, -radius / 2F);
        view.text(w1, (-width / 2F) / 2, radius / 2F);

        view.fill(0);
        view.textAlign(PConstants.RIGHT, PConstants.CENTER);
        view.text(guess, width / 2F, 0);

        view.fill(255);
        view.strokeWeight(2);
        view.stroke(0);
        view.ellipseMode(PConstants.RADIUS);
        view.circle(0, 0, radius);

        view.textAlign(PConstants.CENTER, PConstants.CENTER);
        view.strokeWeight(1);
        view.fill(0, 255, 0);
        view.text(bs, 0, 0);

        view.popMatrix();
    }
}
