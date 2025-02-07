package fr.itmozlgends.coreLittle.Commands;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class UndoCommand implements CommandExecutor {

    private static final Map<Player, Stack<BlockState>> undoStacks = new HashMap<>();

    public static void saveBlockState(Player player, Block block) {
        undoStacks.computeIfAbsent(player, p -> new Stack<>()).push(block.getState());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("§cCette commande est réservée aux joueurs.");
            return true;
        }

        Stack<BlockState> playerUndoStack = undoStacks.get(player);

        if (playerUndoStack == null || playerUndoStack.isEmpty()) {
            player.sendMessage("§cAucune modification à annuler.");
            return true;
        }

        // Restaurer les blocs sauvegardés
        int blocksRestored = 0;
        while (!playerUndoStack.isEmpty()) {
            BlockState state = playerUndoStack.pop();
            state.getBlock().setType(state.getType());
            blocksRestored++;
        }

        player.sendMessage("§aAnnulation réussie : " + blocksRestored + " blocs restaurés.");
        return true;
    }
}
