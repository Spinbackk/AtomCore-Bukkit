package scha.efer.core.listeners;

import java.util.*;
import org.bukkit.event.player.*;
import org.bukkit.event.*;
import net.md_5.bungee.api.chat.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.command.*;
import scha.efer.core.utils.Keys;

public class CommandListener implements Listener
{
    private final List<String> fixList;
    private final List<String> fixList2;

    public CommandListener() {
        this.fixList = Arrays.asList("//calc", "/er", "//calculate", "/pl", "/sudo", "/team setraly", "/team setrally", "/team raly", "/kit preview", "/team rally", null, "/etpa", "/essentials:tpa", "/team r", "/team sr", "/lonca chest", "//solve", "//eval", "/emsg", "/epm", "//evaluate", "/worldedit:/calc", "/worldedit:/calculate", "/worldedit:/eval", "/worldedit:/evaluatec", "/efly", "/worldedit:/solve", "/helpop", "//calc", "/ap", "/autore", "/autore help", "/team tp", "/team t", "/team teleport", "/xteam tp", "/team m", "/team message", "/xteam t", "/xteam teleport", "/xteam m", "/xtem message", "//eval", "//calculate", "//solve", "/bukkit:pl", "/bukkit:r", "/bukkit:w", "/bukkit:op", "stop", "/sk disable all", "bukkit:deop", "bukkit:w", "/bukkit:tell", "/essentials:tell", "/essentials:etell", "/bukkit:msg", "/w", "/?", "/sauc", "/bukkit:me", "/help", "/bukkit:help", "/announce say", "/bukkitcompat:tell", "/bukkit:kill", "/unregister", "/unreg", "/bukkit", "/chatcolor reset", "/bukkit:plugins", "/?", "/plugins", "/logout", "/tell", "//calc", "/bukkit:tell", "/!", "/minecraft:say", "minecraft:me", "/elist", "/eonline", "/essentials:list", "/essentials:online", "/essentials:elist", "/essentials:eonline", "/playerlist", "/etell", "/minecraft:tell", "/whisper", "/ewhisper", "/essentials:whisper", "/essentials:ewhisper", "/essentials:sethome", "/essentials:esethome", "/sethome", "/esethome", "/essentials:eback", "/back", "/eback");
        this.fixList2 = Arrays.asList("/pl", "/plugin", "/plugins", "/minecraft:me", "/bukkit:pl", "/bukkit:plugins", "/ver", "/bukkit:", "/?", "/bukkit:ver", "/bukkit:about", "/arenablockdeleter");
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerCommandPreprocess(final PlayerCommandPreprocessEvent event) {
        this.alternativeCommands(event);
        this.cancelCommandOnDuelWorld(event);
        this.checkerForCanceller(event);
        this.cancelCommand(event);
        this.cancelCommandAlt(event);
        this.commandAliases(event);
    }

    private void alternativeCommands(final PlayerCommandPreprocessEvent event) {
        if (event.isCancelled()) {
            return;
        }
        final Player player = event.getPlayer();
        if (event.getMessage().equalsIgnoreCase("/discord")) {
            event.setCancelled(true);
            final TextComponent message = new TextComponent(ChatColor.translateAlternateColorCodes('&', "                     &e&nT\u0131kla ve sunucuya kat\u0131l!&r "));
            message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/UHvrjrh"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', " "));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e                                &9&lDISCORD"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e          &7Alt b\u00f6l\u00fcmdeki linke t\u0131klayarak &ediscorda &7kat\u0131labilirsin!"));
            player.spigot().sendMessage((BaseComponent)message);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', " "));
        }
        if (event.getMessage().equalsIgnoreCase("/oyver") || event.getMessage().equalsIgnoreCase("/oy") || event.getMessage().equalsIgnoreCase("/vote")) {
            event.setCancelled(true);
            final TextComponent message = new TextComponent(ChatColor.translateAlternateColorCodes('&', "                     &e&nT\u0131kla ve siteye git!&r "));
            message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://minecraft-mp.com/server/273357/vote/"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', " "));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e                                &6&lVOTE"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e          &7Alt b\u00f6l\u00fcmdeki linke t\u0131klayarak &evote &7verebilirsin!"));
            player.spigot().sendMessage((BaseComponent)message);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', " "));
        }
    }

    private void cancelCommandOnDuelWorld(final PlayerCommandPreprocessEvent event) {
        if (event.isCancelled()) {
            return;
        }
        final Player player = event.getPlayer();
        if (player.getWorld().getName().equalsIgnoreCase("orrman")) {
            if (Keys.perm.has(player, "duello.bypass")) {
                return;
            }
            final String[] lines = event.getMessage().split(" ");
            if (lines[0].equalsIgnoreCase("msg") || lines[0].equalsIgnoreCase("report") || lines[0].equalsIgnoreCase("message") || lines[0].equalsIgnoreCase("reply")) {
                return;
            }
            event.setCancelled(true);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Hata &8» &cD\u00fcello esnas\u0131nda bu komutu kullanamazs\u0131n!"));
        }
    }

    private void cancelCommandAlt(final PlayerCommandPreprocessEvent event) {
        if (event.isCancelled()) {
            return;
        }
        final String message = event.getMessage();
        final String[] lines = message.split(" ");
        if (message.contains("/psand\u0131k") || message.contains("/psandik") || message.contains("/vault 50") || message.contains("/vault 51") || message.contains("/vault 52") || message.contains("/vault 53") || message.contains("/vault 54")) {
            if (!event.getPlayer().getWorld().getName().equalsIgnoreCase("world")) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Hata &8» &7Bu komut yaln\u0131zca &e&npandora&7 d\u00fcnyas\u0131nda kullan\u0131labilir."));
            }
            return;
        }
        if (message.contains("/asand\u0131k") || message.contains("/asandik") || message.contains("/vault 30") || message.contains("/vault 31") || message.contains("/vault 32") || message.contains("/vault 33") || message.contains("/vault 34")) {
            if (!event.getPlayer().getWorld().getName().equalsIgnoreCase("orman")) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Hata &8»&7 Bu komut yaln\u0131zca &e&nac\u0131kalan&7 d\u00fcnyas\u0131nda kullan\u0131labilir."));
            }
            return;
        }
        if (message.contains("/nsand\u0131k") || message.contains("/nsandik") || message.contains("/vault 40") || message.contains("/vault 41") || message.contains("/vault 42") || message.contains("/vault 43") || message.contains("/vault 44")) {
            if (!event.getPlayer().getWorld().getName().equalsIgnoreCase("nether")) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Hata &8»&7 Bu komut yaln\u0131zca &e&nac\u0131kalan&7 d\u00fcnyas\u0131nda kullan\u0131labilir."));
            }
            return;
        }
        if (lines[0].equalsIgnoreCase("/krakenmarket") || lines[0].equalsIgnoreCase("/krakenset") || lines[0].equalsIgnoreCase("/krakensat") || lines[0].equalsIgnoreCase("/krakenkit")) {
            if (!event.getPlayer().getWorld().getName().equalsIgnoreCase("kraken")) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Hata &8»&7 Bu komut yaln\u0131zca &e&nkraken&7 d\u00fcnyas\u0131nda kullan\u0131labilir."));
            }
            return;
        }
        if (lines[0].equalsIgnoreCase("/market")) {
            if (event.getPlayer().getWorld().getName().equalsIgnoreCase("uzayarena") || event.getPlayer().getWorld().getName().equalsIgnoreCase("kraken")) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Hata &8» &7Bu komutu bu d\u00fcnyada kulanamazs\u0131n."));
            }
            return;
        }
        if ((lines[0].equalsIgnoreCase("/duels:spec") || lines[0].equalsIgnoreCase("/spec") || lines[0].equalsIgnoreCase("/duel") || lines[0].equalsIgnoreCase("/duels:spectate") || lines[0].equalsIgnoreCase("/spectate")) && !event.getPlayer().getWorld().getName().equalsIgnoreCase("uzayarena")) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Hata &8» &7Bu Komutu yaln\u0131zca &a&nSpawn&7 d\u00fcnyas\u0131nda kullanabilirsin."));
        }
    }

    private void cancelCommand(final PlayerCommandPreprocessEvent event) {
        if (event.isCancelled()) {
            return;
        }
        final String message = event.getMessage();
        if ((message.toLowerCase().contains("/fix all") || message.toLowerCase().contains("/repair") || message.toLowerCase().contains("/repair all") || message.toLowerCase().contains("/fix") || message.toLowerCase().contains("/erepair") || message.toLowerCase().contains("/erepair all") || message.toLowerCase().contains("/efix all") || message.toLowerCase().contains("/erepair hand") || message.toLowerCase().contains("/repair hand") || message.toLowerCase().contains("/tamiret")) && event.getPlayer().getWorld().getName().equalsIgnoreCase("duello")) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Hata &8» &7Bu komutu &bD\u00fcello'da &7kullanamazs\u0131n."));
        }
    }

    private void commandAliases(final PlayerCommandPreprocessEvent event) {
        if (event.isCancelled()) {
            return;
        }
        final Object message = event.getMessage();
        final Object player = event.getPlayer();
        if (((String)message).equalsIgnoreCase("/randomduel") || ((String)message).equalsIgnoreCase("duelgir") || ((String)message).equalsIgnoreCase("rastgeleduel")) {
            event.setCancelled(true);
            ((Player)player).performCommand("queue");
            return;
        }
        if (((String)message).equalsIgnoreCase("/g\u00fccal")) {
            event.setCancelled(true);
            if (Keys.perm.has((Player)player, "troll.komut")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "/effect " + ((Player)player).getName() + " 5 900 3");
                ((Player)player).sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Troll &8» &4G\u00fc\u00e7 &7\u00f6zelli\u011fi &aaktif &7edildi."));
            }
            return;
        }
        if (((String)message).equalsIgnoreCase("hizal")) {
            event.setCancelled(true);
            if (Keys.perm.has((Player)player, "troll.komut")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "/effect " + ((Player)player).getName() + " speed 900 2");
                ((Player)player).sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Troll &8» &bH\u0131z &7\u00f6zelli\u011fi &aaktif &7edildi"));
            }
        }
    }

    private void checkerForCanceller(final PlayerCommandPreprocessEvent event) {
        if (event.isCancelled()) {
            return;
        }
        final String message = event.getMessage();
        final String[] lines = message.split(" ");
        if (this.fixList2.contains(lines[0].toLowerCase())) {
            this.directFix(event);
            return;
        }
        if (this.fixList.contains(lines[0].toLowerCase())) {
            this.fix(event);
        }
    }

    private void fix(final PlayerCommandPreprocessEvent event) {
        if (event.isCancelled()) {
            return;
        }
        final Player player = event.getPlayer();
        if (!player.hasPermission("core.command.bypass")) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Bilgilendirme &8» &7bu komut &cyasakl\u0131 &7komutlar i\u00e7ersinde."));
        }
    }

    private void directFix(final PlayerCommandPreprocessEvent event) {
        if (event.isCancelled()) {
            return;
        }
        event.setCancelled(true);
        event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Bilgilendirme &8» &7bu komut &cyasakl\u0131 &7komutlar i\u00e7ersinde."));
    }
}