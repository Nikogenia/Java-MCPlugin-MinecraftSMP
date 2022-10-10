package de.nikocraft.minecraftsmp.home;

import de.nikocraft.minecraftsmp.Main;
import de.nikocraft.minecraftsmp.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HomeManager {

    private Config config;

    public HomeManager() {

        config = Main.getHomeConfig();

    }

    public Location getHome(Player player) {

        if (!config.getConfig().contains("locations." + player.getUniqueId())) return Bukkit.getWorld("world").getSpawnLocation();

        List<Double> elements = config.getConfig().getDoubleList("locations." + player.getUniqueId());

        Location location = player.getLocation();

        location.setX(elements.get(0));
        location.setY(elements.get(1));
        location.setZ(elements.get(2));
        location.setYaw(elements.get(3).floatValue());
        location.setPitch(elements.get(4).floatValue());
        if (elements.get(5).equals(0d)) location.setWorld(Bukkit.getWorld("world"));
        else if (elements.get(5).equals(1d)) location.setWorld(Bukkit.getWorld("world_nether"));
        else if (elements.get(5).equals(2d)) location.setWorld(Bukkit.getWorld("world_the_end"));

        return location;

    }

    public void setHome(Player player, Location location) {

        List<Double> elements = new ArrayList<>();

        elements.add(location.getX());
        elements.add(location.getY());
        elements.add(location.getZ());
        elements.add((double) location.getYaw());
        elements.add((double) location.getPitch());
        if (location.getWorld().getName().equals("world_nether")) elements.add(1d);
        else if (location.getWorld().getName().equals("world_the_end")) elements.add(2d);
        else elements.add(0d);

        config.getConfig().set("locations." + player.getUniqueId(), elements);

        config.save();

    }

}
