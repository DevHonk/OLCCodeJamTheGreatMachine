package com.devhonk.olccodejam;

import java.util.ArrayList;

public class L2MUtils {

    public static final String CELL = "CELL";

    public static ArrayList<Cell> fromString(String data) {
        String[] lines = data.split("\n");
        return fromString(lines);
    }


    public static ArrayList<Cell> fromString(String[] data) {
        ArrayList<Cell> result = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            String line = data[i];
            String[] words = line.split(" ");
            int cIdx = 0;
            int id = 0;
            int x = 0;
            int y = 0;
            int index = 0;
            boolean state = false;
            String links = "";

            Cell cell = null;
            for (int j = 0; j < words.length; j++) {
                String word = words[j];
                int jCIdx = j - cIdx;
                if (word.equals(CELL)) {
                    cIdx = j;
                    id = 0;
                    x = 0;
                    y = 0;
                    index = 0;
                    links = "";
                    cell = null;
                    state = false;
                } else {
                    switch (jCIdx) {
                        case 1:
                            id = Integer.parseInt(word);
                            break;
                        case 2:
                            x = Integer.parseInt(word);
                            break;
                        case 3:
                            y = Integer.parseInt(word);
                            break;
                        case 4:
                            index = Integer.parseInt(word);
                            break;
                        case 5:

                            state = Boolean.parseBoolean(word);
                            break;

                        case 6:
                            cell = Main.me.createCellFromID(id, x, y);
                            ArrayList<Duo<Integer, Integer>> linksCell = new ArrayList<>();
                            String[] linksArray = word.split(",");
                            for (String s : linksArray)
                                linksCell.add(new Duo<>(0, Integer.parseInt(s)));
                            cell.neighbors = linksCell;
                            cell.state = state;

                            result.add(cell);
                            break;

                    }
                }
            }
        }
        return result;
    }
}
