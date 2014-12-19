package code.Young_Explicit.voting.events;

import org.bukkit.event.player.*;
import org.bukkit.*;
import code.Young_Explicit.voting.*;
import org.bukkit.entity.*;
import org.bukkit.plugin.*;
import org.bukkit.event.*;

public class PlayerEvents implements Listener
{
    @EventHandler
    public void onJoin(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getPlugin((Class)Main.class), (Runnable)new Runnable() {
            @Override
            public void run() {
                if (Main.getDatabaseManager().getPlayerVotes(p) <= 0) {
                    p.sendMessage("§b§m§n---------------------------------------------");
                    p.sendMessage("");
                    p.sendMessage("§dIt appears you haven't voted yet!");
                    p.sendMessage("§d/vote to receive a voting key, a kit, and $50!");
                    p.sendMessage("");
                    p.sendMessage("§b§m§n---------------------------------------------");
                }
            }
        }, 10L);
    }
}
