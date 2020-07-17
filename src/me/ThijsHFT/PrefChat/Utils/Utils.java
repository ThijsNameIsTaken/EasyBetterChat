package me.ThijsHFT.PrefChat.Utils;

import org.bukkit.ChatColor;

public class Utils {
	
	public static String translateColor(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
}
