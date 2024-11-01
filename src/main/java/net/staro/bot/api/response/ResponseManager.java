package net.staro.bot.api.response;

import net.staro.bot.api.Bot;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * A manager for bot responses. Here we store all the Response objects, used throughout the project.
 */
public interface ResponseManager extends ResponseList
{
    /**
     * A method used to get a response upon receiving {@link Message} object in an {@code onUpdateReceived} method
     * @param bot is the dependency injection interface for managers, eventBus and keyboard.
     * @param message is the message received from user.
     * @return response as a String.
     */
    String getResponseForMessage(Bot bot, Message message);

}
