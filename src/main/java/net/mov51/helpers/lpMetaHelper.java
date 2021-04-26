package net.mov51.helpers;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.NodeType;
import net.luckperms.api.node.types.MetaNode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import static net.mov51.helpers.LocationHelper.LocationToString;
import static net.mov51.helpers.LocationHelper.LocationFromString;

public class lpMetaHelper {

    static LuckPerms LuckPermsAPI = LuckPermsProvider.get();
    public static String MetaKey = "spectate-location";

    public static void saveLocation(Player p, Location l) {
        // obtain a User instance (by any means! see above for other ways)
        User user = LuckPermsAPI.getPlayerAdapter(Player.class).getUser(p);
        String s = LocationToString(l);

        // create a new MetaNode holding the level value
        // of course, this can have context/expiry/etc too!

        MetaNode node = MetaNode.builder(MetaKey, s).build();
        // clear any existing meta nodes with the same key - we want to override
        user.data().clear(NodeType.META.predicate(mn -> mn.getMetaKey().equals(MetaKey)));
        // add the new node
        user.data().add(node);

        // save!
        LuckPermsAPI.getUserManager().saveUser(user);
    }

    public static Location getLocation(Player p) {
        // obtain CachedMetaData - the easiest way is via the PlayerAdapter
        // of course, you can get it via a User too if the player is offline.
        CachedMetaData metaData = LuckPermsAPI.getPlayerAdapter(Player.class).getMetaData(p);

        String s = metaData.getMetaValue(MetaKey);
        assert s != null;
        // query & parse the meta value
        return LocationFromString(s);
    }

    public static boolean isLocation(Player p){
        CachedMetaData metaData = LuckPermsAPI.getPlayerAdapter(Player.class).getMetaData(p);
        String s = metaData.getMetaValue(MetaKey);
        return s != null;
    }

    public static void clearLocation(Player p){
        User user = LuckPermsAPI.getPlayerAdapter(Player.class).getUser(p);
        user.data().clear(NodeType.META.predicate(mn -> mn.getMetaKey().equals(MetaKey)));
        LuckPermsAPI.getUserManager().saveUser(user);
    }
}
