package net.maksiurino.gamingconsoles.block.entity.types;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;
import com.geckolib.animatable.GeoBlockEntity;
import com.geckolib.animatable.instance.AnimatableInstanceCache;
import com.geckolib.animatable.manager.AnimatableManager;
import net.maksiurino.gamingconsoles.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import com.geckolib.animation.AnimationController;
import com.geckolib.animation.RawAnimation;
import com.geckolib.util.GeckoLibUtil;

public class NintendoSwitchBlockEntity extends BlockEntity implements GeoBlockEntity {
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    private boolean hasLeftJoycon;
    private boolean hasRightJoycon;


    private boolean oldHasLeftJoycon;
    private boolean oldHasRightJoycon;

    public boolean hasLeftJoycon() {
        return hasLeftJoycon;
    }
    public boolean hasRightJoycon() {
        return hasRightJoycon;
    }

    public void setHasLeftJoycon(boolean hasLeftJoycon) {
        this.hasLeftJoycon = hasLeftJoycon;
        setChanged();
    }

    public void setHasRightJoycon(boolean hasRightJoycon) {
        this.hasRightJoycon = hasRightJoycon;
        setChanged();
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);

        output.putBoolean("has_left_joycon", hasLeftJoycon);
        output.putBoolean("has_right_joycon", hasRightJoycon);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);

        setHasLeftJoycon(input.getBooleanOr("has_left_joycon", false));
        setHasRightJoycon(input.getBooleanOr("has_right_joycon", false));

        oldHasLeftJoycon = hasLeftJoycon;
        oldHasRightJoycon = hasRightJoycon;
    }

    public NintendoSwitchBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.NINTENDO_SWITCH_BLOCK_ENTITY, blockPos, blockState);
    }

    private void resetOlds() {
        oldHasLeftJoycon = hasLeftJoycon;
        oldHasRightJoycon = hasRightJoycon;
    }

    @Override
    public void registerControllers(final AnimatableManager.@NonNull ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(test -> {
            if (hasLeftJoycon && !hasRightJoycon) {
                resetOlds();
                return test.setAndContinue(RawAnimation.begin().thenPlay("animation.nintendo_switch.snap_blue_no-no_no").thenLoop("animation.nintendo_switch.idle_blue_no"));
            } else if (!hasLeftJoycon && hasRightJoycon) {
                resetOlds();
                return test.setAndContinue(RawAnimation.begin().thenPlay("animation.nintendo_switch.snap_no_red-no_no").thenLoop("animation.nintendo_switch.idle_no_red"));
            }
            return test.setAndContinue(RawAnimation.begin().thenLoop("animation.nintendo_switch.idle_no_no"));
        }));
    }

    @Override
    public @NonNull AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }
}
