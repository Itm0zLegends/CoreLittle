package fr.itmozlgends.coreLittle.Commands;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;

public class SetCommand implements CommandExecutor, Listener {

    public static final Map<Player, Vector> pos1Map = new HashMap<>();
    public static final Map<Player, Vector> pos2Map = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Cette commande est réservée aux joueurs.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage("§9Usage : /§eset§9 <bloc>");
            return true;
        }

        Material material = Material.matchMaterial(args[0].toUpperCase());
        if (material == null) {
            player.sendMessage("§9Le §ebloc§9 spécifié est §einvalide§9.");
            return true;
        }

        Vector pos1 = pos1Map.get(player);
        Vector pos2 = pos2Map.get(player);

        if (pos1 == null || pos2 == null) {
            player.sendMessage("§9Tu dois définir les deux §epositions§9 avec la hache.");
            return true;
        }

        fillArea(player, pos1, pos2, material);
        player.sendMessage("§9La zone a été remplie avec §e" + material.name().toLowerCase().replace("_", " ") + "§9.");
        return true;
    }

    @EventHandler
    public void onPlayerClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();

        // Vérifie que le joueur utilise une hache
        if (itemInHand.getType() != Material.WOODEN_AXE) {
            return;
        }

        // Empêche la destruction des blocs pour le clic gauche
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            event.setCancelled(true);
        }

        Block clickedBlock = event.getClickedBlock();
        if (clickedBlock == null) return;

        Vector blockPos = clickedBlock.getLocation().toVector();

        switch (event.getAction()) {
            case LEFT_CLICK_BLOCK -> {
                pos1Map.put(player, blockPos);
                player.sendMessage("§9Position §e1§9 définie en §e" + blockPos + " §9(§e" + countSelectedBlocks(player) + "§9 blocs)");
            }
            case RIGHT_CLICK_BLOCK -> {
                pos2Map.put(player, blockPos);
                player.sendMessage("§9Position §e2§9 définie en §e" + blockPos + " §9(§e" + countSelectedBlocks(player) + "§9 blocs)");
                event.setCancelled(true);
            }
        }
    }

    private void fillArea(Player player, Vector pos1, Vector pos2, Material material) {
        World world = player.getWorld();

        int minX = Math.min(pos1.getBlockX(), pos2.getBlockX());
        int maxX = Math.max(pos1.getBlockX(), pos2.getBlockX());
        int minY = Math.min(pos1.getBlockY(), pos2.getBlockY());
        int maxY = Math.max(pos1.getBlockY(), pos2.getBlockY());
        int minZ = Math.min(pos1.getBlockZ(), pos2.getBlockZ());
        int maxZ = Math.max(pos1.getBlockZ(), pos2.getBlockZ());

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    Block block = world.getBlockAt(x, y, z);

                    // Avant de modifier un bloc, on le sauvegarde pour //undo
                    UndoCommand.saveBlockState(player, block);

                    block.setType(material);
                }
            }
        }
    }

    private int countSelectedBlocks(Player player) {
        Vector pos1 = pos1Map.get(player);
        Vector pos2 = pos2Map.get(player);

        if (pos1 == null || pos2 == null) {
            return 0;
        }

        int xDiff = Math.abs(pos1.getBlockX() - pos2.getBlockX()) + 1;
        int yDiff = Math.abs(pos1.getBlockY() - pos2.getBlockY()) + 1;
        int zDiff = Math.abs(pos1.getBlockZ() - pos2.getBlockZ()) + 1;

        return xDiff * yDiff * zDiff;
    }
}
