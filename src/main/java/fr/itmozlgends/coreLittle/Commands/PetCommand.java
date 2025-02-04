package fr.itmozlgends.coreLittle.Commands;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class PetCommand implements CommandExecutor {

    private final Map<Player, Wolf> transformedPlayers = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Cette commande doit être exécutée par un joueur.");
            return false;
        }

        Player player = (Player) sender;

        if (args.length != 1 || !args[0].equalsIgnoreCase("wolf")) {
            player.sendMessage("Usage : /pet <nom_d_un_animal>");
            return false;
        }

        transformPlayerIntoWolf(player);
        return true;
    }

    private void transformPlayerIntoWolf(Player player) {
        if (transformedPlayers.containsKey(player)) {
            player.sendMessage("Vous êtes déjà transformé !");
            return;
        }

        Location loc = player.getLocation();

        // Spawn du loup et configuration
        Wolf wolf = (Wolf) player.getWorld().spawnEntity(loc, EntityType.WOLF);
        wolf.setCustomName("");
        wolf.setCustomNameVisible(true);
        wolf.setTamed(true);
        wolf.setSilent(true);
        wolf.setAI(false);  // Pas d'IA

        transformedPlayers.put(player, wolf);

        // Rendre le joueur invisible et en mode survie
        player.setGameMode(GameMode.SURVIVAL);
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1, false, false));
        player.sendMessage("Vous êtes maintenant un loup !");

        // Synchronisation des déplacements
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!transformedPlayers.containsKey(player) || wolf.isDead()) {
                    this.cancel();
                    restorePlayer(player);
                    return;
                }

                // Synchroniser les positions
                wolf.teleport(player.getLocation());
                wolf.setVelocity(player.getVelocity());
            }
        }.runTaskTimer(Bukkit.getPluginManager().getPlugin("CoreLittle"), 0L, 1L);
    }

    private void restorePlayer(Player player) {
        Wolf wolf = transformedPlayers.remove(player);
        if (wolf != null) wolf.remove();

        // Restaurer le joueur à son état initial
        player.removePotionEffect(PotionEffectType.INVISIBILITY);
        player.sendMessage("Transformation annulée.");
    }
}