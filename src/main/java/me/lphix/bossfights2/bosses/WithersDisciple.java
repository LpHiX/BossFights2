package me.lphix.bossfights2.bosses;

import me.lphix.bossfights2.BossFights2Plugin;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.w3c.dom.Attr;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class WithersDisciple extends ABoss{
    final List<Player> playerWithBarList = new ArrayList<>();
    Component displayName = Component.text("Wither's Disciple", NamedTextColor.DARK_GRAY);
    public WithersDisciple() {
        super(1000);
    }
    @Override
    public void bossMethods(Entity entity) {
        Set<BossBar.Flag> barFlags = new HashSet<>();
        barFlags.add(BossBar.Flag.DARKEN_SCREEN);
        barFlags.add( BossBar.Flag.CREATE_WORLD_FOG);
        bossBar = BossBar.bossBar(displayName, 1f , BossBar.Color.GREEN, BossBar.Overlay.NOTCHED_6, barFlags);
        bossTask = new BukkitRunnable() {
            @Override
            public void run() {
                List<Player> playersToBeRemoved = playerWithBarList.stream()
                        .filter(p -> bossEntity.getLocation().distance(p.getLocation()) > 100)
                        .toList();
                playersToBeRemoved.forEach(p -> {
                            p.hideBossBar(bossBar);
                            playerWithBarList.remove(p);
                        });
                bossEntity.getNearbyEntities(100, 100, 100)
                        .stream()
                        .filter(e -> e instanceof Player)
                        .map(e -> (Player) e)
                        .filter(p -> !playerWithBarList.contains(p))
                        .forEach(p -> {
                            playerWithBarList.add(p);
                            p.showBossBar(bossBar);
                        });
                bossBar.progress(((float) ((LivingEntity) bossEntity).getHealth()/ (float) maxHealth));
                //TODO Boss give player bossbar, test if health 1000 workst
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
    public void spawnBoss(Location location){
        this.bossEntity = location.getWorld().spawn(location, WitherSkeleton.class, b -> {
            b.customName(displayName);
            b.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
            b.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
            b.setHealth(maxHealth);
        });
        ((LivingEntity) bossEntity).setRemoveWhenFarAway(false);
        BossFights2Plugin plugin = (BossFights2Plugin) BossFights2Plugin.getPlugin();
        plugin.getBossFactory().putBoss(this);
        bossMethods(bossEntity);
    }
    @Override
    public void deathMethods() {
        Consumer<Player> hideBossBar = p -> p.hideBossBar(bossBar);
        playerWithBarList.forEach(hideBossBar);
        bossTask.cancel();
    }

    @Override
    public void removeBar(Player player) {
        if(playerWithBarList.contains(player)){
            player.hideBossBar(bossBar);
        }
    }
}
