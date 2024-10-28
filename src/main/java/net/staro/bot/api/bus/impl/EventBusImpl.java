package net.staro.bot.api.bus.impl;

import net.staro.bot.api.bus.EventBus;
import net.staro.bot.api.bus.EventListener;
import net.staro.bot.api.bus.Subscriber;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;

public class EventBusImpl implements EventBus {
    private final Map<Class<?>, PriorityQueue<EventListener<?>>> listeners = new ConcurrentHashMap<>();

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void post(Object event) {
        PriorityQueue<EventListener<?>> eventListeners = listeners.get(event.getClass());
        if (eventListeners != null) {
            for (EventListener listener : eventListeners) {
                listener.onEvent(event);
            }
        }
    }

    @Override
    public void subscribe(Object listener) {
        if (listener instanceof Subscriber subscriber) {
            for (Map.Entry<Class<?>, PriorityQueue<EventListener<?>>> entry : subscriber.getListeners().entrySet()) {
                listeners.computeIfAbsent(entry.getKey(), k -> new PriorityQueue<>()).addAll(entry.getValue());
            }
        } else {
            throw new IllegalArgumentException("Listener must implement Subscriber interface.");
        }
    }

    @Override
    public void unsubscribe(Object listener) {
        if (listener instanceof Subscriber subscriber) {
            for (Map.Entry<Class<?>, PriorityQueue<EventListener<?>>> entry : subscriber.getListeners().entrySet()) {
                PriorityQueue<EventListener<?>> queue = listeners.get(entry.getKey());
                if (queue != null) {
                    queue.removeAll(entry.getValue());
                }
            }
        } else {
            throw new IllegalArgumentException("Listener must implement Subscriber interface.");
        }
    }

    @Override
    public boolean isSubscribed(Object listener) {
        if (listener instanceof Subscriber subscriber) {
            for (Map.Entry<Class<?>, PriorityQueue<EventListener<?>>> entry : subscriber.getListeners().entrySet()) {
                PriorityQueue<EventListener<?>> queue = listeners.get(entry.getKey());
                if (queue != null && queue.containsAll(entry.getValue())) {
                    return true;
                }
            }
        }

        return false;
    }

}
