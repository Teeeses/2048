package ru.explead.two.logic;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ru.explead.two.MainActivity;
import ru.explead.two.R;
import ru.explead.two.app.App;
import ru.explead.two.utils.Utils;

/**
 * Created by Александр on 13.05.2017.
 */

public class Cell {

    private int value;
    private int x;
    private int nextX;
    private int y;
    private int nextY;

    private Context context;

    private RelativeLayout layout;
    private TextView view;

    public Cell(int value) {
        context = MainActivity.getActivity().getBaseContext();
        this.value = value;
        createCell();
    }

    public void createCell() {
        int number = App.getLenght();
        int size = ((int)App.getWidthScreen() - 30 - 2*context.getResources().getDimensionPixelSize(R.dimen.radius))/number;

        layout = (RelativeLayout) MainActivity.getActivity().getLayoutInflater().inflate(R.layout.cell, null, false);
        layout.setLayoutParams(new RelativeLayout.LayoutParams(size, size));

        view = (TextView) layout.findViewById(R.id.cell);
        view.setTextSize(context.getResources().getDimensionPixelSize(R.dimen.standard_text));
        view.setBackgroundColor(Utils.getColor(value));
        view.setTextColor(Utils.getColorText(value));
        if(value != 0) {
            view.setText(Integer.toString(value));
        }
        view.setTextSize(28);
    }

    public void update() {
        view.setBackgroundColor(Utils.getColor(value));
        view.setTextColor(Utils.getColorText(value));
        if(value != 0) {
            view.setText(Integer.toString(value));
        }
    }

    public void changeCoordinate(int x, int y) {
        this.nextX = x;
        this.nextY = y;
    }

    public void animationNewCell() {
        if(value >= 2) {
            Animation anim = AnimationUtils.loadAnimation(context, R.anim.scale);
            view.startAnimation(anim);
        }
    }

    public RelativeLayout getLayout() {
        return layout;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getNextX() {
        return nextX;
    }

    public void setNextX(int nextX) {
        this.nextX = nextX;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getNextY() {
        return nextY;
    }

    public void setNextY(int nextY) {
        this.nextY = nextY;
    }

    public TextView getView() {
        return view;
    }

    public void setView(TextView view) {
        this.view = view;
    }
}
