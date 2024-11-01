package net.staro.bot.api.telegram;

import lombok.Getter;
import net.staro.bot.api.Bot;

// Supposed to remain a part of the api, however one might inherit it to use the managers from the api.

/**
 * The telegram bot instance.
 */
@Getter
public class TelegramBot extends AbstractTelegramBot
{
    private final String botUsername;
    private final String botToken;

    public TelegramBot(Bot bot, String username, String token)
    {
        super(bot);
        this.botUsername = username;
        this.botToken = token;
    }

}
