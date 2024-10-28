package net.staro.bot.api.bus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static java.lang.Integer.MAX_VALUE;

@Getter
@RequiredArgsConstructor
public enum Priority {
    HIGHEST(MAX_VALUE),
    HIGH(3),
    MEDIUM(2),
    LOW(1),
    DEFAULT(0);

    private final int val;

}
