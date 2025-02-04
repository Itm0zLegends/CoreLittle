package fr.itmozlgends.coreLittle.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class HatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§9Cette commande est réservée aux joueurs.");
            return true;
        }

        Player player = (Player) sender;
        ItemStack itemInHand = player.getInventory().getItemInMainHand();

        if (itemInHand == null || itemInHand.getType() == Material.AIR) {
            player.sendMessage("§9Vous devez tenir un §ebloc§9 ou un §eitem§9 pour utiliser cette commande !");
            return true;
        }

        // Si aucun argument n'est donné, applique le /hat sur le joueur lui-même
        if (args.length == 0) {
            mettreChapeau(player, itemInHand);
            player.sendMessage("§9Chapeau mis sur votre §etête§9 !");
            return true;
        }

        // Recherche du joueur ciblé
        Player targetPlayer = Bukkit.getPlayerExact(args[0]);
        if (targetPlayer == null) {
            player.sendMessage("§9Le joueur §e" + args[0] + "§9 n'est pas en ligne.");
            return true;
        }

        mettreChapeau(targetPlayer, itemInHand);
        player.sendMessage("§9Vous avez mis un chapeau sur la tête de §e" + targetPlayer.getName() + "§9 !");
        targetPlayer.sendMessage("§e" + player.getName() + " §9 placé un chapeau sur votre §etête§9 !");
        return true;
    }

    private void mettreChapeau(Player target, ItemStack item) {
        ItemStack currentHelmet = target.getInventory().getHelmet();
        target.getInventory().setHelmet(item);
        target.getInventory().setItemInMainHand(currentHelmet != null ? currentHelmet : new ItemStack(Material.AIR));
    }
}
