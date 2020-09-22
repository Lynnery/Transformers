package com.yoni.test.KitSystem;

import com.yoni.test.AbilitySystem.Ability;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public abstract class Kit {

    public abstract String getName();

    public abstract String getDescription();

    public abstract Material getIcon();

    public abstract ArrayList<ItemStack> getStartingItems();

    public abstract ArrayList<Ability> getAbilities();

    public void applyOn(Player player) {
        player.sendMessage("You received the " + getName() + " kit!");
        player.getInventory().clear();
        for(ItemStack item : getStartingItems()) {
            player.getInventory().addItem(item);
        }
    }
}
