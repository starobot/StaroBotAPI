package net.staro.bot.api.command.impl;

import lombok.Getter;
import net.staro.bot.api.Bot;
import net.staro.bot.api.command.Command;

@Getter
public abstract class BotCommand implements Command
{
    protected final Bot bot;
    private final String name;

    protected BotCommand(Bot bot, String name)
    {
        this.bot = bot;
        this.name = name;
    }

}
