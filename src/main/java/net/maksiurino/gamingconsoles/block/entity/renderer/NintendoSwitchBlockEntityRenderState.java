package net.maksiurino.gamingconsoles.block.entity.renderer;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;

public class NintendoSwitchBlockEntityRenderState extends BlockEntityRenderState {
    private boolean hasLeftJoycon;
    private boolean hasRightJoycon;

    public boolean hasLeftJoycon() {
        return hasLeftJoycon;
    }

    public boolean hasRightJoycon() {
        return hasRightJoycon;
    }

    public void setHasLeftJoycon(boolean hasLeftJoycon) {
        this.hasLeftJoycon = hasLeftJoycon;
    }

    public void setHasRightJoycon(boolean hasRightJoycon) {
        this.hasRightJoycon = hasRightJoycon;
    }
}
