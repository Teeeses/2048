package ru.explead.two;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;


import ru.explead.two.app.App;
import ru.explead.two.fragments.GameFragment;
import ru.explead.two.fragments.StartFragment;
import ru.explead.two.utils.Utils;

public class MainActivity extends AppCompatActivity {


    private static Activity activity;
    private static Fragment fragment;

    private static SharedPreferences sPref;
    private static Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;
        res = this.getResources();
        sPref = getSharedPreferences(Utils.APP_PREFERENCES, MODE_PRIVATE);

        DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
        App.setWidthScreen(displaymetrics.widthPixels);
        App.setHeightScreen(displaymetrics.heightPixels);

        openStartFragment();
    }

    public void openStartFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        fragment = new StartFragment();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.commit();
    }

    public void openGameFragment(int page) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        fragment = new GameFragment();
        Bundle args = new Bundle();
        args.putInt("page", page);
        fragment.setArguments(args);
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public static Activity getActivity() {
        return activity;
    }

    public static Fragment getFragment() {
        return fragment;
    }

    public static SharedPreferences getsPref() {
        return sPref;
    }

    public static Resources getRes() {
        return res;
    }
}
