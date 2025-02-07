package fr.itmozlgends.coreLittle.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class CommandStop implements CommandExecutor {

    private JavaPlugin plugin;

    public CommandStop(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("corelittle.stop")) {
            final int cooldownSeconds = 10; // Temps avant l'arrêt (en secondes)

            // Informer les joueurs du début du compte à rebours
            Bukkit.broadcastMessage("§9[§eHyko§9] : Le serveur va s'arrêter dans §e" + cooldownSeconds + "§9 secondes. Préparez-vous !");

            new BukkitRunnable() {
                int countdown = cooldownSeconds;

                @Override
                public void run() {
                    if (countdown <= 0) {
                        // Déconnecter tous les joueurs avec un message personnalisé
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            player.kickPlayer("§9Le §eserveur§9 s'arrête. Merci de jouer à §eHyko§9 !");
                        }

                        // Arrêter le serveur
                        plugin.getServer().shutdown();

                        cancel(); // Arrêter le compte à rebours
                    } else if (countdown <= 5) {
                        // Envoyer un message uniquement dans les dernières secondes
                        Bukkit.broadcastMessage("§9[§eHyko§9] : Le serveur s'arrête dans §e" + countdown + "§9 seconde(s) !");
                    }

                    countdown--;
                }
            }.runTaskTimer(plugin, 0, 20); // Exécuter toutes les secondes (20 ticks)

            return true;
        } else {
            sender.sendMessage("§9Vous n'avez pas la permission d'exécuter cette commande§9.");
            return false;
        }
    }
}

