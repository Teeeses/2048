package ru.explead.two.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import ru.explead.two.logic.Cell;
import ru.explead.two.logic.Field;

/**
 * Created by develop on 07.08.2017.
 */

public class FieldView extends RelativeLayout {

    private Context context;
    private Field field;
    private int sizeField;

    private int sizeCell;

    private CellView[][] fieldView;

    public FieldView(Context context) {
        super(context);
        init(context);
    }

    public FieldView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FieldView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        this.context = context;
    }

    public void setField(Field field, int sizeField) {
        this.field = field;
        this.sizeField = sizeField;
        sizeCell = sizeField/field.getSize();

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(sizeField, sizeField);
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        this.setLayoutParams(params);

        createFieldView();
    }


    public void createFieldView() {
        fieldView = new CellView[field.getSize()][field.getSize()];
        LinearLayout verticalLayout = new LinearLayout(context);
        verticalLayout.setOrientation(LinearLayout.VERTICAL);
        verticalLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        for(int i = 0; i < field.getSize(); i++) {
            LinearLayout horizontalLayout = new LinearLayout(context);
            horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
            horizontalLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, sizeCell));
            for(int j = 0; j < field.getSize(); j++) {
                Cell cell = field.getCell(i, j);
                CellView cellView = new CellView(context);
                cellView.create(cell, sizeCell);
                fieldView[i][j] = cellView;

                horizontalLayout.addView(cellView);
            }
            verticalLayout.addView(horizontalLayout);
        }
        this.addView(verticalLayout);
    }

    public void update() {
        for(int i = 0; i < fieldView.length; i++) {
            for(int j = 0; j < fieldView.length; j++) {
                fieldView[i][j].update();
            }
        }
    }
}
