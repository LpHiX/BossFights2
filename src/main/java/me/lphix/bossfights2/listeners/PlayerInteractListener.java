package me.lphix.bossfights2.listeners;

import me.lphix.bossfights2.inventories.BossMenu;
import me.lphix.bossfights2.utilities.ItemUtilities;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {
    ItemUtilities itemUtilities;
    public PlayerInteractListener(ItemUtilities itemUtilities){
        this.itemUtilities = itemUtilities;
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getItem() == null) return;
        if (!e.getItem().getType().equals(Material.MAGMA_CREAM)) return;
        if (!(itemUtilities.checkMetaKey(e.getItem(), "boss-item"))) return;
        if (!p.hasPermission("bossfight2.admin")) {
            p.sendMessage(Component.text("You don't have permission", NamedTextColor.RED));
            return;
        }
        p.openInventory(new BossMenu().getInventory());
    }
}
