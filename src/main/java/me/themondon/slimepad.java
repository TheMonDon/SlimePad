package me.themondon;

import java.util.ArrayList;
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

    //I would normally suggest having seperate classes for your commands and your listeners but 
    //for such a small plugin I would say that having them in a single class is fine.

    //You don't need a constructor for the main class of your plugin.
    /*@SuppressWarnings("unused")
    public void slimepad(){}*/

    //I suggest using UUID here instead of Player so that way you don't cause memory leaks on accident
    //private ArrayList<UUID> jumpers = new ArrayList();
    private ArrayList<Player> jumpers = new ArrayList();
    private Material mat;
    private double height;
    private double distance;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        mat = Material.getMaterial(this.getConfig().getString("material").toUpperCase());
        height = this.getConfig().getDouble("height");
        distance = this.getConfig().getDouble("distance");

        System.out.println("---------------------------");
        System.out.println("         SlimePad v1.6     ");
        System.out.println("---------------------------");

        // Enable the class to check for new players using onPlayerJoin()
        getServer().getPluginManager().registerEvents(this, this);
    }


    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        Player p = (Player)sender;
        //I suggest implementing permissions into your plugin so
        //if (p.hasPermission("SlimePad.use") {
        if (cmd.getName().equalsIgnoreCase("sp")) {
            p.sendMessage("");
            p.sendMessage(ChatColor.AQUA.toString() + ChatColor.BOLD.toString() + "SlimePad");
            p.sendMessage(ChatColor.AQUA.toString() + "Version" + ChatColor.WHITE + ": " + ChatColor.GRAY + "v1.6");
            p.sendMessage(ChatColor.AQUA.toString() + "Developer" + ChatColor.WHITE + ": " + ChatColor.GRAY + "TheMonDon");
            p.sendMessage("");
            return true;
        }
        //}
        return true;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == mat && jumpers.contains(e.getPlayer.getUniqueId())) {
            e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(distance));
            e.getPlayer().setVelocity(new Vector(e.getPlayer().getVelocity().getX(), height, e.getPlayer().getVelocity().getZ()));
            jumpers.add(e.getPlayer().getUniqueId());
        }
    }

    @Override
    public void onDisable() {
        System.out.println("---------------------------");
        System.out.println("         SlimePad v1.6     ");
        System.out.println("---------------------------");
    }
}
