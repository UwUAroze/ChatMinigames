package me.aroze.chatminigames.utils.santa

import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.chat.hover.content.Text
import org.bukkit.entity.Player

class Message(text: String) {

    companion object {
        @JvmStatic
        val EMPTY = Message("")
    }

    private val component = text.colored().toComponent()

    fun url(uri: String): Message {
        component.clickEvent = ClickEvent(ClickEvent.Action.OPEN_URL, uri)
        return this
    }

    fun suggest(command: String): Message {
        component.clickEvent = ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, command)
        return this
    }

    fun command(command: String): Message {
        component.clickEvent = ClickEvent(ClickEvent.Action.RUN_COMMAND, command)
        return this
    }

    fun hover(text: String): Message {
        component.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, Text(TextComponent.fromLegacyText(text.colored())))
        return this
    }

    fun add(message: Message): Message {
        component.addExtra(message.component)
        return this
    }

    fun send(player: Player): Message {
        player.spigot().sendMessage(component)
        return this
    }

    fun component(): BaseComponent {
        return component
    }

}