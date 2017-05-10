package ru.explead.two.logic;

/**
 * Created by develop on 20.01.2017.
 */

public class Level {

    public static int EASY = 0, MEDIUM = 1, HARD = 2, VERY_HARD = 3;
    private int complexity;

    private int level;

    public Level(int complexity, int level) {
        this.complexity = complexity;
        this.level = level;
    }

    public int getComplexity() {
        return complexity;
    }

    public int getLevel() {
        return level;
    }
}
