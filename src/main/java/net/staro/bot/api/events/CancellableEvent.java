package net.staro.bot.api.events;

import lombok.Getter;
import lombok.Setter;

/**
 * A type of event that can be cancelled by the listener.
 * Could be when setting up permissions.
 */
@Getter
@Setter
public class CancellableEvent
{
    private boolean cancelled;

    public CancellableEvent() {
        cancelled = false;
    }

}
