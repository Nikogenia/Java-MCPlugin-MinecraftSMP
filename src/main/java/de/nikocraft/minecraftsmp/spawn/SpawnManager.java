package de.nikocraft.minecraftsmp.spawn;

import de.nikocraft.minecraftsmp.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SpawnManager {

    private int schedulerID;

    private List<Player> flying;

    public SpawnManager() {

        flying = new ArrayList<>();

        run();

    }

    public void run() {

        schedulerID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

            //Run
            @Override
            public void run() {

                for (Player player : Bukkit.getOnlinePlayers()) {

                    if (!player.getGameMode().equals(GameMode.SURVIVAL)) continue;

                    player.setAllowFlight(isInSpawnRadius(player));

                    if (flying.contains(player) & !player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().isAir()) {

                        player.setAllowFlight(false);

                        player.setFlying(false);
                        player.setGliding(false);

                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
                            @Override
                            public void run() {
                                flying.remove(player);
                            }
                        }, 5);

                    }

                }

            }

        }, 3, 3);

    }

    public void stop() {

        Bukkit.getScheduler().cancelTask(schedulerID);

    }

    public static boolean isInSpawnRadius(Player player) {

        if (!player.getWorld().getName().equals("world")) return false;

        return player.getWorld().getSpawnLocation().distance(player.getLocation()) <= 40;

    }

    public List<Player> getFlying() { return flying; }

}
