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

        for (int i = 0; i < motd.size(); i++) {
            motd.set(i, format(motd.get(i)));
        }
        e.setMotd(String.join("\n", motd));
    }

    private String format(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
