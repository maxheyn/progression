package com.jameslin.progression;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.api.DedicatedServerModInitializer;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

public class Progression implements DedicatedServerModInitializer {

    public static ProgressionConfig config;
    public static ProgressionConfig defaultConfig = ProgressionConfig.getDefaultConfig();
    public static Path pathForTheConfig = Paths.get("config/progression.json");
    public static Gson configDataStuff = new GsonBuilder().setPrettyPrinting()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();

    @Override
    public void onInitializeServer() {
        initConfig();
    }

    public static void initConfig() {
        try {
            if (pathForTheConfig.toFile().exists()) {
                config = configDataStuff.fromJson(new String(Files.readAllBytes(pathForTheConfig)),
                        ProgressionConfig.class);
                if (config.isDefaultSettings) {
                    config = defaultConfig;
                    Files.write(pathForTheConfig, Collections.singleton(configDataStuff.toJson(defaultConfig)));
                }
            } else {
                Files.write(pathForTheConfig, Collections.singleton(configDataStuff.toJson(defaultConfig)));
                config = defaultConfig;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
