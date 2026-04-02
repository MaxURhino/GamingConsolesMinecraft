package net.maksiurino.gamingconsoles.item;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.maksiurino.gamingconsoles.GamingConsoles;
import net.maksiurino.gamingconsoles.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModItemGroups {
    public static final ResourceKey<CreativeModeTab> NINTENDO_GROUP = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), GamingConsoles.id("nintendo_switch"));

    public static void bootstrap() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
                NINTENDO_GROUP,
                FabricCreativeModeTab.builder().title(Component.translatable("itemGroup.gamingconsoles.nintendo_switch"))
                        .icon(() -> new ItemStack(ModBlocks.NINTENDO_SWITCH)).displayItems((_, entries) -> {
                            entries.accept(ModBlocks.NINTENDO_SWITCH);
                            entries.accept(ModItems.JOYCON_LEFT);
                            entries.accept(ModItems.JOYCON_RIGHT);
                            entries.accept(ModItems.LCD);
                            entries.accept(ModItems.BLACK_GRILL);
                        }).build());
    }
}
