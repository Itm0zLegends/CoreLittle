package fr.itmozlgends.coreLittle.Commands;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;

public class CutCommand implements CommandExecutor {

    private final Map<Player, Vector> pos1Map;
    private final Map<Player, Vector> pos2Map;

    public CutCommand(Map<Player, Vector> pos1Map, Map<Player, Vector> pos2Map) {
        this.pos1Map = pos1Map;
        this.pos2Map = pos2Map;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Cette commande est réservée aux joueurs.");
            return true;
        }

        Vector pos1 = pos1Map.get(player);
        Vector pos2 = pos2Map.get(player);

        if (pos1 == null || pos2 == null) {
            player.sendMessage("§cTu dois définir les deux positions avec la hache.");
            return true;
        }

        clearArea(player, pos1, pos2);
        player.sendMessage("§aZone coupée avec succès.");
        return true;
    }

    private void clearArea(Player player, Vector pos1, Vector pos2) {
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

                    // Avant de supprimer un bloc, sauvegarde pour //undo
                    UndoCommand.saveBlockState(player, block);

                    block.setType(Material.AIR); // Suppression du bloc
                }
            }
        }
    }
}
