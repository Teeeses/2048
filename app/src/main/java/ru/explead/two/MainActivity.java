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

        openGameFragment();
    }

    public void openGameFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        fragment = new GameFragment();
        transaction.replace(R.id.fragmentContainer, fragment);
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
