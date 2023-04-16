package me.aroze.chatminigames.minigame.impl

import me.aroze.arozeutils.kotlin.divisiblePairsBetween
import me.aroze.arozeutils.kotlin.type.Randomiser
import me.aroze.chatminigames.ChatMinigames.Companion.config
import me.aroze.chatminigames.minigame.GameType
import me.aroze.chatminigames.minigame.GenericGame
import org.bukkit.configuration.ConfigurationSection

object MathGame : GenericGame(GameType.MATH) {

    val settings: ConfigurationSection = config.getConfigurationSection("extra-game-settings.math.arithmetic")
    private val randomOperation = Randomiser(Operation.values().toList())
    private var randomFactor = Randomiser(divisiblePairsBetween(
        settings.getInt("division.min-value"), settings.getInt("division.max-value")
    ))

    private val randomNumber = let {
        val map = hashMapOf<Operation, Randomiser>()
        for (operation in arrayOf(Operation.ADDITION, Operation.SUBTRACTION, Operation.MULTIPLICATION)) {
            val min = settings.getInt("${operation.getType()}.min-value")
            val max = settings.getInt("${operation.getType()}.max-value")
            map[operation] = Randomiser((min..max).toList())
        }
        map
    }

    override fun create() {

        val operation = randomOperation.next() as Operation
        values["operation"] = operation.getSymbol()

        var num1 = if (operation != Operation.DIVISION) randomNumber[operation]!!.next() as Int else 0
        var num2 = if (operation != Operation.DIVISION) randomNumber[operation]!!.next() as Int else 0
        val answer: Int

        when (operation) {
            Operation.DIVISION -> {
                val factor = randomFactor.next() as Pair<Int, Int>
                num1 = factor.first
                num2 = factor.second
                answer = num1 / num2
            }
            Operation.SUBTRACTION -> {
                if (!settings.getBoolean("subtraction.allow-negative-answers") && num1 < num2) {
                    val temp = num1
                    num1 = num2
                    num2 = temp
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

    private enum class Operation {
        ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION;
        fun getType() : String = this.name.lowercase()
        fun getSymbol(): String = settings.getString("${this.getType()}.symbol")
    }

} // hehe line 69