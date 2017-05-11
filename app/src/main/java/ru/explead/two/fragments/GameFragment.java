package ru.explead.two.fragments;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import ru.explead.two.R;
import ru.explead.two.Surface;
import ru.explead.two.app.App;
import ru.explead.two.logic.Controller;
import ru.explead.two.logic.Level;

/**
 * Created by develop on 15.12.2016.
 */
public class GameFragment extends Fragment {

    private int start_x, start_y, end_x, end_y;
    private long startTouch = 0l, endTouch = 0l;

    private LinearLayout field;
    private Surface surface;

    private int widthField;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        RelativeLayout rootLayout = (RelativeLayout) view.findViewById(R.id.rootLayout);

        onTouch(view);

        App.setLevel(new Level(1));

        createField(rootLayout);
        startGame();
        createTable();

        return view;
    }

    private void createField(RelativeLayout rootLayout) {
        field = new LinearLayout(getActivity());
        widthField = (int) App.getWidthScreen() - 30;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(widthField, widthField);
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        field.setLayoutParams(params);
        field.setOrientation(LinearLayout.VERTICAL);
        field.setBackgroundColor(Color.TRANSPARENT);

        rootLayout.addView(field);

    }


    public void createTable() {
        field.removeAllViews();
        int number = App.getController().getField().getSize();
        int size = (widthField)/number;
        LayoutInflater inflater = getActivity().getLayoutInflater();

        for(int i = 0; i < number; i++) {
            LinearLayout lay = new LinearLayout(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, size);
            lay.setOrientation(LinearLayout.HORIZONTAL);
            lay.setLayoutParams(params);
            for(int j = 0; j < number; j++) {
                RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.cell, null, false);
                layout.setLayoutParams(new RelativeLayout.LayoutParams(size, size));

                TextView view = (TextView) layout.findViewById(R.id.cell);
                view.setTextSize(getContext().getResources().getDimensionPixelSize(R.dimen.standard_text));
                view.setBackgroundColor(getContext().getResources().getColor(R.color.menu_blue));
                int value = App.getController().getField().getValue(i, j);
                if(value != 0) {
                    view.setText(Integer.toString(value));
                }
                view.setTextSize(28);
                lay.addView(layout);
            }
            field.addView(lay);
        }
    }


    public void createSurface(RelativeLayout rootLayout) {

        surface = new Surface(getActivity());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(App.getSizeSurface(), App.getSizeSurface());
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        surface.setLayoutParams(params);

        rootLayout.addView(surface);
    }

    public void startGame() {
        Controller controller = new Controller();
        App.setController(controller);
        controller.startGame();
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
                            (App.getController()).logicMove(start_x, start_y, end_x, end_y);
                            createTable();
                            break;
                        default:
                            break;
                    }
                }

                return true;
            }
        });
    }

    public void onWin() {
        Log.d("TAG", "WIN");
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), "Победа", Toast.LENGTH_SHORT).show();

            }
        });
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
