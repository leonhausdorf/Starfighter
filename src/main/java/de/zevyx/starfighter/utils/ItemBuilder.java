package de.zevyx.starfighter.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemBuilder {

    private ItemStack is;

    public ItemBuilder(Material m) {
        is = new ItemStack(m);
    }

    public ItemBuilder setAmount(int amount) {
        is.setAmount(amount);
        return this;
    }

    public ItemBuilder setName(String name) {
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(name);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        ItemMeta im = is.getItemMeta();
        im.setLore(lore);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder enchantItem(Enchantment enchantment, Integer level) {
    	ItemMeta im = is.getItemMeta();
    	im.addEnchant(enchantment, level, true);
        is.setItemMeta(im);
    	return this;
    }

    public ItemStack build() {
        return is;
    }

}
