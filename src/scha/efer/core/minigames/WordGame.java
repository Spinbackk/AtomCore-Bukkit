package scha.efer.core.minigames;

import org.bukkit.scheduler.*;
import java.util.concurrent.*;
import org.bukkit.*;
import scha.efer.core.minigames.status.WordGameStatus;

public class WordGame extends BukkitRunnable
{
    public void run() {
        final String[] chars = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "a", "b", "c", "d", "e", "f", "g", "h", "i", "k", "l", "m", "n", "o", "p", "r", "s", "t", "u", "v", "y", "z" };
        final StringBuilder word = new StringBuilder();
        for (int i = 0; i < 9; ++i) {
            word.append(chars[ThreadLocalRandom.current().nextInt(chars.length)]);
        }
        WordGameStatus.isGameEnabled = true;
        WordGameStatus.word = word.toString();
        Bukkit.broadcastMessage(" ");
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&6&l\u2731 &f&lKELIME OYUNU &6&l\u2731"));
        Bukkit.broadcastMessage(" ");
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7Sohbetteki &akodu yaz &7ve &c\u00f6d\u00fcl\u00fc &7kazan&c!"));
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&6Kod: &e" + WordGameStatus.word));
        Bukkit.broadcastMessage(" ");
    }
}

