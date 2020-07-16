package me.nomaj.moodymagicaltrunk.item;

import net.md_5.bungee.api.ChatColor;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Trunk extends ItemStack {
	private static final Material material = Material.BROWN_SHULKER_BOX;
	private static final String name = ChatColor.MAGIC + "O" + ChatColor.RESET + ChatColor.GOLD + ChatColor.BOLD + "Moody's Magical Trunk" + ChatColor.RESET + ChatColor.MAGIC + "O";
	private static final String description = ChatColor.GREEN + "Owned by: Alastor Moody";
	private static final int amount = 1;
	
	private Inventory [] list = {Bukkit.createInventory(null, 9, "" + ChatColor.DARK_GREEN + "Compartment 1"), Bukkit.createInventory(null, 9, "" + ChatColor.DARK_GREEN + "Compartment 2"), Bukkit.createInventory(null, 9, "" + ChatColor.BOLD + ChatColor.GOLD + "Compartment 3"), Bukkit.createInventory(null, 9, "" + ChatColor.DARK_GREEN + "Compartment 4"), Bukkit.createInventory(null, 9, "" + ChatColor.DARK_GREEN + "Compartment 5"), Bukkit.createInventory(null, 9, "" + ChatColor.DARK_GREEN + "Compartment 6"), Bukkit.createInventory(null, 27, "" + ChatColor.DARK_GREEN + "Compartment 7")};  
	private String[] passwords = {"1","2","3","4","5","6","7"};
	private boolean commandAllow = false;
	private boolean clickedOn = false;
	
	public Trunk() {
		super(material, amount);
		ItemMeta im = this.getItemMeta();
		im.setDisplayName(name);
		ArrayList<String> lore = new ArrayList<>();
		lore.add(description);
		im.setLore(lore);
		im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		this.setItemMeta(im);
	}
	
	public void resetPasswords() {
		String [] list = {"1","2","3","4","5","6","7"};
		this.passwords = list;
	}
	
	public boolean checkPassword(String lockNumber, String password) {
		return (this.passwords[Integer.parseInt(lockNumber) - 1].equals(password));
	}
	
	public String[] getPasswords() {
		return this.passwords;
	}
	
	public void setPassword(String lockNumber, String password) {
		this.passwords[Integer.parseInt(lockNumber) - 1] = password;
	}
	
	public boolean getCommandAllow() {
		return commandAllow;
	}
	
	public void setCommandAllow(boolean val) {
		commandAllow = val;
	}
	
	public boolean getClickedOn() {
		return clickedOn;
	}

	public void setClickedOn(boolean val) {
		clickedOn = val;
	}
	
	public Inventory [] getInventoryList() {
		return list;
	}
}