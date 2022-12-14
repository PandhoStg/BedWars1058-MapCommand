package sntago17.command;

import com.andrei1058.bedwars.api.arena.GameState;
import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.api.language.Language;
import com.andrei1058.bedwars.arena.Arena;

import sntago17.main.Main;
import sntago17.utils.ColorUtil;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CommandPlayMap implements CommandExecutor{
	
private Main plugin; 

	public CommandPlayMap(Main plugin) {
		this.plugin = plugin; 
	}

public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)){
			Bukkit.getConsoleSender().sendMessage(ColorUtil.colorize(plugin.namepl+"&cYou can't run commands from the console."));
			return false;
    }else{
    	
    Player player = (Player) sender;
    IArena arena = Arena.getArenaByPlayer(player);
    FileConfiguration config = plugin.getConfig();
    
    if (arena == null)
      return false; 
    
    if (arena.getStatus() == GameState.waiting || arena.getStatus() == GameState.starting || arena.getStatus() == GameState.playing) {
      player.sendMessage(Language.getMsg(player, "addons.map-command.play-map").replace("{map}", arena.getDisplayName()));
      String path = config.getString("sound.play-map-sound");
      String[] separated = path.split(";");
      try {
    	  int volumen = Integer.valueOf(separated[1]);
    	  float pitch = Float.valueOf(separated[2]);
    	  Sound sound = Sound.valueOf(separated[0]);
    	  for(Player player1 : Bukkit.getOnlinePlayers()) {
    		  player1.playSound(player1.getLocation(), sound, volumen, pitch);
    	  }
      }catch(IllegalArgumentException e) {
    	Bukkit.getConsoleSender().sendMessage(ColorUtil.colorize(plugin.namepl+"&cYou are using an invalid sound for the version."));  
      }
    }
    return true;
    }
  }
}
