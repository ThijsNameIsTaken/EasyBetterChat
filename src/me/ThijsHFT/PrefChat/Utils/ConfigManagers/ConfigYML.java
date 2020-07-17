package me.ThijsHFT.PrefChat.Utils.ConfigManagers;

import me.ThijsHFT.PrefChat.Main;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigYML {
	
	private Main plugin = Main.getPlugin(Main.class);
	
	public static FileConfiguration configcfg;
	public static File configfile;
	
	public void setup() {
		if (!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}
		
		configfile = new File(plugin.getDataFolder(), "config.yml");
		
		if (!configfile.exists()) {
			try {
				configfile.createNewFile();
			} catch (IOException e) {
				plugin.getLogger().log(Level.SEVERE, "The config.yml file could not be created. Disabling plugin, using default chat format");
				Bukkit.getServer().getPluginManager().disablePlugin(plugin);
				return;
			}
		}
		
		if (!Bukkit.getServer().getPluginManager().isPluginEnabled(plugin)) {
			return;
		}
		
		configcfg = YamlConfiguration.loadConfiguration(configfile);
		plugin.getLogger().log(Level.INFO, "The file config.yml has been loaded");
	}
	
	public static FileConfiguration getConfiguration() {
		return configcfg;
	}
	
	public boolean saveData() {
		try {
			configcfg.save(configfile);
			plugin.getLogger().log(Level.INFO, "The file config.yml has been saved.");
			return true;
		} catch (IOException e) {
			plugin.getLogger().log(Level.WARNING, "The file config.yml has not been saved. Modified values wil get lost.");
			return false;
		}
	}
	
	public void reloadData() {
		configcfg = YamlConfiguration.loadConfiguration(configfile);
		plugin.getLogger().log(Level.INFO, "Config reloaded successfully.");
	}

}

