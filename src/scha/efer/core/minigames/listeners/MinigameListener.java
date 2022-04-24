package scha.efer.core.minigames.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import scha.efer.core.minigames.status.WordGameStatus;
import scha.efer.core.utils.Keys;

public class MinigameListener implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onAsyncPlayerChat(final AsyncPlayerChatEvent event) {
        this.wordGame(event);
    }

    private void wordGame(final AsyncPlayerChatEvent event) {
        if (!WordGameStatus.isGameEnabled) {
            return;
        }
        if (!event.getMessage().equalsIgnoreCase(WordGameStatus.word)) {
            return;
        }
        WordGameStatus.isGameEnabled = false;
        Keys.eco.depositPlayer((OfflinePlayer)event.getPlayer(), 500.0);
        Bukkit.broadcastMessage(" ");
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&3Kelime Oyunu &8» &d" + event.getPlayer().getName() + "&7kelime oyununu &ckazandı."));
        Bukkit.broadcastMessage(" ");
    }
}
