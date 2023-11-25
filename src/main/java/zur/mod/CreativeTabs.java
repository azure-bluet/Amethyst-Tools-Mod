/**
 * Copyright © 2023 azure__bluet Licensed under the Apache License, Version 2.0
 **/
package zur.mod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Supplier;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;

public class CreativeTabs {
    public static RegistryObject <CreativeModeTab> tab (
        DeferredRegister <CreativeModeTab> registry,
        String name,
        String trans,
        Supplier <ItemStack> icon,
        List <Supplier <ItemStack>> items
    ) {
        return registry.register (
            name,
            () -> CreativeModeTab.builder ()
                .title (Component.translatable (trans))
                .icon (icon)
                .displayItems (
                    (p, t) -> {
                        for (Supplier <ItemStack> i : items) {
                            t.accept (i.get ());
                        }
                    }
                )
                .build ()
        );
    }

    public static Supplier <ItemStack> getter (RegistryObject <Item> item) {
        return () -> new ItemStack (item.get ());
    }

    public static Supplier <ItemStack> tipped (Supplier <ItemStack> original, Supplier <Potion> potion) {
        return () -> {
            ItemStack st = original.get ();
            PotionUtils.setPotion (st, potion.get ());
            return st;
        };
    }
}
