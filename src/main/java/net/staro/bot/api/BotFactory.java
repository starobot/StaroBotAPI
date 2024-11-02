package net.staro.bot.api;

import lombok.experimental.UtilityClass;
import net.staro.bot.api.bus.impl.EventBusImpl;
import net.staro.bot.api.command.impl.CommandManagerImpl;
import net.staro.bot.api.events.UpdateEvent;
import net.staro.bot.api.keyboard.impl.KeyboardImpl;
import net.staro.bot.api.response.ResponseService;
import net.staro.bot.api.response.impl.ResponseManagerImpl;
import net.staro.bot.api.telegram.AbstractTelegramBot;
import net.staro.bot.api.telegram.TelegramBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 * A utility class to initialize the API within the main class.
 */
@SuppressWarnings("unused")
@UtilityClass
public class BotFactory
{
    public static final String DEFAULT_PREFIX = "/";
    public static final Bot BOT = new BotRecord(
            new EventBusImpl(),
            new KeyboardImpl(UpdateEvent.INSTANCE),
            new ResponseManagerImpl(),
            new CommandManagerImpl());

    /**
     * Registers the bot within the provided Telegram API.
     * @param username is the custom username for the bot.
     * @param token is the custom token for the bot.
     * @throws TelegramApiException if the error occurs upon registering the bot.
     */
    public static void registerNewTelegramBot(String username, String token) throws TelegramApiException
    {
        registerNewTelegramBot(username, token, DEFAULT_PREFIX);
    }

    /**
     * Registers the bot within the provided Telegram API.
     * @param username is the custom username for the bot.
     * @param token is the custom token for the bot.
     * @param prefix is the prefix for the commands.
     * @throws TelegramApiException if the error occurs upon registering the bot.
     */
    public static void registerNewTelegramBot(String username, String token, String prefix) throws TelegramApiException
    {
        LongPollingBot telegramBot = initializeBotApi(username, token, prefix);
        BOT.eventBus().subscribe(telegramBot);
        new TelegramBotsApi(DefaultBotSession.class).registerBot(telegramBot);
    }

    /**
     * Registers the bot within the provided Telegram API.
     * This method is useful for inheriting {@link AbstractTelegramBot} to override the existing message handling logic.
     * @param telegramBot is the bot.
     * @throws TelegramApiException if the error occurs upon registering the bot.
     */
    public static void registerNewTelegramBot(LongPollingBot telegramBot) throws TelegramApiException
    {
        new TelegramBotsApi(DefaultBotSession.class).registerBot(telegramBot);
    }

    /**
     * Initializes the bot API.
     * @param username is the custom username for the bot.
     * @param token is the custom token for the bot.
     * @param prefix is the prefix for the commands.
     * @return {@link AbstractTelegramBot} instance.
     * @throws IllegalArgumentException if the username or token is null.
     */
    private static LongPollingBot initializeBotApi(String username, String token, String prefix)
    {
        if (username == null || token == null)
        {
            throw new IllegalArgumentException("Username and token cannot be null. Please provide valid values from BotFather.");
        }

        BOT.commandManager().setPrefix(prefix);
        BOT.eventBus().subscribe(new ResponseService());
        return new TelegramBot(BOT, username, token);
    }

}
