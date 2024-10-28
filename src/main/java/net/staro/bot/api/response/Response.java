package net.staro.bot.api.response;

/**
 * An interface for custom responses from the bot. Implement it to get A response.
 */
public interface Response {
    /**
     * Sends a bot response to the user upon receiving {@link org.telegram.telegrambots.meta.api.objects.Update}
     * @return a bot response as a String.
     */
    String getResponse();

}
