package net.maksiurino.gamingconsoles.block.types;

import com.mojang.serialization.MapCodec;
import net.maksiurino.gamingconsoles.block.entity.ModBlockEntities;
import net.maksiurino.gamingconsoles.block.entity.types.NintendoSwitchBlockEntity;
import net.maksiurino.gamingconsoles.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SelectableSlotContainer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.Map;
import java.util.OptionalInt;

public class NintendoSwitchBlock extends HorizontalDirectionalBlock implements EntityBlock, SelectableSlotContainer {
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

    @Override
    protected @NonNull InteractionResult useItemOn(@NonNull ItemStack itemStack, @NonNull BlockState state, Level level, @NonNull BlockPos pos,
                                                   @NonNull Player player, @NonNull InteractionHand hand, @NonNull BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof NintendoSwitchBlockEntity be) {
            if (itemStack.is(ModItems.JOYCON_LEFT) && !be.hasLeftJoycon()) {
                be.setHasLeftJoycon(true);
                itemStack.consume(1, player);
                return InteractionResult.SUCCESS;
            } else if (itemStack.is(ModItems.JOYCON_RIGHT) && !be.hasRightJoycon()) {
                be.setHasRightJoycon(true);
                itemStack.consume(1, player);
                return InteractionResult.SUCCESS;
            } else if (itemStack.isEmpty()) {
                OptionalInt hitSlot = this.getHitSlot(hitResult, state.getValue(FACING));
                if (hitSlot.isEmpty()) {
                    return InteractionResult.PASS;
                }
                int hitSlotInt = hitSlot.getAsInt();
                if (hitSlotInt == 0 && be.hasLeftJoycon()) {
                    be.setHasLeftJoycon(false);
                    player.addItem(ModItems.JOYCON_LEFT.getDefaultInstance());
                    return InteractionResult.SUCCESS;
                } else if (hitSlotInt == 1 && be.hasRightJoycon()) {
                    be.setHasRightJoycon(false);
                    player.addItem(ModItems.JOYCON_RIGHT.getDefaultInstance());
                    return InteractionResult.SUCCESS;
                }
            }
        }

        return InteractionResult.PASS;
    }

    @Override
    public int getRows() {
        return 1;
    }

    @Override
    public int getColumns() {
        return 2;
    }
}
