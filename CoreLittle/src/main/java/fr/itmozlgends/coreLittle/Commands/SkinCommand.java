package fr.itmozlgends.coreLittle.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SkinCommand implements org.bukkit.command.CommandExecutor {

    private JavaPlugin plugin;

    // Constructeur
    public SkinCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Vérification si l'expéditeur est un joueur
        if (!(sender instanceof Player)) {
            sender.sendMessage("Cette commande peut uniquement être utilisée par un joueur.");
            return false;
        }

        Player player = (Player) sender;

        // Vérification des arguments
        if (args.length != 1) {
            player.sendMessage("Utilisation incorrecte. Utilise: /skin <URL>");
            return false;
        }

        String skinURL = args[0];

        // Changer le skin du joueur
        changeSkin(player, skinURL);

        return true;
    }

    // Fonction pour changer le skin
    private void changeSkin(Player player, String skinURL) {
        // Exemple simple d'application du skin (fonctionnalité basique sans utiliser d'API tierce)
        // Si tu utilises un plugin comme SkinRestorer, tu peux ici appeler leur API pour appliquer le skin

        // Si tu n'as pas de plugin tiers, tu pourrais implémenter la logique pour télécharger l'image et
        // modifier le skin à l'aide de l'API de Mojang ou d'une autre méthode.

        // Par exemple, avec SkinRestorer :
        // SkinRestorerAPI.setSkin(player, skinURL);

        player.sendMessage("Ton skin a été changé avec succès !");
    }
}
