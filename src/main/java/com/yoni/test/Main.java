package com.yoni.test;

import com.yoni.test.AbilitySystem.Ability;
import com.yoni.test.AbilitySystem.AbilityManager;
import com.yoni.test.KitSystem.Kit;
import com.yoni.test.KitSystem.KitManager;
import com.yoni.test.Utils.ItemStackUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin implements Listener {

    public static Main instance;

    public void onEnable() {
        instance = this;

        getServer().getPluginManager().registerEvents(this, this);

        Bukkit.getScheduler().runTaskLater(this, new Runnable() {
            public void run() {
                try {
                    KitManager.setupKits();
                    AbilityManager.setupAbilities();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Plugin Works!");
            }
        }, 20);
    }

    public static Main getInstance() {
        return instance;
    }

    @EventHandler
    public void selectKit(InventoryClickEvent event) {
        if(!event.getView().getTitle().contains("Kits")) return;
        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();
        String kitName = clickedItem.getItemMeta().getDisplayName().split(" ")[0];
        Kit selectedKit = KitManager.getKit(kitName);
        selectedKit.applyOn(player);
        player.closeInventory();
        event.setCancelled(true);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String tag, String[] args) {
        if(tag.equalsIgnoreCase("test")) {
            Inventory inventory = Bukkit.createInventory(null, 9, "Kits");

            for(Kit kit : KitManager.getKits()) {
                List<String> descriptionLore = new ArrayList<String>();
                descriptionLore.add("");
                descriptionLore.add("");
                descriptionLore.add("&bDescription: &8" + kit.getDescription());
                descriptionLore.add("");
                descriptionLore.add("");
                descriptionLore.add("&bAbilities: &a" + AbilityManager.getStringFromAbilities(kit.getAbilities()));
                for(Ability ability : kit.getAbilities()) {
                    descriptionLore.add("&c" + ability.getName() + " &bability: &8" + ability.getDescription());
                }
                inventory.addItem(ItemStackUtil.createItem(kit.getIcon(), kit.getName() + " Kit", 1, descriptionLore));
            }

            ((Player)sender).openInventory(inventory);
            return true;
        }
        return false;
    }



}
