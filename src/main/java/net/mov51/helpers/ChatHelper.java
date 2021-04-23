package net.mov51.helpers;

import org.bukkit.entity.Player;

public class ChatHelper {

    //prefix used for plugin messages
    //todo add a config for value
    public static final String PREFIX = "§6§l[§2Naspen§6§l]§r";

    public static void sendChat(String message, Player player){
        player.sendMessage(PREFIX + message);
    }
}
