package ru.explead.two.fragments;

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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import ru.explead.two.R;
import ru.explead.two.Surface;
import ru.explead.two.app.App;
import ru.explead.two.logic.Controller;

/**
 * Created by develop on 15.12.2016.
 */
public class GameFragment extends Fragment {

    private int start_x, start_y, end_x, end_y;
    private long startTouch = 0l, endTouch = 0l;

    private Surface surface;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);



        onTouch(view);

        createSurface(view);
        startGame();

        return view;
    }

    public void createSurface(View view) {

        surface = new Surface(getActivity());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(App.getSizeSurface(), App.getSizeSurface());
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        surface.setLayoutParams(params);

        view.addView(surface);
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
