package net.staro.bot.api.bus.impl;

import lombok.Getter;
import net.staro.bot.api.bus.EventListener;
import net.staro.bot.api.bus.Subscriber;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

@Getter
public class SubscriberImpl implements Subscriber {
    private final Map<Class<?>, PriorityQueue<EventListener<?>>> listeners = new HashMap<>();

    protected <E> void listen(EventListener<E> listener) {
        Class<?> eventType = getEventParameterType(listener);
        listeners.computeIfAbsent(eventType, k -> new PriorityQueue<>()).add(listener);
    }

    private Class<?> getEventParameterType(EventListener<?> listener) {
        for (Method method : listener.getClass().getMethods()) {
            if (method.getName().equals("onEvent") && method.getParameterCount() == 1) {
                return method.getParameterTypes()[0];
            }
        }
        throw new IllegalArgumentException("Listener does not have a valid onEvent method");
    }

}
