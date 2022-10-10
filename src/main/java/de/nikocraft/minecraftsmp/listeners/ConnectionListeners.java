package de.nikocraft.minecraftsmp.listeners;

import de.nikocraft.minecraftsmp.Main;
import de.nikocraft.minecraftsmp.tablist.TablistManager;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Map;

public class ConnectionListeners implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        event.getPlayer().sendMessage(ChatColor.GOLD.toString() + ChatColor.BOLD + " \nWelcome on Class Attack, " + ChatColor.YELLOW + ChatColor.BOLD +
                event.getPlayer().getName() + ChatColor.GOLD + ChatColor.BOLD + "!\n ");

        event.setJoinMessage(ChatColor.GRAY + "> " + ChatColor.DARK_GREEN + ChatColor.BOLD + event.getPlayer().getName());

        Main.getTeleportManager().getLastPlayerDamage().put(event.getPlayer(), 0);

        Main.getTablistManager().setTablistHeaderFooter(event.getPlayer());
        Main.getTablistManager().setAllPlayerTeams();

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        event.setQuitMessage(ChatColor.GRAY + "< " + ChatColor.DARK_RED + ChatColor.BOLD + event.getPlayer().getName());

        Main.getTeleportManager().getLastPlayerDamage().remove(event.getPlayer());

    }

}
