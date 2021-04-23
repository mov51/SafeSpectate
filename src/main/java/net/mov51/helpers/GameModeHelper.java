package net.mov51.helpers;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class GameModeHelper {
    public void smartSetGameMode(Player p, GameMode g){
        //receives a bukkit player object and bukkit GameMode object
        if(p.hasPermission("SafeSpectate." + g.toString())){
            //Generates a permission for passed GameMode using the Enum string https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/GameMode.html
            p.setGameMode(g);
            //sets the user to the provided GameMode as long as they have the generated permission
        }
    }
}
