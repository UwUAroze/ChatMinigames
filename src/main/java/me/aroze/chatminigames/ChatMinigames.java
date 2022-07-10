package me.aroze.chatminigames;

import me.aroze.chatminigames.command.StartChatGame;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChatMinigames extends JavaPlugin {

    static int mathNum1;
    static int mathNum2;
    static char mathOperator;
    static int mathAnswer;

    @Override
    public void onEnable() {
        getCommand("startchatgame").setExecutor(new StartChatGame());
    }

    @Override
    public void onDisable() {}

    public static void startGame(int game) {

        // 0 would be a random game
        if (game == 0) game = (int) ((Math.random()* 3) + 1);
        Bukkit.broadcastMessage(game + "");

        switch(game) {
            case 1: //Scramble
                break;

            case 2: //Rush
                break;

            case 3: // Math

                int temp = (int) ((Math.random()* 3));
                switch (temp) {
                    case 0: mathOperator = '+'; break;
                    case 1: mathOperator = 'x'; break;
                    case 2: mathOperator = '-'; break;
                }

                mathNum1 = (int) ((Math.random()* 70) + 5);
                mathNum2 = (int) ((Math.random()* 70) + 5);
                if (mathNum1 < mathNum2) {
                    mathNum1 = mathNum2;
                    mathNum2 = (int) ((Math.random() * mathNum1) + 1);
                }

                switch (mathOperator) {
                    case 'x':
                        mathNum1 = (int) ((Math.random()* 12) + 2);
                        mathNum2 = (int) ((Math.random()* 12) + 2);
                        mathAnswer = mathNum1 * mathNum2;
                        break;
                    case '+':
                        mathAnswer = mathNum1 + mathNum2;
                        break;
                    case '-':
                        mathAnswer = mathNum1 - mathNum2;
                        break;
                }

                Bukkit.broadcastMessage(mathNum1 + " " + mathOperator + " " + mathNum2 + " = " + mathAnswer);


                break;
        }
    }


}
