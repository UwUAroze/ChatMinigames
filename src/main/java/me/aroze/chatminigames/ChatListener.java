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

        if (e.getMessage().equals(mathAnswer) && !mathAnswer.isEmpty()) {
            e.setCancelled(true);

            StringBuilder messageToBroadcast = new StringBuilder();
            for (int i=0; i<instance.getConfig().getStringList("messages.answered-correctly-math").size(); i++) {
                messageToBroadcast.append(instance.getConfig().getStringList("messages.answered-correctly-math").get(i))
                        .append(instance.getConfig().getStringList("messages.answered-correctly-math").size() - 1 == i ? "" : "\n");
            }

            Bukkit.broadcastMessage(color(messageToBroadcast.toString()
                    .replace("{mathNum1}", mathNum1 + "")
                    .replace("{mathNum2}", mathNum2 + "")
                    .replace("{mathOperation}", mathOperation + "")
                    .replace("{mathAnswer}", mathAnswer + "")
                    .replace("{player}", e.getPlayer().getName())
                    .replace("{elapsedTime}", makeTimestamp(System.currentTimeMillis() - startingTime))));

            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), instance.getConfig().getString("rewards.math").replace("{player}", e.getPlayer().getName()));
            if (instance.getConfig().getBoolean("misc.play-noteblock-pling")) e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, (float) instance.getConfig().getDouble("noteblock-volume"), (float) instance.getConfig().getDouble("noteblock-pitch"));

            mathAnswer = "";

            return;
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
