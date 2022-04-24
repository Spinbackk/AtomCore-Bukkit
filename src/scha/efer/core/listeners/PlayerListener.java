package scha.efer.core.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import scha.efer.core.server.utils.BlockCheck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class PlayerListener implements Listener {

    final List<String> worldNameList;

    public PlayerListener() {
        this.worldNameList = Arrays.asList("takimlipandora", "world", "orman");
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onEvent(final PlayerTeleportEvent event) {
        if (event.getCause().equals((Object) PlayerTeleportEvent.TeleportCause.ENDER_PEARL)) {
            event.getTo().setX(Math.floor(event.getTo().getX()) + 0.5);
            event.getTo().setY(Math.floor(event.getTo().getY()) + 0.5);
            event.getTo().setZ(Math.floor(event.getTo().getZ()) + 0.5);
            final BlockCheck landing = new BlockCheck(event.getTo().getBlock());
            boolean cancelTeleport = false;
            if (landing.isSafe) {
                event.getTo().setY(Math.floor(event.getTo().getY()) + landing.adjustY);
            }
            else {
                cancelTeleport = true;
                final double xMin = Math.min(event.getFrom().getX(), event.getTo().getX());
                final double xMax = Math.max(event.getFrom().getX(), event.getTo().getX());
                final double yMin = Math.min(event.getFrom().getY(), event.getTo().getY());
                final double yMax = Math.max(event.getFrom().getY(), event.getTo().getY());
                final double zMin = Math.min(event.getFrom().getZ(), event.getTo().getZ());
                final double zMax = Math.max(event.getFrom().getZ(), event.getTo().getZ());
                final List<Location> locations = new ArrayList<Location>();
                for (double x = xMin; x < xMax; ++x) {
                    for (double y = yMin; y < yMax; ++y) {
                        for (double z = zMin; z < zMax; ++z) {
                            locations.add(new Location(event.getTo().getWorld(), Math.floor(x) + 0.5, Math.floor(y) + 0.5, Math.floor(z) + 0.5));
                        }
                    }
                }
                locations.sort(Comparator.comparing(location -> event.getTo().distanceSquared(location)));
                for (final Location location2 : locations) {
                    final BlockCheck blockCheck = new BlockCheck(location2.getBlock());
                    if (blockCheck.isSafe) {
                        location2.setYaw(event.getTo().getYaw());
                        location2.setPitch(event.getTo().getPitch());
                        location2.setY(Math.floor(location2.getY()) + blockCheck.adjustY);
                        event.setTo(location2);
                        cancelTeleport = false;
                        break;
                    }
                }
            }
            if (cancelTeleport || event.getTo().equals((Object)event.getFrom())) {
                event.setCancelled(true);
                event.getPlayer().getInventory().addItem(new ItemStack[] { new ItemStack(Material.ENDER_PEARL, 1) });
                event.getPlayer().updateInventory();
            }
        }
    }

    @EventHandler
    public void onBlockBreak(final BlockBreakEvent event) {
        if (event.isCancelled()) {
            return;
        }
        if (event.getPlayer().isOp()) {
            return;
        }
        if (!this.worldNameList.contains(event.getBlock().getWorld().getName().toLowerCase())) {
            return;
        }
        if (event.getBlock().getType().equals((Object)Material.WEB)) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final World world = Bukkit.getWorld("uzayarena");
        if (event.getPlayer().isDead()) {
            event.getPlayer().spigot().respawn();
        }
        event.getPlayer().teleport(world.getSpawnLocation());
    }

    @EventHandler
    public void prepareItemCraft(final PrepareItemCraftEvent event) {
        if (event.getRecipe().getResult() != null) {
            final ItemStack stack = event.getRecipe().getResult();
            if (stack != null && stack.getType().equals((Object) Material.WOOL)) {
                final ItemStack air = new ItemStack(Material.AIR);
                event.getInventory().setResult(air);
            }
        }
    }

    @EventHandler
    public void onFoodLevelChange(final FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onWeatherChange(final WeatherChangeEvent event) {
        event.setCancelled(true);
    }

}
