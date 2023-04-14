package me.aroze.chatminigames.minigame

import me.aroze.chatminigames.ChatMinigames.Companion.config

open class GenericGame {

    lateinit var type: GameType
    val values: HashMap<String, String> = hashMapOf()

    fun start() : GenericGame {

        val broadcast = config.getString("todo")
        for (value in values) {
            broadcast.replace("{${value.key}}", value.value)
        }

        return this
    }

}

enum class GameType(val type: String) {
    UNSCRAMBLE("unscramble"),
    RUSH("rush"),
    MATH("math")
}