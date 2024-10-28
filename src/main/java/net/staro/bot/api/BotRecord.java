package net.staro.bot.api;

import net.staro.bot.api.bus.EventBus;
import net.staro.bot.api.command.CommandManager;
import net.staro.bot.api.keyboard.Keyboard;
import net.staro.bot.api.response.ResponseManager;

/**
 * A Bot record that allows us to create a new object in the main function.
 * @param keyboard is the Keyboard and input handler.
 * @param eventBus is the custom EventBus.
 * @param responseManager is the manager for the various kinds of responses.
 */
public record BotRecord(EventBus eventBus,
                        Keyboard keyboard,
                        ResponseManager responseManager,
                        CommandManager commandManager) implements Bot {
}
