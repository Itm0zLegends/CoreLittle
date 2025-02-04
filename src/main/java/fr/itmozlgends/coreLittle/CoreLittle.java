package fr.itmozlgends.coreLittle;

import fr.itmozlgends.coreLittle.Commands.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class CoreLittle extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getLogger().info("Activation de CoreLittle...");
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
        getCommand("streammode").setExecutor(new StreamModeCommand());
        getCommand("vanish").setExecutor(new VanishCommand(this));
        getCommand("prout").setExecutor(new ProutCommand());
        getCommand("version").setExecutor(new VersionCommand());
        getCommand("hat").setExecutor(new HatCommand());
        getCommand("skin").setExecutor(new SkinCommand(this));
        getCommand("spawn-pet").setExecutor(new SpawnPetCommand());
        getCommand("pet").setExecutor(new PetCommand());

        this.getLogger().info("Toutes les commandes de CoreLittle sont activé !");
        this.getLogger().info("CoreLittle activé avec succès !");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.getLogger().info("Déactivation de CoreLittle...");
    }
}