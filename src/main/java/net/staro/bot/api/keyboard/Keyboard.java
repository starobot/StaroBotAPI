package net.staro.bot.api.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

/**
 * A keyboard handler to help us use keyboard and buttons for the response usage.
 */
@SuppressWarnings("unused")
public interface Keyboard extends KeyboardMapsAndRows
{
    ReplyKeyboardMarkup getReplyKeyboard();

    InlineKeyboardMarkup getInlineKeyboard();

    @SuppressWarnings("UnusedReturnValue")
    InlineKeyboardButton createInlineButton(String text, String callbackData);

    void addReplyButtonToRow(int key, String element);

    void addReplyButtonWithNewRow(String element);

    void addInlineButtonToRow(InlineKeyboardButton button);

    void addInlineButtonWithNewRow(InlineKeyboardButton button);

    void deleteAllReplyButtons();

    void deleteAllReplyButtonsWithRow(KeyboardRow row);

    void deleteAllInlineButtons();

}
