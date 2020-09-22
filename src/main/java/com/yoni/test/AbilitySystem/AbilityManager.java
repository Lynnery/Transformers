package com.yoni.test.AbilitySystem;

import com.yoni.test.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.reflections.Reflections;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class AbilityManager {

    private static final String ABILITIES_PACKAGE = "com.yoni.test.AbilitySystem.abilities";
    private static HashMap<String, Ability> abilities;

    public static void setupAbilities() throws IllegalAccessException, InstantiationException {
        abilities = new HashMap<String, Ability>();

        //Reflections code - Adds all abilities by getting all the classes from the abilities package
        Reflections reflections = new Reflections(ABILITIES_PACKAGE);

        Set<Class<? extends Ability>> allAbilities =
                reflections.getSubTypesOf(Ability.class);

        for(Class object : allAbilities) {
            Ability ability = (Ability) object.newInstance();
            if(ability != null) {
                registerAbility(ability);
            }
        }

        //Register Abilities event listeners
        for(Ability ability : abilities.values()) {
            Bukkit.getPluginManager().registerEvents(ability, Main.getPlugin(Main.class));
        }
    }

    private static void registerAbility(Ability ability) {
        abilities.put(ability.getName(), ability);
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + ability.getName() + " ABILITY HAS BEEN REGISTERED!");
    }

    public static String getStringFromAbilities(ArrayList<Ability> abilities) {
        String showText = "";
        for(Ability ability : abilities) {
            showText += ability.getName() + ", ";
        }
        showText = showText.substring(0, showText.length() - 2);
        return showText;
    }

    public static Collection<Ability> getAbilities() {
        return abilities.values();
    }

    public static Ability getAbility(String abilityName) {
        if(abilities.isEmpty()) {
            return null;
        }
        return abilities.get(abilityName.toLowerCase());
    }

}
