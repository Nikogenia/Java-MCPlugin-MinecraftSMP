package de.nikocraft.minecraftsmp.status;

import de.nikocraft.minecraftsmp.utils.StringUtils;
import org.bukkit.ChatColor;

public enum Status {

    ONLINE(0, ChatColor.GREEN + "ONLINE"),
    CHILLING(1, ChatColor.DARK_PURPLE + "CHILLING"),
    GRINDING(2, ChatColor.DARK_GRAY + "GRINDING"),
    BUILDING(3, ChatColor.YELLOW + "BUILDING"),
    REDSTONE(4, ChatColor.RED + "REDSTONE"),
    AFK(5, ChatColor.BLUE + "AFK"),
    TROLLING(6, "%cTROLLING"),
    RECORDING(7, ChatColor.DARK_RED + "RECORDING"),
    LIVE(8, ChatColor.AQUA + "LIVE");

    private int id;

    private String display;

    Status(int id, String display) {

        this.id = id;
        this.display = display;

    }

    public static Status fromID(int id) {

        for (Status status : values()) {
            if (status.getId() == id) return status;
        }

        return ONLINE;

    }

    public static Status fromName(String name) {

        for (Status status : values()) {
            if (status.name().toLowerCase().equals(name.toLowerCase())) return status;
        }

        return ONLINE;

    }

    public int getId() {
        return id;
    }

    public String getDisplay() {
        if (display.contains("%c")) return StringUtils.colorize(display.replace("%c", ""));
        return display;
    }

}
