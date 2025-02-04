package fr.itmozlgends.coreLittle.Commands;

import fr.itmozlgends.coreLittle.CoreLittle;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class VanishCommand implements CommandExecutor {

    private final CoreLittle plugin;
    private final Set<Player> vanishedPlayers = new HashSet<>();

    public VanishCommand(CoreLittle plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("§9§lSeuls les joueurs peuvent exécuter cette commande.");
            return true;
        }

        if (!player.hasPermission("corelittle.vanish")) {
            player.sendMessage("§9§lVous n'avez pas la permission d'utiliser cette commande.");
            return true;
        }

        // Basculer le mode vanish
        if (vanishedPlayers.contains(player)) {
            // Rendre le joueur visible
            vanishedPlayers.remove(player);
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                onlinePlayer.showPlayer(plugin, player);
            }
            player.setInvisible(false); // Annuler l'invisibilité pour le joueur lui-même

            // Réafficher dans la tablist
            player.setPlayerListName(player.getName());

            player.sendMessage("§9Vous êtes maintenant §evisible§9 !");
        } else {
            // Rendre le joueur invisible
            vanishedPlayers.add(player);
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                onlinePlayer.hidePlayer(plugin, player);
            }
            player.setInvisible(true); // Activer l'invisibilité pour le joueur lui-même

            // Retirer de la tablist
            player.setPlayerListName(null);

            player.sendMessage("§9Vous êtes maintenant §einvisible§9 !");
        }

        return true;
    }
}
