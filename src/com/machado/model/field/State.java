package com.machado.model.field;

public enum State {
    ON(0.5),
    UP(1),
    DOWN(0);

    public double value;

    State(double v) {
        this.value = v;
    }
}
