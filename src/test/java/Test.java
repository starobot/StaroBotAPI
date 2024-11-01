import net.staro.bot.api.Bot;
import net.staro.bot.api.BotFactory;
import net.staro.bot.api.bus.impl.EventListenerImpl;
import net.staro.bot.api.command.Builder;
import net.staro.bot.api.command.CommandRegistry;
import net.staro.bot.api.command.impl.BotCommand;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static net.staro.bot.api.command.CommandMap.COMMANDS;

public class Test {
    public static void main(String[] args) throws TelegramApiException {
        BotFactory.registerNewTelegramBot("", "");
        new TestCommands().initialize(BotFactory.BOT);
    }

    public static class TestCommands implements CommandRegistry {
        @Override
        public void initialize(Bot bot) {
            register(new TestCommand(bot));
            register(new HelpCommand(bot));
        }

    }

    public static class HelpCommand extends BotCommand {
        protected HelpCommand(Bot bot) {
            super(bot, "help");
        }

        @Override
        public void build(Builder builder) {
            builder.execute(message -> {
                var commandsList = new StringBuilder();
                for (var command : COMMANDS.values()) {
                    commandsList.append(bot.commandManager().getPrefix());
                    commandsList.append(command.getName());
                    commandsList.append("\n");
                }

                return commandsList.toString();
            });
        }
    }

    public static class TestCommand extends BotCommand {
        protected TestCommand(Bot bot) {
            super(bot, "test");
        }

        @Override
        public boolean isLooping() {
            return true;
        }

        @Override
        public boolean isDeletable() {
            return true;
        }

        @Override
        public void build(Builder builder) {
            builder.execute(message -> "hello").thenMessage(message -> {
                bot.keyboard().createInlineButton("1", "1");
                if (message.getText().equalsIgnoreCase("hello")) {
                    bot.keyboard().createInlineButton("2", "2");
                    return "Make a choice";
                } else {
                    return "You have no choice";
                }
            }).thenInline(query -> {
                if (query.getData().equalsIgnoreCase("1")) {
                    return "Die";
                } else {
                    return "Live";
                }
            });
        }
    }

}
