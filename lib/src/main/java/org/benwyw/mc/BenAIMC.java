package org.benwyw.mc;

import org.benwyw.mc.command.*;
import org.bukkit.plugin.java.JavaPlugin;

public class BenAIMC extends JavaPlugin{
	// Fired when plugin is first enabled
    @Override
    public void onEnable() {
    	this.getCommand("rich").setExecutor(new CommandRich());
    	this.getCommand("clear").setExecutor(new CommandClear());
    	this.getCommand("light").setExecutor(new CommandLight());
    }
    // Fired when plugin is disabled
    @Override
    public void onDisable() {

    }
}
