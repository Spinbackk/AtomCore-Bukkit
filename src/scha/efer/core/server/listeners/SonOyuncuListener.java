package scha.efer.core.server.listeners;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import net.minecraft.server.v1_8_R3.PacketPlayInCustomPayload;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import scha.efer.core.CorePlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SonOyuncuListener implements Listener {

    private static List<Player> whitelist;

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onPlayerLogin(final PlayerLoginEvent e) {
        final String a = e.getHostname();
        if (!a.contains("###") || !a.contains("\",\"sig\":\"") || !a.contains("\",\"token\":\"sl\",\"suid\":\"") || !a.contains("\",\"launcherCHC\":\"") || !a.split("\"")[1].startsWith("minecraft")) {
            e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Bu oyuna ba\u011flanmak i\u00e7in SonOyuncu Launcher kullanmal\u0131s\u0131n: https://sonoyuncu.com.tr/indir/minecraft");
        }
    }

    @EventHandler
    public void onJoin(final PlayerJoinEvent e) {
        final ChannelDuplexHandler channelDuplexHandler = new ChannelDuplexHandler() {
            public void channelRead(final ChannelHandlerContext channelHandlerContext, final Object packet) throws Exception {
                if (packet instanceof PacketPlayInCustomPayload && ((PacketPlayInCustomPayload)packet).a().equals("Teyyapclntvars") && !SonOyuncuListener.whitelist.contains(e.getPlayer())) {
                    SonOyuncuListener.whitelist.add(e.getPlayer());
                }
                super.channelRead(channelHandlerContext, packet);
            }
        };
        final ChannelPipeline pipeline = ((CraftPlayer)e.getPlayer()).getHandle().playerConnection.networkManager.channel.pipeline();
        pipeline.addBefore("packet_handler", e.getPlayer().getName(), (ChannelHandler)channelDuplexHandler);
        Bukkit.getScheduler().runTaskLater(CorePlugin.getInstance(), () -> { //istance ekle
            if (!SonOyuncuListener.whitelist.contains(e.getPlayer()) || !e.getPlayer().getListeningPluginChannels().toString().contains("Teyyap")) {
                e.getPlayer().kickPlayer(new Random().nextBoolean() ? "Internal Exception: io.netty.handler.codec.DecoderException: java.io.IOException: Packet 0/33 (S21PacketChunkData) was larger than I expected, found 61710 bytes extra whilst reading packet 33" : "Internal Exception: io.netty.handler.codec.DecoderException: java.io.IOException: Packet 0/38 (S26PacketMapChunkBulk) was larger than I expected, found 555620 bytes extra whilst reading packet 38");
            }
        }, 300L);
    }

    @EventHandler
    public void onQuit(final PlayerQuitEvent e) {
        if (SonOyuncuListener.whitelist.contains(e.getPlayer())) {
            SonOyuncuListener.whitelist.remove(e.getPlayer());
        }
    }

    static {
        SonOyuncuListener.whitelist = new ArrayList<Player>();
    }
}
