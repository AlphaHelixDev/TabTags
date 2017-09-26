package de.alphahelix.tabtags.instances;

import de.alphahelix.alphalibary.storage.file.ConfigValue;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Messages implements ConfigValue<Messages> {

    private static String prefix = "&7[&3TabTags&7] ",
            tagSetOwn = "&7You set your own tag to &3[tag]",
            tagSetOther = "&7You set [player]'s tag to &3[tag]",
            tagResetOwn = "&7You changed your own tag back",
            tagResetOther = "&7You changed [player]'s tag back";

    public static String getPrefix() {
        return ChatColor.translateAlternateColorCodes('&', prefix);
    }

    public static String getTagSetOwn(String newTag) {
        return ChatColor.translateAlternateColorCodes('&', getPrefix() + tagSetOwn.replace("[tag]", newTag));
    }

    public static String getTagSetOther(Player other, String newTag) {
        return ChatColor.translateAlternateColorCodes('&', getPrefix() + tagSetOther.replace("[player]", other.getDisplayName()).replace("[tag]", newTag));
    }

    public static String getTagResetOwn() {
        return ChatColor.translateAlternateColorCodes('&', getPrefix() + tagResetOwn);
    }

    public static String getTagResetOther(Player other) {
        return ChatColor.translateAlternateColorCodes('&', getPrefix() + tagResetOther.replace("[player]", other.getDisplayName()));
    }

    @Override
    public String name() {
        return "messages";
    }
}
