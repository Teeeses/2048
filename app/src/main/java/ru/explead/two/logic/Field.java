package ru.explead.two.logic;


import ru.explead.two.app.App;

/**
 * Created by develop on 20.01.2017.
 */

public class Field {

    /**
     * Длинна клетки
     */
    private float widthCell;


    private int[][] emptyField;

    public Field(int[][] emptyField) {
        this.emptyField = emptyField;
        widthCell = App.getSizeSurface()/ emptyField.length;
    }

    public float getWidthCell() {
        return widthCell;
    }

    public int[][] getEmptyField() {
        return emptyField;
    }
}
