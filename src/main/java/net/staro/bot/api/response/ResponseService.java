package net.staro.bot.api.response;

import net.staro.bot.api.Bot;
import net.staro.bot.api.bus.Listener;
import net.staro.bot.api.bus.Priority;
import net.staro.bot.api.events.UpdateEvent;
import net.staro.bot.api.keyboard.Keyboard;
import net.staro.bot.api.keyboard.KeyboardMapsAndRows;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import static net.staro.bot.api.command.CommandMap.COMMAND_STATE_MAP;

/**
 * Handles the text sending logic independently of the Telegram API.
 * This class cannot be overriden, however one might unsubscribe it from {@link net.staro.bot.api.bus.EventBus} if the logic is to be corrected/rewritten.
 */
public class ResponseService
{
    // This method is in fact being used via reflection, but the IDE doesn't know about it.
    @SuppressWarnings("unused")
    @Listener(priority = Priority.DEFAULT)
    private void onUpdateReceived(UpdateEvent event)
    {
        // A useful feature for overriding the updateReceived logic without rewriting the existing managers.
        // For example, when setting up the permissions, one might make a listener with a high priority and cancel the UpdateEvent
        //  to disable the upcoming responses from this instance.
        if (event.isCancelled())
        {
            return;
        }

        Bot bot = event.getBot();
        clearButtons(bot.keyboard());
        Update update = event.getUpdate();
        String response;
        if (update.hasMessage())
        {
            Message message = update.getMessage();
            if (message.getText().startsWith(bot.commandManager().getPrefix()))
            {
                String[] parts = message.getText().split(" ", 2);
                String command = parts[0];
                response = bot.commandManager().execute(bot, command, message);
            } else if (COMMAND_STATE_MAP.get(message.getFrom().getId()) != null)
            {
                response = bot.commandManager().handleArgument(bot, message);
            } else
            {
                response = bot.responseManager().getResponseForMessage(bot, message);
            }

            event.setResponse(response);
        } else if (update.hasCallbackQuery())
        {
            response = bot.commandManager().executeInlineCommand(bot, update.getCallbackQuery());
            event.setResponse(response);
        }
    }

    private void clearButtons(Keyboard keyboard)
    {
        keyboard.deleteAllReplyButtons();
        keyboard.deleteAllInlineButtons();
        keyboard.deleteAllReplyButtonsWithRow(KeyboardMapsAndRows.KEYBOARD_ROW);
    }

}
