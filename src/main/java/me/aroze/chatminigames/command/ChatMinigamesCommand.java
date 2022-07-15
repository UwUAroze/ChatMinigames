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
            sender.sendMessage(color(instance.getConfig().getString("messages.other.no-game-specified")));
            return true;
        }

        if (args.length > 1) {
            sender.sendMessage(color(instance.getConfig().getString("messages.other.too-many-args")));
            return true;
        }

        String selectedGame = args[0].toLowerCase();

        switch (selectedGame) {
            case "random":
                startGame(0);
                break;
            case "unscramble":
                startGame(1);
                break;
            case "rush":
                startGame(2);
                break;
            case "math":
                startGame(3);
                break;
            default:
                sender.sendMessage(color(instance.getConfig().getString("messages.other.invalid-game")));
                return true;
        }

        return true;
    }
}
