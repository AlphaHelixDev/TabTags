package de.alphahelix.tabtags.commands;

import de.alphahelix.alphalibary.annotations.SimpleAnnotatedCommand;
import de.alphahelix.alphalibary.annotations.command.Command;
import de.alphahelix.alphalibary.annotations.command.Optional;
import de.alphahelix.alphalibary.core.utils.MessageUtil;
import de.alphahelix.tabtags.instances.Messages;
import de.alphahelix.tabtags.util.TagUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TagCommand extends SimpleAnnotatedCommand {

    @Command(onlyPlayers = true,
            max = 3,
            usage = "<set/reset> [name] [player]",
            alias = {"tags", "tabtag", "ttag"},
            description = "Main tag command")
    public void tag(Player p, @Optional(define = "help") String action, @Optional(define = " ") String name, @Optional(define = "ab") String player) {
        if (action.equalsIgnoreCase("set") && !name.trim().isEmpty()) {
            if (player.equals("ab")) {
                if (p.hasPermission("tabtags.set.own") || p.hasPermission("tabtags.admin")) {
                    TagUtil.setTag(p, name.replace("&", "§"));
                    p.sendMessage(Messages.getTagSetOwn(name));
                }
            } else {
                Player p2 = Bukkit.getPlayer(player);
                if (p.hasPermission("tabtags.set.others") || p.hasPermission("tabtags.admin")) {
                    TagUtil.setTag(p2, name.replace("&", "§"));
                    p.sendMessage(Messages.getTagSetOther(p2, name));
                }
            }
        } else if (action.equalsIgnoreCase("reset")) {
            if (player.equals("ab")) {
                if (p.hasPermission("tabtags.reset.own") || p.hasPermission("tabtags.admin")) {
                    TagUtil.resetTag(p);
                    p.sendMessage(Messages.getTagResetOwn());
                }
            } else {
                Player p2 = Bukkit.getPlayer(player);
                if (p.hasPermission("tabtags.reset.others") || p.hasPermission("tabtags.admin")) {
                    TagUtil.resetTag(p2);
                    p.sendMessage(Messages.getTagResetOther(p2));
                }
            }
        } else {
            MessageUtil.sendCenteredMessage(p, "§3" + new String(new char[53]).replace("\0", "="));
            p.sendMessage(" ");
            MessageUtil.sendCenteredMessage(p, "§7/tag set §8<§7name§8> §3- §7Set's own tag");
            MessageUtil.sendCenteredMessage(p, "§7/tag set §8<§7name§8> §8<§7player§8> §3- §7Set's someone's tag");
            MessageUtil.sendCenteredMessage(p, "§7/tag reset §3- §7Reset's own tag");
            MessageUtil.sendCenteredMessage(p, "§7/tag reset §8<§7player§8> §3- §7Reset's someone's tag");
            p.sendMessage(" ");
            MessageUtil.sendCenteredMessage(p, "§3" + new String(new char[53]).replace("\0", "="));
        }
    }
}
