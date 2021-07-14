package net.choco.buildbattleprostartmessage.message;

import lombok.Getter;
import lombok.Setter;
import net.choco.buildbattleprostartmessage.Main;
import org.bukkit.ChatColor;

public enum Message {

    START_TEXT("messages.start-text"),
    START_TEXT_TIP("messages.start-text-tip");

    @Getter
    @Setter
    private String message;
    @Getter
    private String path;

    Message(String path) {
        this.path = path;
        this.message = ChatColor.translateAlternateColorCodes('&', Main.getInstance().getFileManager().getConfig("translates.yml").get().getString(this.path));
    }

    public String getMessageWithPrefix() {
        return Main.getInstance().getSettings().getPrefix() + this.message;
    }

    public static void reloadMessages() {
        for (Message m : values()) {
            m.setMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getFileManager().getConfig("translates.yml").get().getString(m.getPath())));
        }
    }

}