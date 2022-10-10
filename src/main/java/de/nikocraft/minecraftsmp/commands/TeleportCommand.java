package de.nikocraft.minecraftsmp.commands;

import de.nikocraft.minecraftsmp.Main;
import de.nikocraft.minecraftsmp.teleport.TeleportManager;
import de.nikocraft.minecraftsmp.utils.CommandUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TeleportCommand implements TabCompleter, CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {

            sender.sendMessage(TeleportManager.getTeleportPrefix() + ChatColor.RED + "You need to be a player to use this command!");

            return true;

        }

        Player player = (Player) sender;

        if (args.length == 0) {

            player.sendMessage(TeleportManager.getTeleportPrefix() + ChatColor.RED + "Missing arguments!");

            return true;

        }

        switch (args[0]) {

            case "request":

                if (args.length < 2) {

                    sender.sendMessage(TeleportManager.getTeleportPrefix() + ChatColor.RED + "Missing second argument!");

                    return true;

                }

                if (Bukkit.getPlayer(args[1]) == null) {

                    sender.sendMessage(TeleportManager.getTeleportPrefix() + ChatColor.RED + "Player not found!");

                    return true;

                }

                if (Bukkit.getPlayer(args[1]).equals(player)) {

                    sender.sendMessage(TeleportManager.getTeleportPrefix() + ChatColor.RED + "You cannot request yourself!");

                    return true;

                }

                if (!Main.getTeleportManager().create(player, Bukkit.getPlayer(args[1]))) {

                    sender.sendMessage(TeleportManager.getTeleportPrefix() + ChatColor.RED + "This request already exists!");

                    return true;

                }

                return true;

            case "cancel":

                if (args.length < 2) {

                    for (Player p : Bukkit.getOnlinePlayers()) {
                        Main.getTeleportManager().cancel(player, p);
                    }

                    return true;

                }

                if (Bukkit.getPlayer(args[1]) == null) {

                    sender.sendMessage(TeleportManager.getTeleportPrefix() + ChatColor.RED + "Player not found!");

                    return true;

                }

                if (!Main.getTeleportManager().cancel(player, Bukkit.getPlayer(args[1]))) {

                    sender.sendMessage(TeleportManager.getTeleportPrefix() + ChatColor.RED + "There is no request to this player!");

                    return true;

                }

                return true;

            case "accept":

                if (args.length < 2) {

                    for (Player p : Bukkit.getOnlinePlayers()) {
                        Main.getTeleportManager().accept(p, player);
                    }

                    return true;

                }

                if (Bukkit.getPlayer(args[1]) == null) {

                    sender.sendMessage(TeleportManager.getTeleportPrefix() + ChatColor.RED + "Player not found!");

                    return true;

                }

                if (!Main.getTeleportManager().accept(Bukkit.getPlayer(args[1]), player)) {

                    sender.sendMessage(TeleportManager.getTeleportPrefix() + ChatColor.RED + "There is no request from this player!");

                    return true;

                }

                return true;

            case "decline":

                if (args.length < 2) {

                    for (Player p : Bukkit.getOnlinePlayers()) {
                        Main.getTeleportManager().decline(p, player);
                    }

                    return true;

                }

                if (Bukkit.getPlayer(args[1]) == null) {

                    sender.sendMessage(TeleportManager.getTeleportPrefix() + ChatColor.RED + "Player not found!");

                    return true;

                }

                if (!Main.getTeleportManager().decline(Bukkit.getPlayer(args[1]), player)) {

                    sender.sendMessage(TeleportManager.getTeleportPrefix() + ChatColor.RED + "There is no request from this player!");

                    return true;

                }

                return true;

            default:

                player.sendMessage(TeleportManager.getTeleportPrefix() + ChatColor.RED + "Invalid first argument!");

                return true;

        }

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        ArrayList<String> result = new ArrayList<>();

        if (!(sender instanceof Player)) return result;

        Player player = (Player) sender;

        switch (args.length) {

            case 1:

                result.add("request");
                result.add("accept");
                result.add("decline");
                result.add("cancel");
                result.add("help");

                break;

            case 2:

                switch (args[0]) {

                    case "request":

                        for (Player p : Bukkit.getOnlinePlayers()) {

                            if (!player.equals(p)) {
                                if (Main.getTeleportManager().get(player, p) == null) result.add(p.getName());
                            }

                        }

                        break;

                    case "accept":
                    case "decline":

                        for (Player p : Bukkit.getOnlinePlayers()) {

                            if (Main.getTeleportManager().get(p, player) != null) result.add(p.getName());

                        }

                        break;

                    case "cancel":

                        for (Player p : Bukkit.getOnlinePlayers()) {

                            if (Main.getTeleportManager().get(player, p) != null) result.add(p.getName());

                        }

                        break;

                }

                break;

        }

        return CommandUtils.formatTapCompleterList(result, args);

    }

}
