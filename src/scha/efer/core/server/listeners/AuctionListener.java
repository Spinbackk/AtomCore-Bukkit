package scha.efer.core.server.listeners;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class AuctionListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerCommandPreprocessEvent(final PlayerCommandPreprocessEvent event) {
        final String[] lines = event.getMessage().split(" ");
        if (lines[0].equalsIgnoreCase("/ah") || lines[0].equalsIgnoreCase("/auctionhouse") || lines[0].equalsIgnoreCase("/auction") || lines[0].equalsIgnoreCase("/auc") || lines[0].equalsIgnoreCase("/auctionhouse:ah") || lines[0].equalsIgnoreCase("/auctionhouse:auctionhouse") || lines[0].equalsIgnoreCase("/auctionhouse:auction") || lines[0].equalsIgnoreCase("/auctionhouse:auc")) {
            final World world = event.getPlayer().getWorld();
            if (world.getName().equalsIgnoreCase("world")) {
                return;
            }
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[Tüccar] &cBu komutu sadece pandorada kullanabilirsin!"));
        }
    }

}
