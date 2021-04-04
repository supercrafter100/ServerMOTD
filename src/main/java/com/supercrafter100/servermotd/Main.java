package com.supercrafter100.servermotd;

import com.supercrafter100.servermotd.Command.ServerMOTDCommand;
import com.supercrafter100.servermotd.Listeners.EventListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        this.getCommand("setmotd").setExecutor(new ServerMOTDCommand(this));
        this.getServer().getPluginManager().registerEvents(new EventListener(this), this);
    }

    @Override
    public void onDisable() {
        this.saveConfig();
    }
}
