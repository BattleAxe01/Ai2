package com.machado.controller;

import com.machado.model.Neuron;
import com.machado.model.field.Field;
import com.machado.model.field.Point;
import processing.core.PApplet;
import processing.core.PConstants;

import java.util.ArrayList;
import java.util.Collection;

public class Brain {

    private static final int neuronAmount = 1;
    private static final int inputSize = 2;
    public static final boolean drawIaGuess = true;
    private static final double learningFactor = 2;

    private double cost;
    private final PApplet view;
    Collection<Neuron> neurons = new ArrayList<>();

    public Brain(PApplet view) {
        this.view = view;
        for (int i = 0; i < neuronAmount; i++) neurons.add(new Neuron(inputSize));
    }

    public void train(Collection<Point> points) {
        cost = 0;
        for (Point p : points) {
            double[] data = p.getData();
            for (Neuron n : neurons) {
                double guess = n.guess(data);
                double error = p.getColor().value - guess;
                cost += Math.pow(error, 2);

                for (int i = 0; i < inputSize; i++) {
                    n.weight[i] += error * data[i] * learningFactor;
                }
                n.bias += error * learningFactor;

                p.setIaGuess(guess);
            }
        }
    }

    public void draw() {
        view.textSize(22);
        view.textAlign(PConstants.CENTER, PConstants.TOP);
        String c = String.format("Erro Total: %.5f", cost);
        view.text(c, Field.width + 3 * Field.x + (view.width - (Field.width + 3 * Field.x)) / 2F, Field.y);
        for (Neuron n : neurons) {
            n.draw(view);
        }
    }
}
