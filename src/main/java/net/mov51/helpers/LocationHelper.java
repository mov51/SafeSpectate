package net.mov51.helpers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.Objects;

public class LocationHelper {
    public static String LocationToString(Location l){
        String x = String.valueOf(l.getX());
        String y = String.valueOf(l.getY());
        String z = String.valueOf(l.getZ());
        String world = Objects.requireNonNull(l.getWorld()).toString();

        return x + "," + y + "," + z + "," + world;
    }

    public static Location LocationFromString(String s) {
        String[] sA = s.split(",");
        double x = Double.parseDouble(sA[0]);
        double y = Double.parseDouble(sA[1]);
        double z = Double.parseDouble(sA[2]);
        World world = Bukkit.getServer().getWorld(sA[3]);
        return new Location(world, x, y, z);
    }
}
