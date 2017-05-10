package ru.explead.two.logic;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import ru.explead.features.Utils.Utils;
import ru.explead.features.app.App;
import ru.explead.features.beans.EndPosition;

/**
 * Created by develop on 20.01.2017.
 */

public class Cube {

    private int id;

    private int x;
    private int y;

    private float xPixels;
    private float yPixels;

    private Bitmap bitmap;

    private Field field;
    private EndPosition endPosition;

    /**
     * Статус движения(UP, DOWN, RIGHT, LEFT)
     */
    private int status;

    private float speed;
    /**
     * Количество отрисовок на все движение
     */
    private int numberFrame;

    /**
     * Количество отрисовок на одну клетку
     */
    private int numberFrameCell = 3;


    public Cube( int x, int y, EndPosition endPosition, int id) {
        field = App.getController().getField();
        this.endPosition = endPosition;
        this.x = x;
        this.y = y;
        this.id = id;
        this.bitmap = Utils.getDrawableCube(id);
        xPixels = x*field.getWidthCell();
        yPixels = y*field.getWidthCell();
        speed = field.getWidthCell()/numberFrameCell;
        status = ControllerOne.NO_ACTIVE;
        endPosition.setId(id);
        endPosition.findDrawable();
    }


    public void onDraw(Canvas canvas) {
        //canvas.drawRect(yPixels, xPixels, yPixels + field.getWidthCell(), xPixels + field.getWidthCell(), paint);
        canvas.drawBitmap(bitmap, yPixels, xPixels, null);

    }

    public void onDrawSmall(Canvas canvas, Cube cubeOne, Cube cubeTwo) {
        /*canvas.drawRect(cubeOne.getY()*field.getWidthCell() + field.getWidthCell()*0.25f, cubeOne.getX()*field.getWidthCell() + field.getWidthCell()*0.75f,
                cubeOne.getY()*field.getWidthCell() + field.getWidthCell()*0.75f, cubeOne.getX()*field.getWidthCell() + field.getWidthCell()*0.25f, cubeOne.getPaint());
        if(cubeOne.getX() > cubeTwo.getX()) {
            canvas.drawRect(cubeTwo.getY()*field.getWidthCell() + field.getWidthCell()*0.25f, cubeTwo.getX()*field.getWidthCell() + field.getWidthCell()*0.75f,
                    cubeOne.getY()*field.getWidthCell() + field.getWidthCell()*0.75f, cubeOne.getX()*field.getWidthCell() + field.getWidthCell()*0.25f, cubeOne.getPaint());
        }
        if(cubeOne.getX() < cubeTwo.getX()) {
            canvas.drawRect(cubeOne.getY()*field.getWidthCell() + field.getWidthCell()*0.25f, cubeOne.getX()*field.getWidthCell() + field.getWidthCell()*0.75f,
                    cubeTwo.getY()*field.getWidthCell() + field.getWidthCell()*0.75f, cubeTwo.getX()*field.getWidthCell() + field.getWidthCell()*0.25f, cubeOne.getPaint());
        }
        if(cubeOne.getY() > cubeTwo.getY()) {
            canvas.drawRect(cubeTwo.getY()*field.getWidthCell() + field.getWidthCell()*0.75f, cubeTwo.getX()*field.getWidthCell() + field.getWidthCell()*0.25f,
                    cubeOne.getY()*field.getWidthCell() + field.getWidthCell()*0.25f, cubeOne.getX()*field.getWidthCell() + field.getWidthCell()*0.75f, cubeOne.getPaint());
        }
        if(cubeOne.getY() < cubeTwo.getY()) {
            canvas.drawRect(cubeOne.getY()*field.getWidthCell() + field.getWidthCell()*0.75f, cubeOne.getX()*field.getWidthCell() + field.getWidthCell()*0.25f,
                    cubeTwo.getY()*field.getWidthCell() + field.getWidthCell()*0.25f, cubeTwo.getX()*field.getWidthCell() + field.getWidthCell()*0.75f, cubeOne.getPaint());
        }*/
    }


    public void onMove() {
        if(numberFrame > 0) {
            if (status == ControllerOne.UP) {
                xPixels = xPixels - speed;
            }
            if (status == ControllerOne.RIGHT) {
                yPixels = yPixels + speed;
            }
            if (status == ControllerOne.DOWN) {
                xPixels = xPixels + speed;
            }
            if (status == ControllerOne.LEFT) {
                yPixels = yPixels - speed;
            }
            numberFrame--;
        } else {
            status = ControllerOne.NO_ACTIVE;
            xPixels = x*field.getWidthCell();
            yPixels = y*field.getWidthCell();
        }
    }

    public void setMoveParams(int move, int count) {
        numberFrame = count * numberFrameCell;
        if(move == ControllerOne.UP) {
            status = ControllerOne.UP;
            x = x - count;
        }
        if(move == ControllerOne.RIGHT) {
            status = ControllerOne.RIGHT;
            y = y + count;
        }
        if(move == ControllerOne.DOWN) {
            status = ControllerOne.DOWN;
            x = x + count;
        }
        if(move == ControllerOne.LEFT) {
            status = ControllerOne.LEFT;
            y = y - count;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public EndPosition getEndPosition() {
        return endPosition;
    }

    public Bitmap getPaint() {
        return bitmap;
    }
}
