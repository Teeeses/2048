package ru.explead.two.utils;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;

import ru.explead.two.MainActivity;
import ru.explead.two.R;
import ru.explead.two.app.App;


public class Utils {


    public final static String CURRENT_LEVEL = "current_level";
    public static final String APP_PREFERENCES = "mysettings";

    /*public static Typeface getTypeFaceLevel() {
        return Typeface.createFromAsset(LevelsActivity.getActivity().getAssets(),"fonts/level_personal.ttf");
    }*/

    public static int getColor(View view, int value) {
        Resources res = MainActivity.getActivity().getResources();
        int color = res.getColor(R.color.white);
        if(value == 0) {
            color = res.getColor(R.color.white);
        } else if(value == 1) {
            color = res.getColor(R.color.black);
        } else if(value == 2) {
            color = res.getColor(R.color.two_cell);
        } else if(value == 4) {
            color = res.getColor(R.color.four_cell);
        } else if(value == 8) {
            color = res.getColor(R.color.eight_cell);
        } else if(value == 16) {
            color = res.getColor(R.color.sixteen_cell);
        } else if(value == 32) {
            color = res.getColor(R.color.thirty_two_cell);
        }
        return color;
    }

    public static int getColorText(int value) {
        Resources res = MainActivity.getActivity().getResources();
        int color = res.getColor(R.color.white);
        if(value == 0 || value == 1) {
            color = res.getColor(android.R.color.transparent);
        } else if(value >= 2 && value <= 4) {
            color = res.getColor(R.color.dark_text_cell);
        } else if(value > 4) {
            color = res.getColor(android.R.color.white);
        }
        return  color;
    }
}
