package code.Young_Explicit.voting.utils;

import java.util.*;
import org.bukkit.plugin.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.*;

public class GenerateItems
{
    public HashMap<UUID, Integer> TID;
    public Plugin plugin;
    public Player p;
    public Inventory inv;
    public InventoryHolder holder;
    public ItemStack[] items;
    public int index;
    public int slot;
    
    public GenerateItems(final Plugin plugin, final Player p, final Inventory inv, final int slot, final int time, final ItemStack... item) {
        super();
        this.TID = new HashMap<UUID, Integer>();
        this.plugin = plugin;
        this.p = p;
        this.inv = inv;
        this.slot = slot;
        this.index = 0;
        this.items = item;
        this.holder = inv.getHolder();
        inv.setItem(this.slot, this.get());
        this.startScheduler(p);
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, (Runnable)new Runnable() {
            @Override
            public void run() {
                GenerateItems.this.stopScheduler(p);
            }
        }, (long)(time * 20));
    }
    
    public void startScheduler(final Player p) {
        final int id = Bukkit.getScheduler().scheduleSyncRepeatingTask(this.plugin, (Runnable)new Runnable() {
            @Override
            public void run() {
                GenerateItems.this.next();
                final ItemStack item = GenerateItems.this.inv.getItem(GenerateItems.this.slot);
                if (item == null || GenerateItems.this.prev().equals((Object)item)) {
                    final Player p = (Player)GenerateItems.this.holder;
                    p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 1.0f, 1.0f);
                    GenerateItems.this.inv.setItem(GenerateItems.this.slot, GenerateItems.this.get());
                }
            }
        }, 5L, 5L);
        this.TID.put(p.getUniqueId(), id);
    }
    
    public void stopScheduler(final Player p) {
        if (this.TID.containsKey(p.getUniqueId())) {
            final int id = this.TID.get(p.getUniqueId());
            Bukkit.getScheduler().cancelTask(id);
            this.TID.remove(p.getUniqueId());
        }
    }
    
    private void next() {
        ++this.index;
        this.index %= this.items.length;
    }
    
    public ItemStack get() {
        return this.items[this.index];
    }
    
    public ItemStack prev() {
        return this.items[(this.items.length + this.index - 1) % this.items.length];
    }
}
