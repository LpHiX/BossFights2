package me.lphix.bossfights2.bosses;

import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.scheduler.BukkitTask;

public abstract class ABoss {
    protected Entity bossEntity;
    protected BukkitTask bossTask;
    protected BossBar bossBar;
    protected double maxHealth = 10;

    public ABoss() {
        Bukkit.getLogger().info("ABoss has been constructed");
    }

    public abstract void bossMethods(Entity entity);
    public abstract Entity spawnBoss(Location location);
    public abstract void cancelBossTask();
}
