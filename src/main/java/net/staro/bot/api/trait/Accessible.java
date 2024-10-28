package net.staro.bot.api.trait;

public interface Accessible {
    /**
     * Makes the command accessible to the certain users only.
     * @return true if the command has special permission and false otherwise.
     */
    boolean hasPermisions();

}
