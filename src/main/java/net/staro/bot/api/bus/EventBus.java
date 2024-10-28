package net.staro.bot.api.bus;

/**
 * The Event Bus for posting events to the subscribed listeners.
 */
public interface EventBus {
    /**
     * Distributes an event object to the existing listeners.
     * @param event is an object that can be posted. Usually a class or a record.
     */
    void post(Object event);

    /**
     * Subscribes a listener to the eventbus, allowing it to receive event objects.
     * @param listener is an object that can receive events.
     */
    void subscribe(Object listener);

    /**
     * Unsubscribes a listener from the eventbus, so it doesn't receive event objects anymore.
     * @param listener is an object that can receive events and was being subscribed before using this method.
     */
    void unsubscribe(Object listener);

    /**
     * Checks whether the existing listener is currently subscribed to the eventbus.
     * @param listener is an object that can receive events.
     * @return true if the listener is currently subscribed.
     */
    boolean isSubscribed(Object listener);

}
