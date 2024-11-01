package net.staro.bot.api.response;

import net.staro.bot.api.trait.Initializable;

/**
 * Registers custom responses. Implement this into your custom ResponseManager to add more responses based on the needed context.
 */
@SuppressWarnings("unused")
public interface ResponseRegistry extends ResponseList, Initializable
{
    /**
     * Puts a response into a RESPONSES list.
     * @param response is a response object.
     */
    default void registerResponse(Response response) {
        RESPONSES.add(response);
    }

}
