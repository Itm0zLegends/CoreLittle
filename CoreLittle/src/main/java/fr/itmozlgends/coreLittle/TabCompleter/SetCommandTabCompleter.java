package fr.itmozlgends.coreLittle.TabCompleter;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class SetCommandTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> suggestions = new ArrayList<>();

        if (args.length == 1) {
            for (Material material : Material.values()) {
                if (material.isBlock() && material.name().toLowerCase().startsWith(args[0].toLowerCase())) {
                    suggestions.add(material.name().toLowerCase());
                }
            }
        }
        return suggestions;
    }
}
