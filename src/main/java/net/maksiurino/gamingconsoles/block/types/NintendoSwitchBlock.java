package net.maksiurino.gamingconsoles.block.types;

import com.mojang.serialization.MapCodec;
import net.maksiurino.gamingconsoles.block.entity.ModBlockEntities;
import net.maksiurino.gamingconsoles.block.entity.types.NintendoSwitchBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.Map;

public class NintendoSwitchBlock extends HorizontalDirectionalBlock implements EntityBlock {
    public static final MapCodec<NintendoSwitchBlock> CODEC = simpleCodec(NintendoSwitchBlock::new);
    private static final Map<Direction, VoxelShape> SHAPE = Shapes.rotateHorizontal(Block.box(-2, 0, 6, 18, 11, 9));

    public NintendoSwitchBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    @Override
    public @NonNull MapCodec<NintendoSwitchBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    protected @NonNull VoxelShape getShape(BlockState blockState, @NonNull BlockGetter blockGetter, @NonNull BlockPos blockPos, @NonNull CollisionContext collisionContext) {
        return SHAPE.get(blockState.getValue(FACING));
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return defaultBlockState().setValue(FACING, blockPlaceContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NonNull BlockPos pos, @NonNull BlockState state) {
        return ModBlockEntities.NINTENDO_SWITCH_BLOCK_ENTITY.create(pos, state);
    }
}
