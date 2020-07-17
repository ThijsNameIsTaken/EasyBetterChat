package me.ThijsHFT.PrefChat;

import me.ThijsHFT.PrefChat.Commands.*;
import me.ThijsHFT.PrefChat.Events.*;
import me.ThijsHFT.PrefChat.Utils.ConfigManagers.*;

import java.util.logging.Level;
import net.milkbowl.vault.chat.*;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
  public static Chat vaultChat = null;
  
  // alle configs die geladen moeten worden hier (zelfde format!)
  public ConfigYML configYML;
  // einde configs setup
	
  public void onEnable() {
    Bukkit.getServer().getPluginManager().registerEvents(this, (Plugin)this);
    
    if (!setupVaultChat()) {
    	this.getLogger().log(Level.WARNING, "Vault chat hook failed. Disabling plugin, using default chat format.");
    	Bukkit.getServer().getPluginManager().disablePlugin(this);
    } else {
    	Bukkit.getServer().getPluginManager().registerEvents((Listener)new ChatEvent(), (Plugin)this);
    	this.getLogger().log(Level.INFO, "Vault chat hook successful. Using config-defined chat format.");
    }
    
    saveDefaultConfig();
    loadConfigs();
    
    getCommand("pfcreload").setExecutor((CommandExecutor)new Reload());
  }
  
  public void loadConfigs() {
	  // alle configs hier laden op zelfde manier:
	  // <var> = new <filenaam in ConfigManagers>
	  configYML = new ConfigYML();
	  configYML.setup();
  }
  
  /* Vault Chat Hook setup */
  private boolean setupVaultChat() {
      RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
      if (rsp != null) {
    	  vaultChat = rsp.getProvider();
      }
      return (vaultChat != null);
  }
  
}
