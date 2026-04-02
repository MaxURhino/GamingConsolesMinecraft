package net.maksiurino.gamingconsoles.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "gamingconsoles")
public class GamingConsolesConfig implements ConfigData {
    boolean useOfficialProductNames = true;

    public boolean isUseOfficialProductNames() {
        return useOfficialProductNames;
    }
}
