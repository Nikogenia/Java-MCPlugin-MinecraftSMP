package de.nikocraft.minecraftsmp.listeners;

import de.nikocraft.minecraftsmp.Main;
import de.nikocraft.minecraftsmp.inventory.ShulkerManager;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

import java.util.Arrays;

public class InventoryListeners implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        if (ShulkerManager.isShulker(event.getCurrentItem().getType())) {

            if (!event.getClick().isRightClick()) return;

            if (!ShulkerManager.validInventory(event.getClickedInventory().getType())) return;




        }

    }

}
