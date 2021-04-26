package net.mov51.helpers;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class GameModeHelper {
    public static void smartSetGameMode(Player p, GameMode g){
        //receives a bukkit player object and bukkit GameMode object
        p.setGameMode(g);
        //sets the user to the provided GameMode as long as they have the generated permission
    }
}
