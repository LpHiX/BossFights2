package me.lphix.bossfights2.listeners;

import me.lphix.bossfights2.BossFights2Plugin;
import me.lphix.bossfights2.bosses.ABoss;
import me.lphix.bossfights2.bosses.BossFactory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class PlayerQuitListener implements Listener {
    private final BossFactory bossFactory;

    public PlayerQuitListener(BossFactory bossFactory){
        this.bossFactory = bossFactory;
    }
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e){
        e.getPlayer().sendMessage("bye");
        Consumer<ABoss> removePlayerFromBar = l -> l.removeBar(e.getPlayer());
        bossFactory.getActiveBosses().forEach(removePlayerFromBar);
    }
}
