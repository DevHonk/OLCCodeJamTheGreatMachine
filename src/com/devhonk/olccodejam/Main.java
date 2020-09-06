package com.devhonk.olccodejam;

import processing.core.PApplet;
import processing.core.PImage;
import processing.event.MouseEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;


public class Main extends PApplet {
    public static ArrayList<Duo<Integer, Integer>> links = new ArrayList<>();
    int offX = 0;
    int offY = 0;
    int heldItem = 0;
    int prevMX = 0;
    int prevMY = 0;
    PImage background;

    static Main me;

    public static ArrayList<ArrayList<Cell>> map = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("DevHONK's Logical 2, \"WHY THERE ARE A 2?\" ! ");
        PApplet.main(Main.class, args);
    }

    @Override
    public void settings() {
        size(840, 480);
    }

    @Override
    public void setup() {
        me = this;
        ArrayList<Cell> stage0 = new ArrayList<>();
        background = loadImage("background.png");
        surface.setTitle("Logical 2, \"WHY THERE ARE A 2?\"!");
        map.add(stage0);

    }

    @Override
    public void draw() {
        clear();
        image(background, 0, 0);
        drawAllCells();
        fill(0xFFFFFFFF);
        text(nameFromID(heldItem), 50, 50);
    }

    /**
     * Draws all childs cells except the cell passed as a arguments
     *
     * @see Cell
     */
    public void drawAllCells() {


        for (ArrayList<Cell> list : map) {
            for (Cell c : list) {
                c.tick(frameCount);
                if (c.getCurrentTexture() != null) {
                    for (Duo list1 : c.neighbors) {
                        Cell c1 = map.get(0).get((Integer) list1.getElementB());
                        stroke(0xFFFFFFFF);
                        line((c.location.getElementA() * 64 + offX) + 64, (c.location.getElementB() * 64 + offY), c1.location.getElementA() * 64 + offX, (c1.location.getElementB() * 64 + offY) + 64);
                    }
                    image(c.getCurrentTexture(), c.location.getElementA() * 64 + offX, c.location.getElementB() * 64 + offY);
                }
            }
        }
    }

    public String loadString(String filename) {
        StringBuilder result = new StringBuilder();

        String[] strings = loadStrings(filename);

        for (String string : strings)
            result.append(string).append("\n");
        return result.toString();
    }

    @Override
    public void handleMouseEvent(MouseEvent event) {
        int x = (event.getX() - offX) / 64;
        int y = (event.getY() - offY) / 64;

        if (event.getAction() == MouseEvent.WHEEL) {
            heldItem += event.getCount();
            if (heldItem < 0) heldItem++;
            if (nameFromID(heldItem).equals("Not Valid Cell")) heldItem--;

            System.out.println(heldItem);
        }

        if (event.getAction() == MouseEvent.PRESS) {
            switch (event.getButton()) {
                case LEFT:

                    if (event.isControlDown()) {
                        AtomicBoolean thereSomething = new AtomicBoolean(false);
                        forEach((cell, loc) -> thereSomething.set(x == cell.location.getElementA() && y == cell.location.getElementB()));
                        if (!thereSomething.get())
                            map.get(0).add(createCellFromID(heldItem, x, y));
                        else
                            System.out.println("cant do that");
                    } else {
                        forEach((cell, loc) -> {
                            if (x == cell.location.getElementA() && y == cell.location.getElementB()) {
                                cell.mouseClicked();
                            }
                        });
                    }


                    break;
                case RIGHT:

                    if (event.isControlDown()) {
                        forEach(((cell, loc) -> {
                            System.out.println("x: " + x);
                            if (x == cell.location.getElementA() && y == cell.location.getElementB()) {
                                links.add(loc);
                                System.out.println(links.size());
                                cell.neighbors.add(links.get(0));
                                if (links.size() >= 2) {
                                    System.out.println("link");
                                    links.remove(0);
                                    links.remove(0);
                                }
                            }
                        }));
                    } else {
                        System.out.println("wanna move");
                        float moveX = (event.getX() / (float) (width)) * 2 - 1;
                        float moveY = (event.getY() / (float) (height)) * 2 - 1;

                        offX -= moveX * 4;
                        offY -= moveY * 4;
                    }
                    break;
            }
        }
        prevMX = event.getX();
        prevMY = event.getY();
    }

    public void forEach(TwoInputs<Cell, Duo<Integer, Integer>> action) {
        for (int y = 0; y < map.size(); y++) {
            ArrayList<Cell> list = map.get(y);
            for (int x = 0; x < list.size(); x++) {
                Cell c = list.get(x);
                action.apply(c, new Duo<>(y, x));
            }
        }
    }

    @Override
    public void keyPressed() {
        switch (Character.toUpperCase(key)) {
            case 'L':
                String data = loadString("0.L2M");
                map.set(0, L2MUtils.fromString(data));
                break;
            case 'S':
                data = "";
                int i = 0;

                for (Cell c : map.get(0)) {
                    data += "CELL " + toID(c) + " " +
                            c.location.getElementA() + " " +
                            c.location.getElementB() + " " +
                            i + " " + c.state + " ";
                    for (int j = 0; j < c.neighbors.size(); j++) {
                        Duo<Integer, Integer> link = c.neighbors.get(j);
                        data += link.getElementB() + (j == c.neighbors.size() ? "" : ", ");
                    }

                    data += "\n";
                    i++;
                }
                saveStrings(new File("0.L2M"), new String[]{data});
                break;
        }
    }

    public static boolean getState(Duo<Integer, Integer> location) {
        return getCell(location).state;
    }

    public static Cell getCell(Duo<Integer, Integer> location) {
        return map.get(location.getElementA()).get(location.getElementB());
    }

    public Cell createCellFromID(int id, int x, int y) {
        switch (id) {
            case 0:
                return new SwitchCell(this, x, y);
            case 1:
                return new AndCell(this, x, y);
            case 2:
                return new OrCell(this, x, y);
            case 3:
                return new XorCell(this, x, y);
            case 4:
                return new NotCell(this, x, y);
            case 5:
                return new MemoryCell(this, x, y);
            case 6:
                return new LEDCell(this, x, y);
        }
        return null;
    }

    public int toID(Cell cell) {
        if (cell instanceof SwitchCell) {
            return 0;
        }
        if (cell instanceof AndCell) {
            return 1;
        }
        if (cell instanceof OrCell) {
            return 2;
        }
        if (cell instanceof XorCell) {
            return 3;
        }
        if (cell instanceof NotCell) {
            return 4;
        }
        if (cell instanceof MemoryCell) {
            return 5;
        }
        if (cell instanceof LEDCell) {
            return 6;
        }
        return 0;
    }

    public String nameFromID(int id) {
        switch (id) {
            case 0:
                return "Switch Cell";
            case 1:
                return "And Cell";
            case 2:
                return "Or Cell";
            case 3:
                return "Xor Cell";
            case 4:
                return "Not Cell";
            case 5:
                return "Memory Cell";
            case 6:
                return "LED Cell";
        }
        return "Not Valid Cell";
    }
}
