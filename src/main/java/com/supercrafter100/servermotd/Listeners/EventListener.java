package com.supercrafter100.servermotd.Listeners;

import com.supercrafter100.servermotd.Main;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.util.List;

public class EventListener implements Listener {

    Main plugin;

    public EventListener(Main pl) {
        this.plugin = pl;
    }

    @EventHandler
    public void serverPing(ServerListPingEvent e) {
        List<String> motd = plugin.getConfig().getStringList("motd");
        e.setMotd(ChatColor.translateAlternateColorCodes('&', String.join("\n", motd)));
    }
}
