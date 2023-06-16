package uk.co.notnull.messageshelper;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class MessagesHelper extends AbstractMessageHelper<ConfigurationSection> {
    private static MessagesHelper instance;

    public static MessagesHelper getInstance() {
        if(instance == null) {
            instance = new MessagesHelper();
        }

        return instance;
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
