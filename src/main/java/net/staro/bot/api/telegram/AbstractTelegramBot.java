package net.staro.bot.api.telegram;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import net.staro.bot.api.Bot;
import net.staro.bot.api.events.UpdateEvent;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.HashMap;
import java.util.Map;

/**
 * An extension of the Telegram API {@link TelegramLongPollingBot}.
 * Here we define our bot username and token.
 */
@AllArgsConstructor
public abstract class AbstractTelegramBot extends TelegramLongPollingBot
{
    protected final Bot bot;
    private final Map<Long, Integer> lastMessageIdMap = new HashMap<>();

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
        if (lastMessageIdMap.containsKey(who) && event.isDeletable())
        {
            deleteMessage(who, lastMessageIdMap.get(who));
        }

        lastMessageIdMap.put(who, sendText(who, response, event.getReplyKeyboard(), event.getInlineKeyboard()));
    }

    @SneakyThrows
    private Integer sendText(Long who, String what, ReplyKeyboardMarkup replyKeyboard, InlineKeyboardMarkup inlineKeyboard)
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
    private void deleteMessage(Long chatId, Integer messageId)
    {
        DeleteMessage deleteMessage = new DeleteMessage(chatId.toString(), messageId);
        execute(deleteMessage);
    }

}
