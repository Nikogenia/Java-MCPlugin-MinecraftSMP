package de.nikocraft.minecraftsmp;

import de.nikocraft.minecraftsmp.commands.TeleportCommand;
import de.nikocraft.minecraftsmp.listeners.ConnectionListeners;
import de.nikocraft.minecraftsmp.teleport.TeleportManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;

    private TeleportManager teleportManager;

    @Override
    public void onLoad() {

        instance = this;

        getLogger().info("Plugin loaded.");

    }

    @Override
    public void onEnable() {

        getLogger().info("Load teleport manager ...");
        teleportManager = new TeleportManager();

        getLogger().info("Register listeners ...");
        Bukkit.getPluginManager().registerEvents(new ConnectionListeners(), this);

        getLogger().info("Register commands ...");
        getCommand("teleport_request").setExecutor(new TeleportCommand());

        getLogger().info("Plugin enabled.");

    }

    @Override
    public void onDisable() {

        getLogger().info("Stop teleport manager ...");
        teleportManager.stop();

        getLogger().info("Plugin disabled.");

    }

    public static Main getInstance() {
        return instance;
    }

    public static TeleportManager getTeleportManager() {
        return instance.teleportManager;
    }

}
