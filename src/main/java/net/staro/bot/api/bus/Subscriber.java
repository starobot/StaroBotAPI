package net.staro.bot.api.bus;

import java.util.Map;
import java.util.PriorityQueue;

/**
 * An interface for any class containing a listener object.
 */
public interface Subscriber {
    Map<Class<?>, PriorityQueue<EventListener<?>>> getListeners();

}
