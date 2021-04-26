package net.mov51;

import net.luckperms.api.LuckPerms;
import net.mov51.commands.Spectate;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class SafeSpectate extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        //todo register command
        Objects.requireNonNull(getCommand("SpectateSafe")).setExecutor(new Spectate());

        RegisteredServiceProvider<LuckPerms> LPprovider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
