package net.staro.bot.api.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommandState {
    private Command command;
    private int step;

}
