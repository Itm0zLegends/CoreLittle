package fr.itmozlgends.coreLittle.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ProutCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage("§e§lPrrrroooouuuut§r§9 ! 💨, §e" + sender.getName() + "§9 A pété...");
            player.getWorld().playSound(player.getLocation(), "entity.generic.explode", 1.0f, 1.0f);
        } else {
            sender.sendMessage("§9Seul un joueur peut utiliser cette commande.");
        }
        return true;
    }
}

