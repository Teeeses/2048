package ru.explead.two.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import ru.explead.two.logic.Cell;
import ru.explead.two.logic.Field;

/**
 * Created by develop on 07.08.2017.
 */

public class FieldView extends RelativeLayout {

    private Context context;
    private Field field;
    private int widthField;

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

    public void setField(Field field, int widthField) {
        this.field = field;
        this.widthField = widthField;

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(widthField, widthField);
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        this.setLayoutParams(params);
    }

    public void createEmptyCells() {
        for(int i = 0; i < field.getSize(); i++) {
            for(int j = 0; j < field.getSize(); j++) {
                Cell cell = field.getCell(i, j);
                if(cell.getValue() == 0) {
                    CellView cellView = new CellView(context, widthField/field.getSize());
                    cellView.create(cell);
                    this.addView(cellView);
                }
            }
        }
    }
}
