package com.jameslin.progression;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.world.biome.Biome;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ProgressionConfig {
    public boolean disableEndDimension;
    public boolean disableNetherDimension;
    public boolean displayDisableMessages;
    public String endDisabledMessage;
    public String netherDisabledMessage;
    public boolean isDefaultSettings;
    private static String configPath = "config/progression.json";


    public ProgressionConfig(boolean disableEndDimension, boolean disableNetherDimension, boolean displayDisableMessages,
                             String endDisabledMessage, String netherDisabledMessage) {
        this.disableEndDimension = disableEndDimension;
        this.disableNetherDimension = disableNetherDimension;
        this.displayDisableMessages = displayDisableMessages;
        this.endDisabledMessage = endDisabledMessage;
        this.netherDisabledMessage = netherDisabledMessage;
    }



    public ProgressionConfig(boolean disableEndDimension, boolean disableNetherDimension, boolean displayDisableMessages,
                             String endDisabledMessage, String netherDisabledMessage, boolean isDefaultSettings) {
        this.disableEndDimension = disableEndDimension;
        this.disableNetherDimension = disableNetherDimension;
        this.displayDisableMessages = displayDisableMessages;
        this.endDisabledMessage = endDisabledMessage;
        this.netherDisabledMessage = netherDisabledMessage;
        this.isDefaultSettings = isDefaultSettings;
    }

    public static ProgressionConfig getDefaultConfig() {
        boolean defaultEnd = true;
        boolean defaultNether = false;
        boolean defaultDisplayMessages = true;
        String defaultEndMessage = "-[ The End Dimension is currently disabled by your Server Admin ]-";
        String defaultNetherMessage = "-[ The Nether Dimension is currently disabled by your Server Admin ]-";

        return new ProgressionConfig(defaultEnd, defaultNether, defaultDisplayMessages, defaultEndMessage, defaultNetherMessage,false);
    }

    public static boolean getBoolValueFromEntry(String entry) throws FileNotFoundException {
        Object obj = new JsonParser().parse(new FileReader(configPath));
        JsonObject jo = (JsonObject) obj;

        return jo.get(entry).getAsBoolean();
    }

    public static String getStringValueFromEntry(String entry) throws FileNotFoundException {
        Object obj = new JsonParser().parse(new FileReader(configPath));
        JsonObject jo = (JsonObject) obj;

        return jo.get(entry).getAsString();
    }
}