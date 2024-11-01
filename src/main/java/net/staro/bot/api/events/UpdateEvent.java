package net.staro.bot.api.events;

import lombok.Data;
import net.staro.bot.api.Bot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import javax.annotation.Nullable;

@SuppressWarnings("NullableProblems")
@Data
public class UpdateEvent
{
    public static final UpdateEvent INSTANCE = new UpdateEvent();

    /**
     * Is the bot instance.
     */
    private Bot bot;
    /**
     * Is the update object taken from onUpdateReceived.
     */
    private Update update;
    /**
     * Holds a response, which is being passed to onUpdateReceived.
     */
    @Nullable
    private String response;
    @Nullable
    private ReplyKeyboardMarkup replyKeyboard;
    @Nullable
    private InlineKeyboardMarkup inlineKeyboard;
    /**
     * Is true whenever the command is deletable.
     */
    private boolean deletable;
    /**
     * Is true whenever the command has permissions.
     */
    private boolean hasPermissions;

}
