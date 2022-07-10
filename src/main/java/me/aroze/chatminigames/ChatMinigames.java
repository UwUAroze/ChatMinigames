package me.aroze.chatminigames;

import me.aroze.chatminigames.command.StartChatGame;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChatMinigames extends JavaPlugin {

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
                break;
        }
    }


}
