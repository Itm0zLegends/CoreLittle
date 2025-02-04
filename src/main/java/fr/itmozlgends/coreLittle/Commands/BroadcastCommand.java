package fr.itmozlgends.coreLittle.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BroadcastCommand implements CommandExecutor {
    public BroadcastCommand() {
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§9[§eHyko§9] : Usage : /broadcast <message>");
            return true;
        } else {
            String message = String.join(" ", args);
            Bukkit.broadcastMessage("§9[Annonce] §e" + message);
            sender.sendMessage("§9[§eHyko§9] : Votre message a été diffusé à tous les joueurs !");
            return true;
        }
    }
}

