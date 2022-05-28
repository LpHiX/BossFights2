package me.lphix.bossfights2.inventories;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class BossMenu implements InventoryHolder {
    Inventory inv;
    public BossMenu (){
        inv = Bukkit.createInventory(this, 54);
        init();
    }

    private void init() {
        for (int i = 0; i < 54; i++) {
            ItemStack fillerItem = new ItemStack(Material.LIME_STAINED_GLASS_PANE, 1);
            ItemMeta fillerMeta = fillerItem.getItemMeta();
            fillerMeta.displayName(Component.text(" "));
            fillerItem.setItemMeta(fillerMeta);
            inv.setItem(i, fillerItem);
        }
        inv.setItem(10, new ItemStack(Material.WITHER_SKELETON_SKULL, 1));
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inv;
    }
}
