package ru.explead.two.logic;

import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import ru.explead.features.LevelsActivity;
import ru.explead.features.MainActivity;
import ru.explead.features.Utils.UtilsFieldLevel;
import ru.explead.features.app.App;
import ru.explead.features.fragments.GameFragment;

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
    protected ArrayList<Cube> cube = new ArrayList<>();

    protected Paint paintWall = new Paint();


    public Controller() {

    }


    public void startGame() {
        status = ACTIVE_GAME;
        level = App.getLevel();
        UtilsFieldLevel.getDataLevel(level.getLevel(), level.getComplexity());
    }

    public void createPaint() {
        paintWall.setColor(LevelsActivity.getActivity().getResources().getColor(android.R.color.darker_gray));
        paintWall.setAntiAlias(true);
    }

    /**
     * Проверяем закончилась игра или нет
     * @return - true - если кубики на своих местах
     */
    private boolean checkWin() {

        return true;
    }

    /**
     * Каждый тик потока отрисовки
     */
    public void onTick() {
        for (int i = 0; i < cube.size(); i++) {
            cube.get(i).onMove();
        }
        if(status == ACTIVE_GAME) {
            if(checkWin()) {
                ((GameFragment) MainActivity.getFragment()).onWin();
            }
        }
    }

    /**
     * Проверка двигается ли какой из кубиков в данный момент
     * @return - false - если двигается
     */
    public boolean checkNoActiveMove() {
        for(int i = 0; i < cube.size(); i++) {
            if(cube.get(i).getStatus() != NO_ACTIVE) {
                return false;
            }
        }
        return true;
    }

    public void onMoveUp() {
        if(checkNoActiveMove()) {
            Collections.sort(cube, new Comparator<Cube>() {
                @Override
                public int compare(Cube cubeOne, Cube cubeTwo) {
                    if (cubeOne.getX() > cubeTwo.getX())
                        return 1;
                    if (cubeOne.getX() < cubeTwo.getX())
                        return -1;
                    return 0;
                }
            });

            for (int i = 0; i < cube.size(); i++) {
                int count = 0;
                int x = cube.get(i).getX();
                int y = cube.get(i).getY();

                while (x - count - 1 >= 0 && field.getEmptyField()[x - count - 1][y] < 5 && checkPlaceCube(x - count - 1, y)) {
                    count++;
                }
                cube.get(i).setMoveParams(UP, count);
            }
        }
    }

    public void onMoveDown() {
        if(checkNoActiveMove()) {
            Collections.sort(cube, new Comparator<Cube>() {
                @Override
                public int compare(Cube cubeOne, Cube cubeTwo) {
                    if (cubeOne.getX() < cubeTwo.getX())
                        return 1;
                    if (cubeOne.getX() > cubeTwo.getX())
                        return -1;
                    return 0;
                }
            });

            for (int i = 0; i < cube.size(); i++) {
                int count = 0;
                int x = cube.get(i).getX();
                int y = cube.get(i).getY();

                while (x + count + 1 < field.getEmptyField().length && field.getEmptyField()[x + count + 1][y] < 5 && checkPlaceCube(x + count + 1, y)) {
                    count++;
                }
                cube.get(i).setMoveParams(DOWN, count);
            }
        }
    }

    public void onMoveRight() {
        if(checkNoActiveMove()) {
            Collections.sort(cube, new Comparator<Cube>() {
                @Override
                public int compare(Cube cubeOne, Cube cubeTwo) {
                    if (cubeOne.getY() < cubeTwo.getY())
                        return 1;
                    if (cubeOne.getY() > cubeTwo.getY())
                        return -1;
                    return 0;
                }
            });

            for (int i = 0; i < cube.size(); i++) {
                int count = 0;
                int x = cube.get(i).getX();
                int y = cube.get(i).getY();

                while (y + count + 1 < field.getEmptyField().length && field.getEmptyField()[x][y + count + 1] < 5 && checkPlaceCube(x, y + count + 1)) {
                    count++;
                }
                cube.get(i).setMoveParams(RIGHT, count);
            }
        }
    }

    public void onMoveLeft() {
        if(checkNoActiveMove()) {
            Collections.sort(cube, new Comparator<Cube>() {
                @Override
                public int compare(Cube cubeOne, Cube cubeTwo) {
                    if (cubeOne.getY() > cubeTwo.getY())
                        return 1;
                    if (cubeOne.getY() < cubeTwo.getY())
                        return -1;
                    return 0;
                }
            });

            for (int i = 0; i < cube.size(); i++) {
                int count = 0;
                int x = cube.get(i).getX();
                int y = cube.get(i).getY();

                while (y - count - 1 >= 0 && field.getEmptyField()[x][y - count - 1] < 5 && checkPlaceCube(x, y - count - 1)) {
                    count++;
                }
                cube.get(i).setMoveParams(LEFT, count);
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

    public void setCube(ArrayList<Cube> cube) {
        this.cube = cube;
    }
}
