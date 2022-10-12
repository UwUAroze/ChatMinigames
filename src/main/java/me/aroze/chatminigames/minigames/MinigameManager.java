package me.aroze.chatminigames.minigames;

public class MinigameManager {

    public static long startingTime;

    public static void resetGames() {
        Math.mathAnswer = "";
        Rush.rushWord = "";
        Unscramble.actualWord = "";
        ReactionTime.commandString = "";
    }

    public static void startGame(int game) {

        resetGames();

        // 0 would be a random game
        if (game == 0) game = (int) ((java.lang.Math.random()* 4) + 1);

        switch (game) {
            case 1: Math.start(); break;
            case 2: Rush.start(); break;
            case 3: Unscramble.start(); break;
            case 4: ReactionTime.start(); break;
        }

        startingTime = System.currentTimeMillis();

    }

}
