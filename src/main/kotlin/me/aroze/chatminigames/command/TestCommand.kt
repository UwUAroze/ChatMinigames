package me.aroze.chatminigames.command

import me.aroze.arozeutils.minecraft.command.CommandInfo
import me.aroze.arozeutils.minecraft.command.FancyCommand
import me.aroze.chatminigames.ChatMinigames.Companion.randomisedWords
import me.aroze.chatminigames.minigame.GenericGame
import me.aroze.chatminigames.minigame.impl.MathGame
import me.aroze.chatminigames.minigame.impl.RushGame
import me.aroze.chatminigames.minigame.impl.UnscrambleGame
import org.bukkit.command.CommandSender

@CommandInfo(
    description = "testy test",
    permission = "chatminigames.test",
    permissionMessage = "No testy for you!",
)
object TestCommand : FancyCommand("testgame") {
    override fun onCommand(sender: CommandSender, label: String, args: Array<out String>) {
        MathGame.start()
    }
}