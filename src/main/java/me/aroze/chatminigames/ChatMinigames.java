package me.aroze.chatminigames;

import me.aroze.chatminigames.command.StartChatGame;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ChatMinigames extends JavaPlugin {

    private static final Pattern hexPattern = Pattern.compile("&#[a-fA-F0-9]{6}");
    static int mathNum1;
    static int mathNum2;
    static char mathOperation;
    static String mathAnswer;
    static long startingTime;
    static List<String> wordList;
    public static ChatMinigames instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        wordList = getConfig().getStringList("wordList");
        getCommand("startchatgame").setExecutor(new StartChatGame());
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
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

        switch(game) {
            case 1: //Scramble
                break;

            case 2: //Rush

                String randomWord = wordList.get((int) Math.floor(Math.random() * wordList.size()));
                Bukkit.broadcastMessage(randomWord);

                break;

            case 3: // Math

                int temp = (int) ((Math.random()* 3));
                switch (temp) {
                    case 0: mathOperation = '+'; break;
                    case 1: mathOperation = 'x'; break;
                    case 2: mathOperation = '-'; break;
                }

                mathNum1 = (int) ((Math.random()* 70) + 5);
                mathNum2 = (int) ((Math.random()* 70) + 5);
                if (mathNum1 < mathNum2) {
                    mathNum1 = mathNum2;
                    mathNum2 = (int) ((Math.random() * mathNum1) + 1);
                }

                switch (mathOperation) {
                    case 'x':
                        mathNum1 = (int) ((Math.random()* 11) + 2);
                        mathNum2 = (int) ((Math.random()* 11) + 2);
                        mathAnswer = String.valueOf(mathNum1 * mathNum2);
                        break;
                    case '+':
                        mathAnswer = String.valueOf(mathNum1 + mathNum2);
                        break;
                    case '-':
                        mathAnswer = String.valueOf(mathNum1 - mathNum2);
                        break;
                }

                StringBuilder messageToBroadcast = new StringBuilder();

                for (int i=0; i<instance.getConfig().getStringList("messages.game-start-math").size(); i++) {
                    messageToBroadcast.append(instance.getConfig().getStringList("messages.game-start-math").get(i))
                            .append(instance.getConfig().getStringList("messages.game-start-math").size() - 1 == i ? "" : "\n");
                }

                Bukkit.broadcastMessage(color(messageToBroadcast.toString()
                        .replace("{mathNum1}", mathNum1 + "")
                        .replace("{mathNum2}", mathNum2 + "")
                        .replace("{mathOperation}", mathOperation + "")));

                startingTime = System.currentTimeMillis();

                break;
        }
    }


}
