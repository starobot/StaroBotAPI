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
        ROW_LIST.clear();
        ROW_LIST.add(KEYBOARD_ROW);
        return ReplyKeyboardMarkup.builder()
                .keyboard(ROW_LIST)
                .resizeKeyboard(true)
                .oneTimeKeyboard(true)
                .build();
    }

    @Override
    public InlineKeyboardMarkup getInlineKeyboard() {
        INLINE_BUTTONS.clear();
        INLINE_BUTTONS.add(new ArrayList<>(INLINE_KEYBOARD_BUTTONS));
        return InlineKeyboardMarkup.builder()
                .keyboard(INLINE_BUTTONS)
                .build();
    }

    @Override
    public InlineKeyboardButton createInlineButton(String text, String callbackData) {
        InlineKeyboardButton button = InlineKeyboardButton.builder()
                .text(text)
                .callbackData(callbackData)
                .build();
        INLINE_KEYBOARD_BUTTONS.add(button);
        event.setInlineKeyboard(getInlineKeyboard());
        return button;
    }

    @Override
    public void addReplyButtonToRow(int key, String element) {
        KeyboardButton keyboardButton = new KeyboardButton(element);
        KEYBOARD_BUTTON_MAP.put(key, keyboardButton);
        KEYBOARD_ROW.add(keyboardButton);
        event.setReplyKeyboard(getReplyKeyboard());
    }

    @Override
    public void addReplyButtonWithNewRow(String element) {
        KeyboardRow newRow = new KeyboardRow();
        newRow.add(new KeyboardButton(element));
        ROW_LIST.add(newRow);
        event.setReplyKeyboard(getReplyKeyboard());
    }

    @Override
    public void addInlineButtonToRow(InlineKeyboardButton button) {
        INLINE_KEYBOARD_BUTTONS.add(button);
    }

    @Override
    public void addInlineButtonWithNewRow(InlineKeyboardButton button) {
        List<InlineKeyboardButton> newRow = new ArrayList<>();
        newRow.add(button);
        INLINE_BUTTONS.add(newRow);
        event.setInlineKeyboard(getInlineKeyboard());
    }

    @Override
    public void deleteAllReplyButtons() {
        KEYBOARD_BUTTON_MAP.clear();
        KEYBOARD_ROW.clear();
        event.setReplyKeyboard(null);
    }

    @Override
    public void deleteAllReplyButtonsWithRow(KeyboardRow row) {
        KEYBOARD_BUTTON_MAP.clear();
        ROW_LIST.clear();
        row.clear();
        event.setReplyKeyboard(null);
    }

    @Override
    public void deleteAllInlineButtons() {
        INLINE_KEYBOARD_BUTTONS.clear();
        INLINE_BUTTONS.clear();
        event.setInlineKeyboard(null);
    }

}
