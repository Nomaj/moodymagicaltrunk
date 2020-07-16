package me.nomaj.moodymagicaltrunk;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.nomaj.moodymagicaltrunk.item.Printer;
import me.nomaj.moodymagicaltrunk.item.Trunk;
import net.md_5.bungee.api.ChatColor;

public class EventListener implements Listener {
	
	/* ***************************************************************************
	 *  Function: Adds Trunk to player inventory, only 1 allowed per player.
	 * ***************************************************************************/
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (!player.getInventory().contains(Material.BROWN_SHULKER_BOX)) {
			TrunkStorage.addTrunk(player.getName(), new Trunk());
			player.getInventory().addItem(TrunkStorage.getStorage().get(player.getName()));
			player.updateInventory();
		}
	}
	
	/* ***************************************************************************
	 *  Function: Open inventory with 7 lock options
	 * ***************************************************************************/
	@EventHandler
	public void onRightClickTrunk(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		ItemStack item = player.getInventory().getItemInMainHand();
		if ((item.getType() == Material.BROWN_SHULKER_BOX) && ((event.getAction() == Action.RIGHT_CLICK_BLOCK) || (event.getAction() == Action.RIGHT_CLICK_AIR))) {
			TrunkStorage.getStorage().get(player.getName()).setClickedOn(true);
			Inventory trunk_inv = Bukkit.createInventory(null, 27, "Trunk");	
			trunk_inv.setItem(10, Printer.print("Lock 1", "unlock with /trunk unlock 1 <password>"));
			trunk_inv.setItem(11, Printer.print("Lock 2", "unlock with /trunk unlock 2 <password>"));
			trunk_inv.setItem(12, Printer.print("Lock 3", "unlock with /trunk unlock 3 <password>"));
			trunk_inv.setItem(13, Printer.print("Lock 4", "unlock with /trunk unlock 4 <password>"));
			trunk_inv.setItem(14, Printer.print("Lock 5", "unlock with /trunk unlock 5 <password>"));
			trunk_inv.setItem(15, Printer.print("Lock 6", "unlock with /trunk unlock 6 <password>"));
			trunk_inv.setItem(16, Printer.print("Lock 7", "unlock with /trunk unlock 7 <password>"));
			player.openInventory(trunk_inv);
		} 
	}
	
	/* ***************************************************************************
	 *  Function: Send message to user. User is now allowed to use commands.
	 * ***************************************************************************/
	@EventHandler
	public void onClickInInventory(InventoryClickEvent event) {
		Player player = (Player)event.getWhoClicked();
		ItemStack item = player.getInventory().getItemInMainHand();
		if (item.getType() == Material.BROWN_SHULKER_BOX && TrunkStorage.getStorage().get(player.getName()).getClickedOn()) {
			ItemStack clickedItem = event.getCurrentItem();
			if (clickedItem.getType() == Material.PAPER) {
				ItemMeta im = clickedItem.getItemMeta();
				int lockNumber = Integer.parseInt(im.getDisplayName().split(" ")[1]);			
				player.closeInventory();
				TrunkStorage.getStorage().get(player.getName()).setCommandAllow(true); 
				TrunkStorage.getStorage().get(player.getName()).setClickedOn(false);
				player.sendMessage(ChatColor.DARK_RED + "Type " + ChatColor.GOLD + String.format("/trunk unlock %d <Password>", lockNumber) + ChatColor.DARK_RED + "to unlock it.");
			} 
		} 
	}
	
	@EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        Material m = e.getItemDrop().getItemStack().getType();
        if(m == Material.PAPER || m == Material.BROWN_SHULKER_BOX) {
        	e.setCancelled(true);
        }
    }
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		Material m = e.getBlock().getType();
		if (m == Material.BROWN_SHULKER_BOX) {
			e.setCancelled(true);
		}
	}
}