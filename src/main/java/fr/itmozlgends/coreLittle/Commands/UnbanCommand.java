package fr.itmozlgends.coreLittle.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class UnbanCommand implements CommandExecutor {

    private final JavaPlugin plugin;

    public UnbanCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Vérification des permissions
        if (!sender.hasPermission("corelittle.unban")) {
            sender.sendMessage(ChatColor.RED + "Vous n'avez pas la permission d'utiliser cette commande !");
            return true;
        }

        // Vérification du bon nombre d'arguments
        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Utilisation: /unban <nom_du_joueur>");
            return true;
        }

        String playerName = args[0];

        // Retirer le joueur de la ban list
        if (Bukkit.getBanList(org.bukkit.BanList.Type.NAME).isBanned(playerName)) {
            Bukkit.getBanList(org.bukkit.BanList.Type.NAME).pardon(playerName);
            sender.sendMessage(ChatColor.GREEN + "Le joueur " + playerName + " a été débanni !");
        } else {
            sender.sendMessage(ChatColor.RED + "Le joueur " + playerName + " n'est pas banni.");
        }

        return true;
    }
}
