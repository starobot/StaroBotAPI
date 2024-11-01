package net.staro.bot.api.command;

import java.util.HashMap;
import java.util.Map;

/**
 * An interface for storing commands and command states.
 * @see CommandRegistry
 */
public interface CommandMap
{
    Map<String, Command> COMMANDS = new HashMap<>();
    Map<Long, CommandState> COMMAND_STATE_MAP = new HashMap<>();

}
