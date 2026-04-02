package net.maksiurino.gamingconsoles.block;

import net.maksiurino.gamingconsoles.GamingConsoles;
import net.maksiurino.gamingconsoles.block.types.NintendoSwitchBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

public class ModBlockTypes {
    public static void bootstrap() {
        Registry.register(BuiltInRegistries.BLOCK_TYPE, GamingConsoles.id("nintendo_switch"), NintendoSwitchBlock.CODEC);
    }
}
