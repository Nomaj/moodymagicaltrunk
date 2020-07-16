package me.nomaj.moodymagicaltrunk;

import java.util.HashMap;

import me.nomaj.moodymagicaltrunk.item.Trunk;

/* ***************************************************************************
 *  Function: Store player name and trunk info.
 * ***************************************************************************/
public class TrunkStorage {	
	public static HashMap<String, Trunk> storage = new HashMap<String, Trunk>();
	
	public static void addTrunk(String name, Trunk trun) {
		storage.put(name, trun);
	}
	
	public static HashMap<String, Trunk> getStorage() {
		return storage;
	}
}
