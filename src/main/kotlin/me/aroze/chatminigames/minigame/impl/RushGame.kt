package me.aroze.chatminigames.minigame.impl

import me.aroze.chatminigames.ChatMinigames.Companion.randomisedWords
import me.aroze.chatminigames.minigame.GameType
import me.aroze.chatminigames.minigame.GenericGame

object RushGame : GenericGame() {

    fun create() : GenericGame {
        values["rushWord"] = randomisedWords.next().toString()
        return this.start()
    }

}