package fr.itmozlgends.coreLittle.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class StreamModeCommand implements CommandExecutor {

    private final Set<Player> streamModePlayers = new HashSet<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§9Vous n'avez pas la permission d'utiliser cette commande.");
            return true;
        }

        Player player = (Player) sender;

        // Vérifier la permission
        if (!player.hasPermission("corelittle.stream")) {
            player.sendMessage("§9Vous n'avez pas la permission d'utiliser cette commande.");
            return true;
        }

        // Activer ou désactiver le mode streaming
        if (streamModePlayers.contains(player)) {
            streamModePlayers.remove(player);
            player.sendMessage("§9[§eHyko§9] : Mode §eStream§9 déactivé !");
        } else {
            streamModePlayers.add(player);
            player.sendMessage("§9[§eHyko§9] : Mode §eStream§9 activé !");
        }

        return true;
    }
}
