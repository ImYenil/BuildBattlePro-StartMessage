package net.choco.buildbattleprostartmessage.manager;

import lombok.Getter;
import net.choco.buildbattleprostartmessage.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

@Getter
public class BBSettings {

    private Main plugin;
    private String mainWorld;
    private String prefix = "§8[§ethis.plugin§8]§r ";

    public BBSettings(Main plugin) {
        this.plugin = plugin;
    }

    private void setPrefix(String prefix) {
        if (prefix != null) {
            this.prefix = ChatColor.translateAlternateColorCodes('&', prefix);
        } else {
            Bukkit.getConsoleSender().sendMessage(prefix + "§cVariable prefix could not be loaded! Setting it to default (" + this.prefix + ")");
        }
    }

    public void loadSettings() {
        setPrefix(this.plugin.getFileManager().getConfig("config.yml").get().getString("prefix"));
        mainWorld = this.plugin.getFileManager().getConfig("config.yml").get().getString("world.lobby");
    }
}
