package fr.itmozlgends.coreLittle.Manager;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class RankManager {
    private final File file;
    private final FileConfiguration config;

    public RankManager(File dataFolder) {
        this.file = new File(dataFolder, "ranks.yml");
        this.config = YamlConfiguration.loadConfiguration(this.file);
        if (!this.file.exists() || this.config.getConfigurationSection("ranks") == null) {
            this.createDefaultRanks();
        }
    }

    private void createDefaultRanks() {
        this.config.set("ranks.Administrateur.prefix", "&4[Admin] ");
        this.config.set("ranks.Administrateur.permissions", List.of("core.bypass", "core.reload", "core.kick", "core.ban"));

        this.config.set("ranks.Moderateur.prefix", "&c[Mod] ");
        this.config.set("ranks.Moderateur.permissions", List.of("core.kick", "core.mute", "core.tp"));

        this.config.set("ranks.Guide.prefix", "&9[Guide] ");
        this.config.set("ranks.Guide.permissions", List.of("core.help", "core.tp"));

        this.config.set("ranks.Joueur.prefix", "&7[Joueur] ");
        this.config.set("ranks.Joueur.permissions", List.of("core.help"));

        this.config.set("default", "Joueur");

        this.config.set("rankOrder", List.of("Administrateur", "Moderateur", "Guide", "Joueur"));

        this.save();
    }

    public String getPrefix(String rank) {
        return this.config.getString("ranks." + rank + ".prefix", "&7[Joueur] ");
    }

    public List<String> getPermissions(String rank) {
        return this.config.getStringList("ranks." + rank + ".permissions");
    }

    public List<String> getOrderedRanks() {
        return this.config.getStringList("rankOrder");
    }

    public String getDefaultRank() {
        return this.config.getString("default", "Joueur");
    }

    public Set<String> getAllRanks() {
        return this.config.getConfigurationSection("ranks").getKeys(false);
    }

    public String getPlayerRank(Player player) {
        String uuid = player.getUniqueId().toString();
        return this.config.getString("players." + uuid + ".rank", this.getDefaultRank());
    }

    public void setPlayerRank(Player player, String rank) {
        if (!this.getAllRanks().contains(rank)) {
            throw new IllegalArgumentException("Le rang '" + rank + "' n'existe pas !");
        }
        String uuid = player.getUniqueId().toString();
        this.config.set("players." + uuid + ".rank", rank);
        this.save();
    }

    public Map<String, String> getAllPlayerRanks() {
        Map<String, String> playerRanks = new HashMap<>();
        if (this.config.contains("players")) {
            for (String uuid : this.config.getConfigurationSection("players").getKeys(false)) {
                String rank = this.config.getString("players." + uuid + ".rank", this.getDefaultRank());
                playerRanks.put(uuid, rank);
            }
        }
        return playerRanks;
    }

    private void save() {
        try {
            this.config.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
