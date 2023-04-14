package me.aroze.chatminigames

import me.aroze.arozeutils.kotlin.type.Randomiser
import me.aroze.arozeutils.minecraft.FancyPlugin
import me.aroze.arozeutils.minecraft.generic.coloured
import me.aroze.chatminigames.command.TestCommand
import org.bukkit.Bukkit

class ChatMinigames : FancyPlugin() {

    companion object {
        lateinit var instance: ChatMinigames
        lateinit var randomisedWords: Randomiser
    }

    override fun onEnable() {

        saveDefaultConfig()

        val instance = this
        val wordList = config.getString("wordList")
            .replace(" ", "")
            .split(",")

        randomisedWords = Randomiser(wordList)

        Bukkit.getLogger().info("fnjhuierfi")
        Bukkit.broadcastMessage("&#ab23cdAAAAA".coloured())

        TestCommand
    }

}