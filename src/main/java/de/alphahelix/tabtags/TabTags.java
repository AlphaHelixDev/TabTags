package de.alphahelix.tabtags;

import org.bukkit.plugin.java.JavaPlugin;

public class TabTags extends JavaPlugin {

    private static TabTags instance;

    @Override
    public void onEnable() {
        instance = this;
    }

    public static TabTags getInstance() {
        return instance;
    }
}
