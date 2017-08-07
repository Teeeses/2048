package ru.explead.two.logic;

import android.graphics.Canvas;
import android.graphics.Paint;

import ru.explead.two.ActionsCallback;
import ru.explead.two.MainActivity;
import ru.explead.two.app.App;
import ru.explead.two.fragments.GameFragment;
import ru.explead.two.utils.UtilsFieldLevel;


/**
 * Created by develop on 21.02.2017.
 */

public class Controller {

    public static int ACTIVE_GAME = 1, FINISH = 2;
    private int status;

    private Level level;
    private Field field;

    private int score;
    private int defultMinScore = 16;

    private ActionsCallback actionCallback;


    public Controller(ActionsCallback actionCallback) {
        this.actionCallback = actionCallback;
    }


    public void startGame() {
        status = ACTIVE_GAME;
        score = 0;
        level = App.getLevel();
        UtilsFieldLevel.getDataLevel(level.getLevel());
    }

    public void action(int start_x, int start_y, int end_x, int end_y) {
        if(status == ACTIVE_GAME) {
            logicMove(start_x, start_y, end_x, end_y);
            this.field.newCell();
            actionCallback.onMove();
            actionCallback.onScore(score());
            int roundScore = checkRoundScore();
            if (roundScore != 0) {
                actionCallback.onRoundScore(roundScore);
            }
            if (isGameOver()) {
                actionCallback.onGameOver();
            }
        }
    }

    /**
     * Проверяем закончилась игра или нет
     * @return - true - если проиграли
     */
    private boolean isGameOver() {
        for(int i = 0; i < field.getSize()-1; i++) {
            for(int j = 0; j < field.getSize()-1; j++) {
                int current = field.getValue(i, j);
                int down = field.getValue(i+1, j);
                int right = field.getValue(i, j+1);
                if(current == 0 || down == 0 || right == 0) {
                    return false;
                }
                if(current != 1 && down != 1 && current == down) {
                    return false;
                }
                if(current != 1 && right != 1 && current == right) {
                    return false;
                }
            }
        }
        status = FINISH;
        return true;
    }

    public int checkRoundScore() {
        int count = max() / defultMinScore;
        if(count >= 1) {
            int defult = defultMinScore;
            for(int i = 0; i < count; i++) {
                defultMinScore = defultMinScore * 2;
            }
            return defult;
        }
        return 0;
    }

    public int max() {
        int max = 0;
        for(int i = 0; i < field.getSize(); i++) {
            for (int j = 0; j < field.getSize(); j++) {
                int currentValue = field.getValue(i, j);
                if(currentValue > max) {
                    max = currentValue;
                }
            }
        }
        return max;
    }

