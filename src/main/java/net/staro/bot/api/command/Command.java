package net.staro.bot.api.command;

import net.staro.bot.api.trait.Accessible;
import net.staro.bot.api.trait.Nameable;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface Command extends Nameable, Accessible {
    /**
     * Executes the command when the user message starts with the prefix.
     * @param message is the user message received in {@code onUpdateReceived(Update update)} method.
     * @return a bot response as a String.
     */
    String executeCommand(Message message);

    /**
     * Executes the arguments which allow the user to continue using the command without the prefix.
     * @param step is the argument to be executed. Integer value represents the order of execution.
     * @param message is the user message received in {@code onUpdateReceived(Update update)} method.
     * @return a bot response as a String.
     */
    String executeArgument(int step, Message message);

    /**
     * Executes the inline command when a callback query is received.
     * @param callbackQuery the callback query received from an inline button.
     * @return a bot response as a String.
     */
    String executeInlineCommand(CallbackQuery callbackQuery);

    /**
     * Determines whether the messages sent by bot will be deleted as soon as the next {@link org.telegram.telegrambots.meta.api.objects.Update} is received.
     * @return false by default.
     */
    default boolean isDeletable() {
        return false;
    }

}
