package net.q_silver.kjperms.permstoggle;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import net.q_silver.kjperms.permstoggle.PermsToggle;

public class WorldGuardToggler implements CommandExecutor {

	private PermsToggle plugin = new PermsToggle() ;
	
	public WorldGuardToggler(PermsToggle plugin){
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
			//we can assume that the command was /wgo, and that permissions are correct
		
		if (!(sender instanceof Player)){
			sender.sendMessage("Sorry, this only makes sense with a player!");
			return true;
		}
		Player player = (Player) sender;
			PermissionAttachment pa = plugin.getPAbyName(player.getName());
			if(pa == null){
				return true;
			}
			String WorldName = player.getWorld().getName();
			String PermToToggle = "worldguard.region.bypass." + WorldName;
			if(player.hasPermission(PermToToggle)){
				pa.unsetPermission(PermToToggle);
				player.sendMessage(ChatColor.GOLD + "[PT] You are " + ChatColor.RED + "no longer overriding" + ChatColor.GOLD +" WorldGuard in " + WorldName + "!");
			}else{
				pa.setPermission(PermToToggle, true);
				player.sendMessage(ChatColor.GOLD + "[PT] You are " + ChatColor.GREEN + "now overriding" + ChatColor.GOLD +" WorldGuard in " + WorldName + "!");
			}
		return true;
	}

}
