package net.maksiurino.gamingconsoles.item;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.maksiurino.gamingconsoles.GamingConsoles;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class ModItems {
    public static final Item LCD = registerItem("lcd", Item::new, new Item.Properties());
    public static final Item BLACK_GRILL = registerItem("black_grill", Item::new, new Item.Properties());
    public static final Item JOYCON_LEFT = registerItem("joycon_left", Item::new, new Item.Properties());

    private static <T extends Item> T registerItem(String name, Function<Item.Properties, T> itemFactory, Item.Properties properties) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, GamingConsoles.id(name));
        T item = itemFactory.apply(properties.setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
        return item;
    }

    public static void bootstrap() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register((entries) -> {
            entries.accept(LCD);
            entries.accept(BLACK_GRILL);
        });
    }
}
