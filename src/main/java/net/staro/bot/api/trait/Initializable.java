package net.staro.bot.api.trait;

import net.staro.bot.api.Bot;

/**
 * An interface for manager usage mostly. Allows us to initialize new objects.
 */
public interface Initializable
{
    /**
     * Initializes the object instances. To be used within managers to create new commands or responses or maybe something else.
     * @param bot is the global getter for managers, eventBus and more.
     */
    void initialize(Bot bot);

}
