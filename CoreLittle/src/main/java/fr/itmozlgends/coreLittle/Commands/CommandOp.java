package fr.itmozlgends.coreLittle.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandOp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Vérifie si la commande est "op"
        if (command.getName().equalsIgnoreCase("op")) {
            // Vérifie si le joueur a la permission
            if (!sender.hasPermission("corelittle.op")) {
                sender.sendMessage("§9Vous n'avez pas la §epermission§9 d'utiliser cette commande !");
                return true;
            }

            // Vérifie qu'un argument (nom du joueur) est fourni
            if (args.length != 1) {
                sender.sendMessage("§9Usage: /§eop§9 <player>");
                return true;
            }

            // Récupère le joueur spécifié
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage("§9Le joueur §e" + args[0] + "§9 n'est pas en §eligne§9 !");
                return true;
            }

            // Opère le joueur
            target.setOp(true);
            sender.sendMessage( "§9Le joueur §e" + target.getName() + "§9 est maintenant §eopérateur§9 !");
            target.sendMessage("§9Vous avez été promu au rang d'§eopérateur§9 par §e" + sender.getName() + " §9!");
            return true;
        }

        return false;
    }
}
