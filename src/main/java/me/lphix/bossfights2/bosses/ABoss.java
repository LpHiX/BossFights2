package me.lphix.bossfights2.bosses;

import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Boss;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public abstract class ABoss {
    protected JavaPlugin plugin;
    protected BossFactory bossFactory;
    protected Entity bossEntity;
    protected BukkitTask bossTask;
    protected BossBar bossBar;
    protected double maxHealth;

    public ABoss(BossFactory bossFactory, JavaPlugin plugin, double maxHealth) {
        this.plugin = plugin;
        this.bossFactory = bossFactory;
        this.maxHealth = maxHealth;
        Bukkit.getLogger().info("ABoss has been constructed");
    }

    public abstract void bossMethods(Entity entity);
    public abstract void spawnBoss(Location location);
    public abstract void deathMethods();
    public abstract void removeBar(Player player);

    public Entity getBossEntity() {
        return bossEntity;
    }
}
