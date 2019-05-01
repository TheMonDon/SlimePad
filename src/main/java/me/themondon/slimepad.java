package me.themondon;

import java.util.ArrayList;
import java.util.UUID;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class slimepad extends JavaPlugin implements Listener
{

    private Material mat;
    private double height;
    private double distance;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        mat = Material.getMaterial(this.getConfig().getString("material").toUpperCase());
        height = this.getConfig().getDouble("height");
        distance = this.getConfig().getDouble("distance");

        Bukkit.getLogger().info("---------------------------");
        Bukkit.getLogger().info("         SlimePad v1.7     ");
        Bukkit.getLogger().info("---------------------------");

        getServer().getPluginManager().registerEvents(this, this);
    }


    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        Player p = (Player)sender;
        if (cmd.getName().equalsIgnoreCase("slimepad")) {
            p.sendMessage("");
            p.sendMessage(ChatColor.AQUA.toString() + ChatColor.BOLD.toString() + "SlimePad");
            p.sendMessage(ChatColor.AQUA.toString() + "Version" + ChatColor.WHITE + ": " + ChatColor.GRAY + "v1.7");
            p.sendMessage(ChatColor.AQUA.toString() + "Developer" + ChatColor.WHITE + ": " + ChatColor.GRAY + "TheMonDon");
            p.sendMessage("");
            return true;
        }
        return true;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == mat) {
            e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(distance));
            e.getPlayer().setVelocity(new Vector(e.getPlayer().getVelocity().getX(), height, e.getPlayer().getVelocity().getZ()));
        }
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("---------------------------");
        Bukkit.getLogger().info("         SlimePad v1.7     ");
        Bukkit.getLogger().info("---------------------------");
    }
}
