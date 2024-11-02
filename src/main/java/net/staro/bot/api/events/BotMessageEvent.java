package net.staro.bot.api.events;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.staro.bot.api.Bot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

// This is my attempt to use execute somewhere without the need to rewrite the whole api sending message logic.

/**
 * An event for sending messages without the need to edit {@link net.staro.bot.api.telegram.AbstractTelegramBot}.
 * This event is never posted inside the api.
 */
@Data
@RequiredArgsConstructor
public class BotMessageEvent
{
    private final Bot bot;
    private SendMessage.SendMessageBuilder sendMessageBuilder;
    private DeleteMessage deleteMessage;
    private EditMessageText editMessageText;

}
