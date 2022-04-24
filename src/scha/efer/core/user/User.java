package scha.efer.core.user;

import java.util.*;
import org.bukkit.entity.*;
import me.realized.duels.api.arena.*;
import me.realized.duels.api.match.*;
import me.realized.duels.api.kit.*;
import org.bukkit.*;
import scha.efer.core.CorePlugin;

public class User
{
    final UUID uuid;
    Long lastDamageByEntity;
    Long lastDamageByOther;
    
    public User(final UUID uuid) {
        this.lastDamageByEntity = 0L;
        this.lastDamageByOther = 0L;
        this.uuid = uuid;
    }
    
    public boolean checkCombo() {
        final Player player = this.getPlayer();
        if (!CorePlugin.duels.getArenaManager().isInMatch(player)) {
            return false;
        }
        if (CorePlugin.duels.getArenaManager().get(player) == null) {
            return false;
        }
        final Arena arena = CorePlugin.duels.getArenaManager().get(player);
        if (arena == null) {
            return false;
        }
        final Match match = arena.getMatch();
        if (match == null) {
            return false;
        }
        final Kit kit = match.getKit();
        if (kit == null) {
            return false;
        }
        final String kitName = match.getKit().getName();
        return kitName.toLowerCase().contains("combo");
    }
    
    public void setLastDamageByEntity(final Long lastDamageByEntity) {
        this.lastDamageByEntity = lastDamageByEntity;
    }
    
    public void setLastDamageByOther(final Long lastDamageByOther) {
        this.lastDamageByOther = lastDamageByOther;
    }
    
    public Long getLastDamageByEntity() {
        return this.lastDamageByEntity;
    }
    
    public Long getLastDamageByOther() {
        return this.lastDamageByOther;
    }
    
    private Player getPlayer() {
        return Bukkit.getPlayer(this.uuid);
    }
    
    public void register() {
        CorePlugin.USERS.put(this.uuid, this);
        final Player player = this.getPlayer();
        if (player != null) {
            player.setMaximumNoDamageTicks(0);
        }
    }
}