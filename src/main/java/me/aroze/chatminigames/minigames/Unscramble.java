package me.aroze.chatminigames.minigames;

import me.aroze.chatminigames.utils.WordUtils;
import me.aroze.chatminigames.utils.santa.ChatUtils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;

import static me.aroze.chatminigames.ChatMinigames.instance;

public class Unscramble {

    public static String actualWord = "";
    public static String scrambledWord = "";

    public static void start() {

        actualWord = WordUtils.getRandomWord();
        scrambledWord = WordUtils.shuffleString(actualWord);
        StringBuilder messageToBroadcast = new StringBuilder();

        for (int i=0; i<instance.getConfig().getStringList("messages.game-start.unscramble").size(); i++) {
            messageToBroadcast.append(instance.getConfig().getStringList("messages.game-start.unscramble").get(i))
                    .append(instance.getConfig().getStringList("messages.game-start.unscramble").size() - 1 == i ? "" : "\n");
        }

        Bukkit.broadcastMessage(ChatUtils.colored(messageToBroadcast.toString().replace("{scrambledWord}", scrambledWord)));

    }

}
