package me.aroze.chatminigames.minigame.impl

import me.aroze.chatminigames.ChatMinigames.Companion.randomisedWords
import me.aroze.chatminigames.minigame.GameType
import me.aroze.chatminigames.minigame.GenericGame

object RushGame : GenericGame(GameType.RUSH) {

    override fun create() {
        values["answer"] = randomisedWords.next().toString()
    }

}