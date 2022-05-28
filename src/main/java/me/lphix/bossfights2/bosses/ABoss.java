package me.lphix.bossfights2.bosses;

import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public abstract class ABoss {
    protected Entity bossEntity;
    protected BukkitTask bossTask;
    protected BossBar bossBar;
    protected double maxHealth;

    public ABoss(double maxHealth) {
        this.maxHealth = maxHealth;
        Bukkit.getLogger().info("ABoss has been constructed");
    }

    public abstract void bossMethods(Entity entity);
    public abstract void spawnBoss(Location location);
    public abstract void deathMethods();
    public abstract void removeBar(Player player);
}
