package sntago17.configuration;

import com.andrei1058.bedwars.api.language.Language;

public class Config{
	
  public static void setupMessages() {
    for (Language l : Language.getLanguages()) {
      if (!l.exists("addons.map.play-map"))
        l.set("addons.map.play-map", "{prefix}&aYou are currently playing on &e{map}");  
    } 
  }
}
