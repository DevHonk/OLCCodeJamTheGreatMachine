package com.devhonk.olccodejam;

import processing.core.PApplet;

public class LEDCell extends Cell {

    public LEDCell(PApplet applet, int x, int y) {
        super(applet.loadImage("ledcell.png"), applet.loadImage("ledcellon.png"));
        location = new Duo<>(x, y);
    }

    @Override
    public void tick(int frames) {
        if (neighbors.size() >= 1)
            state = Main.getState(neighbors.get(0));
    }

    public void mouseClicked() {

    }
}
