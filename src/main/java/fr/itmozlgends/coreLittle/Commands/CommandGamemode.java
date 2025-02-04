package fr.itmozlgends.coreLittle.Commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGamemode implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length > 0) {
                String arg = args[0].toLowerCase();  // Utilisation de .toLowerCase() pour ne pas être sensible à la casse

                // Essayer de convertir l'argument en un nombre (si possible)
                int mode = -1;
                try {
                    mode = Integer.parseInt(arg);  // Si l'argument est un chiffre (par exemple, "1")
                } catch (NumberFormatException e) {
                    // Si l'argument n'est pas un chiffre, on gère les lettres
                    switch (arg) {
                        case "c":  // Créatif
                            mode = 1;
                            break;
                        case "s":  // Survie
                            mode = 0;
                            break;
                        case "sp": // Spectateur
                            mode = 3;
                            break;
                        default:
                            player.sendMessage("§9Mode de jeu invalide§9. Utilisez 'c§9', 's§9', 'sp§9' ou les numéros§9 0§9, 1§9, 3§9.");
                            return false;
                    }
                }

                // Maintenant que mode a une valeur valide, on applique le mode correspondant
                switch (mode) {
                    case 0: // Survie
                        player.setGameMode(GameMode.SURVIVAL);
                        player.sendMessage("§9Mode de jeu changé en §eSurvie§9.");
                        break;
                    case 1: // Créatif
                        player.setGameMode(GameMode.CREATIVE);
                        player.sendMessage("§9Mode de jeu changé en §eCréatif§9.");
                        break;
                    case 2: // Spectateur
                        player.setGameMode(GameMode.SPECTATOR);
                        player.sendMessage("§9Mode de jeu changé en §eSpectateur§9.");
                        break;
                    default:
                        player.sendMessage("§9Numéro de mode de jeu §einvalide§9. Utilisez §e0§9 (§eSurvie§9), §e1§9 (§eCréatif§9), ou §e2§9 (§eSpectateur§9).");
                        break;
                }
                return true;
            } else {
                player.sendMessage("§9Veuillez spécifier un §emode de jeu§9. Utilisez '§ec§9', '§es§9', '§esp§9' ou les numéros §e0§9, §e1§9, §e2§9.");
                return false;
            }
        } else {
            sender.sendMessage("Cette commande ne peut être utilisée que par un joueur.");
            return false;
        }
    }
}
