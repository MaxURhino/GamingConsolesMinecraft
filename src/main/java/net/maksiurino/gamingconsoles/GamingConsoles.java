package net.maksiurino.gamingconsoles;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;

import net.maksiurino.gamingconsoles.block.ModBlockTypes;
import net.maksiurino.gamingconsoles.block.ModBlocks;
import net.maksiurino.gamingconsoles.block.entity.ModBlockEntities;
import net.maksiurino.gamingconsoles.config.GamingConsolesConfig;
import net.maksiurino.gamingconsoles.item.ModItemGroups;
import net.maksiurino.gamingconsoles.item.ModItems;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GamingConsoles implements ModInitializer {
	public static final String MOD_ID = "gamingconsoles";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		AutoConfig.register(GamingConsolesConfig.class, JanksonConfigSerializer::new);

		ModItemGroups.bootstrap();
		ModItems.bootstrap();
		ModBlocks.bootstrap();
		ModBlockEntities.bootstrap();
		ModBlockTypes.bootstrap();
	}

	public static Identifier id(final String name) {
		return Identifier.fromNamespaceAndPath(MOD_ID, name);
	}
}