package me.aroze.chatminigames.minigame

import me.aroze.arozeutils.kotlin.extension.replacePlaceholders
import me.aroze.arozeutils.minecraft.generic.coloured
import me.aroze.chatminigames.command.TestCommand.send
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import java.util.EventListener

object GameHandler: Listener {

    var runningGame: GenericGame? = null

    @EventHandler
    fun onChat(event: AsyncPlayerChatEvent) {
        runningGame?.let {
            // TODO: if dont allow cancelled messages and message is cancelled, return
            if (event.message == runningGame!!.values["answer"]) {
                // TODO: allow case insensitive answer if config says so
                // TODO: event.isCancelled = config.whatever

                it.values["player"] = event.player.name

                Bukkit.broadcastMessage(
                    it.type.getMessage("answeredBroadcast")
                         .replacePlaceholders(it.values)
                )

                event.player.sendMessage(
                    it.type.getMessage("answeredPrivate")
                        .replacePlaceholders(it.values)
                )

                // TODO: run reward command

                runningGame = null
            }
        }
    }

}