package fr.itmozlgends.coreLittle.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Cat;

public class SpawnPetCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Cette commande doit être exécutée par un joueur.");
            return false;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage(ChatColor.YELLOW + "Usage : /pet <wolf|cat>");
            return false;
        }

        String petType = args[0].toLowerCase();

        switch (petType) {
            case "wolf":
                spawnWolf(player);
                break;
            case "cat":
                spawnCat(player);
                break;
            default:
                player.sendMessage(ChatColor.RED + "Animal non supporté. Choisissez 'wolf' ou 'cat'.");
                return false;
        }

        return true;
    }

    private void spawnWolf(Player player) {
        Wolf wolf = (Wolf) player.getWorld().spawnEntity(player.getLocation(), EntityType.WOLF);
        wolf.setOwner(player);
        wolf.setCustomName(ChatColor.GOLD + player.getName() + "'s Wolf");
        wolf.setCustomNameVisible(true);
        player.sendMessage(ChatColor.GREEN + "Un loup est maintenant à vos côtés !");
    }

    private void spawnCat(Player player) {
        Cat cat = (Cat) player.getWorld().spawnEntity(player.getLocation(), EntityType.CAT);
        cat.setOwner(player);
        cat.setCustomName(ChatColor.GOLD + player.getName() + "'s Cat");
        cat.setCustomNameVisible(true);
        player.sendMessage(ChatColor.GREEN + "Un chat est maintenant à vos côtés !");
    }
}
