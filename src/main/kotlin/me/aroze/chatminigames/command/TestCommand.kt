package me.aroze.chatminigames.command

import me.aroze.arozeutils.minecraft.command.CommandInfo
import me.aroze.arozeutils.minecraft.command.FancyCommand
import me.aroze.chatminigames.ChatMinigames.Companion.randomisedWords
import me.aroze.chatminigames.minigame.GenericGame
import org.bukkit.command.CommandSender

@CommandInfo(
    description = "testy test",
    permission = "chatminigames.craft",
    permissionMessage = "No testy for you!",
)
object TestCommand : FancyCommand("testgame") {
    override fun onCommand(sender: CommandSender, label: String, args: Array<out String>) {
        val game = GenericGame("testy", randomisedWords.next().toString())
        game.start()
    }
}