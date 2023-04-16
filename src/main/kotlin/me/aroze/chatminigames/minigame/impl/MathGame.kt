package me.aroze.chatminigames.minigame.impl

import me.aroze.arozeutils.kotlin.divisiblePairsBetween
import me.aroze.arozeutils.kotlin.type.Randomiser
import me.aroze.chatminigames.ChatMinigames.Companion.config
import me.aroze.chatminigames.minigame.GameType
import me.aroze.chatminigames.minigame.GenericGame

object MathGame : GenericGame(GameType.MATH) {

    val settings = config.getConfigurationSection("extra-game-settings.math.arithmetic")
    val randomOperation = Randomiser(Operation.values().toList())
    var randomFactor = Randomiser(divisiblePairsBetween(
        settings.getInt("division.min-value"),
        settings.getInt("division.max-value")
    ))

    override fun create() {

        val operation = randomOperation.next() as Operation
        values["operation"] = operation.symbol

        val max = settings.getInt("${operation.getType()}.max-value")
        val min = settings.getInt("${operation.getType()}.min-value")

        var num1 = (min..max).random()
        var num2 = (min..max).random()
        val answer: Int

        when (operation) {
            Operation.DIVISION -> {
                val factor = randomFactor.next() as Pair<Int, Int>
                num1 = factor.first
                num2 = factor.second
                answer = num1 / num2
            }
            Operation.SUBTRACTION -> {
                if (!settings.getBoolean("subtraction.allow-negative-answers")) {
                    num1 = maxOf(num1, num2)
                    num2 = minOf(num1, num2)
                }
                answer = num1 - num2
            }
            Operation.MULTIPLICATION -> answer = num1 * num2
            Operation.ADDITION -> answer = num1 + num2
            else -> answer = 0 // This shouldn't happen
        }

        values["num1"] = num1.toString()
        values["num2"] = num2.toString()
        values["answer"] = answer.toString()

    }

    private enum class Operation(val symbol: String) {
        ADDITION("+"),
        SUBTRACTION("-"),
        MULTIPLICATION("*"),
        DIVISION("÷"),
        ;

        fun getType() : String = this.name.lowercase()
    }

}