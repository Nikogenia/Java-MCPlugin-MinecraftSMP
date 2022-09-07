package de.nikocraft.minecraftsmp.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionListeners implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        event.setJoinMessage(ChatColor.GRAY + "> " + ChatColor.DARK_GREEN + ChatColor.BOLD + event.getPlayer().getName());

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        event.setQuitMessage(ChatColor.GRAY + "< " + ChatColor.DARK_RED + ChatColor.BOLD + event.getPlayer().getName());

    }

}
