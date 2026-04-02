package net.maksiurino.gamingconsoles.block;

import net.maksiurino.gamingconsoles.GamingConsoles;
import net.maksiurino.gamingconsoles.block.types.NintendoSwitchBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

public class ModBlocks {
    public static final Block NINTENDO_SWITCH = register(
            "nintendo_switch",
            NintendoSwitchBlock::new,
            BlockBehaviour.Properties.of().noOcclusion(),
            true
    );

    public static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties, boolean shouldRegisterItem) {
        ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, GamingConsoles.id(name));
        Block block = blockFactory.apply(properties.setId(blockKey));
        if (shouldRegisterItem) {
            ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, GamingConsoles.id(name));

            BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());
            Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
        }
        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }

    public static void bootstrap() {}
}
