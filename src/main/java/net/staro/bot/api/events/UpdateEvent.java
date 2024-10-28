package net.staro.bot.api.events;

import lombok.Data;
import net.staro.bot.api.Bot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import javax.annotation.Nullable;

@Data
public class UpdateEvent {
    public static final UpdateEvent INSTANCE = new UpdateEvent();

    private Bot bot;
    private Update update;
    private String response;
    @Nullable
    private ReplyKeyboardMarkup replyKeyboard;
    @Nullable
    private InlineKeyboardMarkup inlineKeyboard;
    private boolean deletable;

}
