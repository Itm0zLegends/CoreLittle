package fr.itmozlgends.coreLittle.Manager;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerrankManager {
    private final RankManager rankManager;
    private final Map<String, String> playerRanks;

    public PlayerrankManager(RankManager rankManager) {
        this.rankManager = rankManager;
        this.playerRanks = new HashMap<>();
        this.loadPlayerRanks();
    }

    public String getPlayerRank(Player player) {
        String uuid = player.getUniqueId().toString();
        return this.playerRanks.getOrDefault(uuid, this.rankManager.getDefaultRank());
    }

    public void setPlayerRank(Player player, String rank) {
        String uuid = player.getUniqueId().toString();
        this.playerRanks.put(uuid, rank);
        this.rankManager.setPlayerRank(player, rank);
    }

    public String getPlayerPrefix(Player player) {
        String rank = this.getPlayerRank(player);
        return this.rankManager.getPrefix(rank);
    }

    public Set<String> getAvailableRanks() {
        return this.rankManager.getAllRanks();
    }

    private void loadPlayerRanks() {
        this.rankManager.getAllPlayerRanks().forEach(this.playerRanks::put);
    }

    public String getRank(Player player) {
        return getPlayerRank(player);
    }

    public String getRank(UUID uuid) {
        Player player = Bukkit.getPlayer(uuid);
        return (player != null) ? getPlayerRank(player) : this.rankManager.getDefaultRank();
    }

    public void setRank(UUID uuid, String rank) {
        Player player = Bukkit.getPlayer(uuid);
        if (player != null) {
            setPlayerRank(player, rank);
        } else {
            String uuidStr = uuid.toString();
            this.playerRanks.put(uuidStr, rank);
        }
    }
}
