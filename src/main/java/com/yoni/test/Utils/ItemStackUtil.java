package com.yoni.test.Utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemStackUtil {

    public static ItemStack createItem(Material material, int amount, int data,String name,ArrayList<String> lore) {
        ItemStack item = new ItemStack(material,amount,(byte)data);
        ItemMeta im = item.getItemMeta();
        im.setLore(lore);
        im.setDisplayName(name);
        item.setItemMeta(im);
        return item;
    }

    public static ItemStack createItem(Material mat, String name, int amount) {
        ItemStack stack = new ItemStack(mat, amount);
        ItemMeta stackmeta = stack.getItemMeta();
        stackmeta.setDisplayName(name);
        stack.setItemMeta(stackmeta);
        return stack;
    }

    public static ItemStack createItem(Material mat, String name, int amount, List<String> lore) {
        List<String> newLore = new ArrayList<String>();
        for(String string : lore) {
            newLore.add(ChatColor.translateAlternateColorCodes('&', string));
        }
        ItemStack stack = new ItemStack(mat, amount);
        ItemMeta stackmeta = stack.getItemMeta();
        stackmeta.setDisplayName(name);
        stackmeta.setLore(newLore);
        stack.setItemMeta(stackmeta);
        return stack;
    }

    public static ItemStack createItem(ItemStack mat, String name, int amount, List<String> lore) {
        ItemStack stack = mat;
        ItemMeta stackmeta = stack.getItemMeta();
        stackmeta.setDisplayName(name);
        stackmeta.setLore(lore);
        stack.setItemMeta(stackmeta);
        return stack;
    }
    public static ItemStack createItem(ItemStack mat, String name, int amount) {
        ItemStack stack = mat;
        ItemMeta stackmeta = stack.getItemMeta();
        stackmeta.setDisplayName(name);
        stack.setItemMeta(stackmeta);
        return stack;
    }

    public static String createString(String from) {return ChatColor.translateAlternateColorCodes('&', from);}

    public static void setName(ItemStack item, String name){ItemMeta itemm = item.getItemMeta();itemm.setDisplayName(createString(name));item.setItemMeta(itemm);}

    public static void addColoredArmor(ItemStack material, Color color){
        LeatherArmorMeta item = (LeatherArmorMeta) material.getItemMeta();
        item.setColor(color);
        material.setItemMeta(item);

    }
}