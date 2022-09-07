package de.nikocraft.minecraftsmp.teleport;

import de.nikocraft.minecraftsmp.Main;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TeleportManager {

    private final List<TeleportRequest> requests;

    private int schedulerID;

    public TeleportManager() {

        requests = new ArrayList<>();

        run();

    }

    public void run() {

        schedulerID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

            //Run
            @Override
            public void run() {

                for (TeleportRequest request : requests) {

                    if (request.getTimeout() <= 0) {

                        request.getFrom().sendMessage(getTeleportPrefix() + ChatColor.DARK_PURPLE + "Teleport request to " + ChatColor.LIGHT_PURPLE + request.getTo().getName() +
                                ChatColor.DARK_PURPLE + " timed out.");
                        request.getTo().sendMessage(getTeleportPrefix() + ChatColor.DARK_PURPLE + "Teleport request from " + ChatColor.LIGHT_PURPLE + request.getFrom().getName() +
                                ChatColor.DARK_PURPLE + " timed out.");

                        requests.remove(request);

                        continue;

                    }

                    request.setTimeout(request.getTimeout() - 1);

                }

            }

        }, 20, 20);

    }

    public void stop() {

        Bukkit.getScheduler().cancelTask(schedulerID);

    }

    public boolean create(Player from, Player to) {

        for (TeleportRequest request : requests) {

            if (request.getFrom().equals(from) & request.getTo().equals(to)) return false;

        }

        ClickEvent cancelEvent = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/teleport_request cancel " + to.getName());
        ClickEvent acceptEvent = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/teleport_request accept " + from.getName());
        ClickEvent declineEvent = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/teleport_request decline " + from.getName());
        TextComponent cancelText = new TextComponent(ChatColor.GRAY + "[" + ChatColor.YELLOW + "CANCEL" + ChatColor.GRAY + "]");
        cancelText.setClickEvent(cancelEvent);
        TextComponent acceptText = new TextComponent(ChatColor.GRAY + "[" + ChatColor.GREEN + "ACCEPT" + ChatColor.GRAY + "]");
        acceptText.setClickEvent(acceptEvent);
        TextComponent declineText = new TextComponent(ChatColor.GRAY + "[" + ChatColor.RED + "DECLINE" + ChatColor.GRAY + "]");
        declineText.setClickEvent(declineEvent);
        TextComponent fromMessage = new TextComponent(getTeleportPrefix() + ChatColor.DARK_PURPLE + "You sent " + ChatColor.LIGHT_PURPLE + to.getName() + ChatColor.DARK_PURPLE + " a teleport request. ");
        fromMessage.addExtra(cancelText);
        TextComponent toMessage = new TextComponent(getTeleportPrefix() + ChatColor.LIGHT_PURPLE + from.getName() + ChatColor.DARK_PURPLE + " sent you a teleport request.\n");
        toMessage.addExtra(acceptText);
        toMessage.addExtra(" ");
        toMessage.addExtra(declineText);
        to.spigot().sendMessage(toMessage);
        from.spigot().sendMessage(fromMessage);

        requests.add(new TeleportRequest(from, to));

        return true;

    }

    public boolean cancel(Player from, Player to) {

        for (TeleportRequest request : requests) {

            if (request.getFrom().equals(from) & request.getTo().equals(to)) {

                request.getFrom().sendMessage(getTeleportPrefix() + ChatColor.DARK_PURPLE + "Cancelled teleport request to " + ChatColor.LIGHT_PURPLE + request.getTo().getName() +
                        ChatColor.DARK_PURPLE + ".");
                request.getTo().sendMessage(getTeleportPrefix() + ChatColor.DARK_PURPLE + "Cancelled teleport request from " + ChatColor.LIGHT_PURPLE + request.getFrom().getName() +
                        ChatColor.DARK_PURPLE + ".");

                requests.remove(request);

                return true;

            }

        }

        return false;

    }

    public boolean decline(Player from, Player to) {

        for (TeleportRequest request : requests) {

            if (request.getFrom().equals(from) & request.getTo().equals(to)) {

                request.getFrom().sendMessage(getTeleportPrefix() + ChatColor.DARK_PURPLE + "Declined teleport request to " + ChatColor.LIGHT_PURPLE + request.getTo().getName() +
                        ChatColor.DARK_PURPLE + ".");
                request.getTo().sendMessage(getTeleportPrefix() + ChatColor.DARK_PURPLE + "Declined teleport request from " + ChatColor.LIGHT_PURPLE + request.getFrom().getName() +
                        ChatColor.DARK_PURPLE + ".");

                requests.remove(request);

                return true;

            }

        }

        return false;

    }

    public boolean accept(Player from, Player to) {

        for (TeleportRequest request : requests) {

            if (request.getFrom().equals(from) & request.getTo().equals(to)) {

                request.getFrom().sendMessage(getTeleportPrefix() + ChatColor.DARK_PURPLE + "Accepted teleport request to " + ChatColor.LIGHT_PURPLE + request.getTo().getName() +
                        ChatColor.DARK_PURPLE + ".");
                request.getTo().sendMessage(getTeleportPrefix() + ChatColor.DARK_PURPLE + "Accepted teleport request from " + ChatColor.LIGHT_PURPLE + request.getFrom().getName() +
                        ChatColor.DARK_PURPLE + ".");

                from.teleport(to);

                requests.remove(request);

                return true;

            }

        }

        return false;

    }

    public TeleportRequest get(Player from, Player to) {

        for (TeleportRequest request : requests) {

            if (request.getFrom().equals(from) & request.getTo().equals(to)) return request;

        }

        return null;

    }

    public String getTeleportPrefix() {

        return ChatColor.GRAY + "[" + ChatColor.BLUE + ChatColor.BOLD + "Teleport" + ChatColor.RESET +
                ChatColor.GRAY + "] " + ChatColor.WHITE;

    }

}
