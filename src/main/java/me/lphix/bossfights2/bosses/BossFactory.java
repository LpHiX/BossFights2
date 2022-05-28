package me.lphix.bossfights2.bosses;

import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class BossFactory {
    private final Map<Class<? extends ABoss>, Supplier<? extends ABoss>> bossMap = new HashMap<>();
    private final Map<Entity, ABoss> activeBossList = new HashMap<>();

    public <B extends ABoss> void registerBoss(Class<B> clazz, Supplier<B> supplier){
        bossMap.put(clazz, supplier);
    }

    public <B extends ABoss> B spawnBoss(Location location, Class<B> clazz)  {
        Supplier<? extends ABoss> supplier = bossMap.get(clazz);
        if(supplier == null){
            return null;
        }
        B b = (B) supplier.get();
        b.spawnBoss(location);
        return b;
    }
    public <B extends ABoss> B getBossFromEntity(Entity entity){
        if(!activeBossList.containsKey(entity)){
            return null;
        }
        return (B) activeBossList.get(entity);
    }
    public boolean cancelBossTask(Entity entity){
        if(!activeBossList.containsKey(entity)) {
            Bukkit.getLogger().info("Failed to find boss");
            return false;
        }
        activeBossList.get(entity).cancelBossTask();
        Bukkit.getLogger().info("Successfully found boss");
        return true;
    }
    public <B extends ABoss> void putBoss(B boss){
        activeBossList.put(boss.bossEntity, boss);
    }
}
