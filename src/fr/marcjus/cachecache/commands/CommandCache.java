package fr.marcjus.cachecache.commands;

import fr.marcjus.cachecache.Main;
import fr.marcjus.cachecache.TimerTest;
import fr.marcjus.cachecache.game.GameManager;
import fr.marcjus.cachecache.game.TimerSetup;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandCache implements CommandExecutor {

    private final Main main;

    public CommandCache(Main main){
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(args.length == 0){
            return false;
        }

        switch(args[0]){
            case "start":
                if(args.length < 2){
                    return false;
                }
                if(Bukkit.getPlayer(args[1]) == null){
                    sender.sendMessage("§cJoueur introuvable : "+args[1]);
                } else {
                    Player player = Bukkit.getPlayer(args[1]);
                    if(!GameManager.startGame(player, main)){
                        sender.sendMessage("§cLe jeu a deja commence !");
                    }
                }
                break;

            case "stop":
                if(!GameManager.stopGame()){
                    sender.sendMessage("§cLe jeu n'a pas commence !");
                } else {
                    Bukkit.broadcastMessage("§cLe jeu est fini !");
                }
                break;

            default:
                break;
        }

        return true;
    }
}
