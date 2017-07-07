package ru.explead.two;

public interface ActionsCallback {
    void onMove();
    void onGameOver();
    void onScore(int score);
    void onRoundScore(int score);
}
