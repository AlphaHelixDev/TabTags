package de.alphahelix.tabtags;

import de.alphahelix.tabtags.commands.TagCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class TabTags extends JavaPlugin {

    private static TabTags instance;

    @Override
    public void onEnable() {
        instance = this;

        new TagCommand();
    }

    public static TabTags getInstance() {
        return instance;
    }
}
