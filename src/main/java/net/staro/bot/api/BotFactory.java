package net.staro.bot.api;

import lombok.Getter;
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
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 * A utility class to initialize the API within the main class.
 */
@UtilityClass
public class BotFactory {
    @Getter
    private final Bot bot = new BotRecord(
            new EventBusImpl(),
            new KeyboardImpl(UpdateEvent.INSTANCE),
            new ResponseManagerImpl(),
            new CommandManagerImpl());

    /**
     * Registers the bot within the provided Telegram API.
     * @param username is the custom username for the bot.
     * @param token is the custom token for the bot.
     * @param prefix is the prefix for the commands.
     * @throws TelegramApiException if the error occurs upon registering the bot.
     */
    public void registerNewTelegramBot(String username, String token, String prefix) throws TelegramApiException {
        new TelegramBotsApi(DefaultBotSession.class).registerBot(initializeBotApi(username, token, prefix));
    }

    /**
     * Registers the bot within the provided Telegram API.
     * This method is useful for inheriting {@link AbstractTelegramBot} to override the existing message handling logic.
     * @param telegramBot is the bot.
     * @throws TelegramApiException if the error occurs upon registering the bot.
     */
    public void registerNewTelegramBot(AbstractTelegramBot telegramBot) throws TelegramApiException {
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
    private AbstractTelegramBot initializeBotApi(String username, String token, String prefix) {
        if (username != null && token != null) {
            bot.commandManager().setPrefix(prefix);
            bot.eventBus().subscribe(new ResponseService());
            return new TelegramBot(bot, username, token);
        }

        throw new IllegalArgumentException("Username and token cannot be null. Please provide valid values from BotFather.");
    }

}
