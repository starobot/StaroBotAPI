package net.staro.bot.api.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface KeyboardMapsAndRows {
    /**
     * A row for keyboardRow for static usage.
     */
    KeyboardRow KEYBOARD_ROW = new KeyboardRow();
    /**
     * A row for inlineKeyboardButtons for static usage.
     */
    List<InlineKeyboardButton> INLINE_KEYBOARD_BUTTONS = new ArrayList<>();
    /**
     * A list of {@link KeyboardRow} objects for the static usage.
     */
    List<KeyboardRow> ROW_LIST = new ArrayList<>();
    /**
     * A list telegram inline keyboards for the static usage.
     */
    List<List<InlineKeyboardButton>> INLINE_BUTTONS = new ArrayList<>();
    /**
     * A map of keyboard buttons for the static usage.
     */
    Map<Integer, KeyboardButton> KEYBOARD_BUTTON_MAP = new HashMap<>();

}
