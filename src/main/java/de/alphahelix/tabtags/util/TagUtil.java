package de.alphahelix.tabtags.util;

import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.WeakHashMap;

public class TagUtil {

    private static WeakHashMap<UUID, NameCache> nameCache = new WeakHashMap<>();

    public static void setTag(Player p, String tag) {
        if (nameCache.containsKey(p.getUniqueId()))
            nameCache.get(p.getUniqueId()).setLastName(tag);
        else
            nameCache.put(p.getUniqueId(), new NameCache(p.getPlayerListName(), tag));

        p.setPlayerListName(tag);
    }

    public static void resetTag(Player p) {
        if (nameCache.containsKey(p.getUniqueId())) {
            p.setPlayerListName(nameCache.get(p.getUniqueId()).getRealName());
            nameCache.remove(p.getUniqueId());
        }
    }

    public static void renewTag(Player p) {
        if (nameCache.containsKey(p.getUniqueId()))
            p.setPlayerListName(nameCache.get(p.getUniqueId()).getLastName());
    }
}

class NameCache {

    private final String realName;
    private String lastName;

    NameCache(String realName, String lastName) {
        this.realName = realName;
        this.lastName = lastName;
    }

    String getRealName() {
        return realName;
    }

    String getLastName() {
        return lastName;
    }

    NameCache setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}