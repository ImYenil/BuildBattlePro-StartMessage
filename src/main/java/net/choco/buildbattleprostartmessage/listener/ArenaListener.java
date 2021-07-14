package net.choco.buildbattleprostartmessage.listener;

import net.choco.api.events.game.BBGameStateSwitchEvent;
import net.choco.buildbattle.objects.arena.BBArena;
import net.choco.buildbattle.objects.arena.BBArenaState;
import net.choco.buildbattleprostartmessage.message.Message;
import net.choco.buildbattleprostartmessage.Main;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ArenaListener implements Listener {

    @EventHandler
    public void onArenaStart(BBGameStateSwitchEvent e) {
        String arenaName = e.getArena().getName();
        World lobby = Bukkit.getWorld(Main.getInstance().getSettings().getMainWorld());
        BBArena bbArena = e.getArena();
        if (bbArena.getBBArenaState() == BBArenaState.LOBBY)  {
            if (bbArena.getPlayers().size() >= bbArena.getMinPlayers()) {
                lobby.getPlayers().forEach(player -> player.sendMessage(""));
                TextComponent textComponent = new TextComponent(Message.START_TEXT.getMessageWithPrefix().replace("{arena}", arenaName));
                textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Message.START_TEXT_TIP.getMessageWithPrefix().replace("{arena}", arenaName)).create()));
                textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bb join " + arenaName));
                lobby.getPlayers().forEach(player -> player.spigot().sendMessage(textComponent));
                lobby.getPlayers().forEach(player -> player.sendMessage(""));
            }
        }
    }
}
