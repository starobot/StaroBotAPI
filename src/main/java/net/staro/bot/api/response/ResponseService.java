package net.staro.bot.api.response;

import net.staro.bot.api.bus.impl.Listener;
import net.staro.bot.api.bus.impl.SubscriberImpl;
import net.staro.bot.api.events.UpdateEvent;
import net.staro.bot.api.keyboard.Keyboard;
import net.staro.bot.api.keyboard.KeyboardMapsAndRows;

import java.util.function.Consumer;

import static net.staro.bot.api.command.CommandMap.COMMAND_STATE_MAP;

/**
 * Handles the text sending logic independently of the Telegram API.
 * This class cannot be overriden, however one might unsubscribe it from {@link net.staro.bot.api.bus.EventBus} if the logic is to be corrected/rewritten.
 */
public class ResponseService extends SubscriberImpl {
    private final Consumer<Keyboard> clearButtons = keyboard -> {
        keyboard.deleteAllReplyButtons();
        keyboard.deleteAllInlineButtons();
        keyboard.deleteAllReplyButtonsWithRow(KeyboardMapsAndRows.KEYBOARD_ROW);
    };

    public ResponseService() {
        listen(new Listener<UpdateEvent>() {
            @Override
            public void onEvent(UpdateEvent event) {
                var update = event.getUpdate();
                var bot = event.getBot();
                clearButtons.accept(bot.keyboard());
                String response;
                if (update.hasMessage()) {
                    var message = update.getMessage();
                    if (message.getText().startsWith(bot.commandManager().getPrefix())) {
                        String[] parts = message.getText().split(" ", 2);
                        String command = parts[0];
                        response = bot.commandManager().execute(bot, command, message);
                    } else if (COMMAND_STATE_MAP.get(message.getFrom().getId()) != null) {
                        response = bot.commandManager().handleArgument(bot, message);
                    } else {
                        response = bot.responseManager().getResponseForMessage(bot, message);
                    }

                    event.setResponse(response);
                } else if (update.hasCallbackQuery()) {
                    response = bot.commandManager().executeInlineCommand(bot, update.getCallbackQuery());
                    event.setResponse(response);
                }
            }
        });
    }

}
