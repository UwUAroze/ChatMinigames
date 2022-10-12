package me.aroze.chatminigames.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static me.aroze.chatminigames.ChatMinigames.instance;
import static me.aroze.chatminigames.minigames.MinigameManager.startGame;
import static me.aroze.chatminigames.utils.santa.ChatUtils.colored;

public class ChatMinigamesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("chatminigames.forcestart")) {
            sender.sendMessage(colored(instance.getConfig().getString("messages.other.no-permission")));
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(colored(instance.getConfig().getString("messages.other.info")));
            return true;
        }

        if (args.length == 1 && (args[0].equals("reload") || args[0].equals("rl"))) {
            instance.reloadConfig();
            sender.sendMessage(colored(instance.getConfig().getString("messages.other.reload")));
            return true;
        }

        if (args[0].equals("forcestart") || args[0].equals("fs") || args[0].equals("start")) {

            if (args.length == 1) {
                sender.sendMessage(colored(instance.getConfig().getString("messages.other.no-game-specified")));
                return true;
            }

            if (args.length > 2) {
                sender.sendMessage(colored(instance.getConfig().getString("messages.other.too-many-args")));
                return true;
            }

            String selectedGame = args[1].toLowerCase();
            switch (selectedGame) {
                case "random": startGame(0); return true;
                case "math": startGame(1); return true;
                case "rush": startGame(2); return true;
                case "unscramble": startGame(3); return true;
                case "reactiontime": startGame(4); return true;
                default:
                    sender.sendMessage(colored(instance.getConfig().getString("messages.other.invalid-game")));
                    return true;
            }
        }

        return true;
    }
}
