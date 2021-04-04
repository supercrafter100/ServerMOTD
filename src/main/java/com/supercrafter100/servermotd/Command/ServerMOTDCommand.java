package com.supercrafter100.servermotd.Command;

import com.supercrafter100.servermotd.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class ServerMOTDCommand implements CommandExecutor {

    Main plugin;

    public ServerMOTDCommand(Main pl) {
        this.plugin = pl;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission(plugin.getConfig().getString("main.permission"))) {
            sender.sendMessage(format(plugin.getConfig().getString("messages.no_permission")));
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage(format(plugin.getConfig().getString("messages.usage")));
            return true;
        }

        int line;
        try {
            line = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            sender.sendMessage(format(plugin.getConfig().getString("messages.invalid_line")));
            return true;
        }

        if (line < 1 || line > 2) {
            sender.sendMessage(format(plugin.getConfig().getString("messages.only_2_line")));
            return true;
        }

        List<String> motd = plugin.getConfig().getStringList("motd");

        StringBuilder sb = new StringBuilder(args[1]);
        for (int i = 2; i < args.length; i++) {
            sb.append(' ').append(args[i]);
        }
        String newMotd = sb.toString();

        motd.set(line - 1, newMotd);
        plugin.getConfig().set("motd", motd);
        plugin.saveConfig();

        String message = plugin.getConfig().getString("messages.successfully_changed")
                .replace("{line}", String.valueOf(line))
                .replace("{motd}", newMotd);

        sender.sendMessage(format(message));
        return true;
    }

    private String format(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
