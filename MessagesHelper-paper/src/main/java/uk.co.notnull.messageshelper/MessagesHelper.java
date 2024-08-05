package uk.co.notnull.messageshelper;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MessagesHelper extends AbstractMessageHelper<ConfigurationSection> {
    private static final Map<Plugin, MessagesHelper> instances = new HashMap<>();

    public static MessagesHelper getInstance(Plugin plugin) {
        return instances.computeIfAbsent(plugin, (key) -> new MessagesHelper());
    }

    private ConfigurationSection messages;
    public void setMessages(ConfigurationSection messages) {
        this.messages = messages;
    }

    @Override
    public void loadMessages(File file) {
        messages = YamlConfiguration.loadConfiguration(file);
    }

    public String getString(String id) {
        if(messages == null) {
            return "";
        }

        return messages.getString(id, "Message " + id + " does not exist");
    }

    public String getPrefix(Message.MessageType messageType) {
        if(messageType.equals(Message.MessageType.ERROR)) {
            return getString("prefix.error");
        } else if(messageType.equals(Message.MessageType.WARNING)) {
            return getString("prefix.warning");
        } else {
            return getString("prefix.info");
        }
    }
}
