package com.devhonk.olccodejam;

import processing.core.PApplet;

public class MemoryCell  extends Cell {

    public MemoryCell(PApplet applet, int x, int y) {
        super(applet.loadImage("memorycell.png"), applet.loadImage("memorycellon.png"));
        location = new Duo<>(x, y);
    }

    @Override
    public void tick(int frames) {
        if(neighbors.size() >= 2) {
            boolean edit = Main.getState(neighbors.get(0));
            boolean newStateIfEdit = Main.getState(neighbors.get(1));
            state = edit ? newStateIfEdit : state;
        }
    }

    public void mouseClicked() {

    }
}
