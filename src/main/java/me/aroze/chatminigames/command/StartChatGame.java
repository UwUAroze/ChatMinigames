package me.aroze.chatminigames.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

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

        int game = (int) ((Math.random()* 3) + 1);
        sender.sendMessage(game + "");

        if (game == 1) {
            // Scramble


            return true;
        }

        if (game == 2) {
            // Rush


            return true;
        }

        if (game == 3) {
            // Math


            return true;
        }

        return true;
    }
}
