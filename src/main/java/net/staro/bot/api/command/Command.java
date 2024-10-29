package net.staro.bot.api.command;

import net.staro.bot.api.trait.Accessible;
import net.staro.bot.api.trait.Nameable;

public interface Command extends Nameable, Accessible {
    /**
     * Set up the command using a CommandBuilder.
     * @param builder The command builder used to define command logic.
     */
    void build(Builder builder);

    /**
     * Determines whether the messages sent by bot will be deleted as soon as the next {@link org.telegram.telegrambots.meta.api.objects.Update} is received.
     * @return false by default.
     */
    default boolean isDeletable() {
        return false;
    }

    /**
     * Sets the command state to 1. So the command repeats itself upon sending the first expected argument.
     * @return true if the command does loop.
     */
    default boolean isLooping() {
        return false;
    }

}
