package sntago17.main;

import com.andrei1058.bedwars.BedWars;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import sntago17.command.CommandMap;
import sntago17.utils.ColorUtil;

public class Main extends JavaPlugin{
	//public statics importantes  
	public static BedWars bw;	  
	public static Main plugin;
	
	  //public String Integer;
	  public String playMap;
	  public String playNotMap;
	  
	public static Main instance;
	  
	//Obtiene los datos del plugin.yml
	PluginDescriptionFile pdffile = getDescription(); 	
	public String version = pdffile.getVersion();
	public String namepl = ColorUtil.colorize("&8[&c"+pdffile.getName()+"&8] ");	
	
	public void onEnable(){
		//El pl se desabilitara si no se encuentra (BedWars1058)	
	    if (Bukkit.getPluginManager().getPlugin("BedWars1058") == null) {
            getLogger().severe("BedWars1058 was not found. Disabling...");
            Bukkit.getPluginManager().disablePlugin(this);
            return;   	
	    	}
	    //Creando la carpeta de BedWars-Map
		  File folder = new File("plugins/BedWars1058/Addons/MapCommand");
		    if (!folder.exists())
		      folder.mkdirs(); 
		    File configFile = new File(folder, "config.yml");
		    if (!configFile.exists())
		      try {
		        Files.copy(getResource("config.yml"), configFile.toPath(), new java.nio.file.CopyOption[0]);
		      } catch (IOException e) {
		        e.printStackTrace();
		      }  
		    initConfig(configFile);
		  //Mensajes de Entrada 
			Bukkit.getConsoleSender().sendMessage(ColorUtil.colorize(namepl+"&aThe plugin has been activated successfully."));
			Bukkit.getConsoleSender().sendMessage(ColorUtil.colorize("&aAuthor: &fSntago17"));
			Bukkit.getConsoleSender().sendMessage(ColorUtil.colorize("&aVersion: &e"+version));
			Bukkit.getConsoleSender().sendMessage(ColorUtil.colorize("&aDescription: &fYou can see the name of the arena where you are"));
			Bukkit.getConsoleSender().sendMessage(ColorUtil.colorize("&cAn Addon of BedWars1058 &8--- &6Enjoy it..."));	
		    //registro de comandos
		    registerCommands();    
		  }
	public void onDisable(){
		Bukkit.getConsoleSender().sendMessage(ColorUtil.colorize(namepl+"&cThe plugin has been desactivated."));
		Bukkit.getConsoleSender().sendMessage(ColorUtil.colorize("&aAuthor: &fSntago17"));
		Bukkit.getConsoleSender().sendMessage(ColorUtil.colorize("&aVersion: &e"+version));
		Bukkit.getConsoleSender().sendMessage(ColorUtil.colorize("&aDescription: &fYou can see the name of the arena where you are"));
		Bukkit.getConsoleSender().sendMessage(ColorUtil.colorize("&cAn Addon of BedWars1058"));
	}
	
	public void registerCommands(){
		this.getCommand("map").setExecutor(new CommandMap(this)); ;
	}
	  
	  public void initConfig(File file) {
	    YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
	    config.options().copyDefaults(true);
	    config.addDefault("sound.play-map-sound", "NOTE_PLING;10;2");
	    config.addDefault("sound.play-notmap-sound", "NOTE_PLING;10;2");
	    config.options().header("Map Command Add-on for BedWars1058 Mini-game.\nDocumentation:\nSounds for latest version: https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Sound.html\nSounds for 1.8 or older: http://docs.codelanx.com/Bukkit/1.8/org/bukkit/Sound.html");
	    try {
	        config.save(file);
	      } catch (IOException iOException) {}
	    this.playMap = config.getString("sound.play-map-sound");
	    this.playNotMap = config.getString("sound.play-notmap-sound");
	  }
}
