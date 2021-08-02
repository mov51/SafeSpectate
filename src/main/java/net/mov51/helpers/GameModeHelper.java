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
                    sendChat("You're not Toggleable! Please use /gms or /gmsp to specify what gameMode you want to be in!",p);
            }
        }
        return false;
    }

    public static void setSpectatorAndSave(Player p){
        //Save location to LuckPerms
        Location l = p.getLocation();
        saveLocation(p, l);
        //output location
        //Set game mode to spectator
        GameModeHelper.smartSetGameMode(p,GameMode.SPECTATOR);
        //warn player of return location
        sendChat("You will be returned to " + formatCords(l) + " when you exit spectator mode!", p);
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
                sendChat("You've been changed to Spectator by " + pSender.getName() +".",p);
            }
        }else{
            sendChat("That player isn't online!",pSender);
        }
    }


    public static void setSurvivalAndOverride(Player p){
        if(isLocation(p)) {
            Location l = getLocation(p);
            sendChat("You've overridden your return to " + formatCords(l) + ".", p);
        }else{
            sendChat("There's no location to override", p);
        }
        GameModeHelper.smartSetGameMode(p, GameMode.SURVIVAL);
    }

    public static void setSurvivalAndOverride(CommandSender sender){
        Player p = (Player) sender;
        setSurvivalAndOverride(p);
    }

    public static void setSurvivalAndReturn(Player p){
        //is the Location null?
        //Should only happen if it's the first time the player is leaving spectator mode
        if(isLocation(p)){
            //Get location from LuckPerms
            Location l = getLocation(p);

            //Teleports player to the acquired location
            TeleportPlayer(p,l);
            //Warn player of return location
            sendChat("You've been returned to " + formatCords(l) + "!", p);
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
                setSpectatorAndSave(p);
                //Notify the player of who triggered the change
                sendChat("You've been changed to Survival by " + pSender.getName() +".",p);
            }
        }else{
            sendChat("That player isn't online!",pSender);
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
