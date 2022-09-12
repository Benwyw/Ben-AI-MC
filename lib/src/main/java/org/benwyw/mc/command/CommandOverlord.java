package org.benwyw.mc.command;

import java.util.ArrayList;
import java.util.List;

import org.benwyw.mc.BenAIMC;
import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CommandOverlord implements CommandExecutor{
	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			Boolean isInOverlordList = BenAIMC.overlordList.contains(player.getName());
			
			if (isInOverlordList) {
				//Location locUp = player.getLocation();
	            //locUp.setY(locUp.getY()+1);
	            //Block block = player.getWorld().getBlockAt(locUp);
				//if (block.isEmpty()) {
	            	//block.setType(Material.LIGHTNING_ROD);
		            //player.getLocation().getWorld().strikeLightning(locUp);
		        player.getLocation().getWorld().playSound(player, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1.0f, 1.0f);
		            //block.setType(Material.AIR);
	            //}
	            player.playEffect(EntityEffect.TELEPORT_ENDER);
	            player.playEffect(EntityEffect.ZOMBIE_TRANSFORM);
	            
				BenAIMC.overlordList.remove(player.getName());
				for (PotionEffect potionEffect : player.getActivePotionEffects()) {
					player.removePotionEffect(potionEffect.getType());
				}
				String bCastMsg = player.getDisplayName()+" disabled Overlord.";
				Bukkit.broadcastMessage(bCastMsg);
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "discordsrv broadcast "+bCastMsg);
				//player.setFireTicks(0);
			}
			else {
	            BenAIMC.overlordList.add(player.getName());
	            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, Integer.MAX_VALUE));
	            player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, Integer.MAX_VALUE));
	            
	            //Location locUp = player.getLocation();
	            //locUp.setY(locUp.getY()+1);
	            //Block block = player.getWorld().getBlockAt(locUp);
	            
	            //if (block.isEmpty()) {
	            	//block.setType(Material.LIGHTNING_ROD);
		            //player.getLocation().getWorld().strikeLightning(locUp);
	            player.getLocation().getWorld().playSound(player, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1.0f, 1.0f);
		            //block.setType(Material.AIR);
	            //}
	            player.playEffect(EntityEffect.TELEPORT_ENDER);
	            player.playEffect(EntityEffect.ZOMBIE_TRANSFORM);
	            
	            String bCastMsg = player.getDisplayName()+" BECAME OVERLORD!!!";
				Bukkit.broadcastMessage(bCastMsg);
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "discordsrv broadcast "+bCastMsg);
	            
	            /*
	            Location loc = player.getLocation();
	            List<Location> locFire = new ArrayList<Location>();
	            locFire.add(loc);
	            loc.setY(loc.getY()-1);
	            locFire.add(loc);
	            
	            for (Location location : locFire) {
	            	Block block = player.getWorld().getBlockAt(location);
	            	if (block.getType().equals(Material.FIRE))
	            		block.setType(Material.AIR);
	            }
	            */
	            	
	            //int locBelowPlayer = player.getLocation().getBlockY()-1;
	            //int locAbovePlayer = player.getLocation().getBlockY()+1;
	            //player.setFireTicks(Integer.MAX_VALUE);
			}
    	}
    	return true;
	}
}
