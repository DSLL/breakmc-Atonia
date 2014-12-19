package code.Young_Explicit.voting.utils;

import org.bukkit.*;
import org.bukkit.inventory.*;
import java.util.*;
import org.bukkit.inventory.meta.*;

public class ItemUtill
{
    public static ItemStack createItem(final Material material, final String displayname, final String... lore) {
        final ItemStack item = new ItemStack(material);
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        final ArrayList<String> Lore = new ArrayList<String>();
        for (final String loreString : lore) {
            Lore.add(loreString);
        }
        meta.setLore((List)Lore);
        item.setItemMeta(meta);
        return item;
    }
    
    public static ItemStack createItem(final Material material, final int amount, final String displayname, final String... lore) {
        final ItemStack item = new ItemStack(material, amount);
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        final ArrayList<String> Lore = new ArrayList<String>();
        for (final String loreString : lore) {
            Lore.add(loreString);
        }
        meta.setLore((List)Lore);
        item.setItemMeta(meta);
        return item;
    }
    
    public static ItemStack createItem(final Material material, final short data, final int amount, final String displayname, final String... lore) {
        final ItemStack item = new ItemStack(material, amount);
        item.setDurability(data);
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        final ArrayList<String> Lore = new ArrayList<String>();
        for (final String loreString : lore) {
            Lore.add(loreString);
        }
        meta.setLore((List)Lore);
        item.setItemMeta(meta);
        return item;
    }
}
