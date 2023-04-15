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

enum class GameType(private val configName: String? = null) {
    UNSCRAMBLE, RUSH, MATH,
    REACTIONTIME("reactionTime"),
    ;

    private fun getConfigName() = configName ?: name.lowercase()

    fun getMessage(type: String) : String {
        return when(type) {
            "startBroadcast" -> config.getStringList("minigame-messages.game-start.${getConfigName()}").joinToString("\n")
            "answeredBroadcast" -> config.getStringList("minigame-messages.answered-correctly-broadcast.${getConfigName()}").joinToString("\n")
            "answeredPrivate" -> config.getStringList("minigame-messages.answered-correctly-private.${getConfigName()}").joinToString("\n")
            else -> "this shouldnt happen"
        }
    }
}