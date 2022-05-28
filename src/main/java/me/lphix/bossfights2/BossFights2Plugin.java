package me.lphix.bossfights2;

import me.lphix.bossfights2.bosses.BossFactory;
import me.lphix.bossfights2.bosses.WithersDisciple;
import me.lphix.bossfights2.commands.BossCommand;
import me.lphix.bossfights2.listeners.EntityDeathListener;
import me.lphix.bossfights2.listeners.InventoryClickListener;
import me.lphix.bossfights2.listeners.PlayerInteractListener;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Boss;
import org.bukkit.entity.Wither;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class BossFights2Plugin extends JavaPlugin {

    private static JavaPlugin plugin;
    private BossFactory bossFactory;

    @Override
    public void onEnable() {
        setupClasses();
        registerCommands();
        registerListeners();
        registerBosses();

        Bukkit.getLogger().info("[BossFights2] has started!");
    }
    private void setupClasses(){
        plugin = this;
        bossFactory = new BossFactory();
    }
    private void registerCommands(){
        this.getCommand("boss").setExecutor(new BossCommand());
    }
    private void registerListeners(){
        Bukkit.getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new EntityDeathListener(), this);
    }
    private void registerBosses() {
        bossFactory.registerBoss(WithersDisciple.class, WithersDisciple::new);
    }
    @Override
    public void onDisable() {
        Bukkit.getLogger().info("[BossFights2] has ended!");
    }

    public static JavaPlugin getPlugin(){
        return plugin;
    }
    public BossFactory getBossFactory() {
        return bossFactory;
    }
}
