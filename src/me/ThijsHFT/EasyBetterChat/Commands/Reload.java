package me.ThijsHFT.EasyBetterChat.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ThijsHFT.EasyBetterChat.Main;
import me.ThijsHFT.EasyBetterChat.Utils.Utils;

public class Reload implements CommandExecutor {
	
	private Main plugin = Main.getPlugin(Main.class);
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("pfcreload")) {
			if (!(sender instanceof Player)) {
				Bukkit.getServer().getPluginManager().disablePlugin(plugin);
				Bukkit.getServer().getPluginManager().enablePlugin(plugin);
			} else {
				Player p = (Player)sender;
				Bukkit.getServer().getPluginManager().disablePlugin(plugin);
				Bukkit.getServer().getPluginManager().enablePlugin(plugin);
		        p.sendMessage(Utils.translateColor("&2Configuration successfully reloaded."));
			}
			  /*
			  if (!(sender instanceof Player)) {
				  ConfigYML.reloadData();
			  } else {
		        Player p = (Player)sender;
		        ConfigYML.reloadData();
		        p.sendMessage(Utils.translateColor("&1Config reloaded successfully."));
			  }
			  return true;
			*/
		}
		return true;
	
	}

}
