package fr.itmozlgends.coreLittle.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpmCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Seuls les joueurs peuvent exécuter cette commande !");
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage(ChatColor.RED + "Utilisation : /tpm <joueur>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null || !target.isOnline()) {
            player.sendMessage(ChatColor.RED + "Le joueur '" + args[0] + "' n'est pas en ligne !");
            return true;
        }

        if (target == player) {
            player.sendMessage(ChatColor.RED + "Tu ne peux pas te téléporter toi-même !");
            return true;
        }

        target.teleport(player.getLocation());
        player.sendMessage(ChatColor.GREEN + "Le joueur " + target.getName() + " a été téléporté sur toi !");
        target.sendMessage(ChatColor.YELLOW + "Tu as été téléporté à " + player.getName() + ".");

        return true;
    }
}
