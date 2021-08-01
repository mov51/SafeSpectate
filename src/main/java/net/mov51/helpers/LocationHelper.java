package net.mov51.helpers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Objects;

import static net.mov51.helpers.ChatHelper.sendLog;

public class LocationHelper {
    public static String LocationToString(Location l){
        String world = Objects.requireNonNull(l.getWorld()).getName();
        String x = String.valueOf(l.getX());
        String y = String.valueOf(l.getY());
        String z = String.valueOf(l.getZ());
        String Y = String.valueOf(l.getYaw());
        String P = String.valueOf(l.getPitch());

        return  world  + "," + x + "," + y + "," + z  + "," + Y  + "," + P;
    }

    public static Location LocationFromString(String s) {
        //split the provided string
        String [] sA = s.split(",");
        
        //separate the array and typecast
        World world = Bukkit.getWorld(sA[0]);
        double x = Double.parseDouble(sA[1]);
        double y = Double.parseDouble(sA[2]);
        double z = Double.parseDouble(sA[3]);
        float Y = Float.parseFloat(sA[4]);
        float P = Float.parseFloat(sA[5]);

        //Parse then return the location
        return new Location(world, x, y, z, Y, P);
    }

    public static void TeleportPlayer(Player p, Location l){
        Location CurrentLocation = p.getLocation();
        if(l != CurrentLocation){
            Location oldL = p.getLocation();
            p.teleport(l);
            sendLog(p.getName() + " has been teleported to " + formatCoords(l) + "from" + formatCoords(oldL));
        }
    }
    
    public static String formatCoords(Location l){
        //format provided location into readable, rounded coordinates
        //todo add an optional world output for cross-world teleports
        return Math.round(l.getX()) + "," + Math.round(l.getY()) + "," + Math.round(l.getZ());
    }
}
