package scha.efer.core.arena.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import scha.efer.core.CorePlugin;
import scha.efer.core.arena.ArenaCleaner;

public class ArenaListener extends ArenaCleaner implements Listener {

    public ArenaListener(final CorePlugin instance) {
        super(instance);
    }

    @EventHandler
    public void onBlockPlace(final BlockPlaceEvent e) {
        if (this.plugin.getConfig().getStringList("arena.enabled-maps").contains(e.getBlock().getWorld().getName())) {
            this.blockDeleter(e.getBlock());
        }
    }
}