package zonedabone.FlatBed;

import java.io.File;
import java.util.logging.Logger;

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

}
