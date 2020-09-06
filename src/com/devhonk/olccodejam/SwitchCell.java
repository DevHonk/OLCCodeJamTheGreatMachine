package com.devhonk.olccodejam;

import processing.core.PApplet;

public class SwitchCell  extends Cell {

    public SwitchCell(PApplet applet, int x, int y) {
        super(applet.loadImage("switchcell.png"), applet.loadImage("switchcellon.png"));
        location = new Duo<>(x, y);
    }

    @Override
    public void tick(int frames) {

    }

    public void mouseClicked() {
        state = !state;
    }
}
