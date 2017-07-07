package ru.explead.two.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import ru.explead.two.ActionsCallback;
import ru.explead.two.R;
import ru.explead.two.app.App;
import ru.explead.two.logic.Cell;
import ru.explead.two.logic.Controller;
import ru.explead.two.logic.Level;


@EFragment
public class GameFragment extends Fragment implements ActionsCallback {

    private int start_x, start_y, end_x, end_y;

    @ViewById
    RelativeLayout rootLayout;

    @ViewById
    TextView tvScore;

    private LinearLayout field;

    private int widthField;

    private Controller controller;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        int level = getArguments().getInt("page", 0);
        App.setLevel(new Level(level));

        onTouch(view);

        return view;
    }

    @AfterViews
    public void create() {
        createField(rootLayout);
        startGame();
        createTable();
    }

    public void startGame() {
        controller = new Controller(this);
        App.setController(controller);
        controller.startGame();
    }

    private void createField(RelativeLayout rootLayout) {
        field = new LinearLayout(getActivity());
        widthField = (int) App.getWidthScreen() - 30;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(widthField, widthField);
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        field.setLayoutParams(params);
        field.setOrientation(LinearLayout.VERTICAL);
        int margin = getContext().getResources().getDimensionPixelSize(R.dimen.radius);
        field.setPadding(margin, margin, margin, margin);

        rootLayout.addView(field);
    }


    public void createTable() {
        field.removeAllViews();
        int number = App.getController().getField().getSize();
        int size = (widthField - 2*getContext().getResources().getDimensionPixelSize(R.dimen.radius))/number;

        for(int i = 0; i < number; i++) {
            LinearLayout lay = new LinearLayout(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, size);
            lay.setOrientation(LinearLayout.HORIZONTAL);
            lay.setLayoutParams(params);
            for(int j = 0; j < number; j++) {
                Cell[][] cells = App.getController().getField().getField();
                lay.addView(cells[i][j].getLayout());
            }
            field.addView(lay);
        }
    }

    public void onTouch(View view) {
        view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                if(App.getController().getStatus() == Controller.ACTIVE_GAME) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            start_x = (int) event.getX();
                            start_y = (int) event.getY();
                            break;
                        case MotionEvent.ACTION_UP:
                            end_x = (int) event.getX();
                            end_y = (int) event.getY();
                            controller.action(start_x, start_y, end_x, end_y);
                            break;
                        default:
                            break;
                    }
                }

                return true;
            }
        });
    }

    @Override
    public void onMove() {
        Cell[][] cells = App.getController().getField().getField();
        for(int i = 0; i < cells.length; i++) {
            for(int j = 0; j < cells.length; j++) {
                cells[i][j].update();
            }
        }

        /*System.out.println("\n*******************************");
        for(int i = 0; i < cells.length; i++) {
            System.out.println();
            for(int j = 0; j < cells.length; j++) {
                cells[i][j].update();
                System.out.print(Integer.toString(cells[i][j].getValue()) + " ");
            }
        }*/
    }

    @Override
    public void onGameOver() {
        Toast.makeText(getContext(), "Конец игры!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onScore(int score) {
        tvScore.setText(Integer.toString(score));
    }

    @Override
    public void onRoundScore(int score) {
        Toast.makeText(getContext(), "Круглое чилос - " + Integer.toString(score), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
