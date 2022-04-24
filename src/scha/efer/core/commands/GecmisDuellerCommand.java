package scha.efer.core.commands;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import me.realized.duels.api.user.*;
import org.bukkit.*;
import java.util.concurrent.*;
import java.text.*;
import org.bukkit.inventory.*;
import java.util.*;
import org.bukkit.inventory.meta.*;
import scha.efer.core.CorePlugin;

public class GecmisDuellerCommand implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        if (!((Player)sender).getWorld().getName().equals("uzayarena")) {
            return true;
        }
        final Player player = (Player)sender;
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 36, "Geçmiş Düellolar");
        if (CorePlugin.duels.getUserManager().get(player) == null) {
            player.openInventory(inventory);
            return true;
        }
        if (CorePlugin.duels.getUserManager().get(player).getMatches() == null) {
            player.openInventory(inventory);
            return true;
        }
        final List<MatchInfo> matches = new ArrayList<MatchInfo>(CorePlugin.duels.getUserManager().get(player).getMatches());
        if (matches.isEmpty()) {
            player.openInventory(inventory);
            return true;
        }
        int currentPOS = 0;
        for (final MatchInfo matchInfo : matches) {
            if (currentPOS > 36) {
                break;
            }
            final ItemStack stack = new ItemStack(Material.PAPER);
            final ItemMeta meta = stack.getItemMeta();
            meta.setDisplayName("§e#" + ThreadLocalRandom.current().nextInt(1000000, 9999999) + "");
            final List<String> loreList = new ArrayList<String>();
            loreList.add("§a" + matchInfo.getWinner() + " §evs §c" + matchInfo.getLoser());
            loreList.add("§fKit: §6" + Objects.requireNonNull(matchInfo.getKit(), "Bulunmuyor"));
            final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            final Date date = new Date(matchInfo.getCreation());
            final SimpleDateFormat sdf2 = new SimpleDateFormat("mm:ss");
            final Date date2 = new Date(matchInfo.getDuration());
            loreList.add("§fOyun Zamanı: §6" + sdf.format(date));
            loreList.add("§fOyun Süresi: §6" + sdf2.format(date2));
            loreList.add("§fKazanan: §6" + matchInfo.getWinner());
            meta.setLore((List)loreList);
            stack.setItemMeta(meta);
            inventory.setItem(currentPOS, stack);
            ++currentPOS;
        }
        player.openInventory(inventory);
        return true;
    }
}

