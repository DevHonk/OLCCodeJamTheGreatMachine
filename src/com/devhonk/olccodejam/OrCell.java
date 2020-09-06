package com.devhonk.olccodejam;

import processing.core.PApplet;

public class OrCell extends Cell {

    public OrCell(PApplet applet, int x, int y) {
        super(applet.loadImage("orcell.png"), applet.loadImage("orcellon.png"));
        location = new Duo<>(x, y);
    }

    @Override
    public void tick(int frames) {
        if (neighbors.size() >= 2) {//input = 2 / outputs = 1 or more
            state = Main.getState(neighbors.get(0)) | Main.getState(neighbors.get(1));
        } else {
            state = false;
        }
    }

    public void mouseClicked() {

    }
}
