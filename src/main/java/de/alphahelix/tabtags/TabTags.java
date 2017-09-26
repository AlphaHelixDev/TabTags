package de.alphahelix.tabtags;

import de.alphahelix.alphalibary.core.utils.JSONUtil;
import de.alphahelix.alphalibary.storage.file.SimpleConfig;
import de.alphahelix.tabtags.commands.TagCommand;
import de.alphahelix.tabtags.instances.Messages;
import org.bukkit.plugin.java.JavaPlugin;

public class TabTags extends JavaPlugin {

    private static TabTags instance;

    public static TabTags getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        JSONUtil.disableEscaping();

        new TagCommand();

        SimpleConfig messageConfig = new SimpleConfig(this, "messages.json");
        Messages messages = new Messages();

        messageConfig.addValues(messages);
        messageConfig.applyValue(messages);
    }
}
