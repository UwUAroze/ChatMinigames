package me.aroze.chatminigames

import me.aroze.arozeutils.kotlin.type.Randomiser
import me.aroze.arozeutils.minecraft.FancyPlugin
import me.aroze.chatminigames.minigame.GameHandler
import org.bukkit.Bukkit

class ChatMinigames : FancyPlugin() {
    override fun onEnable() {
        saveDefaultConfig()

        val wordList = config.getString("wordList")
            .replace(" ", "")
            .split(",")

        randomisedWords = Randomiser(wordList)

        Bukkit.getPluginManager().registerEvents(GameHandler, this)
    }

    companion object {
        lateinit var randomisedWords: Randomiser

        /**
         * @return [ChatMinigames] instance
         */
        fun get(): ChatMinigames {
            return getPlugin(ChatMinigames::class.java)
        }
    }
}