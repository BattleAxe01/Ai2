package com.machado.controller;

import com.machado.model.Neuron;
import com.machado.model.field.Point;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Collection;

public class Brain {

    private static final int size = 1;
    private static final int inputSize = 2;
    public static final boolean drawIaGuess = true;


    private final PApplet view;

    Collection<Neuron> neurons = new ArrayList<>();

    public Brain(PApplet view) {
        this.view = view;
        for (int i = 0; i < size; i++) neurons.add(new Neuron(inputSize));
    }

    public void train(Collection<Point> points) {
        double cost = 0;
        for (Point p : points) {
            for (Neuron n : neurons) {
                double guess = n.guess(p.getData());
                double error = guess - p.getColor().value;
                cost += Math.pow(error, 2);

                p.setIaGuess(guess);
            }
        }
    }

    public void draw() {
        for (Neuron n : neurons) {
            n.draw(view);
        }
    }
}
