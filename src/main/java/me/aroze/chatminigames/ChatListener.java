package me.aroze.chatminigames;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import static me.aroze.chatminigames.ChatMinigames.*;

public class ChatListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(org.bukkit.event.player.AsyncPlayerChatEvent e) {

        if (!mathAnswer.isEmpty() && e.getMessage().equals(mathAnswer)) {

            if (instance.getConfig().getBoolean("misc.cancel-messages")) e.setCancelled(true);

            StringBuilder messageToBroadcast = new StringBuilder();
            for (int i=0; i<instance.getConfig().getStringList("messages.answered-correctly-broadcast.math").size(); i++) {
                messageToBroadcast.append(instance.getConfig().getStringList("messages.answered-correctly-broadcast.math").get(i))
                        .append(instance.getConfig().getStringList("messages.answered-correctly-broadcast.math").size() - 1 == i ? "" : "\n");
            }

            Bukkit.broadcastMessage(color(messageToBroadcast.toString()
                    .replace("{mathNum1}", mathNum1 + "")
                    .replace("{mathNum2}", mathNum2 + "")
                    .replace("{mathOperation}", mathOperation + "")
                    .replace("{mathAnswer}", mathAnswer + "")
                    .replace("{player}", e.getPlayer().getName())
                    .replace("{elapsedTime}", makeTimestamp(System.currentTimeMillis() - startingTime))));

            e.getPlayer().sendMessage(color(instance.getConfig().getString("messages.answered-correctly-private.math")));
            Bukkit.getScheduler().runTask(instance, () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), instance.getConfig().getString("rewards.math").replace("{player}", e.getPlayer().getName())));
            if (instance.getConfig().getBoolean("misc.play-noteblock-pling")) e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, (float) instance.getConfig().getDouble("misc.noteblock-volume"), (float) instance.getConfig().getDouble("misc.noteblock-pitch"));

            mathAnswer = "";

            return;
        }

        if (!rushWord.isEmpty() && e.getMessage().equalsIgnoreCase(rushWord)) {

            if (instance.getConfig().getBoolean("misc.case-sensitive.rush") && !e.getMessage().equals(rushWord)) return;
            if (instance.getConfig().getBoolean("misc.cancel-messages")) e.setCancelled(true);

            StringBuilder messageToBroadcast = new StringBuilder();
            for (int i=0; i<instance.getConfig().getStringList("messages.answered-correctly-broadcast.rush").size(); i++) {
                messageToBroadcast.append(instance.getConfig().getStringList("messages.answered-correctly-broadcast.rush").get(i))
                        .append(instance.getConfig().getStringList("messages.answered-correctly-broadcast.rush").size() - 1 == i ? "" : "\n");
            }

            Bukkit.broadcastMessage(color(messageToBroadcast.toString()
                    .replace("{rushWord}", rushWord)
                    .replace("{player}", e.getPlayer().getName())
                    .replace("{elapsedTime}", makeTimestamp(System.currentTimeMillis() - startingTime))));

            e.getPlayer().sendMessage(color(instance.getConfig().getString("messages.answered-correctly-private.rush")));
            Bukkit.getScheduler().runTask(instance, () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), instance.getConfig().getString("rewards.math").replace("{player}", e.getPlayer().getName())));
            if (instance.getConfig().getBoolean("misc.play-noteblock-pling")) e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, (float) instance.getConfig().getDouble("misc.noteblock-volume"), (float) instance.getConfig().getDouble("misc.noteblock-pitch"));

            rushWord = "";
        }

        if (!actualWord.isEmpty() && e.getMessage().equalsIgnoreCase(actualWord)) {

            if (instance.getConfig().getBoolean("misc.case-sensitive.unscramble") && !e.getMessage().equals(actualWord)) return;
            if (instance.getConfig().getBoolean("misc.cancel-messages")) e.setCancelled(true);

        }

    }

    public String makeTimestamp(double milliseconds) {
        StringBuilder timestamp = new StringBuilder();

        double seconds = Math.round(milliseconds / 10) / 100.0; // this rounds to 2dp
        int minutes = (int) ((milliseconds / 1000) / 60);

        if (minutes == 1) timestamp.append("1 minute and ");
        else if (minutes > 0) timestamp.append(minutes + " minutes and ");
        timestamp.append(seconds + " seconds");

        return timestamp.toString();
    }

}
