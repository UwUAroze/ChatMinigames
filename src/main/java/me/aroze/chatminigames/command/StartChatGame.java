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

        if (!(selectedGame.equals("random")) && !(selectedGame.equals("scramble")) && !(selectedGame.equals("rush")) && !(selectedGame.equals("math"))) {
            //
            return true;
        }

        startGame(0);
        return true;
    }
}
