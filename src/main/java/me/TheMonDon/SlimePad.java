package me.TheMonDon;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class SlimePad extends JavaPlugin implements Listener, CommandExecutor {

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
        Bukkit.getLogger().info("         SlimePad v1.8     ");
        Bukkit.getLogger().info("---------------------------");

        getServer().getPluginManager().registerEvents(this, this);
        getCommand("slimepad").setExecutor(this);
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("---------------------------");
        Bukkit.getLogger().info("         SlimePad v1.8     ");
        Bukkit.getLogger().info("---------------------------");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        sender.sendMessage(ChatColor.AQUA.toString() + ChatColor.BOLD.toString() + "SlimePad");
        sender.sendMessage(ChatColor.AQUA.toString() + "Version" + ChatColor.WHITE + ": " + ChatColor.GRAY + "v1.8");
        sender.sendMessage(ChatColor.AQUA.toString() + "Developer" + ChatColor.WHITE + ": " + ChatColor.GRAY + "TheMonDon");
        return true;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == mat) {
            e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(distance));
            e.getPlayer().setVelocity(new Vector(e.getPlayer().getVelocity().getX(), height, e.getPlayer().getVelocity().getZ()));
        }
    }
}
