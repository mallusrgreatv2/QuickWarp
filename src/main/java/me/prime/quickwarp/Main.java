package me.prime.quickwarp;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;

public class QuickWarpMain implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can execute this command.");
            return true;
        }

        World targetWorld;
        switch (command.getName().toLowerCase()) {
            case "end":
                targetWorld = Bukkit.getWorld("world_the_end");
                break;
            case "overworld":
                targetWorld = Bukkit.getWorld("world");
                break;
            case "nether":
                targetWorld = Bukkit.getWorld("world_nether");
                break;
            default:
                player.sendMessage("Invalid command. Use /end, /overworld or /nether.");
                return true;
        }

        if (player.getWorld().equals(targetWorld)) {
            player.sendMessage("You cannot warp to the dimension you are in...");
            return true;
        }

        Location targetLocation = new Location(targetWorld, 0, 64, 0);
        player.teleport(targetLocation);
        return true;
    }
}



public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("end").setExecutor(new QuickWarp());
        getCommand("overworld").setExecutor(new QuickWarp());
        getCommand("nether").setExecutor(new QuickWarp());
    }
}