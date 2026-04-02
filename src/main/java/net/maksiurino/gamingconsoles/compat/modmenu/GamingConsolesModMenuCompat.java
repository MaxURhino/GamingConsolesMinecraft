package net.maksiurino.gamingconsoles.compat.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfigClient;
import net.maksiurino.gamingconsoles.config.GamingConsolesConfig;

public class GamingConsolesModMenuCompat implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> AutoConfigClient.getConfigScreen(GamingConsolesConfig.class, parent).get();
    }
}
