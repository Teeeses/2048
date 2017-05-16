package ru.explead.two.logic;


import java.util.Random;

import ru.explead.two.app.App;

/**
 * Created by develop on 20.01.2017.
 */

public class Field {

    /**
     * Длинна клетки
     */
    private float widthCell;


    private Cell[][] field;

    public Field(Cell[][] emptyField) {
        this.field = emptyField;
        widthCell = App.getSizeSurface()/ emptyField.length;

        newCell();
        newCell();
    }

    /* Заполнить случайную ячейку числом 2 */
    public void newCell()
    {
        Random rand = new Random();
        int i, j;
        do
        {
            i = rand.nextInt(field.length);
            j = rand.nextInt(field.length);
        } while (field[i][j].getValue() != 0);

        field[i][j].setValue(2);
        field[i][j].animationNewCell();
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
