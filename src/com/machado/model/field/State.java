package com.machado.model.field;

public enum State {
    ON(0.5),
    UP(0),
    DOWN(1);

    public double value;

    State(double v) {
        this.value = v;
    }
}
