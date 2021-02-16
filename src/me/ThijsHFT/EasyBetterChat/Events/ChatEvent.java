package me.ThijsHFT.EasyBetterChat.Events;

import me.ThijsHFT.EasyBetterChat.Main;
import me.ThijsHFT.EasyBetterChat.Utils.Utils;
import me.ThijsHFT.EasyBetterChat.Utils.ConfigManagers.*;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import net.milkbowl.vault.chat.Chat;

public class ChatEvent implements Listener {
	
	private Chat chat = Main.vaultChat;

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
	  Player player = event.getPlayer();
	  World world = player.getWorld();
	  String group = chat.getPrimaryGroup(player);
	  String prefix = chat.getGroupPrefix(world, group);	  
	  String suffix = chat.getGroupSuffix(world, group);
	  
	  if (suffix == "") {
		  // Dirty fix because java is dumb
		  String eventMessage = event.getMessage().replace("%", "%%");
		  
		  
		  String configString = ConfigYML.getConfiguration().getString("format-string")
				  .replace("{prefix}", prefix).replace("{suffix} ", "").replace("{username}", player.getName()).replace("{msg}", eventMessage);
		  
		  event.setFormat(Utils.translateColor(configString));
	  } else {
		// Dirty fix because java is dumb
		  String eventMessage = event.getMessage().replace("%", "%%");
		  
		  String configString = ConfigYML.getConfiguration().getString("format-string")
				  .replace("{prefix}", prefix).replace("{suffix} ", suffix + " ").replace("{username}", player.getName()).replace("{msg}", eventMessage);
		  
		  event.setFormat(Utils.translateColor(configString));
	  }
	  
	  
	}

}

