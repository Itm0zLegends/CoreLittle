package fr.itmozlgends.coreLittle.Commands;

import fr.itmozlgends.coreLittle.Manager.PlayerrankManager;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetrankCommand implements CommandExecutor {
    private final PlayerrankManager playerRankManager;

    public SetrankCommand(PlayerrankManager playerRankManager) {
        this.playerRankManager = playerRankManager;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            sender.sendMessage("§9Usage : /setrank <joueur> <rang>");
            return true;
        } else {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage("§9Le joueur '§e" + args[0] + "§9' n'est pas en ligne !");
                return true;
            } else {
                String rank = args[1];
                Set<String> availableRanks = this.playerRankManager.getAvailableRanks();
                if (!availableRanks.contains(rank)) {
                    sender.sendMessage("§9Le rang '§e" + rank + "§9' n'existe pas !");
                    sender.sendMessage("§9Rangs disponibles : §e" + String.join(", ", availableRanks));
                    return true;
                } else {
                    this.playerRankManager.setPlayerRank(target, rank);
                    String var10001 = target.getName();
                    sender.sendMessage("§9Le rang de §e" + var10001 + "§9 a été défini sur §e" + rank + "§9.");
                    target.sendMessage("§9Votre rang a été défini sur §e" + rank + "§9.");
                    return true;
                }
            }
        }
    }
}
