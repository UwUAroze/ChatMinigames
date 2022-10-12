package me.aroze.chatminigames;

import me.aroze.chatminigames.command.ChatMinigamesCommand;
import me.aroze.chatminigames.minigames.MinigameManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChatMinigames extends JavaPlugin {

    public static ChatMinigames instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        getCommand("chatminigames").setExecutor(new ChatMinigamesCommand());
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);

        if (getConfig().getBoolean("periodical.automatic-start")) {
            int delay = 20 * getConfig().getInt("periodical.interval-seconds");
            Bukkit.getScheduler().runTaskTimer(this, () -> MinigameManager.startGame(0), delay, delay);
        }

    }

    @Override
    public void onDisable() {}

}
