package scha.efer.core.duels.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import scha.efer.core.CorePlugin;

public class DuelQueueListener implements Listener {

    @EventHandler
    public void onPlayerChangedWorld(final PlayerChangedWorldEvent event) {
        final Player player = event.getPlayer();
        if (CorePlugin.duels.getQueueManager().isInQueue(player)) {
            CorePlugin.duels.getQueueManager().removeFromQueue(player);
            player.sendMessage("§6§lA§e§lC §8» §cDünya değiştirdiğin için artık sırada değilsin.");
        }
    }
}
