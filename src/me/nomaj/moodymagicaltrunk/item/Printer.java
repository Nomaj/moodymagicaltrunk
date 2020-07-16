package me.nomaj.moodymagicaltrunk.item;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

/* ***************************************************************************
 *  Function: Printer go brrrrrrr. Used to create paper with custom name and lore.
 * ***************************************************************************/
public class Printer {
	public static ItemStack print(String name, String description) {
		ItemStack paper = new ItemStack(Material.PAPER);
		
		ItemMeta im = paper.getItemMeta();
		im.setDisplayName("" + ChatColor.BOLD + name);
		ArrayList<String> lore = new ArrayList<>();
		lore.add(description);
		im.setLore(lore);
		im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		paper.setItemMeta(im);
		return paper;
	}
}
