package net.maksiurino.gamingconsoles.item.types;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NonNull;

import java.util.function.Supplier;

public class ConfigurableTranslatableBlockItem extends BlockItem {
    private final Supplier<Boolean> changeNameOn;
    private final Component firstName;
    private final Component changedName;

    public ConfigurableTranslatableBlockItem(Block block, Properties properties, Supplier<Boolean> changeNameOn, Component firstName, Component changedName) {
        super(block, properties);
        this.changeNameOn = changeNameOn;
        this.firstName = firstName;
        this.changedName = changedName;
    }

    @Override
    public @NonNull Component getName(@NonNull ItemStack itemStack) {
        return changeNameOn.get() ? this.changedName : this.firstName;
    }
}
