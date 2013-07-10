package com.daemitus.deadbolt.events;

import org.bukkit.Bukkit;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.InventoryHolder;

import com.daemitus.deadbolt.Deadbolt;
import com.daemitus.deadbolt.DeadboltPlugin;
import com.daemitus.deadbolt.Deadbolted;

public class InventoryListener implements Listener {

	private final DeadboltPlugin plugin = Deadbolt.getPlugin();

	public InventoryListener() {
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler(ignoreCancelled = true)
	public void onInventoryMoveItem(InventoryMoveItemEvent event) {
		InventoryHolder holder = event.getSource().getHolder();
		if(holder instanceof BlockState) {
			BlockState chest = (BlockState)holder;
			Deadbolted db = Deadbolt.get(chest.getBlock());

			if (db.isProtected() && !db.isAutoExpired()) {
				event.setCancelled(true);
			}
		}
	}
}