    /**
     * Каждый тик потока отрисовки
     */
    public void onTick() {
        if(status == ACTIVE_GAME) {
            if(isGameOver()) {
                //((GameFragment) MainActivity.getFragment()).onWin();
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
        Cell[][] field = getField().getField();
        for(int j = 0; j < field.length; j++) {
            int count = 0;
            for(int i = 0; i < field.length; i++) {
                if(field[i][j].getValue() != 0 && field[i][j].getValue() != 1) {
                    if(i != count) {
                        if(field[count][j].getValue() != 1) {
                            if(field[count][j].getValue() == field[i][j].getValue()) {
                                int value = field[i][j].getValue();
                                field[i][j].setValue(0);
                                field[count][j].setValue(2 * value);
                                count++;
                            } else if(field[count][j].getValue() < field[i][j].getValue()) {
                                if(field[count][j].getValue() == 0) {
                                    int value = field[i][j].getValue();
                                    field[i][j].setValue(0);
                                    field[count][j].setValue(value);
                                } else {
                                    count++;
                                    int value = field[i][j].getValue();
                                    field[i][j].setValue(0);
                                    field[count][j].setValue(value);
                                }
                            } else if(field[count][j].getValue() > field[i][j].getValue()) {
                                count++;
                                int value = field[i][j].getValue();
                                field[i][j].setValue(0);
                                field[count][j].setValue(value);
                            }
                        }
                    }
                }
            }
        }
    }

    public void onMoveDown() {
        Cell[][] field = getField().getField();
        for(int j = 0; j < field.length; j++) {
            int count = field.length-1;
            for(int i = field.length-1; i >= 0; i--) {
                if(field[i][j].getValue() != 0 && field[i][j].getValue() != 1) {
                    if(i != count) {
                        if(field[count][j].getValue() != 1) {
                            if(field[count][j].getValue() == field[i][j].getValue()) {
                                int value = field[i][j].getValue();
                                field[i][j].setValue(0);
                                field[count][j].setValue(2*value);
                                count--;
                            } else if(field[count][j].getValue() < field[i][j].getValue()) {
                                if(field[count][j].getValue() == 0) {
                                    int value = field[i][j].getValue();
                                    field[i][j].setValue(0);
                                    field[count][j].setValue(value);
                                } else {
                                    count--;
                                    int value = field[i][j].getValue();
                                    field[i][j].setValue(0);
                                    field[count][j].setValue(value);
                                }
                            } else if(field[count][j].getValue() > field[i][j].getValue()) {
                                count--;
                                int value = field[i][j].getValue();
                                field[i][j].setValue(0);
                                field[count][j].setValue(value);
                            }
                        }
                    }
                }
            }
        }
    }

    public void onMoveRight() {
        Cell[][] field = getField().getField();
        for(int i = 0; i < field.length; i++) {
            int count = field.length-1;
            for(int j = field.length-1; j >= 0; j--) {
                if(field[i][j].getValue() != 0 && field[i][j].getValue() != 1) {
                    if(j != count) {
                        if(field[i][count].getValue() != 1) {
                            if(field[i][count].getValue() == field[i][j].getValue()) {
                                int value = field[i][j].getValue();
                                field[i][j].setValue(0);
                                field[i][count].setValue(2*value);
                                count--;
                            } else if(field[i][count].getValue() < field[i][j].getValue()) {
                                if(field[i][count].getValue() == 0) {
                                    int value = field[i][j].getValue();
                                    field[i][j].setValue(0);
                                    field[i][count].setValue(value);
                                } else {
                                    count--;
                                    int value = field[i][j].getValue();
                                    field[i][j].setValue(0);
                                    field[i][count].setValue(value);
                                }
                            } else if(field[i][count].getValue() > field[i][j].getValue()) {
                                count--;
                                int value = field[i][j].getValue();
                                field[i][j].setValue(0);
                                field[i][count].setValue(value);
                            }
                        }
                    }
                }
            }
        }
    }

    public void onMoveLeft() {
        Cell[][] field = getField().getField();
        for(int i = 0; i < field.length; i++) {
            int count = 0;
            for(int j = 0; j < field.length; j++) {
                if(field[i][j].getValue() != 0 && field[i][j].getValue() != 1) {
                    if(j != count) {
                        if(field[i][count].getValue() != 1) {
                            if(field[i][count].getValue() == field[i][j].getValue()) {
                                int value = field[i][j].getValue();
                                field[i][j].setValue(0);
                                field[i][count].setValue(2 * value);
                                count++;
                            } else if(field[i][count].getValue() < field[i][j].getValue()) {
                                if(field[i][count].getValue() == 0) {
                                    int value = field[i][j].getValue();
                                    field[i][j].setValue(0);
                                    field[i][count].setValue(value);
                                } else {
                                    count++;
                                    int value = field[i][j].getValue();
                                    field[i][j].setValue(0);
                                    field[i][count].setValue(value);
                                }
                            } else if(field[i][count].getValue() > field[i][j].getValue()) {
                                count++;
                                int value = field[i][j].getValue();
                                field[i][j].setValue(0);
                                field[i][count].setValue(value);
                            }
                        }
                    }
                }
            }
        }
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
