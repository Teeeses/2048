package ru.explead.two.views;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import ru.explead.two.logic.Cell;

/**
 * Created by develop on 07.08.2017.
 */

public class CellView extends View {

    private Context context;
    private Cell cell;
    private int size;
    private float globalX;
    private float globalY;

    public CellView(Context context, int size) {
        super(context);
        this.size = size;
        init(context);
    }

    public CellView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CellView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        this.context = context;
    }

    public void create(Cell cell) {
        this.cell = cell;

        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(size, size);
        this.setLayoutParams(param);
        this.setBackgroundColor(Color.GRAY);

        globalX = size*cell.getX();
        globalY = size*cell.getY();

        this.setX(globalX);
        this.setY(globalY);
    }
}
