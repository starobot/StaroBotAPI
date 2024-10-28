package net.staro.bot.api.bus;

/**
 * This is an object for a listener withing the {@link EventBus} that can receive E events
 * @param <E> is an event.
 */
public interface EventListener<E> extends Comparable<EventListener<E>> {
    /**
     * The method accepting the Event.
     * @param event represents the Event class.
     */
    void onEvent(E event);

    /**
     * An integer representing a priority of a listener.
     * @see Priority
     * @return priority and an integer value
     */
    int getPriority();

}
