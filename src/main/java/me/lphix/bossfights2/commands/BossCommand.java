package me.lphix.bossfights2.commands;
import me.lphix.bossfights2.bosses.ABoss;
import me.lphix.bossfights2.bosses.BossFactory;
import me.lphix.bossfights2.utilities.ItemUtilities;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

public class BossCommand implements CommandExecutor {
    BossFactory bossFactory;
    ItemUtilities itemUtilities;
    public BossCommand(BossFactory bossFactory, ItemUtilities itemUtilities){
        this.bossFactory = bossFactory;
        this.itemUtilities = itemUtilities;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player p)){
            sender.sendMessage(Component.text("Command can only be executed by players", NamedTextColor.RED));
            return true;
        }
        if(!p.hasPermission("bossfight2.admin")){
            p.sendMessage(Component.text("You don't have permission", NamedTextColor.RED));
            return true;
        }
        if(args.length == 1){
            if(!args[0].equals("item")) return false;
            ItemStack item = new ItemStack(Material.MAGMA_CREAM);
            itemUtilities.giveMetaKey(item, "boss-item");
            p.getInventory().addItem(item);
        }
        if(args.length == 2){
            if(!args[0].equals("kill")) return false;
            if(StringUtils.isNumeric(args[1])) {
                double range = Double.parseDouble(args[1]);
                int count = 0;
                for (ABoss aBoss:bossFactory.getActiveBosses()){
                    if(aBoss.getBossEntity().getLocation().distance(p.getLocation()) < range){
                        ((LivingEntity) aBoss.getBossEntity()).setHealth(0.0);
                        count++;
                    }
                }
                p.sendMessage(Component.text("Killed " + count + " bosses within a radius of " + range, NamedTextColor.GREEN));
                return true;
            } else if (args[1].equals("all")) {
                int count = 0;
                for (ABoss aBoss:bossFactory.getActiveBosses()){
                    ((LivingEntity) aBoss.getBossEntity()).setHealth(0.0);
                    count++;
                }
                p.sendMessage(Component.text("Killed " + count + " bosses, all that were present in the server.", NamedTextColor.GREEN));
                return true;
            }
        }
        return false;
    }
}
