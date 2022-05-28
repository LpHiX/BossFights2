package me.lphix.bossfights2.listeners;

import me.lphix.bossfights2.BossFights2Plugin;
import me.lphix.bossfights2.bosses.BossFactory;
import me.lphix.bossfights2.bosses.WithersDisciple;
import me.lphix.bossfights2.inventories.BossMenu;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {
    @EventHandler
    public static void onInventoryClick(InventoryClickEvent e){
        if(e.getClickedInventory() == null){
            return;
        }
        if(e.getClickedInventory().getHolder() instanceof BossMenu){
            Player p = (Player) e.getWhoClicked();
            BossFights2Plugin bossFights2Plugin = (BossFights2Plugin) BossFights2Plugin.getPlugin();
            BossFactory bossFactory = bossFights2Plugin.getBossFactory();
            e.setCancelled(true);
            switch(e.getSlot()){
                case 10:
                    bossFactory.spawnBoss(p.getLocation(), WithersDisciple.class);
            }
        }
    }
}
