package sntago17.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.ChatColor;

public class ColorUtil {
  public static String colorize(String s) {
    if (s == null)
      return " "; 
    return ChatColor.translateAlternateColorCodes('&', s);
  }
  
  public static List<String> colorize(List<String> s) {
    List<String> newList = new ArrayList<>();
    s.forEach(st -> {
        
        });
    return newList;
  }
  
  public static String[] colorize(String[] lines) {
    return colorize(Arrays.asList((String[])lines.clone())).<String>toArray(new String[0]);
  }
}
