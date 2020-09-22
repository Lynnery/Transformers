package com.yoni.test.KitSystem.kits;

import com.yoni.test.AbilitySystem.Ability;
import com.yoni.test.AbilitySystem.abilities.CobwebShooting;
import com.yoni.test.AbilitySystem.abilities.WallClimbing;
import com.yoni.test.KitSystem.Kit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class Spider extends Kit {

    public String getName() {
        return "Spider";
    }

    public String getDescription() {
        return "Epic Spider kit, allows you to shoot cobwebs and climb on walls!";
    }

    public Material getIcon() {
        return Material.COBWEB;
    }

    public ArrayList<ItemStack> getStartingItems() {
        return new ArrayList<ItemStack>();
    }

    public ArrayList<Ability> getAbilities() {
        ArrayList<Ability> abilities = new ArrayList<Ability>();
        abilities.add(new WallClimbing());
        abilities.add(new CobwebShooting());
        return abilities;
    }
}
