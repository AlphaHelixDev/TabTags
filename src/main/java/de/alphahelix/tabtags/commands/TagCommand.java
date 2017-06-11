package de.alphahelix.tabtags.commands;

import de.alphahelix.alphalibary.command.CommandWatcher;
import de.alphahelix.alphalibary.command.SimpleCommand;
import de.alphahelix.alphalibary.command.arguments.PlayerArgument;
import de.alphahelix.alphalibary.command.arguments.StringArgument;
import de.alphahelix.alphalibary.utils.MessageUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;
import java.util.WeakHashMap;

public class TagCommand extends SimpleCommand {

    private static WeakHashMap<UUID, String> nameCache = new WeakHashMap<>();

    public TagCommand() {
        super("tag", "Main tag command", "tags", "tabtag", "ttag");
    }

    @Override
    public boolean execute(CommandSender cs, String s, String[] args) {

        if (!(cs instanceof Player)) return true;

        Player p = (Player) cs;

        CommandWatcher setOwnC = new CommandWatcher(args); // /tag set <name>
        CommandWatcher setOtherC = new CommandWatcher(args); // /tag set <name> <player>
        CommandWatcher resetC = new CommandWatcher(args); // /tag reset
        CommandWatcher resetOtherC = new CommandWatcher(args); // /tag reset <player>

        StringArgument set = new StringArgument();
        StringArgument name = new StringArgument();
        StringArgument reset = new StringArgument();
        PlayerArgument player = new PlayerArgument();

        setOwnC.addArguments(set, name);
        setOtherC.addArguments(set, name, player);
        resetC.addArguments(reset);
        resetOtherC.addArguments(reset, player);

        if (setOwnC.isSame()) {
            if (p.hasPermission("tabtags.set.own") || p.hasPermission("tabtags.admin")) {
                nameCache.put(p.getUniqueId(), p.getPlayerListName());
                p.setPlayerListName(name.fromArgument().replace("&", "§"));
            }
            return true;
        } else if (setOtherC.isSame()) {
            if (p.hasPermission("tabtags.set.others") || p.hasPermission("tabtags.admin")) {
                nameCache.put(p.getUniqueId(), player.fromArgument().getPlayerListName());
                player.fromArgument().setPlayerListName(name.fromArgument().replace("&", "§"));
            }
            return true;
        } else if (resetC.isSame()) {
            if (p.hasPermission("tabtags.reset.own") || p.hasPermission("tabtags.admin")) {
                p.setPlayerListName(nameCache.get(p.getUniqueId()));
                nameCache.remove(p.getUniqueId());
            }
            return true;
        } else if (resetOtherC.isSame()) {
            if (p.hasPermission("tabtags.reset.others") || p.hasPermission("tabtags.admin")) {
                player.fromArgument().setPlayerListName(nameCache.get(player.fromArgument().getUniqueId()));
                nameCache.remove(player.fromArgument().getUniqueId());
            }
            return true;
        }

        MessageUtil.sendCenteredMessage(p, "§3" + new String(new char[53]).replace("\0", "="));
        p.sendMessage(" ");
        MessageUtil.sendCenteredMessage(p, "§7/tag set §8<§7name§8> §3- §7Set's own tag");
        MessageUtil.sendCenteredMessage(p, "§7/tag set §8<§7name§8> §8<§7player§8> §3- §7Set's someone's tag");
        MessageUtil.sendCenteredMessage(p, "§7/tag reset §3- §7Reset's own tag");
        MessageUtil.sendCenteredMessage(p, "§7/tag reset §8<§7player§8> §3- §7Reset's someone's tag");
        p.sendMessage(" ");
        MessageUtil.sendCenteredMessage(p, "§3" + new String(new char[53]).replace("\0", "="));
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender commandSender, String s, String[] strings) {
        return null;
    }
}
