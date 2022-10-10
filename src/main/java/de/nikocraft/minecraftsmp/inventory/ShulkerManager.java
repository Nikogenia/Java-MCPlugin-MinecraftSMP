package de.nikocraft.minecraftsmp.inventory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Map;

public class ShulkerManager {

    private Map<Player, ItemStack> openShulkers;
    private Map<Player, Inventory> previousInventory;

    public void open(Player player, ItemStack shulker) {

    }

    public void stop() {



    }

    public static boolean isShulker(Material material) {

        return Arrays.asList(Material.SHULKER_BOX, Material.WHITE_SHULKER_BOX, Material.ORANGE_SHULKER_BOX, Material.MAGENTA_SHULKER_BOX,
                        Material.LIGHT_BLUE_SHULKER_BOX, Material.YELLOW_SHULKER_BOX, Material.LIME_SHULKER_BOX, Material.PINK_SHULKER_BOX,
                        Material.GRAY_SHULKER_BOX, Material.LIGHT_GRAY_SHULKER_BOX, Material.CYAN_SHULKER_BOX, Material.PURPLE_SHULKER_BOX,
                        Material.BLUE_SHULKER_BOX, Material.BROWN_SHULKER_BOX, Material.GREEN_SHULKER_BOX, Material.RED_SHULKER_BOX, Material.BLACK_SHULKER_BOX).contains(material);

    }

    public static boolean validInventory(InventoryType inventory) {

        return Arrays.asList(InventoryType.BARREL, InventoryType.CHEST, InventoryType.ENDER_CHEST, InventoryType.PLAYER).contains(inventory);

    }

}
