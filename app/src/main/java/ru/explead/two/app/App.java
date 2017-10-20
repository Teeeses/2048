package ru.explead.two.app;

import android.app.Application;

import ru.explead.two.logic.Controller;
import ru.explead.two.logic.Level;


/**
 * Created by develop on 30.12.2016.
 */

public class App extends Application {

    private static float widthScreen;
    private static float heightScreen;
    private static int sizeSurface;

    private static Controller controller;
    private static Level level;


    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static float getWidthScreen() {
        return widthScreen;
    }

    public static void setWidthScreen(float widthScreen) {
        App.widthScreen = widthScreen;
    }

    public static float getHeightScreen() {
        return heightScreen;
    }

    public static void setHeightScreen(float heightScreen) {
        App.heightScreen = heightScreen;
    }

    public static int getSizeSurface() {
        return sizeSurface;
    }

    public static void setSizeSurface(int sizeSurface) {
        App.sizeSurface = sizeSurface;
    }

    public static Controller getController() {
        return controller;
    }

    public static void setController(Controller controller) {
        App.controller = controller;
    }

    public static Level getLevel() {
        return level;
    }

    public static void setLevel(Level level) {
        App.level = level;
    }

}
