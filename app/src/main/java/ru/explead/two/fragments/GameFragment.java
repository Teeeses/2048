package ru.explead.two.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import ru.explead.two.ActionsCallback;
import ru.explead.two.R;
import ru.explead.two.app.App;
import ru.explead.two.logic.Controller;
import ru.explead.two.logic.Level;
import ru.explead.two.views.FieldView;


public class GameFragment extends Fragment implements ActionsCallback {

    private int start_x, start_y, end_x, end_y;

    private TextView tvScore;

    private Controller controller;
    private FieldView fieldView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        tvScore = (TextView) view.findViewById(R.id.tvScore);

        int level = getArguments().getInt("page", 0);
        App.setLevel(new Level(level));

        controller = new Controller(this);
        App.setController(controller);
        controller.startGame();


        createField(view);
        onTouch(view);

        return view;
    }


    private void createField(View view) {
        fieldView = (FieldView) view.findViewById(R.id.fieldView);
        fieldView.setField(controller.getField(), (int)App.getWidthScreen() - 30);
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
        fieldView.update();

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
        Toast.makeText(getContext(), "Круглое число - " + Integer.toString(score), Toast.LENGTH_SHORT).show();
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
