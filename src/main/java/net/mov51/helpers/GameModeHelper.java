package net.mov51.helpers;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import static net.mov51.helpers.ChatHelper.sendLog;

public class GameModeHelper {
    public static void smartSetGameMode(Player p, GameMode g){
        //receives a bukkit player object and bukkit GameMode object
        GameMode oldG = p.getGameMode();
        p.setGameMode(g);
        sendLog(p.getName() + " has been set to gamemode " + g + " from " + oldG);
        //sets the user to the provided GameMode as long as they have the generated permission
    }
}
