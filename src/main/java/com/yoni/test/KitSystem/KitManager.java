package com.yoni.test.KitSystem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.reflections.Reflections;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class KitManager {

    private static final String KITS_PACKAGE = "com.yoni.test.KitSystem.kits";
    private static HashMap<String, Kit> kits;

    public static void setupKits() throws IllegalAccessException, InstantiationException {
        kits = new HashMap<String, Kit>();

        //Reflections code - Adds all kits by getting all the classes from the kits package
        Reflections reflections = new Reflections(KITS_PACKAGE);

        Set<Class<? extends Kit>> allKits =
                reflections.getSubTypesOf(Kit.class);

        for(Class object : allKits) {
            Kit kit = (Kit) object.newInstance();
            if(kit != null) {
                registerKit(kit);
            }
        }
    }

    public static Collection<Kit> getKits() {
        return kits.values();
    }

    private static void registerKit(Kit kit) {
        kits.put(kit.getName(), kit);
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + kit.getName() + " KIT HAS BEEN REGISTERED!");
    }

    public static Kit getKit(String kitName) {
        if(kits.isEmpty())
            return null;
        return kits.get(kitName.toLowerCase());
    }

}
