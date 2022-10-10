package de.nikocraft.minecraftsmp.utils;

import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.Random;

public class StringUtils {

    public static String colorize(String text) {

        String result = "";

        Random random = new Random();

        for (int i = 0; i < text.length(); i++) {
            result += Arrays.asList(ChatColor.AQUA, ChatColor.BLUE, ChatColor.RED, ChatColor.GREEN, ChatColor.LIGHT_PURPLE,
                    ChatColor.DARK_PURPLE, ChatColor.YELLOW, ChatColor.GOLD).get(random.nextInt(8)) + String.valueOf(text.charAt(i));
        }

        return result;

    }

}
