package net.choco.buildbattleprostartmessage;

import lombok.Getter;
import net.choco.buildbattleprostartmessage.listener.ArenaListener;
import net.choco.buildbattleprostartmessage.manager.FileManager;
import net.choco.buildbattleprostartmessage.manager.BBSettings;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Getter
    private static Main instance;

    @Getter
    private FileManager fileManager;

    @Getter
    private BBSettings settings;

    @Override
    public void onEnable() {
        instance = this;

        this.fileManager = new FileManager(this);
        this.loadAllConfigs();

        this.settings = new BBSettings(this);
        this.settings.loadSettings();

        if (!Bukkit.getPluginManager().isPluginEnabled("BuildBattlePro")) {
            info("BuildBattlePro was not found. Disabling...");
            this.setEnabled(false);
            return;
        }

        Bukkit.getPluginManager().registerEvents(new ArenaListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void loadAllConfigs() {
        this.fileManager.getConfig("config.yml").copyDefaults(true).save();
        this.fileManager.getConfig("translates.yml").copyDefaults(true).save();
    }

    private void reloadAllConfigs() {
        for (FileManager.Config c : FileManager.configs.values()) {
            c.reload();
        }
    }

    public static void info(String message) {
        Bukkit.getConsoleSender().sendMessage(Main.getInstance().getSettings().getPrefix() + message);
    }
}
