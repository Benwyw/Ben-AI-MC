package org.benwyw.mc.command;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CommandLight implements CommandExecutor{
	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    	if (sender instanceof Player) {
            //Player player = (Player) sender;
            System.out.println("label: "+label);
            System.out.println("args: "+args[0]);
            Player targetPlayer = Bukkit.getPlayer(args[0]);
            targetPlayer.setGameMode(GameMode.SURVIVAL);
            targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 50));
            targetPlayer.damage(5);
    	}
    	return true;
	}
}
