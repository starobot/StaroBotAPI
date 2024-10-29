package net.staro.bot.api.command.impl;

import lombok.Getter;
import lombok.Setter;
import net.staro.bot.api.Bot;
import net.staro.bot.api.command.Builder;
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
        if (command == null) {
            return "Unknown command: " + keyword;
        }

        UpdateEvent.INSTANCE.setDeletable(command.isDeletable());
        if (command.hasPermisions()) {
            return "Permission denied for command: " + keyword;
        }

        Builder builder = new CommandBuilder();
        command.build(builder);
        COMMAND_STATE_MAP.put(message.getFrom().getId(), new CommandState(command, 1));
        return builder.handle(message, 0);
    }

    @Override
    public String handleArgument(Bot bot, Message message) {
        Long userId = message.getFrom().getId();
        CommandState state = COMMAND_STATE_MAP.get(userId);
        if (state == null) {
            return "No active command for user.";
        }

        Command command = state.getCommand();
        int step = state.getStep();
        Builder builder = new CommandBuilder();
        command.build(builder);
        String response = builder.handle(message, step);
        if (step >= builder.getMaxSteps() - 1 || response == null) {
            if (command.isLooping()) {
                state.setStep(1);
            } else {
                COMMAND_STATE_MAP.remove(userId);
            }
        } else {
            state.setStep(step + 1);
        }

        return response;
    }

    @Override
    public String executeInlineCommand(Bot bot, CallbackQuery callbackQuery) {
        long userId = callbackQuery.getFrom().getId();
        CommandState state = COMMAND_STATE_MAP.get(userId);
        if (state == null) {
            return "No active command for user.";
        }

        int step = state.getStep();
        Command command = state.getCommand();
        Builder builder = new CommandBuilder();
        command.build(builder);
        String response = builder.handle(callbackQuery, step);
        if (step >= builder.getMaxSteps() - 1 || response == null) {
            if (command.isLooping()) {
                state.setStep(1);
            } else {
                COMMAND_STATE_MAP.remove(userId);
            }
        } else {
            state.setStep(step + 1);
        }

        return response;
    }

}
