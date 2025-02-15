package fr.itmozlgends.coreLittle.Listener;

import fr.itmozlgends.coreLittle.Manager.PlayerrankManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
    private final PlayerrankManager playerRankManager;

    public ChatListener(PlayerrankManager playerRankManager) {
        this.playerRankManager = playerRankManager;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String rankPrefix = this.playerRankManager.getPlayerPrefix(player);
        String chatFormat = "{PLAYER} &f: {MESSAGE}";
        chatFormat = chatFormat.replace("{PLAYER}", rankPrefix + player.getName()).replace("{MESSAGE}", event.getMessage());
        chatFormat = ChatColor.translateAlternateColorCodes('&', chatFormat);
        event.setFormat(chatFormat);
    }
}
