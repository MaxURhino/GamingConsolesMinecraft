package net.maksiurino.gamingconsoles.block;

import me.shedaniel.autoconfig.AutoConfig;
import net.maksiurino.gamingconsoles.GamingConsoles;
import net.maksiurino.gamingconsoles.block.types.NintendoSwitchBlock;
import net.maksiurino.gamingconsoles.config.GamingConsolesConfig;
import net.maksiurino.gamingconsoles.item.types.ConfigurableTranslatableBlockItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {
    public static final Block NINTENDO_SWITCH = registerWithConfigurableTranslatableName(
            "nintendo_switch",
            NintendoSwitchBlock::new,
            BlockBehaviour.Properties.of().noOcclusion(),
            true,
            () -> AutoConfig.getConfigHolder(GamingConsolesConfig.class).get().isUseOfficialProductNames(),
            Component.translatable("block.gamingconsoles.nintendo_switch"),
            Component.translatable("block.gamingconsoles.nintendo_switch.official")
    );

    public static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties, boolean shouldRegisterItem) {
        return registerWithConfigurableTranslatableName(name, blockFactory, properties, shouldRegisterItem, () -> shouldRegisterItem, Component.translatable("block.gamingconsoles." + name), Component.translatable("block.gamingconsoles." + name));
    }

    public static Block registerWithConfigurableTranslatableName(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties, boolean shouldRegisterItem, Supplier<Boolean> changeNameOn, Component firstName, Component changedName) {
        ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, GamingConsoles.id(name));
        Block block = blockFactory.apply(properties.setId(blockKey));
        if (shouldRegisterItem) {
            ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, GamingConsoles.id(name));

            ConfigurableTranslatableBlockItem blockItem = new ConfigurableTranslatableBlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix(), changeNameOn, firstName, changedName);
            Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
        }
        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }

    public static void bootstrap() {}
}
