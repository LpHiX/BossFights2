package me.lphix.bossfights2.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    @EventHandler
    static public void onPlayerLeave(PlayerQuitEvent e){
        e.getPlayer().sendMessage("bye");
        //TODO Remove player bossbar
    }
}
