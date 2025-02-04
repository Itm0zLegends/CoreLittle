package fr.itmozlgends.coreLittle.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Map;

public class CommandFreeze implements CommandExecutor, Listener {

    private final Map<Player, Location> frozenPlayers = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("freeze.use")) {
            sender.sendMessage("§9Vous n'avez pas la §epermission§9 d'utiliser cette commande !");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("§9Usage : /§efreeze§9 <player>");
            return true;
        }

        Player target = Bukkit.getPlayerExact(args[0]);

        if (target == null) {
            sender.sendMessage("§9Le joueur §e" + args[0] + "§9 n'est pas en §eligne§9.");
            return true;
        }

        if (frozenPlayers.containsKey(target)) {
            frozenPlayers.remove(target);
            target.sendMessage("§9Vous pouvez maintenant §ebouger§9 !");
            sender.sendMessage("§9Le joueur §e" + target.getName() + "§9 n'est plus §egelé§9.");
        } else {
            frozenPlayers.put(target, target.getLocation().clone());
            target.sendMessage("§9Vous avez été gelé par un administrateur !");
            sender.sendMessage("§9Le joueur §e" + target.getName() + "§9 est maintenant §egelé§9.");
        }

        return true;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (frozenPlayers.containsKey(player)) {
            Location freezeLocation = frozenPlayers.get(player);

            // Vérifie si le joueur a bougé avant de téléporter
            if (hasPlayerMoved(event)) {
                player.teleport(freezeLocation); // Retour immédiat à la position initiale
            }
        }
    }

    /**
     * Vérifie si le joueur a changé de position réelle
     */
    private boolean hasPlayerMoved(PlayerMoveEvent event) {
        Location from = event.getFrom();
        Location to = event.getTo();

        return to != null && (from.getX() != to.getX() || from.getY() != to.getY() || from.getZ() != to.getZ());
    }
}
