package net.mov51.helpers;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.mov51.helpers.ChatHelper.*;
import static net.mov51.helpers.LocationHelper.TeleportPlayer;
import static net.mov51.helpers.LocationHelper.formatCords;
import static net.mov51.helpers.PermissionHelper.isPlayer;
import static net.mov51.helpers.lpMetaHelper.*;
import static org.bukkit.Bukkit.getPlayerExact;
import static org.bukkit.Bukkit.getServer;

public class GameModeHelper {
    public static void smartSetGameMode(Player p, GameMode g){
        //receives a bukkit player object and bukkit GameMode object
        GameMode oldG = p.getGameMode();
        p.setGameMode(g);
        sendLog(p.getName() + " has been set to GameMode " + g + " from " + oldG);
        //sets the user to the provided GameMode as long as they have the generated permission
    }

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
                    //If the player isn't in Spectator or Survival then they need to specify their intent
                    sendWarn(p, "You're not Toggleable! Please use /gms or /gmsp to specify what gameMode you want to be in!");
            }
        }
        return false;
    }

    public static void setSpectatorAndSave(Player p){
        //Is player already in Spectator mode?
        if(!isGameMode(p,GameMode.SPECTATOR)){
            //get current Location of player
            Location l = p.getLocation();
            //Save location to LuckPerms
            saveLocation(p, l);
            //Set game mode to spectator
            GameModeHelper.smartSetGameMode(p,GameMode.SPECTATOR);
            //warn player of return location
            sendChat(p, "You will be returned to " + formatCords(l) + " when you exit spectator mode!");
        } else {
            sendWarn(p, "You're already in Spectator mode!");
        }
    }

    public static void setSpectatorAndSave(CommandSender sender){
        Player p = (Player) sender;
        setSpectatorAndSave(p);
    }

    public static void setOtherSpectatorAndSave(CommandSender sender,String username){
        //Gets the player object using their username
        Player p = getPlayerExact(username);
        //Gets the sender as a player object
        Player pSender = (Player) sender;
        if (p != null){
            //is the player already the target GameMode?
            if(!isGameMode(p, GameMode.SPECTATOR)){
                //Toggle their GameMode
                setSpectatorAndSave(p);
                //Notify the player of who triggered the change
                sendChat(p, "You've been changed to Spectator by " + pSender.getName() +".");
            }
        }else{
            sendWarn(pSender, "That player isn't online!");
        }
    }


    public static void setSurvivalAndOverride(Player p){
        if(!isGameMode(p,GameMode.SURVIVAL)){
            if(isLocation(p)) {
                Location l = getLocation(p);
                sendChat(p, "You've overridden your return to " + formatCords(l) + ".");
            }else{
                sendChat(p, "There's no location to override");
            }
            GameModeHelper.smartSetGameMode(p, GameMode.SURVIVAL);
        }else{
            sendWarn(p,"You're already in Survival mode!");
        }
    }

    public static void setSurvivalAndOverride(CommandSender sender){
        Player p = (Player) sender;
        setSurvivalAndOverride(p);
    }

    public static void setSurvivalAndReturn(Player p){
        //is the player already in Survival mode?
        if (!isGameMode(p, GameMode.SURVIVAL)) {
            //is the Location null?
            //Should only happen if it's the first time the player is leaving spectator mode
            if(isLocation(p)){
                //Get location from LuckPerms
                Location l = getLocation(p);

                //Teleports player to the acquired location
                TeleportPlayer(p,l);
                //Warn player of return location
                sendChat(p, "You've been returned to " + formatCords(l) + "!");
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
                sendChat(p, "No Location to return to. You've been sent to your spawn instead <3");
            }
        } else {
            sendWarn(p, "You're already in Survival mode!");
        }
    }

    public static void setSurvivalAndReturn(CommandSender sender){
        Player p = (Player) sender;
        setSurvivalAndReturn(p);
    }

    public static void setOtherSurvivalAndReturn(CommandSender sender,String username){
        //Gets the player object using their username
        Player p = getPlayerExact(username);
        //Gets the sender as a player object
        Player pSender = (Player) sender;
        if (p != null){
            //is the player already the target GameMode?
            if(!isGameMode(p, GameMode.SURVIVAL)){
                //Toggle their GameMode
                setSurvivalAndReturn(p);
                //Notify the player of who triggered the change
                sendWarn(p, "You've been changed to Survival by " + pSender.getName() +".");
            }
        }else{
            sendWarn(pSender, "That player isn't online!");
        }
    }

    public static boolean isGameMode(Player p, GameMode G){
        return p.getGameMode() == G;
    }

    public static boolean isGameMode(CommandSender s, GameMode G){
        Player p = (Player) s;
        return isGameMode(p,G);
    }
}
