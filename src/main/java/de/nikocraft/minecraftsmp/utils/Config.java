package de.nikocraft.minecraftsmp.utils;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {

    private final File file;

    private final YamlConfiguration config;

    public Config(String dirPath, String fileName) {

        File dir = new File(dirPath);

        if (!dir.exists()) dir.mkdirs();

        file = new File(dir, fileName);

        if (!file.exists()) {

            try {

                file.createNewFile();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        config = YamlConfiguration.loadConfiguration(file);

    }

    public void save() {

        try {

            config.save(file);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public File getFile() {
        return file;
    }

    public YamlConfiguration getConfig() {
        return config;
    }

}
