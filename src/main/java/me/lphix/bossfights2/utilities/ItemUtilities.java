package me.lphix.bossfights2.utilities;

import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class ItemUtilities {
    private final JavaPlugin plugin;

    public ItemUtilities(JavaPlugin plugin){
        this.plugin = plugin;
    }
    public void giveMetaKey(ItemStack item, String s) {
        NamespacedKey key = new NamespacedKey(plugin, "meta-key");
        ItemMeta itemMeta = item.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        container.set(key, PersistentDataType.STRING, s);
        item.setItemMeta(itemMeta);
    }
    public void setName(ItemStack item, Component c){
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(c);
        item.setItemMeta(itemMeta);
    }

    public boolean checkMetaKey(ItemStack item, String s){
        NamespacedKey key = new NamespacedKey(plugin, "meta-key");
        ItemMeta itemMeta = item.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        if(!container.getKeys().contains(key)) return false;
        return Objects.equals(container.get(key, PersistentDataType.STRING), s);
    }
}
