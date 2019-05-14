package me.TheMonDon;

import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.List;

public class SlimePad extends JavaPlugin implements Listener, CommandExecutor {

    private Material mat;
    private double height;
    private double distance;
    private List<String> enabledWorlds = getConfig().getStringList("enabledWorlds");

    @Override
    public void onEnable() {
        saveDefaultConfig();
        mat = Material.getMaterial(this.getConfig().getString("material").toUpperCase());
        height = this.getConfig().getDouble("height");
        distance = this.getConfig().getDouble("distance");

        Bukkit.getLogger().info("---------------------------");
        Bukkit.getLogger().info("         SlimePad v2.2     ");
        Bukkit.getLogger().info("---------------------------");

        Metrics metrics = new Metrics(this);
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("slimepad").setExecutor(this);
        getCommand("slimepad-reload").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("slimepad")) {
            sender.sendMessage(ChatColor.AQUA.toString() + ChatColor.BOLD.toString() + "SlimePad");
            sender.sendMessage(ChatColor.AQUA.toString() + "Version" + ChatColor.WHITE + ": " + ChatColor.GRAY + "v2.2");
            sender.sendMessage(ChatColor.AQUA.toString() + "Developer" + ChatColor.WHITE + ": " + ChatColor.GRAY + "TheMonDon");
        }
        if (cmd.getName().equalsIgnoreCase("slimepad-reload")) {
            if (sender.hasPermission("sp.reload")) {
                enabledWorlds.clear();
                reloadConfig();
                enabledWorlds = getConfig().getStringList("enabledWorlds");
                mat = Material.getMaterial(this.getConfig().getString("material").toUpperCase());
                height = this.getConfig().getDouble("height");
                distance = this.getConfig().getDouble("distance");
                sender.sendMessage(ChatColor.GREEN + "SlimePad Configuration Reloaded.");
            }
            return true;
        }
        return true;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if(enabledWorlds.contains(p.getLocation().getWorld().getName())){
            if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == mat) {
                p.setVelocity(e.getPlayer().getLocation().getDirection().multiply(distance));
                p.setVelocity(new Vector(e.getPlayer().getVelocity().getX(), height, e.getPlayer().getVelocity().getZ()));
            }
        }
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("---------------------------");
        Bukkit.getLogger().info("         SlimePad v2.2     ");
        Bukkit.getLogger().info("---------------------------");
    }
}
