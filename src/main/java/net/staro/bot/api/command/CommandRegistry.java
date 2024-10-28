package net.staro.bot.api.command;

import net.staro.bot.api.trait.Initializable;

/**
 * An interface used for registering new commands. Implement it in your custom manager to initialize new bot commands.
 */
public interface CommandRegistry extends Initializable, CommandMap {
    /**
     * Registers a command by putting it in the {@code COMMANDS} map.
     * @param command is the command to be put it the map.
     */
    default void register(Command command) {
        COMMANDS.put(command.getName(), command);
    }

}
