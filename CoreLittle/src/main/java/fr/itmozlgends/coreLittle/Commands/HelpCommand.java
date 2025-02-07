package fr.itmozlgends.coreLittle.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HelpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("help")) {
            if (args.length == 0) {
                // Aide générale
                sender.sendMessage("§9-------- §eAide§9 du §eServeur§9 --------");
                sender.sendMessage("§9/§ehelp§9 - Voir toutes les §ecommandes§9 du serveur !");
                sender.sendMessage("§9/§etest§9 - Une simple commande de §etest§9.");
                sender.sendMessage("§9 Plusieurs §ecommande§9 sont en développement.");
                sender.sendMessage("§9---------------------------------");
            } else if (args[0].equalsIgnoreCase("vip")) {
                if (sender.hasPermission("corelittle.helpvip")) {
                    sender.sendMessage("§9-------- §eAide§9 des §eVIP§9 --------");
                    sender.sendMessage("§9/§efly§9 - Permet de §evoler§9 !");
                    sender.sendMessage("§9/§ehat§9 - Permet de mettre un §ebloc§9 sur sa tête !");
                    sender.sendMessage("§9/§eskin§9 - Permet de changer de §eskin§9 !");
                    sender.sendMessage("§9------------------------------------------");
                } else {
                    sender.sendMessage("§9[§eCoreLittle§9] : Vous n'avez pas la §epermission§9 d'accéder à cette §esection§9 !");
                }
            } else if (args[0].equalsIgnoreCase("streamer")) {
                if (sender.hasPermission("corelittle.helpstreamer")) {
                    sender.sendMessage("§9-------- §eAide§9 des §eStreamers§9 --------");
                    sender.sendMessage("§9/§estreammode§9 - Permet de se mettre en mode §eStreamer§9 !");
                    sender.sendMessage("§9/§efly§9 - Permet de §evoler§9 !");
                    sender.sendMessage("§9/§ehat§9 - Permet de mettre un §ebloc§9 sur sa tête !");
                    sender.sendMessage("§9/§eskin§9 - Permet de changer de §eskin§9 !");
                    sender.sendMessage("§9------------------------------------------");
                } else {
                    sender.sendMessage("§9[§eCoreLittle§9] : Vous n'avez pas la §epermission§9 d'accéder à cette §esection§9 !");
                }
            } else if (args[0].equalsIgnoreCase("staff")) {
                if (sender.hasPermission("corelittle.helpstaff")) {
                    sender.sendMessage("§9-------- §eAide§9 du §eStaff§9 --------");
                    sender.sendMessage("§9/§egm§9 - Permet de changer de §eMode de Jeu§9.");
                    sender.sendMessage("§9/§eban§9 - Permet de §ebannir§9 un joueur.");
                    sender.sendMessage("§9/§eofflineban§9 - Permet de §ebannir§9 un joueur qui est hors ligne.");
                    sender.sendMessage("§9/§evanish§9 - Permet de devenir §einvisible§9 ou §evisible§9.");
                    sender.sendMessage("§9/§epardon§9 - Permet de §edébannir§9 un joueur.");
                    sender.sendMessage("§9/§eunban§9 - Permet de §edébannir§9 un joueur.");
                    sender.sendMessage("§9/§efreeze§9 - §eGele§9 ou §eDégele§9  un joueur.");
                    sender.sendMessage("§9/§ebroadcast§9 - Permet d'envoyer un §emessage§9 à tout les joueurs.");
                    sender.sendMessage( "§9--------------------------------");
                } else {
                    sender.sendMessage("§9[§eCoreLittle§9] : Vous n'avez pas la §epermission§9 d'accéder à cette §esection§9 !");
                }
            } else if (args[0].equalsIgnoreCase("admin")) {
                if (sender.hasPermission("corelittle.helpadmin")) {
                    sender.sendMessage("§9-------- §eAide§9 des §eAdministrateurs§9 --------");
                    sender.sendMessage("§9/§eop§9 <joueur> - Donner les permissions d'§eopérateur§9.");
                    sender.sendMessage("§9/§edeop§9 <joueur> - Retirer les permissions d'§eopérateur§9.");
                    sender.sendMessage("§9/§ewhitelist§9 <on|off> - Activer ou désactiver la §ewhitelist§9.");
                    sender.sendMessage("§9/§estop§9 - Permet de §estopper§9 le serveur.");
                    sender.sendMessage("§9/§ereload§9 - §eRecharge§9 tous les §eplugins§9 du serveur.");
                    sender.sendMessage("§9/§egm§9 - Permet de changer de §eMode de Jeu§9.");
                    sender.sendMessage("§9/§eban§9 - Permet de §ebannir§9 un joueur.");
                    sender.sendMessage("§9/§eofflineban§9 - Permet de §ebannir§9 un joueur qui est hors ligne.");
                    sender.sendMessage("§9/§evanish§9 - Permet de devenir §einvisible§9 ou §evisible§9.");
                    sender.sendMessage("§9/§epardon§9 - Permet de §edébannir§9 un joueur.");
                    sender.sendMessage("§9/§eunban§9 - Permet de §edébannir§9 un joueur.");
                    sender.sendMessage("§9/§efreeze§9 - §eGele§9 ou §eDégele§9  un joueur.");
                    sender.sendMessage("§9/§ebroadcast§9 - Permet d'envoyer un §emessage§9 à tout les joueurs.");
                    sender.sendMessage("§9------------------------------------------");
                } else {
                    sender.sendMessage("§9[§eCoreLittle§9] : Vous n'avez pas la §epermission§9 d'accéder à cette §esection§9 !");
                }
            } else {
                sender.sendMessage("Usage : /help");
            }
            return true;
        }
        return false;
    }
}

