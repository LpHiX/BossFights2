package me.lphix.bossfights2.bosses;

import me.lphix.bossfights2.BossFights2Plugin;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class WithersDisciple extends ABoss{
    final List<Player> playerWithBarList = new ArrayList<>();
    Component displayname = Component.text("Wither's Disciple", NamedTextColor.DARK_GRAY);
    public WithersDisciple() {
    }
    public Entity spawnBoss(Location location){
        this.bossEntity = location.getWorld().spawn(location, WitherSkeleton.class, b -> {
            b.customName(displayname);
            b.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
        });
        BossFights2Plugin plugin = (BossFights2Plugin) BossFights2Plugin.getPlugin();
        plugin.getBossFactory().putBoss(this);
        bossMethods(bossEntity);
        return bossEntity;
    }
    @Override
    public void bossMethods(Entity entity) {
        bossTask = new BukkitRunnable() {
            @Override
            public void run() {
                //TODO Boss give player bossbar, test if health 1000 works
                // Stage 1
                //  6 seconds per attack if persin is with 100 blocks
                //  pathfinding: circle around player 50 blocks range
                //  summon meteor rain
                //  teleport explosion spiral
                // Stage 2
                //  pathfinding: 10 seconds between teleport and run towards player
                //  summon blazes to shoot fireballs
                //  explosions flying up, barrage of arrows to player
                // Stage 3
                //  4 seconds per attack, only run at player
                //  jump shamsh
                //  lifesteal line
                //  meteor summon, teleport away and circle around player
                //  summon 4 ghost zombies (alive for 10 seconds)
            }
        }.runTaskTimer(BossFights2Plugin.getPlugin(), 0L, 20L);
    }
    @Override
    public void cancelBossTask() {
        bossTask.cancel();
    }
}
