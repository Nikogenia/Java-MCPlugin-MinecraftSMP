package de.nikocraft.minecraftsmp.listeners;

import de.nikocraft.minecraftsmp.Main;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class EntityListeners implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {

        if (event.getEntityType().equals(EntityType.PLAYER)) Main.getPlayerListeners().onDamage(event);

    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {

        if (event.getEntityType().equals(EntityType.PLAYER)) Main.getPlayerListeners().onEntityDamage(event);

    }

    @EventHandler
    public void onGlide(EntityToggleGlideEvent event) {

        if (event.getEntityType().equals(EntityType.PLAYER)) Main.getPlayerListeners().onGlide(event);

    }

}
