package me.aroze.chatminigames;

import me.aroze.chatminigames.command.StartChatGame;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChatMinigames extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("startchatgame").setExecutor(new StartChatGame());
    }

    @Override
    public void onDisable() {


    }
}
