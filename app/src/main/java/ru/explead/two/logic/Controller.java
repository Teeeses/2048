package ru.explead.two.logic;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

import ru.explead.two.MainActivity;
import ru.explead.two.app.App;
import ru.explead.two.fragments.GameFragment;
import ru.explead.two.utils.UtilsFieldLevel;


/**
 * Created by develop on 21.02.2017.
 */

public class Controller {

    /**
     * левый нижний угол - (0, *), координаты.
     */
    public static int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3, NO_ACTIVE = -1;

    public static int ACTIVE_GAME = 1, FINISH = 2;
    protected int status;

    protected Level level;
    protected Field field;

    private int score;

    protected Paint paintWall = new Paint();


    public Controller() {

    }


    public void startGame() {
        status = ACTIVE_GAME;
        score = 0;
        level = App.getLevel();
        UtilsFieldLevel.getDataLevel(level.getLevel());
    }

    public void onDraw(Canvas canvas) {

    }

    public void createPaint() {
        paintWall.setColor(MainActivity.getRes().getColor(android.R.color.darker_gray));
        paintWall.setAntiAlias(true);
    }

    /**
     * Проверяем закончилась игра или нет
     * @return - true - если кубики на своих местах
     */
    private boolean checkGameOver() {

        return true;
    }

    /**
     * Каждый тик потока отрисовки
     */
    public void onTick() {
        if(status == ACTIVE_GAME) {
            if(checkGameOver()) {
                ((GameFragment) MainActivity.getFragment()).onWin();
            }
        }
    }

    /**
     * Проверка двигается ли какой из кубиков в данный момент
     * @return - false - если двигается
     */
    /*public boolean checkNoActiveMove() {
        for(int i = 0; i < cube.size(); i++) {
            if(cube.get(i).getStatus() != NO_ACTIVE) {
                return false;
            }
        }
        return true;
    }*/


    public int score() {
        int score = 0;
        for(int i = 0; i < field.getSize(); i++) {
            for(int j = 0; j < field.getSize(); j++) {
                if(field.getValue(i, j) != 1) {
                    score = score + field.getValue(i, j);
                }
            }
        }
        this.score = score;
        return score;
    }

    public void onMoveUp() {
        int[][] field = getField().getField();
        for(int j = 0; j < field.length; j++) {
            int count = 0;
            for(int i = 0; i < field.length; i++) {
                if(field[i][j] != 0 && field[i][j] != 1) {
                    if(i != count) {
                        if(field[count][j] != 1) {
                            if(field[count][j] == field[i][j]) {
                                int value = field[i][j];
                                field[i][j] = 0;
                                field[count][j] = 2 * value;
                                count++;
                            } else if(field[count][j] < field[i][j]) {
                                if(field[count][j] == 0) {
                                    int value = field[i][j];
                                    field[i][j] = 0;
                                    field[count][j] = value;
                                } else {
                                    count++;
                                    int value = field[i][j];
                                    field[i][j] = 0;
                                    field[count][j] = value;
                                }
                            } else if(field[count][j] > field[i][j]) {
                                count++;
                                int value = field[i][j];
                                field[i][j] = 0;
                                field[count][j] = value;
                            }
                        }
                    }
                }
            }
        }

        this.field.newCell();
    }

    public void onMoveDown() {
        int[][] field = getField().getField();
        for(int j = 0; j < field.length; j++) {
            int count = field.length-1;
            for(int i = field.length-1; i >= 0; i--) {
                if(field[i][j] != 0 && field[i][j] != 1) {
                    if(i != count) {
                        if(field[count][j] != 1) {
                            if(field[count][j] == field[i][j]) {
                                int value = field[i][j];
                                field[i][j] = 0;
                                field[count][j] = 2*value;
                                count--;
                            } else if(field[count][j] < field[i][j]) {
                                if(field[count][j] == 0) {
                                    int value = field[i][j];
                                    field[i][j] = 0;
                                    field[count][j] = value;
                                } else {
                                    count--;
                                    int value = field[i][j];
                                    field[i][j] = 0;
                                    field[count][j] = value;
                                }
                            } else if(field[count][j] > field[i][j]) {
                                count--;
                                int value = field[i][j];
                                field[i][j] = 0;
                                field[count][j] = value;
                            }
                        }
                    }
                }
            }
        }

        this.field.newCell();
    }

