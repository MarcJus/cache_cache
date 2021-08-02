package fr.marcjus.cachecache;

import fr.marcjus.cachecache.commands.CommandCache;
import fr.marcjus.cachecache.commands.CommandCacheComplete;
import fr.marcjus.cachecache.commands.CommandPnj;
import fr.marcjus.cachecache.game.GameListeners;
import fr.marcjus.cachecache.game.GameManager;
import fr.marcjus.cachecache.listeners.PnjListeners;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PnjListeners(this), this);
        pm.registerEvents(new GameListeners(), this);
        getCommand("pnj").setExecutor(new CommandPnj());
        getCommand("cache").setExecutor(new CommandCache(this));
        getCommand("cache").setTabCompleter(new CommandCacheComplete());
    }

    @Override
    public void onDisable() {
        GameManager.stopGame();
    }
}
