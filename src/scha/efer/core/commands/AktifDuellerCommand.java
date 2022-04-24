package scha.efer.core.commands;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import me.realized.duels.api.arena.*;
import org.bukkit.*;
import java.util.concurrent.*;
import java.text.*;
import org.bukkit.inventory.*;
import java.util.*;
import me.realized.duels.api.match.*;
import org.bukkit.inventory.meta.*;
import scha.efer.core.CorePlugin;

public class AktifDuellerCommand implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        if (!((Player)sender).getWorld().getName().equals("uzayarena")) {
            return true;
        }
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 36, "Aktif Düellolar");
        int currentPOS = 0;
        for (final Arena arena : CorePlugin.duels.getArenaManager().getArenas()) {
            if (currentPOS > 36) {
                break;
            }
            final Match match = arena.getMatch();
            if (match == null) {
                continue;
            }
            final ItemStack stack = new ItemStack(Material.WOOL);
            final ItemMeta meta = stack.getItemMeta();
            meta.setDisplayName("§e#" + ThreadLocalRandom.current().nextInt(1000000, 9999999) + "");
            final List<String> loreList = new ArrayList<String>();
            loreList.add(" ");
            loreList.add("§a" + ((Player)match.getPlayers().toArray()[0]).getName() + " §evs §c" + ((Player)match.getPlayers().toArray()[1]).getName());
            final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            final Date date = new Date(match.getStart());
            loreList.add("§fBaşladığı Zaman: §6" + sdf.format(date));
            loreList.add("§fBahis: §6" + match.getBet());
            loreList.add("§fKit: §6" + match.getKit().getName());
            loreList.add("§fDurum: §aOyunda");
            meta.setLore((List)loreList);
            stack.setItemMeta(meta);
            inventory.setItem(currentPOS, stack);
            ++currentPOS;
        }
        ((Player)sender).openInventory(inventory);
        return true;
    }
}
