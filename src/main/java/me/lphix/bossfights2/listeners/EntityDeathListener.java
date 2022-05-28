package me.lphix.bossfights2.listeners;

import me.lphix.bossfights2.BossFights2Plugin;
import me.lphix.bossfights2.bosses.BossFactory;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.scheduler.BukkitTask;

public class EntityDeathListener implements Listener {
    BossFactory bossFactory;
    public EntityDeathListener(BossFactory bossFactory) {
        this.bossFactory = bossFactory;
    }
    @EventHandler
    public void onEntityDeath(EntityDeathEvent e){
        if(bossFactory.deathMethods(e.getEntity())){
            Bukkit.getLogger().info("[BossFights] BossTask Successfully cancelled");
        }
    }
}
