package fr.itmozlgends.coreLittle.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class ReloadCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("corelittle.reload")) {
            sender.sendMessage("§9Vous n'avez pas la permission d'utiliser cette commande.");
            return true;
        }

        sender.sendMessage("§9[§eHyko§9] §eRechargement§9 des §eplugins§9 en cours...");

        // Désactiver et réactiver tous les plugins
        for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
            if (plugin.isEnabled()) {
                Bukkit.getPluginManager().disablePlugin(plugin);
            }
            Bukkit.getPluginManager().enablePlugin(plugin);
        }

        sender.sendMessage("§9[§eHyko§9] Tous les §ePlugins§9 on été §erechargés§9 !");
        return true;
    }
}
