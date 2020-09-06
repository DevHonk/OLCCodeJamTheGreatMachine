package com.devhonk.olccodejam;

import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Cell {
    protected Duo<Integer, Integer> location = new Duo<>(0, 0);
    protected boolean state;

    protected ArrayList<Duo<Integer, Integer>> neighbors = new ArrayList<>();
    private PImage[] textures;

    public Cell(PImage offTex, PImage onTex) {
        textures = new PImage[]
                {offTex, onTex};

    }

    public abstract void tick(int frames);

    public PImage getCurrentTexture() {
        return state ? textures[1] : textures[0];
    }

    public abstract void mouseClicked();
}
