package net.maksiurino.gamingconsoles.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.maksiurino.gamingconsoles.GamingConsoles;
import net.maksiurino.gamingconsoles.block.ModBlocks;
import net.maksiurino.gamingconsoles.block.entity.types.NintendoSwitchBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntities {
    public static final BlockEntityType<NintendoSwitchBlockEntity> NINTENDO_SWITCH_BLOCK_ENTITY = register(
            "nintendo_switch", NintendoSwitchBlockEntity::new, ModBlocks.NINTENDO_SWITCH
    );

    private static <T extends BlockEntity> BlockEntityType<T> register(
            String name, FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory,
            Block... blocks) {
        return Registry.register(
                BuiltInRegistries.BLOCK_ENTITY_TYPE,
                GamingConsoles.id(name),
                FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build()
        );
    }

    public static void bootstrap() {}
}
