package fr.itmozlgends.coreLittle.Commands;

import fr.itmozlgends.coreLittle.Manager.PlayerrankManager;
import fr.itmozlgends.coreLittle.Manager.RankManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StaffCommand implements CommandExecutor, Listener {
    private final PlayerrankManager playerRankManager;
    private final RankManager rankManager;

    public StaffCommand(PlayerrankManager playerRankManager, RankManager rankManager) {
        this.playerRankManager = playerRankManager;
        this.rankManager = rankManager;
    }

    // Implémentation de la commande /staff
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Seuls les joueurs peuvent utiliser cette commande !");
            return true;
        }

        Player player = (Player) sender;
        openStaffMenu(player);
        return true;
    }

    public void openStaffMenu(Player player) {
        Inventory inv = Bukkit.createInventory(null, 9, ChatColor.RED + "Menu Staff");

        ItemStack players = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta playersMeta = players.getItemMeta();
        playersMeta.setDisplayName(ChatColor.GREEN + "Joueurs");
        players.setItemMeta(playersMeta);

        ItemStack actions = new ItemStack(Material.NETHER_STAR);
        ItemMeta actionsMeta = actions.getItemMeta();
        actionsMeta.setDisplayName(ChatColor.YELLOW + "Actions Staff");
        actions.setItemMeta(actionsMeta);

        inv.setItem(3, players);
        inv.setItem(5, actions);

        player.openInventory(inv);
    }

    public void openPlayersMenu(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.GREEN + "Joueurs Connectés");

        for (Player target : Bukkit.getOnlinePlayers()) {
            ItemStack head = new ItemStack(Material.PLAYER_HEAD);
            ItemMeta meta = head.getItemMeta();
            meta.setDisplayName(ChatColor.YELLOW + target.getName());

            String rank = playerRankManager.getRank(target.getUniqueId());
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + "Rang: " + ChatColor.RED + rank);

            meta.setLore(lore);
            head.setItemMeta(meta);

            inv.addItem(head);
        }

        ItemStack back = new ItemStack(Material.BARRIER);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName(ChatColor.RED + "Retour");
        back.setItemMeta(backMeta);

        inv.setItem(53, back);

        player.openInventory(inv);
    }

    public void openActionsMenu(Player player) {
        Inventory inv = Bukkit.createInventory(null, 9, ChatColor.YELLOW + "Actions Staff");

        ItemStack randomTp = new ItemStack(Material.ENDER_PEARL);
        ItemMeta randomTpMeta = randomTp.getItemMeta();
        randomTpMeta.setDisplayName(ChatColor.AQUA + "Téléportation Aléatoire");
        randomTp.setItemMeta(randomTpMeta);

        ItemStack back = new ItemStack(Material.BARRIER);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName(ChatColor.RED + "Retour");
        back.setItemMeta(backMeta);

        inv.setItem(3, randomTp);
        inv.setItem(8, back);

        player.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().contains("Staff") || event.getView().getTitle().contains("Joueurs")) {
            event.setCancelled(true);

            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem == null || !clickedItem.hasItemMeta()) return;

            String itemName = ChatColor.stripColor(clickedItem.getItemMeta().getDisplayName());

            if (itemName.equalsIgnoreCase("Joueurs")) {
                openPlayersMenu(player);
            } else if (itemName.equalsIgnoreCase("Actions Staff")) {
                openActionsMenu(player);
            } else if (itemName.equalsIgnoreCase("Retour")) {
                openStaffMenu(player);
            }
        }
    }
}
