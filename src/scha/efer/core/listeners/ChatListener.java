package scha.efer.core.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onAsyncPlayerChat(final AsyncPlayerChatEvent event) {
        this.isMessageContainsHack(event);
        this.isMessageContainsAdvert(event);
    }


    private void isMessageContainsAdvert(final AsyncPlayerChatEvent event) {
        if (event.isCancelled()) {
            return;
        }
        if (!event.getMessage().contains("jeystcraft") && !event.getMessage().contains("jeyst")) {
            return;
        }
        final Player player = event.getPlayer();
        event.setCancelled(true);
        player.chat("Ben AtomCraft'da reklam yapabilcek kadar salak birisiyim, bunu kabul ediyorum!");
    }

    private void isMessageContainsHack(final AsyncPlayerChatEvent event) {
        if (event.isCancelled()) {
            return;
        }
        if (!event.getMessage().contains("hile") && !event.getMessage().contains("hack")) {
            return;
        }
        final Player player = event.getPlayer();
        event.setCancelled(true);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lA&e&lC &8» &eEğer rakibin hile olduğunu düşünüyorsan lütfen &c/report &e komutunu kullan."));
    }

}
