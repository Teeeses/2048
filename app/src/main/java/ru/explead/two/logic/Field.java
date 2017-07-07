package ru.explead.two.logic;


import java.util.ArrayList;
import java.util.Random;

import ru.explead.two.app.App;


public class Field {

    /**
     * Длинна клетки
     */
    private float widthCell;


    private Cell[][] field;

    public Field(Cell[][] field) {
        this.field = field;
        widthCell = App.getSizeSurface()/ field.length;

        this.field = field;
        newCell();
        newCell();
    }

    /* Заполнить случайную ячейку числом 2 */
    public void newCell()
    {
        ArrayList<Coordinate> coordinates = isExistEmptyCells();
        if(coordinates.size() > 0) {
            Random rand = new Random();
            Coordinate coordinate = coordinates.get(rand.nextInt(coordinates.size()));

            field[coordinate.getX()][coordinate.getY()].setValue(2);
            field[coordinate.getX()][coordinate.getY()].animationNewCell();
        }
    }

    public ArrayList<Coordinate> isExistEmptyCells() {

        ArrayList<Coordinate> coordinates = new ArrayList<>();
        for(int i = 0; i < getSize(); i++) {
            for(int j = 0; j < getSize(); j++) {
                if(field[i][j].getValue() == 0) {
                    coordinates.add(new Coordinate(i, j));
                }
            }
        }
        return coordinates;
    }

    public Cell getCell(int x, int y) {
        return field[x][y];
    }

    public int getValue(int x, int y) {
        return field[x][y].getValue();
    }

    public int getSize() {
        return field.length;
    }

    public float getWidthCell() {
        return widthCell;
    }

    public Cell[][] getField() {
        return field;
    }
}
