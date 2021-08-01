package fr.marcjus.cachecache.game;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TimerSetup extends BukkitRunnable {

    private int timer = 30;
    private static BossBar bar;

    public TimerSetup(){
        bar = Bukkit.createBossBar(String.valueOf(timer), BarColor.GREEN, BarStyle.SEGMENTED_10);
        for(Player player : Bukkit.getOnlinePlayers()){
            bar.addPlayer(player);
        }
    }

    @Override
    public void run() {
        if(!GameManager.getState().equals(GameState.STARTING)){
            cancel();
            return;
        }

        if(timer == 30){
            Bukkit.broadcastMessage("§cAttention! Il est interdit de se teleporter durant le jeu");
        }

        if(timer <= 10){
            bar.setColor(BarColor.RED);
        }

        if(timer == 0){
            bar.removeAll();
            Bukkit.broadcastMessage("§aLe temps est fini! A vous de jouer !");
            GameManager.makePlayersVisible(true);
            GameManager.setState(GameState.PLAYING);
            cancel();
        }
        double bossBarProgress = timer / 30.0;
        bar.setProgress(bossBarProgress);
        bar.setTitle("Temps restant : "+timer);
        timer--;
    }

    public static BossBar getBar() {
        return bar;
    }
}
