package me.aroze.chatminigames.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static me.aroze.chatminigames.ChatMinigames.color;
import static me.aroze.chatminigames.ChatMinigames.startGame;
import static me.aroze.chatminigames.ChatMinigames.instance;

public class ChatMinigamesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("chatminigames.forcestart")) {
            sender.sendMessage(color(instance.getConfig().getString("messages.other.no-permission")));
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(color(instance.getConfig().getString("messages.other.info")));
            return true;
        }

        if (args.length == 1 && (args[0].equals("reload") || args[0].equals("rl"))) {
            // reload logic
            return true;
        }

        if (args[0].equals("forcestart") || args[0].equals("fs") || args[0].equals("start")) {

            if (args.length == 1) {
                sender.sendMessage(color(instance.getConfig().getString("messages.other.no-game-specified")));
                return true;
            }

            if (args.length > 2) {
                sender.sendMessage(color(instance.getConfig().getString("messages.other.too-many-args")));
                return true;
            }

            String selectedGame = args[1].toLowerCase();
            switch (selectedGame) {
                case "random":
                    startGame(0);
                    return true;
                case "unscramble":
                    startGame(1);
                    return true;
                case "rush":
                    startGame(2);
                    return true;
                case "math":
                    startGame(3);
                    return true;
                default:
                    sender.sendMessage(color(instance.getConfig().getString("messages.other.invalid-game")));
                    return true;
            }
        }

        return true;
    }
}
