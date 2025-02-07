package fr.itmozlgends.coreLittle.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class VersionCommand implements CommandExecutor {
    public VersionCommand() {
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("Version du Serveur : 1.21.4\n Version du Plugin : CoreLittle 1.0");
        return true;
    }
}