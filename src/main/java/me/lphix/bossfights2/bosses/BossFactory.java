package me.lphix.bossfights2.bosses;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

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

    @SuppressWarnings("unchecked")
    public <B extends ABoss> void spawnBoss(Location location, Class<B> clazz)  {
        Supplier<? extends ABoss> supplier = bossMap.get(clazz);
        if(supplier == null){
            return;
        }
        B b = (B) supplier.get();
        b.spawnBoss(location);
    }
    @SuppressWarnings("unchecked")
    public <B extends ABoss> B getBossFromEntity(Entity entity){
        if(!activeBossList.containsKey(entity)){
            return null;
        }
        return (B) activeBossList.get(entity);
    }
    public boolean deathMethods(Entity entity){
        if(!activeBossList.containsKey(entity)) {
            return false;
        }
        ABoss aBoss = activeBossList.get(entity);
        aBoss.deathMethods();
        return true;
    }
    public <B extends ABoss> void putBoss(B boss){
        activeBossList.put(boss.bossEntity, boss);
    }
    public List<ABoss> getActiveBosses(){
        return activeBossList.values().stream().toList();
    }
}
