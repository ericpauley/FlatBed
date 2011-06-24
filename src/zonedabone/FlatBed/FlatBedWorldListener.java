package zonedabone.FlatBed;

import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.WorldListener;


public class FlatBedWorldListener extends WorldListener {
	
	public static FlatBed plugin;
	
	public FlatBedWorldListener(FlatBed instance) {
		plugin = instance;
	}
	
	public void onChunkLoad(ChunkLoadEvent e){
		plugin.flatten(e.getChunk());
	}

}
