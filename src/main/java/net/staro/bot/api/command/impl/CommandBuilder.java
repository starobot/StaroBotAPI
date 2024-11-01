package net.staro.bot.api.command.impl;

import net.staro.bot.api.command.Builder;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CommandBuilder implements Builder
{
    private final List<ArgumentAction> actions = new ArrayList<>();

    @Override
    public Builder execute(Function<Message, String> action)
    {
        actions.add(new ArgumentAction(msg -> action.apply((Message) msg), ActionType.MESSAGE));
        return this;
    }

    @Override
    public Builder thenMessage(Function<Message, String> action)
    {
        actions.add(new ArgumentAction(msg -> action.apply((Message) msg), ActionType.MESSAGE));
        return this;
    }

    @Override
    public Builder thenInline(Function<CallbackQuery, String> action)
    {
        actions.add(new ArgumentAction(msg -> action.apply((CallbackQuery) msg), ActionType.CALLBACK_QUERY));
        return this;
    }

    @Override
    public String handle(Message message, int step)
    {
        var stage = actions.get(step);
        return stage.action.apply(message);
    }

    @Override
    public String handle(CallbackQuery query, int step)
    {
        var stage = actions.get(step);
        return stage.action.apply(query);
    }

    @Override
    public int getMaxSteps()
    {
        return actions.size();
    }

    private record ArgumentAction(Function<Object, String> action, ActionType type)
    {
    }

    private enum ActionType
    {
        MESSAGE, CALLBACK_QUERY
    }

}
