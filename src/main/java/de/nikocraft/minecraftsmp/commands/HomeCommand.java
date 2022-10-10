package de.nikocraft.minecraftsmp.commands;

import de.nikocraft.minecraftsmp.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HomeCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {

            sender.sendMessage(Main.getTeleportManager().getTeleportPrefix() + ChatColor.RED + "You need to be a player to use this command!");

            return true;

        }

        Player player = (Player) sender;

        if (Main.getTeleportManager().getLastPlayerDamage().get(player) > 0) {

            sender.sendMessage(Main.getTeleportManager().getTeleportPrefix() + ChatColor.RED + "You are in combat! Please wait " +
                    Main.getTeleportManager().getLastPlayerDamage().get(player) + " seconds ...");

            return true;

        }

        player.teleport(Main.getHomeManager().getHome(player));

        player.sendMessage(Main.getTeleportManager().getTeleportPrefix() + ChatColor.GREEN + "You was teleported to your home.");

        return true;

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        return new ArrayList<>();

    }

}
