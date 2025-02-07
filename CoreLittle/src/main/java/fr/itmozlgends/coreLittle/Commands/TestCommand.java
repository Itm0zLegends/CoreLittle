package fr.itmozlgends.coreLittle.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TestCommand implements CommandExecutor {
    public TestCommand() {
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("§9Commande de test: §eCeci est une commande de test.");
        return true;
    }
}
