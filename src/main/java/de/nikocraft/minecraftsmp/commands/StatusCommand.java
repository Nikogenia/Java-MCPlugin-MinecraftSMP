package de.nikocraft.minecraftsmp.commands;

import de.nikocraft.minecraftsmp.Main;
import de.nikocraft.minecraftsmp.status.Status;
import de.nikocraft.minecraftsmp.status.StatusManager;
import de.nikocraft.minecraftsmp.teleport.TeleportManager;
import de.nikocraft.minecraftsmp.utils.CommandUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class StatusCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {

            sender.sendMessage(StatusManager.getStatusPrefix() + ChatColor.RED + "You need to be a player to use this command!");

            return true;

        }

        Player player = (Player) sender;

        if (args.length == 0) {

            player.sendMessage(StatusManager.getStatusPrefix() + ChatColor.RED + "Missing arguments!");

            return true;

        }

        Main.getStatusManager().setStatus(player, Status.fromName(args[0]));

        Main.getTablistManager().setAllPlayerTeams();

        player.sendMessage(StatusManager.getStatusPrefix() + ChatColor.GREEN + "Your status was set to " + Status.fromName(args[0]).getDisplay() + ChatColor.GREEN + "!");

        return true;

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        ArrayList<String> result = new ArrayList<>();

        if (!(sender instanceof Player)) return result;

        Player player = (Player) sender;

        for (Status status : Status.values()) {
            result.add(status.name().toLowerCase());
        }

        return CommandUtils.formatTapCompleterList(result, args);

    }

}
