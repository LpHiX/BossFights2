package me.lphix.bossfights2.listeners;

import me.lphix.bossfights2.BossFights2Plugin;
import me.lphix.bossfights2.bosses.BossFactory;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.scheduler.BukkitTask;

public class EntityDeathListener implements Listener {
    @EventHandler
    static public void onEntityDeath(EntityDeathEvent e){
        BossFights2Plugin bossFights2Plugin = (BossFights2Plugin) BossFights2Plugin.getPlugin();
        BossFactory bossFactory = bossFights2Plugin.getBossFactory();
        if(bossFactory.deathMethods(e.getEntity())){
            Bukkit.getLogger().info("[BossFights] BossTask Successfully cancelled");
        }
    }
}
