package net.maksiurino.gamingconsoles.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.maksiurino.gamingconsoles.block.ModBlocks;
import net.maksiurino.gamingconsoles.block.entity.ModBlockEntities;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

@Environment(EnvType.CLIENT)
public class GamingConsolesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.putBlock(ModBlocks.NINTENDO_SWITCH, ChunkSectionLayer.TRANSLUCENT);
        BlockEntityRenderers.register(ModBlockEntities.NINTENDO_SWITCH_BLOCK_ENTITY, _ -> new GeoBlockRenderer<>(ModBlockEntities.NINTENDO_SWITCH_BLOCK_ENTITY));
    }
}
