package de.nikocraft.minecraftsmp.status;

import de.nikocraft.minecraftsmp.Main;
import de.nikocraft.minecraftsmp.utils.Config;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class StatusManager {

    private Config config;

    public StatusManager() {

        config = Main.getMainConfig();

    }

    public Status getStatus(Player player) {

        if (!config.getConfig().contains("status." + player.getUniqueId())) return Status.ONLINE;

        return Status.fromID(config.getConfig().getInt("status." + player.getUniqueId()));

    }

    public void setStatus(Player player, Status status) {

        config.getConfig().set("status." + player.getUniqueId(), status.getId());

        config.save();

    }

    public static String getStatusPrefix() {

        return ChatColor.GRAY + "[" + ChatColor.DARK_GREEN + ChatColor.BOLD + "Status" + ChatColor.RESET +
                ChatColor.GRAY + "] " + ChatColor.WHITE;

    }

}
