package ru.explead.two.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import org.androidannotations.annotations.Click;

import ru.explead.two.MainActivity;
import ru.explead.two.R;

public class StartFragment extends Fragment {

    private int maxPage = 2;
    private int page = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start, container, false);

        TextView tvLevelText = (TextView) view.findViewById(R.id.tvLevelText);

        ImageView arrowLeft = (ImageView) view.findViewById(R.id.arrowLeft);
        arrowLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePage(false);
            }
        });

        ImageView arrowRight = (ImageView) view.findViewById(R.id.arrowRight);
        arrowRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePage(false);
            }
        });

        ImageView btnPlay = (ImageView) view.findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).openGameFragment(page);
            }
        });

        return view;
    }

    private void changePage(boolean direction) {
        if(direction) {
            page++;
            if(page > maxPage) {
                page = 1;
            }
        } else {
            page--;
            if(page < 1) {
                page = maxPage;
            }
        }
    }
}
