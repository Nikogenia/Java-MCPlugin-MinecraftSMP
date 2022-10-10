package de.nikocraft.minecraftsmp.commands;

import de.nikocraft.minecraftsmp.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SetHomeCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {

            sender.sendMessage(Main.getTeleportManager().getTeleportPrefix() + ChatColor.RED + "You need to be a player to use this command!");

            return true;

        }

        Player player = (Player) sender;

        Main.getHomeManager().setHome(player, player.getLocation());

        player.sendMessage(Main.getTeleportManager().getTeleportPrefix() + ChatColor.GREEN + "Successfully set your home.");

        return true;

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        return new ArrayList<>();

    }

}
