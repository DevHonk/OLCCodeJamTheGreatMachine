package com.devhonk.olccodejam;

import processing.core.PApplet;

public class NotCell extends Cell {

    public NotCell(PApplet applet, int x, int y) {
        super(applet.loadImage("notcell.png"), applet.loadImage("notcellon.png"));
        location = new Duo<>(x, y);
    }

    @Override
    public void tick(int frames) {

        if (neighbors.size() >= 1) {//input = 1 / outputs = 1 or more
            System.out.println(Main.getCell(neighbors.get(0)));
            state = !Main.getState(neighbors.get(0));
        }
    }

    public void mouseClicked() {

    }
}
