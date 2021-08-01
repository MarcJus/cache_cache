package fr.marcjus.cachecache;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class TimerTest extends BukkitRunnable {

    private int timer = 30;

    @Override
    public void run() {
        if(timer == 0){
            cancel();
        }
        double bossBarTimer = timer / 30.0;
        Bukkit.broadcastMessage("§2Timer : "+timer);
        Bukkit.broadcastMessage("§aTimer bossbar : "+bossBarTimer);
        timer --;
    }
}
