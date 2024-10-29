package net.staro.bot.api.command;

import net.staro.bot.api.Bot;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface CommandManager extends CommandMap {
    /**
     * Gets the current prefix for the bot commands.
     * @return prefix as a String.
     */
    String getPrefix();

    /**
     * Sets the current prefix to the new one.
     * @param prefix is a String to replace the current prefix with the new one.
     */
    void setPrefix(String prefix);

    /**
     * Executes the command in the {@code onUpdateReceived(Update update)} method.
     * @param bot is the getter interface for the api to get necessary managers without creating new instances of the classes.
     * @param keyword is the command prefix.
     * @param message is the message sent by user.
     * @return a bot response as a String.
     */
    String execute(Bot bot, String keyword, Message message);

    /**
     * Handles the arguments withing the command without the need to execute the command again.
     * Only to be executed in case {@code COMMAND_STATE_MAP.get(id) != null}.
     * @param message is the message sent by user.
     * @return a bot response as a String.
     */
    String handleArgument(Bot bot, Message message);

    /**
     * Executes an inline command based on callback data.
     * @param bot is the getter interface for the api to get necessary managers without creating new instances of the classes.
     * @param callbackQuery is the original callback query object.
     * @return a bot response as a String.
     */
    String executeInlineCommand(Bot bot, CallbackQuery callbackQuery);

}
