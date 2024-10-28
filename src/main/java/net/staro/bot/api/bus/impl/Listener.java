package net.staro.bot.api.bus.impl;

import lombok.Getter;
import net.staro.bot.api.bus.EventListener;
import net.staro.bot.api.bus.Priority;

@Getter
public abstract class Listener<E> implements EventListener<E> {
    private final Object instance;
    private final int priority;

    protected Listener() {
        this(Priority.DEFAULT.getVal());
    }

    protected Listener(int priority) {
        this.instance = this;
        this.priority = priority;
    }

    @Override
    public int compareTo(EventListener listener) {
        return Integer.compare(listener.getPriority(), this.priority);
    }

}