    public void onMoveRight() {
        int[][] field = getField().getField();
        for(int i = 0; i < field.length; i++) {
            int count = field.length-1;
            for(int j = field.length-1; j >= 0; j--) {
                if(field[i][j] != 0 && field[i][j] != 1) {
                    if(j != count) {
                        if(field[i][count] != 1) {
                            if(field[i][count] == field[i][j]) {
                                int value = field[i][j];
                                field[i][j] = 0;
                                field[i][count] = 2*value;
                                count--;
                            } else if(field[i][count] < field[i][j]) {
                                if(field[i][count] == 0) {
                                    int value = field[i][j];
                                    field[i][j] = 0;
                                    field[i][count] = value;
                                } else {
                                    count--;
                                    int value = field[i][j];
                                    field[i][j] = 0;
                                    field[i][count] = value;
                                }
                            } else if(field[i][count] > field[i][j]) {
                                count--;
                                int value = field[i][j];
                                field[i][j] = 0;
                                field[i][count] = value;
                            }
                        }
                    }
                }
            }
        }

        this.field.newCell();
    }

    public void onMoveLeft() {
        int[][] field = getField().getField();
        for(int i = 0; i < field.length; i++) {
            int count = 0;
            for(int j = 0; j < field.length; j++) {
                if(field[i][j] != 0 && field[i][j] != 1) {
                    if(j != count) {
                        if(field[i][count] != 1) {
                            if(field[i][count] == field[i][j]) {
                                int value = field[i][j];
                                field[i][j] = 0;
                                field[i][count] = 2 * value;
                                count++;
                            } else if(field[i][count] < field[i][j]) {
                                if(field[i][count] == 0) {
                                    int value = field[i][j];
                                    field[i][j] = 0;
                                    field[i][count] = value;
                                } else {
                                    count++;
                                    int value = field[i][j];
                                    field[i][j] = 0;
                                    field[i][count] = value;
                                }
                            } else if(field[i][count] > field[i][j]) {
                                count++;
                                int value = field[i][j];
                                field[i][j] = 0;
                                field[i][count] = value;
                            }
                        }
                    }
                }
            }
        }

        this.field.newCell();
    }

    public void logicMove(int start_x, int start_y, int end_x, int end_y) {
        int side1 = (start_x - end_x);
        int side2 = (start_y - end_y);
        int hypotenuse = (int) (Math.sqrt(Math.abs(side1 * side1) + Math.abs(side2 * side2)));
        double angle = (Math.asin((double) side2 / hypotenuse)) * 57.295f;
        if (hypotenuse > 50 && ((angle < 30 && angle > -30) || (angle > 60) || (angle < -60))) {
            if ((side1 <= 0 && side2 >= 0 && angle < 30) || (side1 <= 0 && side2 <= 0 && angle > -30)) {
                onMoveRight();
            } else if ((side1 <= 0 && side2 >= 0 && angle > 60) || (side1 >= 0 && side2 >= 0 && angle > 60)) {
                onMoveUp();
            } else if ((side1 >= 0 && side2 >= 0 && angle < 30) || (side1 >= 0 && side2 <= 0 && angle > -30)) {
                onMoveLeft();
            } else if ((side1 >= 0 && side2 <= 0 && angle < -60) || (side1 <= 0 && side2 <= 0 && angle < -60)) {
                onMoveDown();
            }
        }
    }


    public Level getLevel() {
        return level;
    }

    public Field getField() {
        return field;
    }

    public int getStatus() {
        return status;
    }

    public void setField(Field field) {
        this.field = field;
    }

}
