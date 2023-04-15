package me.aroze.chatminigames.minigame

import me.aroze.arozeutils.kotlin.extension.replacePlaceholders
import me.aroze.arozeutils.kotlin.makeTimestamp
import me.aroze.arozeutils.minecraft.generic.coloured
import me.aroze.chatminigames.ChatMinigames.Companion.config
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
        val endTime = System.currentTimeMillis()
        runningGame?.let {

            val settings = config.getConfigurationSection("misc-settings")
            if (settings.getBoolean("ignore-cancelled-messages") && event.isCancelled) return

            if (settings.getBoolean("case-sensitive") && event.message != runningGame!!.values["answer"]) return
            else if (!event.message.equals(runningGame!!.values["answer"], true)) return

            event.isCancelled = settings.getBoolean("cancel-winning-answers")

            it.values["player"] = event.player.name
            it.values["elapsedTime"] = (endTime - it.startTime).makeTimestamp()

            Bukkit.broadcastMessage(
                it.type.getMessage("answeredBroadcast")
                    .replacePlaceholders(it.values)
                    .coloured()
            )

            event.player.sendMessage(
                it.type.getMessage("answeredPrivate")
                    .replacePlaceholders(it.values)
                    .coloured()
            )

            // TODO: run reward command

            runningGame = null
        }
    }

}