package me.lphix.bossfights2.listeners;

import me.lphix.bossfights2.inventories.BossMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {
    @EventHandler
    public static void onPlayerInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(e.getItem() == null){
            return;
        }
        if(e.getItem().getType().equals(Material.MAGMA_CREAM)){
            p.openInventory(new BossMenu().getInventory());
        }
    }
}
