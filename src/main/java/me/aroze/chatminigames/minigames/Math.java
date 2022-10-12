package me.aroze.chatminigames.minigames;

import me.aroze.chatminigames.utils.santa.ChatUtils;
import org.bukkit.Bukkit;

import static me.aroze.chatminigames.ChatMinigames.instance;

public class Math {

    public static int mathNum1, mathNum2;
    public static char mathOperation;
    public static String mathAnswer = "";

    public static void start() {

        StringBuilder messageToBroadcast = new StringBuilder();

        int randomOperation = (int) ((java.lang.Math.random()* 3));
        switch (randomOperation) {
            case 0: mathOperation = '+'; break;
            case 1: mathOperation = 'x'; break;
            case 2: mathOperation = '-'; break;
        }

        mathNum1 = (int) ((java.lang.Math.random()* 70) + 5);
        mathNum2 = (int) ((java.lang.Math.random()* 70) + 5);
        if (mathNum1 < mathNum2) {
            mathNum1 = mathNum2;
            mathNum2 = (int) ((java.lang.Math.random() * mathNum1) + 1);
        }

        switch (mathOperation) {
            case 'x':
                mathNum1 = (int) ((java.lang.Math.random()* 11) + 2);
                mathNum2 = (int) ((java.lang.Math.random()* 11) + 2);
                mathAnswer = String.valueOf(mathNum1 * mathNum2);
                break;
            case '+':
                mathAnswer = String.valueOf(mathNum1 + mathNum2);
                break;
            case '-':
                mathAnswer = String.valueOf(mathNum1 - mathNum2);
                break;
        }

        for (int i=0; i<instance.getConfig().getStringList("messages.game-start.math").size(); i++) {
            messageToBroadcast.append(instance.getConfig().getStringList("messages.game-start.math").get(i))
                    .append(instance.getConfig().getStringList("messages.game-start.math").size() - 1 == i ? "" : "\n");
        }

        Bukkit.broadcastMessage(ChatUtils.colored(messageToBroadcast.toString()
                .replace("{mathNum1}", mathNum1 + "")
                .replace("{mathNum2}", mathNum2 + "")
                .replace("{mathOperation}", mathOperation + "")));
    }

}
