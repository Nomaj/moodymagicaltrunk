package me.nomaj.moodymagicaltrunk.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.nomaj.moodymagicaltrunk.TrunkStorage;
import net.md_5.bungee.api.ChatColor;

public class TrunkCommands implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player)sender;
		ItemStack item = player.getInventory().getItemInMainHand();
		
		/* ***************************************************************************
		 *  Condition: item in main hand is a Brown_Shulker_Box
		 * ***************************************************************************/
		if (item.getType() == Material.BROWN_SHULKER_BOX) {
			/* ***************************************************************************
			 *  Condition: player used /trunk and player is allowed to use /trunk
			 * ***************************************************************************/
			if (label.equalsIgnoreCase("trunk") && TrunkStorage.getStorage().get(player.getName()).getCommandAllow()) {    
				/* ***************************************************************************
				 *  Command: /trunk
				 * ***************************************************************************/
				if (args.length == 0) {
					player.sendMessage(ChatColor.RED + "\n======================================================");
					player.sendMessage(ChatColor.RED + "Right click the trunk to open a menu, click on a lock");
					player.sendMessage(ChatColor.RED + "You will be prompted to enter the password, the default password is the lock number");
					player.sendMessage(ChatColor.RED + "Locks automatically are locked after closing trunk inventory");
					player.sendMessage(ChatColor.RED + "/trunk unlock <1-7> <password>  Unlock lock #");
					player.sendMessage(ChatColor.RED + "/trunk set <1-7> <password>    	Set password for lock <1-7>");
					player.sendMessage(ChatColor.RED + "/trunk set reset     			Clear all passwords");
					player.sendMessage(ChatColor.RED + "/trunk remove     				remove trunk");
					player.sendMessage(ChatColor.RED + "======================================================\n");
					return true;
				}
				/* ***************************************************************************
				 *  Command: /trunk remove
				 * ***************************************************************************/
				if (args.length ==1) {
					if (args[0].equalsIgnoreCase("remove")) {
						player.getInventory().remove(Material.BROWN_SHULKER_BOX);
					}
				}
				/* ***************************************************************************
				 *  Command: /trunk set reset
				 * ***************************************************************************/
				if (args.length == 2) {
					if (args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("reset")) {
							TrunkStorage.getStorage().get(player.getName()).resetPasswords();
							player.sendMessage(ChatColor.RED + "All lock passwords have been reset.");
							return true;
					}
				}
				
				/* ***************************************************************************
				 *  Command: /trunk set <1-7> <password> or /trunk unlock <1-7> <password>
				 * ***************************************************************************/
				if (args.length == 3 && ((Integer.parseInt(args[1]) >= 1) && (Integer.parseInt(args[1]) <= 7))) {
					if (args[0].equalsIgnoreCase("set")) {
						TrunkStorage.getStorage().get(player.getName()).setPassword(args[1], args[2]);
						player.sendMessage(String.format(ChatColor.GREEN + "Password for lock %d has been changed.", Integer.parseInt(args[1])));
						TrunkStorage.getStorage().get(player.getName()).setCommandAllow(false);
						return true;
					} 
					if (args[0].equalsIgnoreCase("unlock")) {
						if (TrunkStorage.getStorage().get(player.getName()).checkPassword(args[1], args[2])) {
							player.sendMessage(String.format(ChatColor.GREEN + "Lock %d has been unlocked.", Integer.parseInt(args[1])));
							player.openInventory(TrunkStorage.getStorage().get(player.getName()).getInventoryList()[Integer.parseInt(args[1])-1]);
							TrunkStorage.getStorage().get(player.getName()).setCommandAllow(false);
							return true;
						} 
						player.sendMessage(ChatColor.RED + "Wrong password.");
						player.sendMessage(args[2]); //
						return true;
					} 
				}
				player.sendMessage(ChatColor.RED + "Command is invalid. Type /trunk for commands list.");
				return false;
			}
			/* ***************************************************************************
			 *  Condition: Player used /trunk and player is not allowed to use /trunk
			 * ***************************************************************************/
			if (label.equalsIgnoreCase("trunk") && !(TrunkStorage.getStorage().get(player.getName()).getCommandAllow())) {
				player.sendMessage(ChatColor.RED + "Right click and select lock to use commands");
				return true;
			} 
		}
		/* ***************************************************************************
		 *  Condition: player used /trunk and item in main hand is not a Brown_Shulker_box
		 * ***************************************************************************/
		if (label.equalsIgnoreCase("trunk") && !(item.getType() == Material.BROWN_SHULKER_BOX)) {
			player.sendMessage("You must be holding Moody's Magical Trunk to use this command.");
			return true;
		} 
		return false;
	}
}