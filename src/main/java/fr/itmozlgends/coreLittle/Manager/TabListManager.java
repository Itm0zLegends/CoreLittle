package fr.itmozlgends.coreLittle.Manager;

import fr.itmozlgends.coreLittle.CoreLittle;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TabListManager extends BukkitRunnable {
    private final JavaPlugin plugin;
    private final PlayerrankManager playerRankManager;
    private final RankManager rankManager;
    private FileConfiguration config;
    private File configFile;
    private int updateInterval;

    public TabListManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.playerRankManager = ((CoreLittle) plugin).getPlayerRankManager();
        this.rankManager = ((CoreLittle) plugin).getRankManager();
        loadConfig();
    }

    private void loadConfig() {
        configFile = new File(plugin.getDataFolder(), "TabList.yml");

        // Vérifie si le fichier existe, sinon le créer
        if (!configFile.exists()) {
            createDefaultConfig();
        }

        config = YamlConfiguration.loadConfiguration(configFile);
        updateInterval = config.getInt("tablist.update_interval", 5) * 20; // Convertir secondes en ticks
    }

    private void createDefaultConfig() {
        config = new YamlConfiguration();
        config.set("tablist.header", "&6🚀 Bienvenue sur le serveur ! 🚀\n&7Joueurs en ligne : &a%online_players%");
        config.set("tablist.footer", "&e🌟 Profite bien et amuse-toi ! 🌟\n&bPing : &f%ping% ms");
        config.set("tablist.update_interval", 5); // En secondes

        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            updateTablist(player);
            updatePlayerPrefix(player);
        }
    }

    private void updateTablist(Player player) {
        String header = ChatColor.translateAlternateColorCodes('&', config.getString("tablist.header", "&6🚀 Serveur &7Joueurs en ligne : &a%online_players%"));
        String footer = ChatColor.translateAlternateColorCodes('&', config.getString("tablist.footer", "&e🌟 Profite bien et amuse-toi ! 🌟\n&bPing : &f%ping% ms"));

        // Remplacement des variables dynamiques
        header = header.replace("%online_players%", String.valueOf(Bukkit.getOnlinePlayers().size()));
        footer = footer.replace("%ping%", String.valueOf(player.getPing()));

        player.sendPlayerListHeaderAndFooter(Component.text(header), Component.text(footer));
    }

    private void updatePlayerPrefix(Player player) {
        String rank = playerRankManager.getPlayerRank(player);
        String prefix = playerRankManager.getPlayerPrefix(player);

        Scoreboard board = player.getScoreboard();
        if (board == null || board == Bukkit.getScoreboardManager().getMainScoreboard()) {
            board = Bukkit.getScoreboardManager().getNewScoreboard();
        }

        int priority = getRankPriority(rank);
        String teamName = priority + "_" + rank;

        Team team = board.getTeam(teamName);
        if (team == null) {
            team = board.registerNewTeam(teamName);
        }

        team.setPrefix(ChatColor.translateAlternateColorCodes('&', prefix));
        team.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.ALWAYS);
        team.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);

        team.addEntry(player.getName());
        player.setScoreboard(board);
    }

    private int getRankPriority(String rank) {
        List<String> ranks = rankManager.getOrderedRanks();
        int index = ranks.indexOf(rank);
        return (index != -1) ? index + 1 : 99;
    }

    public int getUpdateInterval() {
        return updateInterval;
    }
}
