package com.devhonk.olccodejam;

import processing.core.PApplet;

public class XorCell extends Cell {

    public XorCell(PApplet applet, int x, int y) {
        super(applet.loadImage("xorcell.png"), applet.loadImage("xorcellon.png"));
        location = new Duo<>(x, y);
    }

    @Override
    public void tick(int frames) {

        if(neighbors.size() >= 2) {

            state = Main.getState(neighbors.get(0)) ^ Main.getState(neighbors.get(1));
        }

    }

    public void mouseClicked() {

    }
}
