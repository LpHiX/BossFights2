package me.lphix.bossfights2;

import me.lphix.bossfights2.bosses.BossFactory;
import me.lphix.bossfights2.bosses.WithersDisciple;
import me.lphix.bossfights2.commands.BossCommand;
import me.lphix.bossfights2.listeners.EntityDeathListener;
import me.lphix.bossfights2.listeners.InventoryClickListener;
import me.lphix.bossfights2.listeners.PlayerInteractListener;
import me.lphix.bossfights2.utilities.ItemUtilities;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Boss;
import org.bukkit.entity.Wither;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class BossFights2Plugin extends JavaPlugin {

    private BossFactory bossFactory;
    private ItemUtilities itemUtilities;

    @Override
    public void onEnable() {
        setupClasses();
        registerCommands();
        registerListeners();
        registerBosses();

        Bukkit.getLogger().info("[BossFights2] has started!");
    }
    private void setupClasses(){
        bossFactory = new BossFactory();
        itemUtilities = new ItemUtilities(this);
    }
    private void registerCommands(){
        this.getCommand("boss").setExecutor(new BossCommand(bossFactory, itemUtilities));
    }
    private void registerListeners(){
        Bukkit.getServer().getPluginManager().registerEvents(new InventoryClickListener(bossFactory), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerInteractListener(itemUtilities), this);
        Bukkit.getServer().getPluginManager().registerEvents(new EntityDeathListener(bossFactory), this);
    }
    private void registerBosses() {
        bossFactory.registerBoss(WithersDisciple.class, () -> new WithersDisciple(bossFactory, this));
    }
    @Override
    public void onDisable() {
        Bukkit.getLogger().info("[BossFights2] has ended!");
    }
}
