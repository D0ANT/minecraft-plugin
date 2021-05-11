package mc.plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class Tabcomplete implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
    	List<String> tab = new ArrayList<>();/*/
    	if(args.length == 3) { //random min max entity
        	//@NotNull List<org.bukkit.entity.Entity> entity = Bukkit.getServer().getWorld("world").getPlayer;
			@NotNull Player[] entity = new Player[Bukkit.getOnlinePlayers().size()];
    		
        	for(int i=0;i<entity.length;i++) {
        		tab.add(entity[i].toString());
        	}
        	return tab;
        }*/
        if(cmd.getName().equalsIgnoreCase("inventory")){
            tab.add("open");
            return tab;
        } else if(cmd.getName().equalsIgnoreCase("fly")){
            tab.add("enable");
            tab.add("disable");
            return tab;
        } else if(cmd.getName().equalsIgnoreCase("do_config")){
            tab.add("reload");
            tab.add("say");
        }
        return null;
    }
}
