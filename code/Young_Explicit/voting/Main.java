package code.Young_Explicit.voting;

import org.bukkit.plugin.java.*;
import org.bukkit.event.*;
import org.bukkit.plugin.*;
import code.Young_Explicit.voting.events.*;
import org.bukkit.entity.*;
import org.bukkit.command.*;
import me.spoony.JSONChatLib.*;
import me.spoony.chatlib.*;
import code.Young_Explicit.voting.utils.*;
import org.bukkit.inventory.*;
import org.bukkit.*;
import org.bukkit.enchantments.*;

public class Main extends JavaPlugin
{
    private static DatabaseManager dbmanager;
    
    public void onLoad() {
        Main.dbmanager = new DatabaseManager();
    }
    
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents((Listener)new PlayerEvents(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new VoteManager(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new VoteHandler(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new NametagListener(), (Plugin)this);
        Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)this, (Runnable)new Runnable() {
            @Override
            public void run() {
                Player[] onlinePlayers;
                for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
                    final Player all = onlinePlayers[i];
                    if (!all.getName().equalsIgnoreCase("Dawhn") && !all.getName().equalsIgnoreCase("Young_Explicit") && Main.dbmanager.getPlayerVotes(all) == 0) {
                        all.sendMessage("§b§m§n---------------------------------------------");
                        all.sendMessage("");
                        all.sendMessage("§dIt appears you haven't voted yet!");
                        all.sendMessage("§d/vote to receive a voting key, a kit, and $50!");
                        all.sendMessage("");
                        all.sendMessage("§b§m§n---------------------------------------------");
                    }
                }
            }
        }, 0L, 3000L);
    }
    
    public void onDisable() {
        getDatabaseManager().jc.save();
    }
    
    public static DatabaseManager getDatabaseManager() {
        return Main.dbmanager;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String cmdLabel, final String[] args) {
        final Player p = (Player)sender;
        if (args.length == 0) {
            if (cmd.getName().equalsIgnoreCase("vote")) {
                p.sendMessage("§b§m§n---------------------------------------------");
                p.sendMessage("");
                MessageSender.sendMessage(p, new ChatPart[] { new ChatPart("Voting Link 1", JSONChatColor.GREEN, new JSONChatFormat[] { JSONChatFormat.BOLD }).setHoverEvent(JSONChatHoverEventType.SHOW_TEXT, "§bClick here to vote").setClickEvent(JSONChatClickEventType.OPEN_URL, "http://minecraftservers.org/vote/182573") });
                MessageSender.sendMessage(p, new ChatPart[] { new ChatPart("Voting Link 2", JSONChatColor.GREEN, new JSONChatFormat[] { JSONChatFormat.BOLD }).setHoverEvent(JSONChatHoverEventType.SHOW_TEXT, "§bClick here to vote").setClickEvent(JSONChatClickEventType.OPEN_URL, "http://topg.org/Minecraft/in-396658") });
                MessageSender.sendMessage(p, new ChatPart[] { new ChatPart("Voting Link 3", JSONChatColor.RED, new JSONChatFormat[] { JSONChatFormat.BOLD }).setHoverEvent(JSONChatHoverEventType.SHOW_TEXT, "§cCurrently Disabled") });
                p.sendMessage("");
                p.sendMessage("§b§m§n---------------------------------------------");
            }
            if (cmd.getName().equalsIgnoreCase("keys")) {
                p.sendMessage("§b§m§n---------------------------------------------");
                p.sendMessage("");
                p.sendMessage("§aYou currently have §d§l" + getDatabaseManager().getPlayerKeys(p) + " §a§lVoting Keys.");
                p.sendMessage("§aUse them at spawn!");
                p.sendMessage("");
                p.sendMessage("§b§m§n---------------------------------------------");
            }
            if (cmd.getName().equalsIgnoreCase("cleardb") && p.hasPermission("cleardb.clear")) {
                getDatabaseManager().clearVotes();
                p.sendMessage("§aThe database has been cleared.");
            }
            if (cmd.getName().equalsIgnoreCase("votekit")) {
                if (!getDatabaseManager().hasVoted(p)) {
                    p.sendMessage("§cYou have not voted yet. Type /vote!");
                    return false;
                }
                if (!Cooldowns.tryCooldown(p, "voteKit", 396000L)) {
                    p.sendMessage("§cYou have already used this kit for today.");
                    return false;
                }
                p.sendMessage("§aKit Vote loaded.");
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.IRON_HELMET) });
                final ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE);
                chestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
                p.getInventory().addItem(new ItemStack[] { chestplate });
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.IRON_LEGGINGS) });
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.IRON_BOOTS) });
                final ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
                sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
                p.getInventory().addItem(new ItemStack[] { sword });
                final ItemStack p2 = new ItemStack(Material.POTION);
                p2.setDurability((short)8226);
                p.getInventory().addItem(new ItemStack[] { p2 });
                final ItemStack p3 = new ItemStack(Material.POTION);
                p3.setDurability((short)8233);
                p.getInventory().addItem(new ItemStack[] { p3 });
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
            }
        }
        else if (args.length == 2 && cmd.getName().equalsIgnoreCase("givekey") && p.hasPermission("voting.givekey")) {
            getDatabaseManager().setPlayerKeys(Bukkit.getPlayer(args[0]), getDatabaseManager().getPlayerKeys(Bukkit.getPlayer(args[0])) + Integer.parseInt(args[1]));
            p.sendMessage("§aGiving §6" + Integer.parseInt(args[1]) + " §akeys to " + Bukkit.getPlayer(args[0]).getName() + "§a.");
        }
        return false;
    }
}
