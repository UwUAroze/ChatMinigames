package me.aroze.chatminigames.listener;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import static me.aroze.chatminigames.ChatMinigames.instance;
import static me.aroze.chatminigames.minigames.Math.*;
import static me.aroze.chatminigames.minigames.MinigameManager.resetGames;
import static me.aroze.chatminigames.minigames.MinigameManager.startingTime;
import static me.aroze.chatminigames.minigames.Rush.*;
import static me.aroze.chatminigames.minigames.Unscramble.*;
import static me.aroze.chatminigames.utils.MiscUtils.makeTimestamp;
import static me.aroze.chatminigames.utils.santa.ChatUtils.colored;

public class ChatListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onChat(org.bukkit.event.player.AsyncPlayerChatEvent e) {

        if (!mathAnswer.isEmpty() && e.getMessage().equals(mathAnswer)) {

            if (instance.getConfig().getBoolean("misc.cancel-messages")) e.setCancelled(true);

            StringBuilder temp = new StringBuilder();
            for (int i=0; i<instance.getConfig().getStringList("messages.answered-correctly-broadcast.math").size(); i++) {
                temp.append(instance.getConfig().getStringList("messages.answered-correctly-broadcast.math").get(i))
                        .append(instance.getConfig().getStringList("messages.answered-correctly-broadcast.math").size() - 1 == i ? "" : "\n");
            }

            String message = colored(temp.toString()
                    .replace("{mathNum1}", mathNum1 + "")
                    .replace("{mathNum2}", mathNum2 + "")
                    .replace("{mathOperation}", mathOperation + "")
                    .replace("{mathAnswer}", mathAnswer + "")
                    .replace("{player}", e.getPlayer().getName())
                    .replace("{elapsedTime}", makeTimestamp(System.currentTimeMillis() - startingTime)));
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) onlinePlayer.sendMessage(message);

            e.getPlayer().sendMessage(colored(instance.getConfig().getString("messages.answered-correctly-private.math")));
            Bukkit.getScheduler().runTask(instance, () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), instance.getConfig().getString("rewards.math").replace("{player}", e.getPlayer().getName())));
            if (instance.getConfig().getBoolean("misc.play-noteblock-pling")) e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, (float) instance.getConfig().getDouble("misc.noteblock-volume"), (float) instance.getConfig().getDouble("misc.noteblock-pitch"));

            resetGames();
            return;
        }

        if (!rushWord.isEmpty() && e.getMessage().equalsIgnoreCase(rushWord)) {

            if (instance.getConfig().getBoolean("misc.case-sensitive.rush") && !e.getMessage().equals(rushWord)) return;
            if (instance.getConfig().getBoolean("misc.cancel-messages")) e.setCancelled(true);

            StringBuilder temp = new StringBuilder();
            for (int i=0; i<instance.getConfig().getStringList("messages.answered-correctly-broadcast.rush").size(); i++) {
                temp.append(instance.getConfig().getStringList("messages.answered-correctly-broadcast.rush").get(i))
                        .append(instance.getConfig().getStringList("messages.answered-correctly-broadcast.rush").size() - 1 == i ? "" : "\n");
            }

            String message = colored(temp.toString()
                    .replace("{rushWord}", rushWord)
                    .replace("{player}", e.getPlayer().getName())
                    .replace("{elapsedTime}", makeTimestamp(System.currentTimeMillis() - startingTime)));
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) onlinePlayer.sendMessage(message);

            e.getPlayer().sendMessage(colored(instance.getConfig().getString("messages.answered-correctly-private.rush")));
            Bukkit.getScheduler().runTask(instance, () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), instance.getConfig().getString("rewards.rush").replace("{player}", e.getPlayer().getName())));
            if (instance.getConfig().getBoolean("misc.play-noteblock-pling")) e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, (float) instance.getConfig().getDouble("misc.noteblock-volume"), (float) instance.getConfig().getDouble("misc.noteblock-pitch"));

            resetGames();
            return;
        }

        if (!actualWord.isEmpty() && e.getMessage().equalsIgnoreCase(actualWord)) {

            if (instance.getConfig().getBoolean("misc.case-sensitive.unscramble") && !e.getMessage().equals(actualWord)) return;
            if (instance.getConfig().getBoolean("misc.cancel-messages")) e.setCancelled(true);

            StringBuilder temp = new StringBuilder();
            for (int i=0; i<instance.getConfig().getStringList("messages.answered-correctly-broadcast.unscramble").size(); i++) {
                temp.append(instance.getConfig().getStringList("messages.answered-correctly-broadcast.unscramble").get(i))
                        .append(instance.getConfig().getStringList("messages.answered-correctly-broadcast.unscramble").size() - 1 == i ? "" : "\n");
            }

            String message = colored(temp.toString()
                    .replace("{actualWord}", actualWord)
                    .replace("{scrambledWord}", scrambledWord)
                    .replace("{player}", e.getPlayer().getName())
                    .replace("{elapsedTime}", makeTimestamp(System.currentTimeMillis() - startingTime)));
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) onlinePlayer.sendMessage(message);

            e.getPlayer().sendMessage(colored(instance.getConfig().getString("messages.answered-correctly-private.unscramble")));
            Bukkit.getScheduler().runTask(instance, () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), instance.getConfig().getString("rewards.unscramble").replace("{player}", e.getPlayer().getName())));
            if (instance.getConfig().getBoolean("misc.play-noteblock-pling")) e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, (float) instance.getConfig().getDouble("misc.noteblock-volume"), (float) instance.getConfig().getDouble("misc.noteblock-pitch"));

            resetGames();
            return;
        }

    }

}
