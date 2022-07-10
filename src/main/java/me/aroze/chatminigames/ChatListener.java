package me.aroze.chatminigames;

import org.bukkit.Bukkit;
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


            mathAnswer = "";

            return;
        }

    }

    public String makeTimestamp(long milliseconds) {
        StringBuilder timestamp = new StringBuilder();

        double seconds = milliseconds / 1000;
        long minutes = (milliseconds / 1000) / 60;

        if (minutes == 1) timestamp.append("1 minute and ");
        else if (minutes > 0) timestamp.append(minutes + " minutes and ");
        timestamp.append(seconds + " seconds");

        return timestamp.toString();
    }

}
