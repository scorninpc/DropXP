package com.github.boltydawg.dropxp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * handles the execution of the /droplvl command, which is used to give admins
 * xp bottles based off of levels rather than actual xp
 * @author Jason
 *
 */
public class CommandLevelBottle implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		Player player, executer;

		//checks if sender is not a player (console)
		if(!(sender instanceof Player)){
			//checks if is passing player to get the bottle
			if (args.length < 2) {
				sender.sendMessage(ChatColor.DARK_AQUA + "[DropXP] " + ChatColor.BLUE + "From console, you need to inform the player!");
				return true;
			}
		}

		//checks if player is informed
		if (args.length >= 2) {
			player = Bukkit.getPlayer(args[1]);
			if(player == null) {
				sender.sendMessage(ChatColor.DARK_AQUA + "[DropXP] " + ChatColor.BLUE + "Player not found!");
				return true;
			}
		}
		else {
			player = (Player)sender;
		}
		
		//checks if their inventory is full
		if(player.getInventory().firstEmpty() == -1) {
			sender.sendMessage(ChatColor.DARK_AQUA + "[DropXP] " + ChatColor.BLUE + "Player inventory is full!");
			return true;
		}
		
		//checks if they've entered their arguments correctly
		else if(args != null && args.length >= 1) {
			int xp;
			//checks if they've entered an integer, and gives them the bottle if it is
			try {
				xp = Integer.parseInt(args[0]);
				player.getInventory().addItem(MainListener.makeLevelBottle(xp));
				player.playSound(player.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 0.5f, 1.65f);
			}
			catch(NumberFormatException e) {
				player.sendMessage(ChatColor.DARK_AQUA + "[DropXP] "+ ChatColor.RED + "Invalid Number");
			}
			return true;
		}
		
		//returns false if they don't enter their arguments correctly
		else {
			return false;
		}
	}
	
}
