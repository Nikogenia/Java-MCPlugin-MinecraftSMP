package de.nikocraft.minecraftsmp.teleport;

import org.bukkit.entity.Player;

public class TeleportRequest {

    private Player from;

    private Player to;

    private int timeout;

    public TeleportRequest(Player from, Player to) {

        this.from = from;
        this.to = to;
        timeout = 60;

    }

    public Player getFrom() {
        return from;
    }

    public Player getTo() {
        return to;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

}
