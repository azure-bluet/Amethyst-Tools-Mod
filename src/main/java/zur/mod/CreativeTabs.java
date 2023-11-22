package zur.mod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Supplier;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

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
}
