package me.aroze.chatminigames.minigame

import me.aroze.arozeutils.minecraft.generic.coloured
import org.bukkit.Bukkit

class GenericGame(
    val answer : String,
    val question : String,
) {

    fun start() {
        Bukkit.broadcastMessage(question.coloured())
    }

}