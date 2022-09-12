package org.benwyw.mc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.benwyw.mc.command.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class BenAIMC extends JavaPlugin implements Listener{
	public static List<String> overlordList = new ArrayList<String>();
	public static HashMap<Player, Block> playerBlockList = new HashMap<Player, Block>();
	
	// Fired when plugin is first enabled
    @Override
    public void onEnable() {
    	this.getCommand("rich").setExecutor(new CommandRich());
    	this.getCommand("clear").setExecutor(new CommandClear());
    	this.getCommand("light").setExecutor(new CommandLight());
    	this.getCommand("overlord").setExecutor(new CommandOverlord());
    	getServer().getPluginManager().registerEvents(this, this);
    }
    // Fired when plugin is disabled
    @Override
    public void onDisable() {
    	overlordList.clear();
    	playerBlockList.clear();
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerMove(PlayerMoveEvent event) {
    	if(overlordList.contains(event.getPlayer().getName())){
    		//log.debug("Prep pName: "+event.getPlayer().getName());
        	//log.debug("Prep oL: "+overlordList);
    		Player player = event.getPlayer();
    		World world = event.getPlayer().getWorld();
    		Location loc = player.getLocation();
    		loc.setY(loc.getY()-1);
    		Block block = world.getBlockAt(loc);
    		//log.debug("Detected block type: "+block);
    		
    		if(event.getPlayer().isSneaking()) {
    			if(playerBlockList.containsValue(block)) {
	    			block.setType(Material.AIR);
	    			loc.setY(loc.getY()-1);
	    			
	    			block = world.getBlockAt(loc);
	    			if(block.isEmpty() || block.isLiquid()) {
	    				if(playerBlockList.containsKey(player))
	    					playerBlockList.get(player).setType(Material.AIR);
	    				block.setType(Material.NETHERITE_BLOCK);
	    				playerBlockList.put(player, block);
	    				//log.debug("Changed to block type: "+block);
	    			}
    			}
    		}
	    	else if(block.isEmpty() || block.isLiquid()) {
	    		if(playerBlockList.containsKey(player))
					playerBlockList.get(player).setType(Material.AIR);
				block.setType(Material.NETHERITE_BLOCK);
				playerBlockList.put(player, block);
				//log.debug("Changed to block type: "+block);
			}
    	}
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerQuit(PlayerQuitEvent event) {
    	Player player = event.getPlayer();
    	if(overlordList.contains(player.getName())){
    		if(playerBlockList.containsKey(player)) {
    			//log.debug(playerBlockList.toString());
				playerBlockList.get(player).setType(Material.AIR);
				playerBlockList.remove(player);
				//log.debug(playerBlockList.toString());
    		}
    	}
    }
}
