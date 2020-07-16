package me.nomaj.moodymagicaltrunk;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.nomaj.moodymagicaltrunk.commands.TrunkCommands;

public class Hub extends JavaPlugin {
	@Override
	public void onEnable() {
		getCommand("trunk").setExecutor(new TrunkCommands());
		getServer().getPluginManager().registerEvents(new EventListener(), (Plugin)this);
	}
}

