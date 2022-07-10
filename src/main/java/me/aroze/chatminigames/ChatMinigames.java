package me.aroze.chatminigames;

import me.aroze.chatminigames.command.StartChatGame;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ChatMinigames extends JavaPlugin {

    private static final Pattern hexPattern = Pattern.compile("&#[a-fA-F0-9]{6}");
    static int mathNum1;
    static int mathNum2;
    static char mathOperator;
    static int mathAnswer;
    public static ChatMinigames instance;

    @Override
    public void onEnable() {
        instance = this;
        getCommand("startchatgame").setExecutor(new StartChatGame());
    }

    @Override
    public void onDisable() {}

    public static String color(String text) {
        Matcher match = hexPattern.matcher(text);
        while (match.find()) {
            String color = text.substring(match.start(), match.end());
            text = text.replace(color, ChatColor.of(color.substring(1)).toString());
            match = hexPattern.matcher(text);
        }
        return ChatColor.translateAlternateColorCodes('&', text);
    }



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
