package net.staro.bot.api.command.impl;

import lombok.Getter;
import lombok.Setter;
import net.staro.bot.api.Bot;
import net.staro.bot.api.command.Command;
import net.staro.bot.api.command.CommandManager;
import net.staro.bot.api.command.CommandState;
import net.staro.bot.api.events.UpdateEvent;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

@Setter
@Getter
public class CommandManagerImpl implements CommandManager {
    private String prefix;

    @Override
    public String execute(Bot bot, String keyword, Message message) {
        String prefix = bot.commandManager().getPrefix();
        keyword = keyword.substring(prefix.length());
        Command command = COMMANDS.get(keyword);
        if (command != null) {
            if (command.hasPermisions()) {
                return "Permission denied for command: " + keyword;
            }

            COMMAND_STATE_MAP.put(message.getFrom().getId(), new CommandState(command, 1));
            return command.executeCommand(message);
        }

        return "Unknown command: " + keyword;
    }

    @Override
    public String handleArgument(Message message) {
        long userId = message.getFrom().getId();
        CommandState state = COMMAND_STATE_MAP.get(userId);
        if (state == null) {
            return "No active command for user.";
        }

        int step = state.getStep();
        String response = state.getCommand().executeArgument(step, message);
        if (response != null) {
            state.setStep(step + 1);
        } else {
            COMMAND_STATE_MAP.remove(userId);
        }

        return response;
    }

    @Override
    public String executeInlineCommand(Bot bot, CallbackQuery callbackQuery) {
        for (Command command : COMMANDS.values()) {
            String response = command.executeInlineCommand(callbackQuery);
            if (response != null) {
                UpdateEvent.INSTANCE.setDeletable(command.isDeletable());
                return response;
            }
        }

        return "Error.";
    }

}
