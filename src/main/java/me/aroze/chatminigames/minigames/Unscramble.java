package me.aroze.chatminigames.minigames;

import me.aroze.chatminigames.utils.WordUtils;
import me.aroze.chatminigames.utils.santa.ChatUtils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static me.aroze.chatminigames.ChatMinigames.instance;

public class Unscramble {

    public static String actualWord = "";
    public static String scrambledWord = "";

    public static void start() {

        actualWord = WordUtils.getRandomWord();
        scrambledWord = WordUtils.shuffleString(actualWord);
        StringBuilder temp = new StringBuilder();

        for (int i=0; i<instance.getConfig().getStringList("messages.game-start.unscramble").size(); i++) {
            temp.append(instance.getConfig().getStringList("messages.game-start.unscramble").get(i))
                    .append(instance.getConfig().getStringList("messages.game-start.unscramble").size() - 1 == i ? "" : "\n");
        }

        String message = ChatUtils.colored(temp.toString().replace("{scrambledWord}", scrambledWord));
        for (Player player : Bukkit.getOnlinePlayers()) player.sendMessage(message);

    }

}
