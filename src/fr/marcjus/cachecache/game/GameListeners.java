package fr.marcjus.cachecache.game;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityTeleportEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class GameListeners implements Listener {

    @EventHandler
    public void onTeleport(EntityTeleportEvent e){
        Entity ent = e.getEntity();
        if(!GameManager.getState().equals(GameState.STOPPED)){
            if(ent.getName().equals(GameManager.getPlayer().getName())){
                e.setCancelled(true);
                ent.sendMessage("§cAttention! Il est interdit de se teleporter durant le jeu");
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if (GameManager.getState().equals(GameState.STARTING)) {
            if (GameManager.getPlayer().getName().equals(player.getName())) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        Entity ent = e.getEntity();
        if (GameManager.getState().equals(GameState.STARTING)) {
            if (!GameManager.getPlayer().getName().equals(damager.getName()))
                e.setCancelled(true);
            if(ent instanceof Player)
                e.setCancelled(true);
        } else if (GameManager.getState().equals(GameState.PLAYING)){
            if(ent.getName().equals(GameManager.getPlayer().getName()))
                e.setCancelled(true);
        }
    }

    @EventHandler
    public void onRegenerate(EntityRegainHealthEvent e) {
        Entity ent = e.getEntity();
        if (!GameManager.getState().equals(GameState.STOPPED)) {
            if (!ent.getName().equals(GameManager.getPlayer().getName())) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        if(GameManager.getState().equals(GameState.STARTING))TimerSetup.getBar().addPlayer(player);
    }

}
