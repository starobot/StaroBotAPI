package net.staro.bot.api.telegram;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import net.staro.bot.api.Bot;
import net.staro.bot.api.bus.Listener;
import net.staro.bot.api.events.BotMessageEvent;
import net.staro.bot.api.events.UpdateEvent;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

/**
 * An extension of the Telegram API {@link TelegramLongPollingBot}.
 * Here we define our bot username and token.
 */
@AllArgsConstructor
public abstract class AbstractTelegramBot extends TelegramLongPollingBot
{
    protected final Bot bot;

    @Override
    public void onUpdateReceived(Update update)
    {
        UpdateEvent event = UpdateEvent.INSTANCE;
        event.setBot(bot);
        event.setUpdate(update);
        bot.eventBus().post(event);
        Long who;
        if (update.hasMessage() && update.getMessage().getFrom() != null)
        {
            who = update.getMessage().getFrom().getId();
        } else if (update.hasCallbackQuery() && update.getCallbackQuery().getFrom() != null)
        {
            who = update.getCallbackQuery().getFrom().getId();
        } else
        {
            return;
        }

        String response = event.getResponse();
        if (Messenger.LAST_MESSAGE_ID_MAP.containsKey(who) && event.isDeletable())
        {
            deleteMessage(who, Messenger.LAST_MESSAGE_ID_MAP.get(who));
        }

        Messenger.LAST_MESSAGE_ID_MAP.put(who, sendText(who, response, event.getReplyKeyboard(), event.getInlineKeyboard()));
    }

    @SneakyThrows
    public Integer sendText(Long who, String what, ReplyKeyboardMarkup replyKeyboard, InlineKeyboardMarkup inlineKeyboard)
    {
        SendMessage.SendMessageBuilder messageBuilder = SendMessage.builder().chatId(who.toString()).text(what);
        if (inlineKeyboard != null)
        {
            messageBuilder.replyMarkup(inlineKeyboard);
        }

        if (replyKeyboard != null)
        {
            messageBuilder.replyMarkup(replyKeyboard);
        }

        Message sentMessage = execute(messageBuilder.build());
        return sentMessage.getMessageId();
    }

    @SneakyThrows
    public void deleteMessage(Long chatId, Integer messageId)
    {
        DeleteMessage deleteMessage = new DeleteMessage(chatId.toString(), messageId);
        execute(deleteMessage);
    }

    /**
     * Allows to use the execute method anywhere using BotMessageEvent event.
     * @param event is the custom event that can be posted whenever needed using event bus.
     */
    @SuppressWarnings("unused")
    @SneakyThrows
    @Listener
    private void onMethodReceived(BotMessageEvent event)
    {
        if (event.getSendMessageBuilder() != null)
        {
            execute(event.getEditMessageText());
            event.setSendMessageBuilder(null);
        }

        if (event.getEditMessageText() != null)
        {
            execute(event.getEditMessageText());
            event.setEditMessageText(null);
        }

        if (event.getDeleteMessage() != null)
        {
            execute(event.getDeleteMessage());
            event.setDeleteMessage(null);
        }
    }

}
