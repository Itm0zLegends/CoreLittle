package fr.itmozlgends.coreLittle.Commands;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class BanofflineCommand implements CommandExecutor {

    // Map pour stocker les Ban ID (clé = pseudo du joueur, valeur = ID du ban)
    private static final HashMap<String, String> banIds = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            sender.sendMessage("§9§lUtilisation: /offlineban <joueur> <raison>");
            return false;
        }

        // Récupère le nom du joueur à bannir et la raison
        String playerName = args[0];
        StringBuilder reasonBuilder = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            reasonBuilder.append(args[i]).append(" ");
        }
        String reason = reasonBuilder.toString().trim();

        // Générer un ID unique pour le ban
        String banId = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        banIds.put(playerName, banId); // Stocker l'ID du ban pour ce joueur

        // Vérifier si le joueur est actuellement en ligne
        Player playerToBan = Bukkit.getPlayer(playerName);
        if (playerToBan != null) {
            // Créer un message de bannissement personnalisé
            String banMessage = "§9§lVous avez été banni !\n"
                    + "§r§9ID du ban : §e" + banId + "\n"
                    + "§9Pseudo : §e" + playerToBan.getName() + "\n"
                    + "§9Banni le : §e" + new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date()) + "\n"
                    + "§9Raison : §e" + reason + "\n"
                    + "§9Discord : §eitmozlegends_ \n"
                    + "§9Pour plus d'informations : §b§lhyko.fr";

            // Ajouter le joueur à la liste des bannis
            Bukkit.getBanList(BanList.Type.NAME).addBan(playerToBan.getName(), reason, null, sender.getName());

            // Expulse le joueur avec le message personnalisé
            playerToBan.kickPlayer(banMessage);

            sender.sendMessage("§9Le joueur§e " + playerToBan.getName() + "§9 a été §ebanni§9 avec succès.");
            sender.sendMessage("§9ID du ban : §e" + banId);
        } else {
            // Si le joueur est hors ligne, ajouter le ban sans l'expulser
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(playerName);
            if (offlinePlayer.hasPlayedBefore()) {
                // Ajouter le joueur à la liste des bannis
                Bukkit.getBanList(BanList.Type.NAME).addBan(playerName, reason, null, sender.getName());
                sender.sendMessage("§9Le joueur§e " + playerName + "§9 a été §ebanni§9 avec succès.");

                // Ajouter l'ID de ban dans la map pour une récupération future
                sender.sendMessage("§9ID du ban : §e" + banId);
            } else {
                sender.sendMessage("§9Le joueur§e " + playerName + "§9 n'a jamais rejoint le serveur.");
            }
        }

        return true;
    }

    // Méthode pour récupérer un Ban ID (utilisée dans le listener)
    public static String getBanId(String playerName) {
        return banIds.getOrDefault(playerName, "Inconnu");
    }
}
