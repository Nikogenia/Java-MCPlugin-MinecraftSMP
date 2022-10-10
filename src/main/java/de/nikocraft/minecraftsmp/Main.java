package de.nikocraft.minecraftsmp;

import de.nikocraft.minecraftsmp.commands.*;
import de.nikocraft.minecraftsmp.home.HomeManager;
import de.nikocraft.minecraftsmp.inventory.ShulkerManager;
import de.nikocraft.minecraftsmp.listeners.ConnectionListeners;
import de.nikocraft.minecraftsmp.listeners.EntityListeners;
import de.nikocraft.minecraftsmp.listeners.PlayerListeners;
import de.nikocraft.minecraftsmp.spawn.SpawnManager;
import de.nikocraft.minecraftsmp.status.Status;
import de.nikocraft.minecraftsmp.status.StatusManager;
import de.nikocraft.minecraftsmp.tablist.TablistManager;
import de.nikocraft.minecraftsmp.teleport.TeleportManager;
import de.nikocraft.minecraftsmp.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;

    private Config mainConfig;
    private Config homeConfig;

    private String serverIP;

    private TablistManager tablistManager;

    private TeleportManager teleportManager;

    private HomeManager homeManager;

    private ShulkerManager shulkerManager;

    private SpawnManager spawnManager;

    private StatusManager statusManager;

    private ConnectionListeners connectionListeners;
    private PlayerListeners playerListeners;
    private EntityListeners entityListeners;

    @Override
    public void onLoad() {

        instance = this;

        getLogger().info("Load configurations ...");
        String configPath = "./plugins/MinecraftSMP/";
        mainConfig = new Config(configPath, "MainConfig.yml");
        homeConfig = new Config(configPath, "HomeConfig.yml");

        getLogger().info("Plugin loaded.");

    }

    @Override
    public void onEnable() {

        if (mainConfig.getConfig().contains("ip")) serverIP = mainConfig.getConfig().getString("ip"); else serverIP = "Unknown";

        getLogger().info("Load tablist manager ...");
        tablistManager = new TablistManager();

        getLogger().info("Load teleport manager ...");
        teleportManager = new TeleportManager();

        getLogger().info("Load home manager ...");
        homeManager = new HomeManager();

        getLogger().info("Load shulker manager ...");
        shulkerManager = new ShulkerManager();

        getLogger().info("Load spawn manager ...");
        spawnManager = new SpawnManager();

        getLogger().info("Load status manager ...");
        statusManager = new StatusManager();

        getLogger().info("Register listeners ...");
        connectionListeners = new ConnectionListeners();
        playerListeners = new PlayerListeners();
        entityListeners = new EntityListeners();
        Bukkit.getPluginManager().registerEvents(connectionListeners, this);
        Bukkit.getPluginManager().registerEvents(playerListeners, this);
        Bukkit.getPluginManager().registerEvents(entityListeners, this);

        getLogger().info("Register commands ...");
        getCommand("teleportrequest").setExecutor(new TeleportCommand());
        getCommand("status").setExecutor(new StatusCommand());
        getCommand("displayip").setExecutor(new IpCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("shopping").setExecutor(new ShoppingCommand());
        getCommand("stronghold").setExecutor(new StrongholdCommand());
        getCommand("home").setExecutor(new HomeCommand());
        getCommand("sethome").setExecutor(new SetHomeCommand());

        getLogger().info("Plugin enabled.");

    }

    @Override
    public void onDisable() {

        getLogger().info("Stop teleport manager ...");
        teleportManager.stop();

        getLogger().info("Stop shulker manager ...");
        shulkerManager.stop();

        getLogger().info("Stop spawn manager ...");
        spawnManager.stop();

        mainConfig.getConfig().set("ip", getServerIP());

        getLogger().info("Save configurations ...");
        mainConfig.save();
        homeConfig.save();

        getLogger().info("Plugin disabled.");

    }

    public static Main getInstance() {
        return instance;
    }

    public static String getServerIP() {
        return instance.serverIP;
    }

    public static void setServerIP(String ip) {
        instance.serverIP = ip;
    }

    public static TablistManager getTablistManager() {
        return instance.tablistManager;
    }

    public static TeleportManager getTeleportManager() {
        return instance.teleportManager;
    }

    public static HomeManager getHomeManager() {
        return instance.homeManager;
    }

    public static ShulkerManager getShulkerManager() {
        return instance.shulkerManager;
    }

    public static SpawnManager getSpawnManager() {
        return instance.spawnManager;
    }

    public static StatusManager getStatusManager() {
        return instance.statusManager;
    }

    public static Config getMainConfig() {
        return instance.mainConfig;
    }

    public static Config getHomeConfig() {
        return instance.homeConfig;
    }

    public static ConnectionListeners getConnectionListeners() {
        return instance.connectionListeners;
    }

    public static PlayerListeners getPlayerListeners() {
        return instance.playerListeners;
    }

    public static EntityListeners getEntityListeners() {
        return instance.entityListeners;
    }

}
