package net.staro.bot.api.bus.impl;

import lombok.Getter;
import net.staro.bot.api.bus.EventListener;

import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.function.Consumer;

@Getter
public class EventListenerImpl implements EventListener
{
    private final Object instance;
    private final Method method;
    private final int priority;
    private final Consumer<Object> consumer;

    public EventListenerImpl(Object instance, Method method, int priority)
    {
        this.instance = instance;
        this.method = method;
        this.priority = priority;
        this.consumer = createConsumer();
    }

    /**
     * Creates a Consumer to handle the method invocation using lambda metafactory for a higher efficiency.
     *
     * @return A consumer that accepts an event and invokes the listener method.
     */
    @SuppressWarnings("unchecked")
    private Consumer<Object> createConsumer()
    {
        try
        {
            MethodHandles.Lookup lookup = MethodHandles.privateLookupIn(method.getDeclaringClass(), MethodHandles.lookup());
            MethodType methodType = MethodType.methodType(void.class, method.getParameters()[0].getType());
            MethodHandle methodHandle = lookup.findVirtual(method.getDeclaringClass(), method.getName(), methodType);
            MethodType invokedType = MethodType.methodType(Consumer.class, method.getDeclaringClass());
            MethodHandle lambdaFactory = LambdaMetafactory.metafactory(
                    lookup, "accept", invokedType, MethodType.methodType(void.class, Object.class), methodHandle, methodType).getTarget();
            return (Consumer<Object>) lambdaFactory.invoke(instance);
        } catch (Throwable throwable)
        {
            throw new IllegalStateException(throwable.getMessage());
        }
    }

    /**
     * Invokes the listener method with the given event.
     *
     * @param event The event to be passed to the listener method.
     */
    @Override
    public void invoke(Object event)
    {
        consumer.accept(event);
    }

}
