package scha.efer.core.commands;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.enchantments.*;
import org.bukkit.inventory.*;

import scha.efer.core.server.IsChatEnabled;
import scha.efer.core.utils.Keys;
import scha.efer.core.utils.Voting;

import java.util.*;

public class CoreCommand implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage("Bu komut sadece oyuncular icindir!");
            return false;
        }
        final Player player = (Player)sender;
        if (cmd.getName().equalsIgnoreCase("atompickill14")) {
            if (Keys.eco.getBalance((OfflinePlayer)player) < 8000.0) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&lPVP &8&l\u25ba &7Kit I almak i\u00e7in &c8.000 \u26c1 &7paran\u0131z olmal\u0131."));
                return false;
            }
            Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco take " + player.getName() + " 8000");
            Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "kit asdbuqraasd " + player.getName());
            return false;
        }
        else if (cmd.getName().equalsIgnoreCase("sil")) {
            if (!Keys.perm.has(player, "sil.sil")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lA&e&lC &8» &7Bilinmeyen bir komut kulland\u0131n, l\u00fctfen kontrol et."));
                return false;
            }
            for (int x = 0; x < 290; ++x) {
                Bukkit.broadcastMessage(" ");
            }
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&6&lA&e&lC &8» &cSohbet Temizlendi!"));
            return false;
        }
        else if (cmd.getName().equalsIgnoreCase("sohbet")) {
            if (!Keys.perm.has(player, "sohbet.kilit")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lA&e&lC &8» &7Bilinmeyen bir komut kulland\u0131n, l\u00fctfen kontrol et."));
                return false;
            }
            if (args.length < 1) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lA&e&lC &8» &7/Sohbet <&aa\u00e7, kapat&7>"));
                return false;
            }
            if (args[0].equalsIgnoreCase("ac") || args[0].equalsIgnoreCase("a\u00e7") || args[0].equalsIgnoreCase("on")) {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&6&lA&e&lC &8» &aSohbet a\u00e7\u0131ld\u0131 art\u0131k yaz\u0131 yazabilirsin!"));
                IsChatEnabled.isChatDisabled = false;
                return false;
            }
            if (args[0].equalsIgnoreCase("off") || args[0].equalsIgnoreCase("kapat") || args[0].equalsIgnoreCase("kapa")) {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&6&lA&e&lC &8» &cSohbet kapat\u0131ld\u0131 art\u0131k yaz\u0131 yazamazs\u0131n!"));
                IsChatEnabled.isChatDisabled = true;
                return false;
            }
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lA&e&lC &8» &7Sohbet <&aa\u00e7, kapat&7>"));
            return false;
        }
        else if (cmd.getName().equalsIgnoreCase("cekilisbaslat")) {
            if (!Keys.perm.has(player, "minecraft.turk")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lA&e&lC &8» &7Bilinmeyen bir komut kulland\u0131n, l\u00fctfen kontrol et."));
                return false;
            }
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&3\u00c7ekili\u015f &8» &7\u00c7ekili\u015fe son &610 saniye&7!"));
            ArrayList<Player>[] var1 = null;
            final Player[] var2 = new Player[1];
            final Player player2 = null;
            Bukkit.getScheduler().scheduleSyncDelayedTask(Keys.fuckScript, () -> {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&3\u00c7ekili\u015f &8» &7\u00c7ekili\u015fe son &65 saniye&7!"));
                Bukkit.getScheduler().scheduleSyncDelayedTask(Keys.fuckScript, () -> {
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&3\u00c7ekili\u015f &8» &7\u00c7ekili\u015fe son &63 saniye&7!"));
                    Bukkit.getScheduler().scheduleSyncDelayedTask(Keys.fuckScript, () -> {
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&3\u00c7ekili\u015f &8» &7\u00c7ekili\u015fe son &62 saniye&7!"));
                        Bukkit.getScheduler().scheduleSyncDelayedTask(Keys.fuckScript, () -> {
                            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&3\u00c7ekili\u015f &8» &7\u00c7ekili\u015fe son &61 saniye&7!"));
                            var1[0] = new ArrayList<Player>(Bukkit.getOnlinePlayers());
                            var2[0] = (Player) var1[0].get(new Random().nextInt(var1[0].size()));
                            Bukkit.getScheduler().scheduleSyncDelayedTask(Keys.fuckScript, () -> {
                                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', " &e&l\u00c7EK\u0130L\u0130\u015e SONUCU"));
                                Bukkit.broadcastMessage(" ");
                                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', " &aKazanan &8» &d" + player2.getName()));
                                Bukkit.broadcastMessage(" ");
                                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', " &6atomcraft.pw"));
                            }, 40L);
                        }, 20L);
                    }, 20L);
                }, 40L);
                return;
            }, 100L);
            return false;
        }
        else if (cmd.getName().equalsIgnoreCase("herkeseparaver")) {
            if (!Keys.perm.has(player, "herkese.paraver")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lA&e&lC &8» &7Bilinmeyen bir komut kulland\u0131n, l\u00fctfen kontrol et."));
                return false;
            }
            if (args.length < 1) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lA&e&lC &8» &7/herkeseparaver <miktar>"));
                return false;
            }
            if (!this.checkStringIsInteger(args[0])) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lA&e&lC &8» &7/herkeseparaver <miktar>"));
                return false;
            }
            final int amount = Integer.parseInt(args[0]);
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&6&lParaEventi&8&l] &6Yonetici " + player.getName() + "&7, herkese &6" + amount + " &7verdi!"));
            for (final Player var4 : Bukkit.getOnlinePlayers()) {
                Keys.eco.depositPlayer((OfflinePlayer)var4, (double)amount);
            }
            return false;
        }
        else if (cmd.getName().equalsIgnoreCase("sus")) {
            if (!Keys.perm.has(player, "mute.class")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lA&e&lC &8» &7Bilinmeyen bir komut kulland\u0131n, l\u00fctfen kontrol et."));
                return false;
            }
            if (args.length != 3) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lA&e&lC &8» &f/Sus &dIsim &cS\u00fcre &bSebep!"));
                return false;
            }
            Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "tempmute " + args[0] + " " + args[1]);
            Bukkit.broadcastMessage(" ");
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "           &e&lSUSTURMA"));
            Bukkit.broadcastMessage(" ");
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "   &a&l\u2713 &fYetkili &8» &a" + player.getName()));
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "   &c&l\u2718 &fSusturulan &8» &a" + args[0]));
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "   &a&l\u2713 &fSebebi &8» &a" + args[2]));
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "   &c&l\u2718 &fS\u00fcresi &8» &a" + args[1]));
            Bukkit.broadcastMessage(" ");
            return false;
        }
        else if (cmd.getName().equalsIgnoreCase("tamiret-para")) {
            if (!Keys.eco.has((OfflinePlayer)player, 50.0)) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&lPVP &8&l\u25ba &7E\u015fyay\u0131 tamir etmek i\u00e7in &c50 \u26c1 &7paran\u0131z olmal\u0131."));
                return false;
            }
            final ItemStack stack = player.getItemInHand();
            if (stack.getDurability() == 0) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&lPVP &8&l\u25ba &7Bu e\u015fya zaten hi\u00e7 hasar almam\u0131\u015f."));
                return false;
            }
            Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco take " + player.getName() + " 50");
            stack.setDurability((short)0);
            player.getInventory().setItemInHand(stack);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&lPVP &8&l\u25ba &7E\u015fya ba\u015far\u0131yla tamir edildi."));
            return false;
        }
        else if (cmd.getName().equalsIgnoreCase("tamiret-para-hersey")) {
            if (!Keys.eco.has((OfflinePlayer)player, 250.0)) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&lPVP &8&l\u25ba &7E\u015fyay\u0131 tamir etmek i\u00e7in &c250 \u26c1 &7paran\u0131z olmal\u0131."));
                return false;
            }
            final ItemStack stack = player.getItemInHand();
            if (stack.getDurability() == 0) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&lPVP &8&l\u25ba &7Bu e\u015fya zaten hi\u00e7 hasar almam\u0131\u015f."));
                return false;
            }
            Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco take " + player.getName() + " 250");
            player.performCommand("repairall");
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&lPVP &8&l\u25ba &7E\u015fyalar\u0131n ba\u015far\u0131yla tamir edildi."));
            return false;
        }
        else if (cmd.getName().equalsIgnoreCase("setsat1")) {
            ItemStack stack = null;
            int slot = -1;
            for (int x2 = 0; x2 < 36; ++x2) {
                if (player.getInventory().getItem(x2) != null) {
                    final ItemStack loopStack = player.getInventory().getItem(x2);
                    if (loopStack != null && loopStack.getType() != null && loopStack.getType().equals((Object)Material.DIAMOND_HELMET)) {
                        boolean var5 = false;
                        boolean var6 = false;
                        for (final Enchantment enchantment : loopStack.getEnchantments().keySet()) {
                            if (enchantment.equals((Object)Enchantment.PROTECTION_ENVIRONMENTAL) && loopStack.getEnchantments().get(enchantment) == 1) {
                                var6 = true;
                            }
                            if (enchantment.equals((Object)Enchantment.DURABILITY) && loopStack.getEnchantments().get(enchantment) == 1) {
                                var5 = true;
                            }
                            if (var6 && var5) {
                                stack = loopStack;
                                slot = x2;
                                break;
                            }
                        }
                    }
                }
            }
            if (stack != null) {
                player.getInventory().setItem(slot, new ItemStack(Material.AIR));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cKafal\u0131\u011f\u0131n\u0131 &a8 TL &cKar\u015f\u0131l\u0131\u011f\u0131nda Satt\u0131n!"));
                Keys.eco.depositPlayer((OfflinePlayer)player, 8.0);
                return false;
            }
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cKafal\u0131\u011f\u0131n bulunmuyor."));
            return false;
        }
        else if (cmd.getName().equalsIgnoreCase("setsat3")) {
            ItemStack stack = null;
            int slot = -1;
            for (int x2 = 0; x2 < 36; ++x2) {
                if (player.getInventory().getItem(x2) != null) {
                    final ItemStack loopStack = player.getInventory().getItem(x2);
                    if (loopStack != null && loopStack.getType() != null && loopStack.getType().equals((Object)Material.DIAMOND_LEGGINGS)) {
                        boolean var5 = false;
                        boolean var6 = false;
                        for (final Enchantment enchantment : loopStack.getEnchantments().keySet()) {
                            if (enchantment.equals((Object)Enchantment.PROTECTION_ENVIRONMENTAL) && loopStack.getEnchantments().get(enchantment) == 1) {
                                var6 = true;
                            }
                            if (enchantment.equals((Object)Enchantment.DURABILITY) && loopStack.getEnchantments().get(enchantment) == 1) {
                                var5 = true;
                            }
                            if (var6 && var5) {
                                stack = loopStack;
                                slot = x2;
                                break;
                            }
                        }
                    }
                }
            }
            if (stack != null) {
                player.getInventory().setItem(slot, new ItemStack(Material.AIR));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cPantolununu &a8 TL &cKar\u015f\u0131l\u0131\u011f\u0131nda Satt\u0131n!"));
                Keys.eco.depositPlayer((OfflinePlayer)player, 8.0);
                return false;
            }
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cPantolonun bulunmuyor."));
            return false;
        }
        else if (cmd.getName().equalsIgnoreCase("setsat2")) {
            ItemStack stack = null;
            int slot = -1;
            for (int x2 = 0; x2 < 36; ++x2) {
                if (player.getInventory().getItem(x2) != null) {
                    final ItemStack loopStack = player.getInventory().getItem(x2);
                    if (loopStack != null && loopStack.getType() != null && loopStack.getType().equals((Object)Material.DIAMOND_CHESTPLATE)) {
                        boolean var5 = false;
                        boolean var6 = false;
                        for (final Enchantment enchantment : loopStack.getEnchantments().keySet()) {
                            if (enchantment.equals((Object)Enchantment.PROTECTION_ENVIRONMENTAL) && loopStack.getEnchantments().get(enchantment) == 1) {
                                var6 = true;
                            }
                            if (enchantment.equals((Object)Enchantment.DURABILITY) && loopStack.getEnchantments().get(enchantment) == 1) {
                                var5 = true;
                            }
                            if (var6 && var5) {
                                stack = loopStack;
                                slot = x2;
                                break;
                            }
                        }
                    }
                }
            }
            if (stack != null) {
                player.getInventory().setItem(slot, new ItemStack(Material.AIR));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cG\u00f6\u011f\u00fcsl\u00fc\u011f\u00fcn\u00fc &a8 TL &cKar\u015f\u0131l\u0131\u011f\u0131nda Satt\u0131n!"));
                Keys.eco.depositPlayer((OfflinePlayer)player, 8.0);
                return false;
            }
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cG\u00f6\u011f\u00fcsl\u00fc\u011f\u00fcn bulunmuyor."));
            return false;
        }
        else if (cmd.getName().equalsIgnoreCase("setsat4")) {
            ItemStack stack = null;
            int slot = -1;
            for (int x2 = 0; x2 < 36; ++x2) {
                if (player.getInventory().getItem(x2) != null) {
                    final ItemStack loopStack = player.getInventory().getItem(x2);
                    if (loopStack != null && loopStack.getType() != null && loopStack.getType().equals((Object)Material.DIAMOND_BOOTS)) {
                        boolean var5 = false;
                        boolean var6 = false;
                        for (final Enchantment enchantment : loopStack.getEnchantments().keySet()) {
                            if (enchantment.equals((Object)Enchantment.PROTECTION_ENVIRONMENTAL) && loopStack.getEnchantments().get(enchantment) == 1) {
                                var6 = true;
                            }
                            if (enchantment.equals((Object)Enchantment.DURABILITY) && loopStack.getEnchantments().get(enchantment) == 1) {
                                var5 = true;
                            }
                            if (var6 && var5) {
                                stack = loopStack;
                                slot = x2;
                                break;
                            }
                        }
                    }
                }
            }
            if (stack != null) {
                player.getInventory().setItem(slot, new ItemStack(Material.AIR));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cBotunu &a8 TL &cKar\u015f\u0131l\u0131\u011f\u0131nda Satt\u0131n!"));
                Keys.eco.depositPlayer((OfflinePlayer)player, 8.0);
                return false;
            }
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cBotun bulunmuyor."));
            return false;
        }
        else if (cmd.getName().equalsIgnoreCase("setsat5")) {
            ItemStack stack = null;
            int slot = -1;
            for (int x2 = 0; x2 < 36; ++x2) {
                if (player.getInventory().getItem(x2) != null) {
                    final ItemStack loopStack = player.getInventory().getItem(x2);
                    if (loopStack != null && loopStack.getType() != null && loopStack.getType().equals((Object)Material.DIAMOND_HELMET)) {
                        boolean var5 = false;
                        boolean var6 = false;
                        for (final Enchantment enchantment : loopStack.getEnchantments().keySet()) {
                            if (enchantment.equals((Object)Enchantment.PROTECTION_ENVIRONMENTAL) && loopStack.getEnchantments().get(enchantment) == 2) {
                                var6 = true;
                            }
                            if (enchantment.equals((Object)Enchantment.DURABILITY) && loopStack.getEnchantments().get(enchantment) == 2) {
                                var5 = true;
                            }
                            if (var6 && var5) {
                                stack = loopStack;
                                slot = x2;
                                break;
                            }
                        }
                    }
                }
            }
            if (stack != null) {
                player.getInventory().setItem(slot, new ItemStack(Material.AIR));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cKafal\u0131\u011f\u0131n\u0131 &a12 TL &cKar\u015f\u0131l\u0131\u011f\u0131nda Satt\u0131n!"));
                Keys.eco.depositPlayer((OfflinePlayer)player, 12.0);
                return false;
            }
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cKafal\u0131\u011f\u0131n bulunmuyor."));
            return false;
        }
        else if (cmd.getName().equalsIgnoreCase("setsat7")) {
            ItemStack stack = null;
            int slot = -1;
            for (int x2 = 0; x2 < 36; ++x2) {
                if (player.getInventory().getItem(x2) != null) {
                    final ItemStack loopStack = player.getInventory().getItem(x2);
                    if (loopStack != null && loopStack.getType() != null && loopStack.getType().equals((Object)Material.DIAMOND_LEGGINGS)) {
                        boolean var5 = false;
                        boolean var6 = false;
                        for (final Enchantment enchantment : loopStack.getEnchantments().keySet()) {
                            if (enchantment.equals((Object)Enchantment.PROTECTION_ENVIRONMENTAL) && loopStack.getEnchantments().get(enchantment) == 2) {
                                var6 = true;
                            }
                            if (enchantment.equals((Object)Enchantment.DURABILITY) && loopStack.getEnchantments().get(enchantment) == 2) {
                                var5 = true;
                            }
                            if (var6 && var5) {
                                stack = loopStack;
                                slot = x2;
                                break;
                            }
                        }
                    }
                }
            }
            if (stack != null) {
                player.getInventory().setItem(slot, new ItemStack(Material.AIR));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cPantolununu &a12 TL &cKar\u015f\u0131l\u0131\u011f\u0131nda Satt\u0131n!"));
                Keys.eco.depositPlayer((OfflinePlayer)player, 12.0);
                return false;
            }
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cPantolonun bulunmuyor."));
            return false;
        }
        else if (cmd.getName().equalsIgnoreCase("setsat6")) {
            ItemStack stack = null;
            int slot = -1;
            for (int x2 = 0; x2 < 36; ++x2) {
                if (player.getInventory().getItem(x2) != null) {
                    final ItemStack loopStack = player.getInventory().getItem(x2);
                    if (loopStack != null && loopStack.getType() != null && loopStack.getType().equals((Object)Material.DIAMOND_CHESTPLATE)) {
                        boolean var5 = false;
                        boolean var6 = false;
                        for (final Enchantment enchantment : loopStack.getEnchantments().keySet()) {
                            if (enchantment.equals((Object)Enchantment.PROTECTION_ENVIRONMENTAL) && loopStack.getEnchantments().get(enchantment) == 2) {
                                var6 = true;
                            }
                            if (enchantment.equals((Object)Enchantment.DURABILITY) && loopStack.getEnchantments().get(enchantment) == 2) {
                                var5 = true;
                            }
                            if (var6 && var5) {
                                stack = loopStack;
                                slot = x2;
                                break;
                            }
                        }
                    }
                }
            }
            if (stack != null) {
                player.getInventory().setItem(slot, new ItemStack(Material.AIR));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cG\u00f6\u011f\u00fcsl\u00fc\u011f\u00fcn\u00fc &a12 TL &cKar\u015f\u0131l\u0131\u011f\u0131nda Satt\u0131n!"));
                Keys.eco.depositPlayer((OfflinePlayer)player, 12.0);
                return false;
            }
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cG\u00f6\u011f\u00fcsl\u00fc\u011f\u00fcn bulunmuyor."));
            return false;
        }
        else if (cmd.getName().equalsIgnoreCase("setsat8")) {
            ItemStack stack = null;
            int slot = -1;
            for (int x2 = 0; x2 < 36; ++x2) {
                if (player.getInventory().getItem(x2) != null) {
                    final ItemStack loopStack = player.getInventory().getItem(x2);
                    if (loopStack != null && loopStack.getType() != null && loopStack.getType().equals((Object)Material.DIAMOND_BOOTS)) {
                        boolean var5 = false;
                        boolean var6 = false;
                        for (final Enchantment enchantment : loopStack.getEnchantments().keySet()) {
                            if (enchantment.equals((Object)Enchantment.PROTECTION_ENVIRONMENTAL) && loopStack.getEnchantments().get(enchantment) == 2) {
                                var6 = true;
                            }
                            if (enchantment.equals((Object)Enchantment.DURABILITY) && loopStack.getEnchantments().get(enchantment) == 2) {
                                var5 = true;
                            }
                            if (var6 && var5) {
                                stack = loopStack;
                                slot = x2;
                                break;
                            }
                        }
                    }
                }
            }
            if (stack != null) {
                player.getInventory().setItem(slot, new ItemStack(Material.AIR));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cBotunu &a12 TL &cKar\u015f\u0131l\u0131\u011f\u0131nda Satt\u0131n!"));
                Keys.eco.depositPlayer((OfflinePlayer)player, 12.0);
                return false;
            }
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cBotun bulunmuyor."));
            return false;
        }
        else if (cmd.getName().equalsIgnoreCase("kilicsat1")) {
            ItemStack stack = null;
            int slot = -1;
            for (int x2 = 0; x2 < 36; ++x2) {
                if (player.getInventory().getItem(x2) != null) {
                    final ItemStack loopStack = player.getInventory().getItem(x2);
                    if (loopStack != null && loopStack.getType() != null && loopStack.getType().equals((Object)Material.DIAMOND_SWORD)) {
                        boolean var5 = false;
                        for (final Enchantment enchantment2 : loopStack.getEnchantments().keySet()) {
                            if (enchantment2.equals((Object)Enchantment.DAMAGE_ALL) && loopStack.getEnchantments().get(enchantment2) == 1) {
                                var5 = true;
                            }
                            if (var5) {
                                stack = loopStack;
                                slot = x2;
                                break;
                            }
                        }
                    }
                }
            }
            if (stack != null) {
                player.getInventory().setItem(slot, new ItemStack(Material.AIR));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cK\u0131l\u0131c\u0131n\u0131 &a4 TL &cKar\u015f\u0131l\u0131\u011f\u0131nda Satt\u0131n!"));
                Keys.eco.depositPlayer((OfflinePlayer)player, 4.0);
                return false;
            }
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cK\u0131l\u0131c\u0131n bulunmuyor."));
            return false;
        }
        else if (cmd.getName().equalsIgnoreCase("kilicsat2")) {
            ItemStack stack = null;
            int slot = -1;
            for (int x2 = 0; x2 < 36; ++x2) {
                if (player.getInventory().getItem(x2) != null) {
                    final ItemStack loopStack = player.getInventory().getItem(x2);
                    if (loopStack != null && loopStack.getType() != null && loopStack.getType().equals((Object)Material.DIAMOND_SWORD)) {
                        boolean var5 = false;
                        for (final Enchantment enchantment2 : loopStack.getEnchantments().keySet()) {
                            if (enchantment2.equals((Object)Enchantment.DAMAGE_ALL) && loopStack.getEnchantments().get(enchantment2) == 2) {
                                var5 = true;
                            }
                            if (var5) {
                                stack = loopStack;
                                slot = x2;
                                break;
                            }
                        }
                    }
                }
            }
            if (stack != null) {
                player.getInventory().setItem(slot, new ItemStack(Material.AIR));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cK\u0131l\u0131c\u0131n\u0131 &a8 TL &cKar\u015f\u0131l\u0131\u011f\u0131nda Satt\u0131n!"));
                Keys.eco.depositPlayer((OfflinePlayer)player, 8.0);
                return false;
            }
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cK\u0131l\u0131c\u0131n bulunmuyor."));
            return false;
        }
        else if (cmd.getName().equalsIgnoreCase("oksat1")) {
            ItemStack stack = null;
            int slot = -1;
            for (int x2 = 0; x2 < 36; ++x2) {
                if (player.getInventory().getItem(x2) != null) {
                    final ItemStack loopStack = player.getInventory().getItem(x2);
                    if (loopStack != null && loopStack.getType() != null && loopStack.getType().equals((Object)Material.BOW)) {
                        boolean var5 = false;
                        for (final Enchantment enchantment2 : loopStack.getEnchantments().keySet()) {
                            if (enchantment2.equals((Object)Enchantment.ARROW_DAMAGE) && loopStack.getEnchantments().get(enchantment2) == 1) {
                                var5 = true;
                            }
                            if (var5) {
                                stack = loopStack;
                                slot = x2;
                                break;
                            }
                        }
                    }
                }
            }
            if (stack != null) {
                player.getInventory().setItem(slot, new ItemStack(Material.AIR));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYay\u0131n\u0131 &a4 TL &cKar\u015f\u0131l\u0131\u011f\u0131nda Satt\u0131n!"));
                Keys.eco.depositPlayer((OfflinePlayer)player, 4.0);
                return false;
            }
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYay\u0131n bulunmuyor."));
            return false;
        }
        else if (cmd.getName().equalsIgnoreCase("oksat2")) {
            ItemStack stack = null;
            int slot = -1;
            for (int x2 = 0; x2 < 36; ++x2) {
                if (player.getInventory().getItem(x2) != null) {
                    final ItemStack loopStack = player.getInventory().getItem(x2);
                    if (loopStack != null && loopStack.getType() != null && loopStack.getType().equals((Object)Material.BOW)) {
                        boolean var5 = false;
                        boolean var6 = false;
                        for (final Enchantment enchantment : loopStack.getEnchantments().keySet()) {
                            if (enchantment.equals((Object)Enchantment.ARROW_DAMAGE) && loopStack.getEnchantments().get(enchantment) == 3) {
                                var5 = true;
                            }
                            if (enchantment.equals((Object)Enchantment.DURABILITY) && loopStack.getEnchantments().get(enchantment) == 2) {
                                var6 = true;
                            }
                            if (var5 && var6) {
                                stack = loopStack;
                                slot = x2;
                                break;
                            }
                        }
                    }
                }
            }
            if (stack != null) {
                player.getInventory().setItem(slot, new ItemStack(Material.AIR));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYay\u0131n\u0131 &a8 TL &cKar\u015f\u0131l\u0131\u011f\u0131nda Satt\u0131n!"));
                Keys.eco.depositPlayer((OfflinePlayer)player, 8.0);
                return false;
            }
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYay\u0131n bulunmuyor."));
            return false;
        }
        else if (cmd.getName().equalsIgnoreCase("kraken1s")) {
            if (!Keys.eco.has((OfflinePlayer)player, 150.0)) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&lPVP &8&l\u25ba &7Kit I almak i\u00e7in &c150 \u26c1 &7paran\u0131z olmal\u0131."));
                return false;
            }
            Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco take " + player.getName() + " 150");
            Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "kit kraken1 " + player.getName());
            return false;
        }
        else if (cmd.getName().equalsIgnoreCase("kraken2s")) {
            if (!Keys.eco.has((OfflinePlayer)player, 150.0)) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&lPVP &8&l\u25ba &7Kit I almak i\u00e7in &c300 \u26c1 &7paran\u0131z olmal\u0131."));
                return false;
            }
            Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco take " + player.getName() + " 300");
            Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "kit kraken2 " + player.getName());
            return false;
        }
        else if (cmd.getName().equalsIgnoreCase("tamiret")) {
            if (!Keys.perm.has(player, "repair.sk")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lA&e&lC &8» &7Bilinmeyen bir komut kulland\u0131n, l\u00fctfen kontrol et."));
                return false;
            }
            final ItemStack stack = player.getItemInHand();
            stack.setDurability((short)0);
            player.getInventory().setItemInHand(stack);
            ChatColor.translateAlternateColorCodes('&', "&6&lA&e&lC &8» &aE\u015fya ba\u015far\u0131yla tamir edildi");
            return false;
        }
        else {
            if (!cmd.getName().equalsIgnoreCase("oyla")) {
                if (cmd.getName().equalsIgnoreCase("evet")) {
                    if (!Voting.isVotingEnabled) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2[&aOylama&2] &c\u015euanda y\u00fcr\u00fcrl\u00fckte olan bir oylama yok!"));
                        return false;
                    }
                    if (Voting.isPlayerUsedVote.get(player.getUniqueId()) != null) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2[&aOylama&2] &cZaten oyunuzu kullanm\u0131\u015fs\u0131n\u0131z!"));
                        return false;
                    }
                    Voting.isPlayerUsedVote.put(player.getUniqueId(), true);
                    final Integer yes = Voting.yes;
                    ++Voting.yes;
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2[&aOylama&2] &3Oyunuzu ba\u015far\u0131yla kulland\u0131n\u0131z!"));
                }
                if (cmd.getName().equalsIgnoreCase("hay\u0131r")) {
                    if (!Voting.isVotingEnabled) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2[&aOylama&2] &c\u015euanda y\u00fcr\u00fcrl\u00fckte olan bir oylama yok!"));
                        return false;
                    }
                    if (Voting.isPlayerUsedVote.get(player.getUniqueId()) != null) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2[&aOylama&2] &cZaten oyunuzu kullanm\u0131\u015fs\u0131n\u0131z!"));
                        return false;
                    }
                    Voting.isPlayerUsedVote.put(player.getUniqueId(), true);
                    final Integer no = Voting.no;
                    ++Voting.no;
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2[&aOylama&2] &3Oyunuzu ba\u015far\u0131yla kulland\u0131n\u0131z!"));
                }
                return false;
            }
            if (!Keys.perm.has(player, "skript.op")) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lA&e&lC &8» &7Bilinmeyen bir komut kulland\u0131n, l\u00fctfen kontrol et."));
                return false;
            }
            if (args.length != 2) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b/oyla &3<&bSure&3> <&bKonu ama bo\u015fluk yerine _ kullan&3>"));
                return false;
            }
            if (!this.checkStringIsInteger(args[0])) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b/oyla &3<&bSure&3> <&bKonu ama bo\u015fluk yerine _ kullan&3>"));
                return false;
            }
            final int time = Integer.parseInt(args[0]);
            Voting.yes = 0;
            Voting.no = 0;
            if (!Voting.isPlayerUsedVote.isEmpty()) {
                for (final UUID uuid : Voting.isPlayerUsedVote.keySet()) {
                    Voting.isPlayerUsedVote.remove(uuid);
                }
            }
            Bukkit.broadcastMessage(" ");
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&2[&aOylama&2] " + sender.getName() + "&8 adl\u0131 oyuncu bir oylama ba\u015flatt\u0131"));
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&6[&eSoru&6]: " + args[1].replace("_", " ")));
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&2[&aOylama&2] &7Kabul etmek icin,&a/evet&7,reddetmek icin &c/hay\u0131r &7yaz\u0131n\u0131z."));
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&3[&bZaman&3] &7Oylamanin bitmesine kalan sure &2" + time + "&7 saniye"));
            Bukkit.broadcastMessage(" ");
            Voting.isVotingEnabled = true;
            final Iterator<UUID> iterator = null;
            UUID uuid2 = null;
            Bukkit.getScheduler().scheduleSyncDelayedTask(Keys.fuckScript, () -> {
                Voting.isVotingEnabled = false;
                Bukkit.broadcastMessage(" ");
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&2[&aOylama&2] &6Oylama tamamlandi"));
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&2[&aOylama&2] &aEvet&7 say\u0131s\u0131 &6" + Voting.yes));
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&2[&aOylama&2] &cHay\u0131r&7 say\u0131s\u0131 &6" + Voting.no));
                Bukkit.broadcastMessage(" ");
                if (!Voting.isPlayerUsedVote.isEmpty()) {
                    Voting.isPlayerUsedVote.keySet().iterator();
                    while (iterator.hasNext()) {
                        /*uuid2 = iterator.next();
                        Voting.isPlayerUsedVote.remove(uuid2);*/
                    }
                }
                return;
            }, time * 20L);
            return false;
        }
    }

    private Boolean checkStringIsInteger(final String string) {
        try {
            Integer.parseInt(string);
        }
        catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }
}