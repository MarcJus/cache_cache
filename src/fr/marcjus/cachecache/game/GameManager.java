package fr.marcjus.cachecache.game;

import fr.marcjus.cachecache.Main;
import net.minecraft.network.protocol.game.PacketPlayOutKickDisconnect;
import net.minecraft.network.protocol.game.PacketPlayOutSpawnEntity;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.sql.Time;

public class GameManager {

    private GameManager(){}

    private static GameState state = GameState.STOPPED;
    private static Player player_game = null;
    private static Main main_instance;

    public static boolean startGame(Player player, Main main){
        main_instance = main;
        if(!state.equals(GameState.STOPPED)){
            return false;
        }
        state = GameState.STARTING;
        player_game = player;
        makePlayersVisible(false);
        for(Player players : Bukkit.getOnlinePlayers()){
            if(!players.getName().equals(player.getName())){
                players.setGameMode(GameMode.SURVIVAL);
            }
        }
        Bukkit.broadcastMessage("Joueur : "+player_game.getName());
        TimerSetup timer = new TimerSetup();
        timer.runTaskTimer(main, 0, 20);
        return true;
    }

    public static boolean stopGame(){
        if(state.equals(GameState.STOPPED))return false;
        state = GameState.STOPPED;
        makePlayersVisible(true);
        for(Player players : Bukkit.getOnlinePlayers()){
            if(!players.equals(player_game)){
                players.setGameMode(GameMode.CREATIVE);
                if(TimerSetup.getBar() != null){
                }
            }
        }
        if(TimerSetup.getBar() != null)TimerSetup.getBar().removeAll();
        player_game = null;
        return true;
    }

    public static boolean makePlayersVisible(boolean visible){
        if(getState().equals(GameState.STOPPED))return false;
        for(Player player : Bukkit.getOnlinePlayers()){
            if(player != player_game){
                if(visible)player_game.showPlayer(main_instance, player);
                else player_game.hidePlayer(main_instance, player);
            }
        }
        return true;
    }

    public static GameState getState() {
        return state;
    }

    public static Player getPlayer() {
        return player_game;
    }

    public static void setState(GameState state) {
        GameManager.state = state;
    }
}
