package scha.efer.core.listeners;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;
import scha.efer.core.CorePlugin;
import scha.efer.core.hits.HitType;
import scha.efer.core.user.User;

public class ServerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final User user = new User(event.getPlayer().getUniqueId());
        user.register();
    }

    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent event) {
        CorePlugin.USERS.remove(event.getPlayer().getUniqueId());
        event.getPlayer().setMaximumNoDamageTicks(20);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onInventoryClick(final InventoryClickEvent event) {
        if (event.getClickedInventory() == null) {
            return;
        }
        if (event.getClickedInventory().getTitle() == null) {
            return;
        }
        final String title = event.getClickedInventory().getTitle();
        if (title == null) {
            return;
        }
        if (title.toLowerCase().contains("Geçmiş Düellolar")) {
            event.setCancelled(true);
        }
        if (title.toLowerCase().contains("Aktif Düellolar")) {
            event.setCancelled(true);
            final ItemStack stack = event.getCurrentItem();
            if (stack == null) {
                return;
            }
            if (!stack.hasItemMeta()) {
                return;
            }
            final ItemMeta meta = stack.getItemMeta();
            final String specName = ((String)meta.getLore().toArray()[1]).split(" ")[0].substring(2);
            ((Player)event.getWhoClicked()).performCommand("spec " + specName);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onInventoryMoveItem(final InventoryMoveItemEvent event) {
        if (event.getInitiator().getTitle().toLowerCase().contains("Geçmiş Düellolar") || event.getInitiator().getTitle().toLowerCase().contains("Aktif Düellolar")) {
            event.setCancelled(true);
            return;
        }
        if (event.getDestination().getTitle().toLowerCase().contains("Geçmiş Düellolar") || event.getDestination().getTitle().toLowerCase().contains("Aktif Düellolar")) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onInventoryDrag(final InventoryDragEvent event) {
        final InventoryView view = event.getWhoClicked().getOpenInventory();
        if (view == null) {
            return;
        }
        final String title = view.getTitle();
        if (title == null) {
            return;
        }
        if (title.toLowerCase().contains("Geçmiş Düellolar") || title.toLowerCase().contains("Aktif Düellolar")) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onProjectileLaunch(final ProjectileLaunchEvent event) {
        final Entity entity = (Entity)event.getEntity();
        final EntityType entityType = event.getEntityType();
        if (entityType.equals((Object)EntityType.ARROW)) {
            entity.setVelocity(entity.getVelocity().multiply(1.131999999999));
            return;
        }
        if (entityType.equals((Object)EntityType.FISHING_HOOK)) {
            entity.setVelocity(entity.getVelocity().multiply(1.134677999999));
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onEntityDamageByEntity(final EntityDamageByEntityEvent event) {
        if (!event.getEntityType().equals((Object) EntityType.PLAYER)) {
            return;
        }
        final Player victim = (Player)event.getEntity();
        final Entity damager = event.getDamager();
        if (damager.getType().equals((Object)EntityType.FISHING_HOOK)) {
            damager.teleport(victim.getEyeLocation());
            return;
        }
        if (damager.getType().equals((Object)EntityType.ARROW)) {
            return;
        }
        final User user = CorePlugin.USERS.get(victim.getUniqueId());
        if (user.checkCombo()) {
            return;
        }
        final Long now = this.getNow();
        if (now - user.getLastDamageByEntity() < 398L) {
            event.setCancelled(true);
            return;
        }
        if (!damager.getType().equals((Object)EntityType.PLAYER)) {
            return;
        }
        final ItemStack stack = ((Player)damager).getItemInHand();
        if (stack == null) {
            return;
        }
        if (stack.containsEnchantment(Enchantment.KNOCKBACK)) {
            this.duzenleyici_handler((Entity)victim, HitType.SAVURMA);
        }
        else {
            this.duzenleyici_handler((Entity)victim, HitType.YUMRUK);
        }
        user.setLastDamageByEntity(now);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onEntityDamage(final EntityDamageEvent event) {
        if (!event.getEntityType().equals((Object)EntityType.PLAYER)) {
            return;
        }
        if (event.getCause().equals((Object)EntityDamageEvent.DamageCause.ENTITY_ATTACK)) {
            return;
        }
        final User user = CorePlugin.USERS.get(event.getEntity().getUniqueId());
        if (user.checkCombo()) {
            return;
        }
        final Long now = this.getNow();
        if (now - user.getLastDamageByOther() < 398L) {
            event.setCancelled(true);
            return;
        }
        user.setLastDamageByOther(now);
        this.duzenleyici_handler(event.getEntity(), HitType.DIGER);
    }

    private synchronized void duzenleyici_handler(final Entity entity, final HitType duzenleyici) {
        if (duzenleyici.equals(HitType.YUMRUK)) {
            this.duzenleyici_yumruk(entity);
        }
        else if (duzenleyici.equals(HitType.SAVURMA)) {
            this.duzenleyici_savurma(entity);
        }
        else if (duzenleyici.equals(HitType.DIGER)) {
            this.duzenleyici_diger(entity);
        }
    }

    private void duzenleyici_yumruk(final Entity entity) {
        final Vector vector = entity.getVelocity();
        vector.setY(vector.getY() - 0.1);
    }

    private void duzenleyici_savurma(final Entity entity) {
        final Vector vector = entity.getVelocity();
        vector.setY(-0.1996100016258789);
        entity.setVelocity(vector);
    }

    private void duzenleyici_diger(final Entity entity) {
        final Vector vector = entity.getVelocity();
        vector.setY(-0.1616100016258789);
        entity.setVelocity(vector);
    }

    private Long getNow() {
        return System.currentTimeMillis();
    }

}
