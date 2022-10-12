package me.aroze.chatminigames.command;

import me.aroze.chatminigames.minigames.MinigameManager;
import me.aroze.chatminigames.utils.MiscUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.aroze.chatminigames.ChatMinigames.instance;
import static me.aroze.chatminigames.minigames.MinigameManager.resetGames;
import static me.aroze.chatminigames.minigames.MinigameManager.startingTime;
import static me.aroze.chatminigames.minigames.ReactionTime.commandString;
import static me.aroze.chatminigames.utils.santa.ChatUtils.colored;

public class ClickCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length != 1) return true;
        if (!args[0].equals(commandString)) return true;
        if (!(sender instanceof Player player)) return true;

        StringBuilder temp = new StringBuilder();
        for (int i=0; i<instance.getConfig().getStringList("messages.answered-correctly-broadcast.reactionTime").size(); i++) {
            temp.append(instance.getConfig().getStringList("messages.answered-correctly-broadcast.reactionTime").get(i))
                    .append(instance.getConfig().getStringList("messages.answered-correctly-broadcast.reactionTime").size() - 1 == i ? "" : "\n");
        }

        String message = colored(temp.toString()
                .replace("{player}", player.getName())
                .replace("{elapsedTime}", MiscUtils.makeTimestamp(System.currentTimeMillis() - startingTime)));
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) onlinePlayer.sendMessage(message);

        player.sendMessage(colored(instance.getConfig().getString("messages.answered-correctly-private.reactionTime")));
        Bukkit.getScheduler().runTask(instance, () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), instance.getConfig().getString("rewards.reactionTime").replace("{player}", player.getName())));
        if (instance.getConfig().getBoolean("misc.play-noteblock-pling")) player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, (float) instance.getConfig().getDouble("misc.noteblock-volume"), (float) instance.getConfig().getDouble("misc.noteblock-pitch"));

        resetGames();
        return true;
    }
}
