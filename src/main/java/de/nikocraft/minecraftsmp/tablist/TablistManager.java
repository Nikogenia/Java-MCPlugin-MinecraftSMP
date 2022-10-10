package de.nikocraft.minecraftsmp.tablist;

import de.nikocraft.minecraftsmp.Main;
import de.nikocraft.minecraftsmp.status.Status;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class TablistManager {

    public void setTablistHeaderFooter(Player player) {

        player.setPlayerListHeader(ChatColor.GRAY + "[ " + ChatColor.GOLD + ChatColor.BOLD + "CHALLENGE SERVER - CLASS ATTACK" + ChatColor.GRAY + " ]" +
                ChatColor.RED + ChatColor.BOLD + "\n \n" + "!WARNING - SERVER STILL IN DEVELOPMENT!" + "\n");

        player.setPlayerListFooter("\n" + ChatColor.GRAY + "[ " + ChatColor.DARK_PURPLE + "IP: " + ChatColor.ITALIC + Main.getServerIP() + ChatColor.GRAY + " ]" +
                "\n" + ChatColor.GRAY + "[ " + ChatColor.DARK_RED + "Hosted by Nikocraft" + ChatColor.GRAY + " ]" +
                "\n" + ChatColor.GRAY + "     [ " + ChatColor.GREEN + "Special thanks to Reikiboi, Chaosrenz, Monorugus, DerCoolste10 and Tillerheimer" + ChatColor.GRAY + " ]     ");

    }

    public void setAllPlayerTeams() {

        for (Player player : Bukkit.getOnlinePlayers()) setPlayerTeams(player);

    }

    public void setPlayerTeams(Player player) {

        Scoreboard scoreboard = player.getScoreboard();

        for (Status status : Status.values()) {

            Team team = scoreboard.getTeam(status.getId() + status.name());

            if (team == null) team = scoreboard.registerNewTeam(status.getId() + status.name());

            team.setPrefix(ChatColor.GRAY + "[" + status.getDisplay() + ChatColor.GRAY + "] ");

            team.setColor(ChatColor.WHITE);

            for (Player target : Bukkit.getOnlinePlayers()) {
                if (Main.getStatusManager().getStatus(target).equals(status)) team.addEntry(target.getName());
            }

        }

    }

}
