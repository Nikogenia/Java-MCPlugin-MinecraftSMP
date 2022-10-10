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


public class IpCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {

            sender.sendMessage(getIPPrefix() + ChatColor.RED + "Missing arguments!");

            return true;

        }

        Main.setServerIP(args[0]);

        for (Player player : Bukkit.getOnlinePlayers()) {
            Main.getTablistManager().setTablistHeaderFooter(player);
        }

        sender.sendMessage(getIPPrefix() + ChatColor.GREEN + "Successfully changed the server IP!");

        return true;

    }

    //Called, on typing the command in the chat
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        //Return empty list
        return new ArrayList<>();

    }

    public String getIPPrefix() {

        return ChatColor.GRAY + "[" + ChatColor.DARK_RED + ChatColor.BOLD + "IP" + ChatColor.RESET +
                ChatColor.GRAY + "] " + ChatColor.WHITE;

    }

}