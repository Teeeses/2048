package ru.explead.two.utils;


import ru.explead.two.app.App;
import ru.explead.two.logic.Cell;
import ru.explead.two.logic.Field;

/**
 * Created by develop on 20.01.2017.
 */

public class UtilsFieldLevel {


    public static void getDataLevel(int level) {
        getLevelFromEasy(level);
    }

    private static void getLevelFromEasy(int level) {
        if (level == 1) {
            App.setLenght(4);
            Cell[][] mass = new Cell[][]{
                    {new Cell(0), new Cell(0), new Cell(0), new Cell(0)},
                    {new Cell(0), new Cell(0), new Cell(0), new Cell(0)},
                    {new Cell(0), new Cell(0), new Cell(0), new Cell(0)},
                    {new Cell(0), new Cell(0), new Cell(0), new Cell(0)}
            };
            Field field = new Field(mass);
            App.getController().setField(field);
        }

        if (level == 2) {
            App.setLenght(5);
            Cell[][] mass = new Cell[][]{
                    {new Cell(1), new Cell(1), new Cell(0), new Cell(1), new Cell(1)},
                    {new Cell(1), new Cell(1), new Cell(0), new Cell(1), new Cell(1)},
                    {new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0)},
                    {new Cell(1), new Cell(1), new Cell(0), new Cell(1), new Cell(1)},
                    {new Cell(1), new Cell(1), new Cell(0), new Cell(1), new Cell(1)}
            };
            Field field = new Field(mass);
            App.getController().setField(field);
        }
    }
}
