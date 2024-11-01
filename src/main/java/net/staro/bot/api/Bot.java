package net.staro.bot.api;

import net.staro.bot.api.bus.EventBus;
import net.staro.bot.api.command.CommandManager;
import net.staro.bot.api.keyboard.Keyboard;
import net.staro.bot.api.response.ResponseManager;

/**
 * An interface for dependency injection. Allows us to reference managers without creating new objects.
 */
public interface Bot
{
    /**
     * A reference to the EventBus.
     * @return eventBus interface.
     */
    EventBus eventBus();

    /**
     * A reference to the Keyboard and input handler.
     * @return keyboard interface.
     */
    Keyboard keyboard();

    /**
     * A reference to the response manager that handles responses from bot to user.
     * @return responseManager interface.
     */
    ResponseManager responseManager();

    /**
     * A reference to the CommandManager.
     * @return CommandManager interface.
     */
    CommandManager commandManager();

}
