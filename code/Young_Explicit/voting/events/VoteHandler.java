package code.Young_Explicit.voting.events;

import com.vexsoftware.votifier.model.*;
import org.bukkit.*;
import code.Young_Explicit.voting.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class VoteHandler implements Listener
{
    @EventHandler
    public void onVote(final VotifierEvent e) {
        if (Bukkit.getPlayer(e.getVote().getUsername()).isOnline()) {
            final Player p = Bukkit.getPlayer(e.getVote().getUsername());
            Bukkit.broadcastMessage("§b" + p.getName() + " has just voted! /vote for great rewards!");
            Main.getDatabaseManager().setPlayerVote(p, Main.getDatabaseManager().getPlayerVotes(p) + 1);
            if (Main.getDatabaseManager().getPlayerVotes(p) == 1) {
                p.sendMessage("§b§m§n---------------------------------------------");
                p.sendMessage("");
                p.sendMessage("§aThanks for voting! /votekit to use your kit!");
                p.sendMessage("§a1 more vote to receive a voting key!");
                p.sendMessage("");
                p.sendMessage("§b§m§n---------------------------------------------");
                Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco give " + p.getName() + " 50");
            }
            else if (Main.getDatabaseManager().getPlayerVotes(p) == 2) {
                p.sendMessage("§b§m§n---------------------------------------------");
                p.sendMessage("");
                p.sendMessage("§aThanks for voting!");
                p.sendMessage("§aYou've received a voting key! Use at spawn!");
                p.sendMessage("");
                p.sendMessage("§b§m§n---------------------------------------------");
                Main.getDatabaseManager().setPlayerKeys(p, Main.getDatabaseManager().getPlayerKeys(p) + 1);
                Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco give " + p.getName() + " 50");
            }
        }
    }
}
