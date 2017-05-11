package ru.explead.two.utils;


import ru.explead.two.app.App;
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
            int[][] mass = new int[][]{
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0}
            };
            Field field = new Field(mass);
            App.getController().setField(field);
        }
    }
}
