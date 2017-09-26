package de.alphahelix.tabtags.listener;

import de.alphahelix.alphalibary.core.SimpleLoader;
import de.alphahelix.alphalibary.core.utils.Util;
import de.alphahelix.tabtags.util.TagUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class TagListener implements SimpleLoader {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Util.runLater(5, false, () -> TagUtil.renewTag(e.getPlayer()));
    }

}
