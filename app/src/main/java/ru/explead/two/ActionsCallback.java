package ru.explead.two;

/**
 * Created by develop on 18.05.2017.
 */

public interface ActionsCallback {
    void onMove();
    void onGameOver();
    void onScore(int score);
    void onRoundScore(int score);
}
