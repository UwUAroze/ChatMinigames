package me.aroze.chatminigames.minigames;

import me.aroze.chatminigames.utils.WordUtils;
import me.aroze.chatminigames.utils.santa.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static me.aroze.chatminigames.ChatMinigames.instance;

public class Rush {

    public static String rushWord = "";

    public static void start() {

        rushWord = WordUtils.getRandomWord();
        StringBuilder temp = new StringBuilder();

        for (int i=0; i<instance.getConfig().getStringList("messages.game-start.rush").size(); i++) {
            temp.append(instance.getConfig().getStringList("messages.game-start.rush").get(i))
                    .append(instance.getConfig().getStringList("messages.game-start.rush").size() - 1 == i ? "" : "\n");
        }

        String message = ChatUtils.colored(temp.toString().replace("{rushWord}", rushWord));
        for (Player player : Bukkit.getOnlinePlayers()) player.sendMessage(message);
    }

}
