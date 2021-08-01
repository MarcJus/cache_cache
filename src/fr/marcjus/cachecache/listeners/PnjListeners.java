package fr.marcjus.cachecache.listeners;

import fr.marcjus.cachecache.Main;
import fr.marcjus.cachecache.game.GameManager;
import fr.marcjus.cachecache.game.GameState;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class PnjListeners implements Listener {

    private final Main main;

    public PnjListeners(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onClick(PlayerInteractAtEntityEvent e) {
        Player player = e.getPlayer();
        Entity ent = e.getRightClicked();
        if (ent instanceof Villager) {
            if (ent.isCustomNameVisible() && ent.getCustomName() != null) {
                if (ent.getCustomName().equals("§eCache cache")) {
                    GameManager.startGame(player, main);
                }
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e){
        Entity ent = e.getEntity();
        if(ent instanceof Villager)
            if(ent.getCustomName() != null)
                if(ent.getCustomName().equals("§eCache cache")) e.setCancelled(true);
    }

}