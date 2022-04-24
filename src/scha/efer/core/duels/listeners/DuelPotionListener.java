package scha.efer.core.duels.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class DuelPotionListener implements Listener {

    @EventHandler
    public void onProjectileLaunch(final ProjectileLaunchEvent event) {
        if (event.getEntity().getShooter() == null) {
            return;
        }
        if (!(event.getEntity().getShooter() instanceof Player)) {
            return;
        }
        final Player shooter = (Player)event.getEntity().getShooter();
        if (!shooter.getAllowFlight()) {
            return;
        }
        shooter.setAllowFlight(false);
    }

}
