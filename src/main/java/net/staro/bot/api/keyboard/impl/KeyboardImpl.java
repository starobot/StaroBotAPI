package net.staro.bot.api.keyboard.impl;

import lombok.RequiredArgsConstructor;
import net.staro.bot.api.events.UpdateEvent;
import net.staro.bot.api.keyboard.Keyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

/**
 * A custom keyboard builder. To be implemented into the onUpdate through {@link net.staro.bot.api.Bot}.
 */
@RequiredArgsConstructor
public class KeyboardImpl implements Keyboard {
    private final UpdateEvent event;

    @Override
    public ReplyKeyboardMarkup getReplyKeyboard() {
        ROW_LIST.add(KEYBOARD_ROW);
        return ReplyKeyboardMarkup.builder()
                .keyboard(ROW_LIST)
                .resizeKeyboard(true)
                .oneTimeKeyboard(true)
                .build();
    }

    @Override
    public InlineKeyboardMarkup getInlineKeyboard() {
        INLINE_BUTTONS.add(INLINE_KEYBOARD_BUTTONS);
        return InlineKeyboardMarkup.builder()
                .keyboard(INLINE_BUTTONS)
                .build();
    }

    @Override
    public InlineKeyboardButton createInlineButton(String text, String callbackData) {
        event.setInlineKeyboard(getInlineKeyboard());
        InlineKeyboardButton button = InlineKeyboardButton.builder()
                .text(text)
                .callbackData(callbackData)
                .build();
        INLINE_KEYBOARD_BUTTONS.add(button);
        return button;
    }

    @Override
    public void addReplyButtonToRow(int key, String element) {
        event.setReplyKeyboard(getReplyKeyboard());
        KeyboardButton keyboardButton = new KeyboardButton(element);
        KEYBOARD_BUTTON_MAP.put(key, keyboardButton);
        KEYBOARD_ROW.add(keyboardButton);
    }

    @Override
    public void addReplyButtonWithNewRow(String element) {
        event.setReplyKeyboard(getReplyKeyboard());
        KeyboardRow newRow = new KeyboardRow();
        newRow.add(new KeyboardButton(element));
        ROW_LIST.add(newRow);
    }

    @Override
    public void addInlineButtonToRow(InlineKeyboardButton button) {
        INLINE_KEYBOARD_BUTTONS.add(button);
    }

    @Override
    public void addInlineButtonWithNewRow(InlineKeyboardButton button) {
        event.setInlineKeyboard(getInlineKeyboard());
        List<InlineKeyboardButton> newRow = new ArrayList<>();
        newRow.add(button);
        INLINE_BUTTONS.add(newRow);
    }

    @Override
    public void deleteAllReplyButtons() {
        event.setReplyKeyboard(null);
        KEYBOARD_BUTTON_MAP.clear();
        KEYBOARD_ROW.clear();
    }

    @Override
    public void deleteAllReplyButtonsWithRow(KeyboardRow row) {
        event.setReplyKeyboard(null);
        KEYBOARD_BUTTON_MAP.clear();
        ROW_LIST.clear();
        row.clear();
    }

    @Override
    public void deleteAllInlineButtons() {
        event.setInlineKeyboard(null);
        INLINE_KEYBOARD_BUTTONS.clear();
        INLINE_BUTTONS.clear();
    }

}
