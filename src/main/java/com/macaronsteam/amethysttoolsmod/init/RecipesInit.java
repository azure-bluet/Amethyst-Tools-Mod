/**
 * Copyright © 2022 Kitoglav Licensed under the Apache License, Version 2.0
 **/
package com.macaronsteam.amethysttoolsmod.init;

import java.util.function.Supplier;
import org.apache.logging.log4j.core.util.ReflectionUtil;
import com.google.common.primitives.Booleans;
import com.macaronsteam.amethysttoolsmod.AmethystToolsMod;
import com.macaronsteam.amethysttoolsmod.config.AmethystToolsModConfig;
import com.macaronsteam.amethysttoolsmod.recipe.ConfigValueCondition;
import com.macaronsteam.amethysttoolsmod.recipe.TippedAmethystArrowRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RecipesInit {
  public static RegistryObject<RecipeSerializer<?>> RECIPE_AMETHYST_TIPPED_ARROW;
  public static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, AmethystToolsMod.MODID);

  public static void register() {
    RECIPE_AMETHYST_TIPPED_ARROW = register("crafting_special_amethysttippedarrow", () -> new SimpleCraftingRecipeSerializer<>(TippedAmethystArrowRecipe::new), AmethystToolsModConfig.enableAmethystArrows.get(), AmethystToolsModConfig.enableExtraArrows.get());
    CraftingHelper.register(ConfigValueCondition.Serializer.INSTANCE);
  }

  private static RegistryObject<RecipeSerializer<?>> register(String name, Supplier <SimpleCraftingRecipeSerializer <?>> recipe, boolean... condition) {
    if (!Booleans.contains(condition, false) && name != null && recipe != null)
      return RECIPES.register(name, recipe);
    return ReflectionUtil.instantiate (RegistryObject.class);
  }
}
