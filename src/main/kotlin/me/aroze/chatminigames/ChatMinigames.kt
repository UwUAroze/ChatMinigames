package me.aroze.chatminigames

import me.aroze.arozeutils.minecraft.FancyPlugin
import me.aroze.arozeutils.minecraft.generic.coloured
import me.aroze.chatminigames.command.TestCommand
import org.bukkit.Bukkit

class ChatMinigames : FancyPlugin() {

    companion object {
        fun getInstance() : ChatMinigames { return getPlugin(ChatMinigames::class.java) }
    }

    override fun onEnable() {
        Bukkit.getLogger().info("fnjhuierfi")
        Bukkit.broadcastMessage("&#ab23cdAAAAA".coloured())

        TestCommand
    }

}