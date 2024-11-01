package net.staro.bot.api.command;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.function.Function;

@SuppressWarnings("UnusedReturnValue")
public interface Builder
{
    Builder execute(Function<Message, String> action);

    Builder thenMessage(Function<Message, String> action);

    Builder thenInline(Function<CallbackQuery, String> action);

    String handle(Message message, int step);

    String handle(CallbackQuery query, int step);

    int getMaxSteps();

}
