package net.mov51.helpers;

import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.mov51.helpers.ChatHelper.sendChat;
import static net.mov51.helpers.GameModeHelper.setSpectatorAndSave;
import static net.mov51.helpers.GameModeHelper.setSurvivalAndReturn;
import static net.mov51.helpers.PermissionHelper.*;
import static org.bukkit.Bukkit.getPlayerExact;

public class ToggleHelper {

    public static boolean GameModeToggle(CommandSender sender) {
        if(isPlayer(sender)){
            //get Player Object
            Player p = (Player) sender;
            //Get player GameMode Enum
            GameMode pGameMode = p.getGameMode();

            //Switch on Player GameMode
            switch (pGameMode){
                //isSpectator?
                case SPECTATOR:
                    setSurvivalAndReturn(p);
                    break;
                //isSurvival?
                case SURVIVAL:
                    setSpectatorAndSave(p);
                    break;
                default:
                    //not sure how you got here, but you really shouldn't be, lol
                    sendChat("What are you?",p);
            }
        }
        return false;
    }

    public static void toggleOtherGameMode(CommandSender sender,String username,GameMode g){
        //Gets the player object using their username
        Player p = getPlayerExact(username);
        //Gets the sender as a player object
        Player pSender = (Player) sender;
        if (p != null){
            //is the player already the target GameMode?
            if(!isGameMode(p, g)){
                //Toggle their GameMode
                GameModeToggle(p);
                //Notify the player of who triggered the change
                sendChat("You've been changed to " + g.toString() + " by " + pSender.getName() +".",p);
            }
        }else{
            sendChat("That player isn't online!",pSender);
        }
    }

    //Method over load for toggleOtherGameMode(CommandSender sender,String username,GameMode g)
    //Acts as an actual toggle and does not require a GameMode as an input
    public static void toggleOtherGameMode(CommandSender sender,String username){
        Player p = getPlayerExact(username);
        Player pSender = (Player) sender;

        //Define the target GameMode
        //The default GameMode is spectator
        GameMode targetG = GameMode.SPECTATOR;

        if (p != null){
            //If the player is in spectator mode, then the target GameMode is changed to survival
            if(isGameMode(p, GameMode.SPECTATOR)){
                targetG = GameMode.SURVIVAL;
            }

            if(!isGameMode(p, targetG)){
                //Calls the original method with the new Target GameMode
                toggleOtherGameMode(sender,username,targetG);
            }
        }else{
            sendChat("That player isn't online or doesn't exist!",pSender);
        }
    }
}


