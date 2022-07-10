package me.aroze.chatminigames.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static me.aroze.chatminigames.ChatMinigames.startGame;

public class StartChatGame implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            //
            return true;
        }

        if (args.length > 1) {
            //
            return true;
        }

        String selectedGame = args[0].toLowerCase();

        switch (selectedGame) {
            case "random":
                startGame(0);
                break;
            case "scramble":
                startGame(1);
                break;
            case "rush":
                startGame(2);
                break;
            case "math":
                startGame(3);
                break;
            default:
                // invalid game message
                return true;
        }

        return true;
    }
}
