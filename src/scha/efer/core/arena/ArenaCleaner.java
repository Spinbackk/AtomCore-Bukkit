package scha.efer.core.arena;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.plugin.Plugin;
import scha.efer.core.CorePlugin;

public class ArenaCleaner {

    protected CorePlugin plugin;

    public ArenaCleaner(final CorePlugin instance) {
        this.plugin = instance;
    }

    public void blockDeleter(final Block block) {
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, (Runnable)new Runnable() {
            @Override
            public void run() {
                block.setData((byte)0);
                block.setTypeId(0);
            }
        }, this.plugin.getConfig().getInt("arena.temizleyici-saniye")* 20L);
    }
}
