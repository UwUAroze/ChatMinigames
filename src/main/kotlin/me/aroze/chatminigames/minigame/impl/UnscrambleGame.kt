package me.aroze.chatminigames.minigame.impl

import me.aroze.chatminigames.ChatMinigames.Companion.randomisedWords
import me.aroze.chatminigames.minigame.GameType
import me.aroze.chatminigames.minigame.GenericGame

object UnscrambleGame : GenericGame(GameType.UNSCRAMBLE) {

        override fun create() {
            values["answer"] = randomisedWords.next().toString()
            values["scrambled"] = values["answer"]!!.toCharArray().let {
                it.shuffle()
                it.joinToString("")
            }
        }

}