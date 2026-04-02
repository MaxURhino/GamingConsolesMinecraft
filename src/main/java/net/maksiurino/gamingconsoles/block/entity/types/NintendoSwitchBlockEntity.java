package net.maksiurino.gamingconsoles.block.entity.types;

import org.jspecify.annotations.NonNull;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import net.maksiurino.gamingconsoles.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class NintendoSwitchBlockEntity extends BlockEntity implements GeoBlockEntity {
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    public static final RawAnimation IDLE_NO_NO = RawAnimation.begin().thenLoop("animation.nintendo_switch.idle_no_no");

    public NintendoSwitchBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.NINTENDO_SWITCH_BLOCK_ENTITY, blockPos, blockState);
    }

    @Override
    public void registerControllers(final AnimatableManager.@NonNull ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>("Idle", test -> test.setAndContinue(IDLE_NO_NO)));
    }

    @Override
    public @NonNull AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }
}
