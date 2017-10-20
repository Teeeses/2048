package ru.explead.two.utils;


import ru.explead.two.logic.Cell;
import ru.explead.two.logic.Field;


public class UtilsFieldLevel {


    public static Field getData(int level) {
        Field field = null;
        if (level == 1) {
            Cell[][] mass = new Cell[][]{
                    {new Cell(0), new Cell(0), new Cell(0), new Cell(0)},
                    {new Cell(0), new Cell(0), new Cell(0), new Cell(0)},
                    {new Cell(0), new Cell(0), new Cell(0), new Cell(0)},
                    {new Cell(0), new Cell(0), new Cell(0), new Cell(0)}
            };
            field = new Field(mass);
        }

        if (level == 2) {
            Cell[][] mass = new Cell[][]{
                    {new Cell(1), new Cell(1), new Cell(0), new Cell(1), new Cell(1)},
                    {new Cell(1), new Cell(1), new Cell(0), new Cell(1), new Cell(1)},
                    {new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0)},
                    {new Cell(1), new Cell(1), new Cell(0), new Cell(1), new Cell(1)},
                    {new Cell(1), new Cell(1), new Cell(0), new Cell(1), new Cell(1)}
            };
            field = new Field(mass);
        }

        if (level == 3) {
            Cell[][] mass = new Cell[][]{
                    {new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0)},
                    {new Cell(0), new Cell(1), new Cell(0), new Cell(1), new Cell(0)},
                    {new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0)},
                    {new Cell(0), new Cell(1), new Cell(0), new Cell(1), new Cell(0)},
                    {new Cell(0), new Cell(0), new Cell(0), new Cell(0), new Cell(0)}
            };
            field = new Field(mass);
        }
        return field;
    }
}
