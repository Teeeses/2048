package ru.explead.two.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

import ru.explead.two.MainActivity;
import ru.explead.two.R;

/**
 * Created by develop on 18.05.2017.
 */

@EFragment
public class StartFragment extends Fragment {

    private int maxPage = 2;
    private int page = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start, container, false);

        TextView tvLevelText = (TextView) view.findViewById(R.id.tvLevelText);

        return view;
    }

    @Click(R.id.arrowLeft)
    public void clickArrowLeft() {
        changePage(false);
    }

    @Click(R.id.arrowRight)
    public void clickArrowRight() {
        changePage(true);
    }

    @Click(R.id.btnPlay)
    public void clickPlay() {
        ((MainActivity)getActivity()).openGameFragment(page);
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