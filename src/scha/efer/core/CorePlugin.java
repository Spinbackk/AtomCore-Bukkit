package scha.efer.core;

import lombok.Getter;
import me.realized.duels.api.Duels;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import scha.efer.core.arena.listener.ArenaListener;
import scha.efer.core.commands.AktifDuellerCommand;
import scha.efer.core.commands.CoreCommand;
import scha.efer.core.commands.GecmisDuellerCommand;
import scha.efer.core.duels.listeners.DuelPotionListener;
import scha.efer.core.duels.listeners.DuelQueueListener;
import scha.efer.core.killrewards.KillRewardsListener;
import scha.efer.core.listeners.ChatListener;
import scha.efer.core.listeners.CommandListener;
import scha.efer.core.listeners.PlayerListener;
import scha.efer.core.listeners.ServerListener;
import scha.efer.core.minigames.WordGame;
import scha.efer.core.minigames.listeners.MinigameListener;
import scha.efer.core.server.listeners.AuctionListener;
import scha.efer.core.server.listeners.SonOyuncuListener;
import scha.efer.core.user.User;
import scha.efer.core.utils.Keys;

import java.util.HashMap;
import java.util.UUID;

public class CorePlugin extends JavaPlugin implements Listener {

    public static Duels duels;
    public static final HashMap<UUID, User> USERS;

    @Getter
    private static CorePlugin instance;

    public void onEnable() {
        final PluginManager pluginM = this.getServer().getPluginManager();
        instance = this;

        registerUsers();
        registerDepends(pluginM);

        registerListeners(pluginM);

        loadConfigs();
        startTasks();
        getCommand("allah").setExecutor(new CoreCommand());
        /*final Object cmd = new CoreCommand();
        this.getCommand("atompickill14").setExecutor((CommandExecutor)cmd);
        this.getCommand("sil").setExecutor((CommandExecutor)cmd);
        this.getCommand("sohbet").setExecutor((CommandExecutor)cmd);
        this.getCommand("cekilisbaslat").setExecutor((CommandExecutor)cmd);
        this.getCommand("herkeseparaver").setExecutor((CommandExecutor)cmd);
        this.getCommand("sus").setExecutor((CommandExecutor)cmd);
        this.getCommand("tamiret-para").setExecutor((CommandExecutor)cmd);
        this.getCommand("tamiret-para-hersey").setExecutor((CommandExecutor)cmd);
        this.getCommand("setsat1").setExecutor((CommandExecutor)cmd);
        this.getCommand("setsat2").setExecutor((CommandExecutor)cmd);
        this.getCommand("setsat3").setExecutor((CommandExecutor)cmd);
        this.getCommand("setsat4").setExecutor((CommandExecutor)cmd);
        this.getCommand("setsat5").setExecutor((CommandExecutor)cmd);
        this.getCommand("setsat6").setExecutor((CommandExecutor)cmd);
        this.getCommand("setsat7").setExecutor((CommandExecutor)cmd);
        this.getCommand("setsat8").setExecutor((CommandExecutor)cmd);
        this.getCommand("kilicsat1").setExecutor((CommandExecutor)cmd);
        this.getCommand("kilicsat2").setExecutor((CommandExecutor)cmd);
        this.getCommand("oksat1").setExecutor((CommandExecutor)cmd);
        this.getCommand("oksat2").setExecutor((CommandExecutor)cmd);
        this.getCommand("kraken1s").setExecutor((CommandExecutor)cmd);
        this.getCommand("kraken2s").setExecutor((CommandExecutor)cmd);
        this.getCommand("tamiret").setExecutor((CommandExecutor)cmd);
        this.getCommand("evet").setExecutor((CommandExecutor)cmd);
        this.getCommand("hay\u0131r").setExecutor((CommandExecutor)cmd);
        this.getCommand("oyla").setExecutor((CommandExecutor)cmd);*/
    }

    public void onDisable() {

    }

    private void registerDepends(final PluginManager pluginM) {
        CorePlugin.duels = (Duels) pluginM.getPlugin("Duels");

        final Object rsp = this.getServer().getServicesManager().getRegistration((Class) Economy.class);
        Keys.eco = (Economy)((RegisteredServiceProvider)rsp).getProvider();
        final Object rsp2 = this.getServer().getServicesManager().getRegistration((Class)Permission.class);
        Keys.perm = (Permission)((RegisteredServiceProvider)rsp2).getProvider();
    }

