package net.mov51.helpers;

import org.bukkit.entity.Player;

import static net.mov51.SafeSpectate.chatPrefix;
import static net.mov51.SafeSpectate.logPrefix;

public class ChatHelper {

    //prefix used for plugin messages


    public static void sendChat(String message, Player player){
        player.sendMessage(chatPrefix + message);
    }

    public static void sendLog(String message){
        System.out.println(logPrefix + message);
    }

    public static void sendWarn(Player p, String type){
        switch (type){
            case "noPerm":
                sendChat("Sorry! You don't have permission to do that! If you need help please contact staff!",p);
            case "noOverride":
                sendChat("Sorry! Only staff members can avoid the return teleport!",p);
        }
    }
}
