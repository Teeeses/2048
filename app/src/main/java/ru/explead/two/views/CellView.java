package ru.explead.two.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Locale;

import ru.explead.two.R;
import ru.explead.two.logic.Cell;
import ru.explead.two.utils.Utils;

/**
 * Created by develop on 07.08.2017.
 */

public class CellView extends RelativeLayout {

    private Context context;
    private Cell cell;
    private int size;
    private float globalX;
    private float globalY;

    private TextView textView;

    public CellView(Context context) {
        super(context);
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

    public void create(Cell cell, int size) {
        this.cell = cell;
        this.size = size;


        textView = (TextView) LayoutInflater.from(context).inflate(R.layout.cell, null, false);
        textView.setLayoutParams(new RelativeLayout.LayoutParams(size, size));
        update();

        globalX = size*cell.getX();
        globalY = size*cell.getY();

        this.setX(globalX);
        this.setY(globalY);

        this.addView(textView);
    }

    public void update() {
        textView.setBackgroundColor(Utils.getColor(textView, cell.getValue()));
        textView.setTextColor(Utils.getColorText(cell.getValue()));
        setText();
    }

    public void animationNewCell() {
        if(cell.getValue() >= 2) {
            Animation anim = AnimationUtils.loadAnimation(context, R.anim.scale_appear);
            textView.startAnimation(anim);
        }
    }

    public void setText() {
        if(cell.getValue() == 1) {
            textView.setText("");
        } else if(cell.getValue() != 0) {
            textView.setText(String.format(Locale.ROOT, "%d", cell.getValue()));
        }
    }
}
