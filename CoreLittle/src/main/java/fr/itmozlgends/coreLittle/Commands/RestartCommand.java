// NE MARCHE PAS / EN DEVELOPPEMENT

package fr.itmozlgends.coreLittle.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

public class RestartCommand implements CommandExecutor, Listener {

    private final Plugin plugin;

    public RestartCommand(Plugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equalsIgnoreCase("restart")) {
            return false;
        }

        // Vérification des permissions
        if (!sender.hasPermission("server.restart")) {
            sender.sendMessage(ChatColor.RED + "Vous n'avez pas la permission d'exécuter cette commande.");
            return true;
        }

        // Avertir les joueurs
        Bukkit.broadcastMessage(ChatColor.GOLD + "[Serveur] Redémarrage du serveur dans 5 secondes...");

        // Planification de l'arrêt et du redémarrage
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            Bukkit.getServer().shutdown();
            restartServer();
        }, 100L); // 5 secondes (20 ticks par seconde)

        return true;
    }

    private void restartServer() {
        try {
            // Relance directe du serveur en Java
            String javaHome = System.getProperty("java.home");
            String javaBin = javaHome + "/bin/java";
            String jarPath = new java.io.File("server.jar").getAbsolutePath();
            String command = javaBin + " -Xmx1024M -Xms1024M -jar " + jarPath + " nogui";
            new ProcessBuilder(command.split(" ")).start();
        } catch (Exception e) {
            Bukkit.getLogger().severe("Erreur lors de la tentative de redémarrage du serveur : " + e.getMessage());
        }
    }

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        String command = event.getMessage().toLowerCase();
        if (command.startsWith("/restart")) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + "La commande /restart est désactivée sur ce serveur.");
        }
    }
}
