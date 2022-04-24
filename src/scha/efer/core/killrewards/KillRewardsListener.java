package scha.efer.core.killrewards;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import scha.efer.core.utils.Keys;

public class KillRewardsListener implements Listener {
    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityDeath(final EntityDeathEvent event) {
        if (event.getEntity() == null || event.getEntity().getKiller() == null) {
            return;
        }
        if (event.getEntity().getKiller().getType().equals((Object)EntityType.PLAYER) && event.getEntity().getType().equals((Object) EntityType.PLAYER) && event.getEntity().getKiller().getUniqueId() != event.getEntity().getUniqueId()) {
            this.controlPoint1(event);
        }
    }

    private void controlPoint1(final EntityDeathEvent event) {
        final Player player = event.getEntity().getKiller();
        final Player entity = (Player)event.getEntity();
        final int givingBalance = this.findPermission(player);
        Keys.eco.depositPlayer((OfflinePlayer)player, (double)givingBalance);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Öldürme &8» &a" + entity.getName() + " &7adl\u0131 oyuncuyu \u00f6l\u00fcrd\u00fcn ve &e" + givingBalance + " Coin &7kazand\u0131n!"));
    }

    private int findPermission(final Player player) {
        if (player.hasPermission("atomvip.odul")) {
            return 90;
        }
        if (player.hasPermission("mvip.odul")) {
            return 80;
        }
        if (player.hasPermission("vip+.odul")) {
            return 70;
        }
        if (player.hasPermission("vip.odul")) {
            return 60;
        }
        if (player.hasPermission("atom.odul")) {
            return 50;
        }
        if (player.hasPermission("zumrut.odul")) {
            return 35;
        }
        if (player.hasPermission("elmas.odul")) {
            return 30;
        }
        if (player.hasPermission("altin.odul")) {
            return 25;
        }
        if (player.hasPermission("demir.odul")) {
            return 20;
        }
        if (player.hasPermission("komur.odul")) {
            return 15;
        }
        if (player.hasPermission("oyuncu.odul")) {
            return 10;
        }
        return 0;
    }
}