package fr.itmozlgends.coreLittle;

import fr.itmozlgends.coreLittle.Commands.*;
import fr.itmozlgends.coreLittle.Listener.ChatListener;
import fr.itmozlgends.coreLittle.Manager.*;
import fr.itmozlgends.coreLittle.TabCompleter.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class CoreLittle extends JavaPlugin {
    private RankManager rankManager;
    private PlayerrankManager playerRankManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getLogger().info("Activation de CoreLittle...");

        // Initialisation des gestionnaires
        initializeManagers();
        registerListeners();

        this.getLogger().info("Activation des Commandes de CoreLittle...");
        getCommand("broadcast").setExecutor(new BroadcastCommand());
        getCommand("op").setExecutor(new CommandOp());
        getCommand("deop").setExecutor(new CommandDeop());
        getCommand("ban").setExecutor(new BanCommand());
        getCommand("offlineban").setExecutor(new BanofflineCommand());
        getCommand("stop").setExecutor(new CommandStop(this));
        getCommand("gm").setExecutor(new CommandGamemode());
        getCommand("freeze").setExecutor(new CommandFreeze());
        getCommand("unban").setExecutor(new UnbanCommand(this));
        getCommand("pardon").setExecutor(new UnbanCommand(this));
        getCommand("help").setExecutor(new HelpCommand());
        getCommand("reload").setExecutor(new ReloadCommand());
        getCommand("restart").setExecutor(new RestartCommand(this));
        getCommand("streammode").setExecutor(new StreamModeCommand());
        getCommand("vanish").setExecutor(new VanishCommand(this));
        getCommand("prout").setExecutor(new ProutCommand());
        getCommand("version").setExecutor(new VersionCommand());
        getCommand("hat").setExecutor(new HatCommand());
        getCommand("skin").setExecutor(new SkinCommand(this));
        getCommand("spawn-pet").setExecutor(new SpawnPetCommand());
        getCommand("pet").setExecutor(new PetCommand());
        getCommand("tpm").setExecutor(new TpmCommand());

        // StaffCommand
        StaffCommand staffCommand = new StaffCommand(playerRankManager, rankManager);
        getCommand("staff").setExecutor(staffCommand);
        getServer().getPluginManager().registerEvents(staffCommand, this);

        getCommand("setrank").setExecutor(new SetrankCommand(this.playerRankManager));

        // WorldEdit
        SetCommand setCommand = new SetCommand();
        getCommand("set").setExecutor(setCommand);
        getCommand("set").setTabCompleter(new SetCommandTabCompleter());
        getServer().getPluginManager().registerEvents(setCommand, this);

        getCommand("cut").setExecutor(new CutCommand(SetCommand.pos1Map, SetCommand.pos2Map));
        getCommand("undo").setExecutor(new UndoCommand());

        this.getLogger().info("Toutes les commandes de CoreLittle sont activées !");
        this.getLogger().info("CoreLittle activé avec succès !");

        TabListManager tablistManager = new TabListManager(this);
        tablistManager.runTaskTimer(this, 0L, tablistManager.getUpdateInterval());
    }

    public RankManager getRankManager() {
        return rankManager;
    }

    public PlayerrankManager getPlayerRankManager() {
        return playerRankManager;
    }


    private void initializeManagers() {
        this.rankManager = new RankManager(this.getDataFolder());
        this.playerRankManager = new PlayerrankManager(this.rankManager);
        this.getLogger().info("Tous les gestionnaires ont été initialisés.");
    }

    private void registerListeners() {
        this.getServer().getPluginManager().registerEvents(new ChatListener(this.playerRankManager), this);
        this.getLogger().info("Tous les listeners ont été enregistrés.");
    }


    @Override
    public void onDisable() {
        this.getLogger().info("Déactivation de CoreLittle...");
        this.getLogger().info("Au revoir !");
    }
}
