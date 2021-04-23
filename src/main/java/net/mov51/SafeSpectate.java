package net.mov51;

import net.mov51.commands.Spectate;
import org.bukkit.plugin.java.JavaPlugin;

public final class SafeSpectate extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        //todo register command
        getCommand("SpectateSafe").setExecutor(new Spectate());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
