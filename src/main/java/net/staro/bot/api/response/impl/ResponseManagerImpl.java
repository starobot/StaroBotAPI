package net.staro.bot.api.response.impl;

import net.staro.bot.api.Bot;
import net.staro.bot.api.response.Response;
import net.staro.bot.api.response.ResponseManager;
import org.telegram.telegrambots.meta.api.objects.Message;

public class ResponseManagerImpl implements ResponseManager
{
    @Override
    public String getResponseForMessage(Bot bot, Message message)
    {
        StringBuilder responseBuilder = new StringBuilder();
        for (Response response : RESPONSES)
        {
            String individualResponse = response.getResponse();
            if (individualResponse != null && !individualResponse.isEmpty())
            {
                responseBuilder.append(individualResponse).append("\n");
            }
        }

        return responseBuilder.toString();
    }

}