    private void registerCommands() {
        final Object cmd = new CoreCommand();
        /*this.getCommand("aktifdueller").setExecutor(new AktifDuellerCommand());
        this.getCommand("gecmisdueller").setExecutor(new GecmisDuellerCommand());*/
        this.getCommand("atompickill14").setExecutor((CommandExecutor)cmd);
        this.getCommand("sil").setExecutor((CommandExecutor)cmd);
        this.getCommand("sohbet").setExecutor((CommandExecutor)cmd);
        this.getCommand("cekilisbaslat").setExecutor((CommandExecutor)cmd);
        this.getCommand("herkeseparaver").setExecutor((CommandExecutor)cmd);
        this.getCommand("sus").setExecutor((CommandExecutor)cmd);
        this.getCommand("tamiret-para").setExecutor((CommandExecutor)cmd);
        this.getCommand("tamiret-para-hersey").setExecutor((CommandExecutor)cmd);
        this.getCommand("setsat1").setExecutor((CommandExecutor)cmd);
        this.getCommand("setsat2").setExecutor((CommandExecutor)cmd);
        this.getCommand("setsat3").setExecutor((CommandExecutor)cmd);
        this.getCommand("setsat4").setExecutor((CommandExecutor)cmd);
        this.getCommand("setsat5").setExecutor((CommandExecutor)cmd);
        this.getCommand("setsat6").setExecutor((CommandExecutor)cmd);
        this.getCommand("setsat7").setExecutor((CommandExecutor)cmd);
        this.getCommand("setsat8").setExecutor((CommandExecutor)cmd);
        this.getCommand("kilicsat1").setExecutor((CommandExecutor)cmd);
        this.getCommand("kilicsat2").setExecutor((CommandExecutor)cmd);
        this.getCommand("oksat1").setExecutor((CommandExecutor)cmd);
        this.getCommand("oksat2").setExecutor((CommandExecutor)cmd);
        this.getCommand("kraken1s").setExecutor((CommandExecutor)cmd);
        this.getCommand("kraken2s").setExecutor((CommandExecutor)cmd);
        this.getCommand("tamiret").setExecutor((CommandExecutor)cmd);
        this.getCommand("evet").setExecutor((CommandExecutor)cmd);
        this.getCommand("hay\u0131r").setExecutor((CommandExecutor)cmd);
        this.getCommand("oyla").setExecutor((CommandExecutor)cmd);
    }

    private void registerListeners(final PluginManager pluginM) {
        pluginM.registerEvents(new ServerListener(), this);
        pluginM.registerEvents(new ArenaListener(this), this);
        pluginM.registerEvents(new DuelPotionListener(), this);
        pluginM.registerEvents(new DuelQueueListener(), this);
        pluginM.registerEvents(new ChatListener(), this);
        pluginM.registerEvents(new CommandListener(), this);
        pluginM.registerEvents(new PlayerListener(), this);
        pluginM.registerEvents(new MinigameListener(), this);
        pluginM.registerEvents(new SonOyuncuListener(), this);
        pluginM.registerEvents(new AuctionListener(), this);
        pluginM.registerEvents(new KillRewardsListener(), this);
    }

    private void loadConfigs() {
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
    }

    private void startTasks() {
        final Object wordGame = new WordGame();
        ((WordGame)wordGame).runTaskTimer(this, 48000L, 48000L);
    }

    private void registerUsers() {
        for (final Player player : Bukkit.getOnlinePlayers()) {
            final UUID uuid = player.getUniqueId();
            final User user = new User(uuid);
            user.register();
        }
    }

    private void unregisterUsers() {
        for (final UUID uuid : CorePlugin.USERS.keySet()) {
            final Player player = Bukkit.getPlayer(uuid);
            if (player != null) {
                player.setMaximumNoDamageTicks(20);
            }
        }
        CorePlugin.USERS.clear();
    }

    static {
        USERS = new HashMap<UUID, User>();
    }

}
