package me.aroze.chatminigames.minigames;

import me.aroze.chatminigames.utils.WordUtils;
import me.aroze.chatminigames.utils.santa.ChatUtils;
import org.bukkit.Bukkit;

import static me.aroze.chatminigames.ChatMinigames.instance;

public class Rush {

    public static String rushWord = "";

    public static void start() {

        rushWord = WordUtils.getRandomWord();
        StringBuilder messageToBroadcast = new StringBuilder();

        for (int i=0; i<instance.getConfig().getStringList("messages.game-start.rush").size(); i++) {
            messageToBroadcast.append(instance.getConfig().getStringList("messages.game-start.rush").get(i))
                    .append(instance.getConfig().getStringList("messages.game-start.rush").size() - 1 == i ? "" : "\n");
        }

        Bukkit.broadcastMessage(ChatUtils.colored(messageToBroadcast.toString().replace("{rushWord}", rushWord)));
    }

}
