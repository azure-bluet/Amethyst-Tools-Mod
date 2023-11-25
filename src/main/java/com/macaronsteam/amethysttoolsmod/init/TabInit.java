package com.macaronsteam.amethysttoolsmod.init;

import com.macaronsteam.amethysttoolsmod.AmethystToolsMod;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import zur.mod.CreativeTabs;
import zur.utils.AdvList;

import static zur.mod.CreativeTabs.getter;
import static zur.mod.CreativeTabs.tipped;

import java.util.List;

public class TabInit {
    public static final DeferredRegister <CreativeModeTab> registry = DeferredRegister.create (Registries.CREATIVE_MODE_TAB, AmethystToolsMod.MODID);
    public static final RegistryObject <CreativeModeTab> default_tab = CreativeTabs.tab (
        registry,
        "default",
        "tab.amethysttoolsmod.default",
        () -> new ItemStack (ItemsInit.ITEM_AMETHYST_TRIDENT.get ()),
        (
            AdvList.of (
                getter (ItemsInit.ITEM_AMETHYST_ARROW),
                getter (ItemsInit.ITEM_AMETHYST_CLUSTER),
                getter (ItemsInit.ITEM_AMETHYST_CLUSTER_LV2),
                getter (ItemsInit.ITEM_AMETHYST_DUST),
                getter (ItemsInit.ITEM_AMETHYST_SPECTRAL_ARROW),
                getter (ItemsInit.ITEM_AMETHYST_TRIDENT)
            )
            .iteration (
                (Potion p) -> (tipped (getter (ItemsInit.ITEM_AMETHYST_TIPPED_ARROW), (() -> p))),
                new AdvList.ListIter <Potion> (
                    List.of (
                        Potions.NIGHT_VISION,
                        Potions.INVISIBILITY,
                        Potions.LEAPING,
                        Potions.FIRE_RESISTANCE,
                        Potions.SWIFTNESS,
                        Potions.SLOWNESS,
                        Potions.TURTLE_MASTER,
                        Potions.WATER_BREATHING,
                        Potions.HEALING,
                        Potions.HARMING,
                        Potions.POISON,
                        Potions.REGENERATION,
                        Potions.STRENGTH,
                        Potions.WEAKNESS,
                        Potions.LUCK,
                        Potions.SLOW_FALLING
                    )
                )
            )
            .iteration (
                (String s) -> (() -> new ItemStack (ForgeRegistries.ITEMS.getValue (new ResourceLocation (s)), 1)),
                new AdvList.Iter <String> () {
                    private int index_type = 0, index_material = 0;
                    public boolean ended () {
                        return this.index_material >= ItemsInit.mats.length;
                    }
                    public String next () {
                        String ret = "amethysttoolsmod:" + ItemsInit.mats [index_material] + "_" + ItemsInit.types [index_type] +"_amethyst";
                        index_type ++;
                        if (this.index_type >= ItemsInit.types.length) {
                            index_type = 0;
                            index_material ++;
                        }
                        return ret;
                    }
                }
            )
            .list ()
        )
    );
}
