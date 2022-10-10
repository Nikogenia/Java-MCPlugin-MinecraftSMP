package de.nikocraft.minecraftsmp.listeners;

import de.nikocraft.minecraftsmp.Main;
import de.nikocraft.minecraftsmp.spawn.SpawnManager;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.KeybindComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class PlayerListeners implements Listener {

    @EventHandler
    public void onToggleFlight(PlayerToggleFlightEvent event) {

        if (!event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) return;

        if (!SpawnManager.isInSpawnRadius(event.getPlayer())) return;

        event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("Press ")
                .append(new KeybindComponent("key.swapOffhand"))
                .append(" to boost yourself!")
                .create());

        event.setCancelled(true);

        event.getPlayer().setGliding(true);

        Main.getSpawnManager().getFlying().add(event.getPlayer());

    }

    public void onDamage(EntityDamageEvent event) {

        if ((event.getCause().equals(EntityDamageEvent.DamageCause.FALL) | event.getCause().equals(EntityDamageEvent.DamageCause.FLY_INTO_WALL))
                & Main.getSpawnManager().getFlying().contains(event.getEntity())) {
            event.setCancelled(true);
        }

    }

    public void onEntityDamage(EntityDamageByEntityEvent event) {

        if (event.getDamager().getType().equals(EntityType.PLAYER) | event.getDamager().getType().equals(EntityType.ARROW))
            Main.getTeleportManager().getLastPlayerDamage().put((Player) event.getEntity(), 15);

    }

    public void onGlide(EntityToggleGlideEvent event) {

        if (Main.getSpawnManager().getFlying().contains(event.getEntity())) event.setCancelled(true);

    }

    @EventHandler
    public void onSwapHand(PlayerSwapHandItemsEvent event) {

        if (!Main.getSpawnManager().getFlying().contains(event.getPlayer())) return;

        event.setCancelled(true);

        event.getPlayer().setVelocity(event.getPlayer().getLocation().getDirection().multiply(3));

    }

    @EventHandler
    public void onExplode(EntityExplodeEvent event) {

        if (!event.getLocation().getWorld().getName().equals("world")) return;

        if (event.getLocation().distance(new Location(Bukkit.getWorld("world"), 432, 68, 333, 0, 0)) < 50 |
                event.getLocation().distance(new Location(Bukkit.getWorld("world"), 0, 200, 0, 0, 0)) < 130) {
            event.blockList().clear();
        }

    }

}
