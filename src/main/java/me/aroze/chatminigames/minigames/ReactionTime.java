package me.aroze.chatminigames.minigames;

import me.aroze.chatminigames.utils.WordUtils;
import me.aroze.chatminigames.utils.santa.ChatUtils;
import me.aroze.chatminigames.utils.santa.Message;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static me.aroze.chatminigames.ChatMinigames.instance;

public class ReactionTime {

    public static String commandString = "";

    public static void start() {

        commandString = WordUtils.generateRandomString();
        StringBuilder temp = new StringBuilder();

        for (int i=0; i<instance.getConfig().getStringList("messages.game-start.reactionTime").size(); i++) {
            temp.append(instance.getConfig().getStringList("messages.game-start.reactionTime").get(i))
                    .append(instance.getConfig().getStringList("messages.game-start.reactionTime").size() - 1 == i ? "" : "\n");
        }

        Message message = new Message(ChatUtils.colored(temp.toString()))
                .hover(instance.getConfig().getString("messages.starting-hover.reactionTime"))
                .command("/clickcommand " + commandString);

        for (Player player : Bukkit.getOnlinePlayers()) message.send(player);

    }

}
