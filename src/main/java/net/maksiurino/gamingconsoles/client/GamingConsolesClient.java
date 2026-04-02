package net.maksiurino.gamingconsoles.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.maksiurino.gamingconsoles.block.entity.ModBlockEntities;
import net.maksiurino.gamingconsoles.block.entity.renderer.NintendoSwitchBlockEntityRenderState;
import net.maksiurino.gamingconsoles.block.entity.types.NintendoSwitchBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import com.geckolib.renderer.GeoBlockRenderer;

@Environment(EnvType.CLIENT)
public class GamingConsolesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRenderers.register(ModBlockEntities.NINTENDO_SWITCH_BLOCK_ENTITY, context -> new GeoBlockRenderer<NintendoSwitchBlockEntity, NintendoSwitchBlockEntityRenderState>(context, ModBlockEntities.NINTENDO_SWITCH_BLOCK_ENTITY));
    }
}
