package me.aroze.chatminigames.minigames;

public class MinigameManager {

    public static long startingTime;

    public static void resetGames() {
        Math.mathAnswer = "";
        Rush.rushWord = "";
        Unscramble.actualWord = "";
    }

    public static void startGame(int game) {

        resetGames();

        // 0 would be a random game
        if (game == 0) game = (int) ((java.lang.Math.random()* 3) + 1);

        switch (game) {
            case 1: Math.start(); break;
            case 2: Rush.start(); break;
            case 3: Unscramble.start(); break;
        }

        startingTime = System.currentTimeMillis();

    }

}
