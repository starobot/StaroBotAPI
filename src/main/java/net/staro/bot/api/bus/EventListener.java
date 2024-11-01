package net.staro.bot.api.bus;

import java.lang.reflect.Method;

/**
 * This is an object representing and event listener that can receive posted event objects withing the {@link EventBus}.
 */
public interface EventListener
{
    /**
     * The method accepting the Event.
     * @param event represents the Event class.
     */
    void invoke(Object event);

    /**
     * Gets the instance containing the listener method.
     *
     * @return The object instance.
     */
    Object getInstance();

    /**
     * Gets the method associated with this event listener.
     *
     * @return The listener method.
     */
    Method getMethod();

    /**
     * Gets the priority of this event listener.
     *
     * @return The priority value.
     */
    int getPriority();

}
