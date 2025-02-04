package fr.itmozlgends.coreLittle.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandDeop implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("corelittle.deop")) {
            sender.sendMessage("§9Vous n'avez pas la permission d'utiliser cette commande.");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("§9Usage : /§edeop§9 <player>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            sender.sendMessage("§9Le joueur §e" + args[0] + " §9n'est pas en §eligne§9!");
            return true;
        }

        if (!target.isOp()) {
            sender.sendMessage(ChatColor.YELLOW + target.getName() + "§9 n'est pas §eopérateur§9.");
            return true;
        }

        target.setOp(false);
        target.sendMessage("§9Vous avez été retiré de la liste des §eopérateurs§9 par§e " + sender.getName() + " §9!");
        sender.sendMessage("§9Le joueur " + target.getName() + " §9a été retiré des §eopérateurs§9 par§e " + sender.getName() + " §9!");
        return true;
    }
}
