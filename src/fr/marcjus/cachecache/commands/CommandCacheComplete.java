package fr.marcjus.cachecache.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CommandCacheComplete implements TabCompleter {

    private final List<String> COMPLETES = Arrays.asList("start", "stop");

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

        if(args.length == 1){
            final List<String> completes = new ArrayList<>();
            StringUtil.copyPartialMatches(args[0], COMPLETES, completes);
            Collections.sort(completes);

            return completes;
        }
        return null;
    }
}
