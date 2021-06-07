package net.mov51.helpers;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.mov51.helpers.ChatHelper.sendChat;
import static net.mov51.helpers.LocationHelper.TeleportPlayer;
import static net.mov51.helpers.LocationHelper.formatCoords;
import static net.mov51.helpers.PermissionHelper.*;
import static net.mov51.helpers.lpMetaHelper.*;
import static org.bukkit.Bukkit.getPlayerExact;
import static org.bukkit.Bukkit.getServer;

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
                        //is the Location null?
                        //Should only happen if it's the first time the player is leaving spectator mode
                        if(isLocation(p)){
                            //Get location from LuckPerms
                            Location l = getLocation(p);

                            //Teleports player to the acquired location
                            TeleportPlayer(p,l);
                            //Warn player of return location
                            sendChat("You've been returned to " + formatCoords(l) + "!", p);
                            //Set new GameMode
                            GameModeHelper.smartSetGameMode(p, GameMode.SURVIVAL);
                            //Remove stored Return Location
                            clearLocation(p);
                        }else{
                            //Get player's bed spawn
                            Location l = p.getBedSpawnLocation();
                            //Is bed null?
                            if(l != null){
                                //if no, send player to bed
                                TeleportPlayer(p,l);
                            }else{
                                //if yes send to server spawn
                                //Get the default OverWorld (id0) and then get the current spawn location for that world
                                Location WorldSpawn = getServer().getWorlds().get(0).getSpawnLocation();
                                //Teleport the player to server spawn
                                TeleportPlayer(p,WorldSpawn);
                            }
                            //Now that they've been moved, set their GameMode
                            GameModeHelper.smartSetGameMode(p, GameMode.SURVIVAL);
                            //Warn the player about why they were sent here
                            sendChat("No Location to return to. You've been sent to your spawn instead <3", p);
                        }
                    break;
                //isSurvival?
                case SURVIVAL:
                        //Save location to LuckPerms
                        Location l = p.getLocation();
                        saveLocation(p, l);
                        //output location
                        //Set game mode to spectator
                        GameModeHelper.smartSetGameMode(p,GameMode.SPECTATOR);
                        //warn player of return location
                        sendChat("You will be returned to " + formatCoords(l) + " when you exit spectator mode!", p);
                    break;
                default:
                    //not sure how you got here, but you really shouldn't be, lol
                    sendChat("What are you?",p);
            }
        }
        return false;
    }

    public static void toggleOtherGameMode(CommandSender sender,String username,GameMode g){
        //Get's the player object using their username
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

    //Method overload for toggleOtherGameMode(CommandSender sender,String username,GameMode g)
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
            sendChat("That player isn't online!",pSender);
        }
    }
}


