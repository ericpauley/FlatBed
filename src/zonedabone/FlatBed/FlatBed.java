package zonedabone.FlatBed;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World.Environment;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

public class FlatBed extends JavaPlugin {

	public static Configuration chunks = new Configuration(new File("plugins/FlatBed/chunks.yml"));
	public static PluginDescriptionFile pdf;
	Logger log = Logger.getLogger("minecraft");
	private final FlatBedWorldListener worldListener = new FlatBedWorldListener(this);
	
	@Override
	public void onDisable() {
		chunks.save();
		log.info(pdf.getName() + " version " + pdf.getVersion() + " DISABLED");

	}

	@Override
	public void onEnable() {
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvent(Event.Type.CHUNK_LOAD, worldListener, Event.Priority.Normal, this);
				
		chunks.load();pdf = this.getDescription();
		log.info(pdf.getName() + " version " + pdf.getVersion() + " ENABLED");

	}
	
	public void flatten(Chunk chunk){
		String path = Integer.toString(chunk.getX())+'.'+Integer.toString(chunk.getZ());
		if(!FlatBed.chunks.getBoolean(path, false)){
			FlatBed.chunks.setProperty(path, true);
			if(chunk.getWorld().getEnvironment()!=Environment.SKYLANDS){
				for(int x = 0;x<16;x++){
					for(int z = 0;z<16;z++){
						chunk.getBlock(x, 0, z).setType(Material.BEDROCK);
						for(int y = 1;y<6;y++){
							Block block = chunk.getBlock(x, y, z);
							if(block.getType()==Material.BEDROCK){
								block.setType(Material.STONE);
							}
						}
					}
				}
				if(chunk.getWorld().getEnvironment()==Environment.NETHER){
					for(int x = 0;x<16;x++){
						for(int z = 0;z<16;z++){
							chunk.getBlock(x, 127, z).setType(Material.BEDROCK);
							for(int y = 122;y<127;y++){
								Block block = chunk.getBlock(x, y, z);
								if(block.getType()==Material.BEDROCK){
									block.setType(Material.STONE);
								}
							}
						}
					}
				}
			}
		}
	}

}
