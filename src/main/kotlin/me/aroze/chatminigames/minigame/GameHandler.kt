package me.aroze.chatminigames.minigame

import me.aroze.arozeutils.kotlin.extension.replacePlaceholders
import me.aroze.arozeutils.kotlin.makeTimestamp
import me.aroze.arozeutils.minecraft.generic.coloured
import me.aroze.arozeutils.minecraft.generic.sync
import me.aroze.chatminigames.ChatMinigames.Companion.config
import me.aroze.chatminigames.command.TestCommand.send
import org.bukkit.Bukkit
import org.bukkit.Sound
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

            sync { _ ->
                for (command in config.getStringList("rewards.${runningGame!!.type.getConfigName()}")) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replacePlaceholders(it.values))
                }

                val sounds = config.getConfigurationSection("effects.sound")
                if (sounds.getBoolean("enabled")) {
                    Bukkit.getPlayer(it.values["player"]).playSound(
                        Bukkit.getPlayer(it.values["player"]).location,
                        Sound.valueOf(sounds.getString("sound").replace(".", "_").uppercase()),
                        sounds.get("volume").toString().toFloat(),
                        sounds.get("pitch").toString().toFloat()
                    )
                }

                runningGame = null
            }
        }
    }

}