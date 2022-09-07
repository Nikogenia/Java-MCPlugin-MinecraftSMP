package de.nikocraft.minecraftsmp.utils;

import org.bukkit.ChatColor;

import java.util.ArrayList;

public class CommandUtils {

    public static ArrayList<String> formatTapCompleterList(ArrayList<String> list, String[] args) {

        if (args.length == 0) return null;

        ArrayList<String> result = new ArrayList<>();

        String currentArg = args[args.length - 1].toLowerCase();

        for (String c : list) {

            String complete = c.toLowerCase();

            if (complete.startsWith(currentArg)) result.add(c);

        }

        return result;

    }

}
