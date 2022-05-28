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
    @EventHandler
    static public void onPlayerLeave(PlayerQuitEvent e){
        e.getPlayer().sendMessage("bye");
        BossFights2Plugin plugin = (BossFights2Plugin) BossFights2Plugin.getPlugin();
        BossFactory bossFactory = plugin.getBossFactory();
        Consumer<ABoss> removePlayerFromBar = l -> l.removeBar(e.getPlayer());
        bossFactory.getActiveBosses().forEach(removePlayerFromBar);
    }
}
