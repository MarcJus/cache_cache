package fr.marcjus.cachecache.commands;

import fr.marcjus.cachecache.game.GameManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftVillager;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class
CommandPnj implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player player){
            Location loc = player.getLocation();
            Villager villager = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
            villager.setCustomName("§eCache cache");
            villager.setCustomNameVisible(true);
            villager.setInvulnerable(true);
            villager.setProfession(Villager.Profession.NONE);
            villager.setAI(false);
            player.sendMessage("§aPnj apparu");
        } else {
            sender.sendMessage("§cVous n'etes pas un joueur");
        }
        return true;
    }
}
