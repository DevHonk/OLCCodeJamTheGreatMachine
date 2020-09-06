package com.devhonk.olccodejam;

import processing.core.PApplet;

public class AndCell extends Cell {

    public AndCell(PApplet applet, int x, int y) {
        super(applet.loadImage("andcell.png"), applet.loadImage("andcellon.png"));
        location = new Duo<>(x, y);
    }

    @Override
    public void tick(int frames) {

        if (neighbors.size() >= 2) {//input = 2 / outputs = 1 or more
            state = Main.getState(neighbors.get(0)) & Main.getState(neighbors.get(1));
        } else {
            state = false;
        }
    }

    public void mouseClicked() {

    }
}
