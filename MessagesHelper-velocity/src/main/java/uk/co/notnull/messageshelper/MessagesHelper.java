package uk.co.notnull.messageshelper;

import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MessagesHelper extends AbstractMessageHelper<ConfigurationNode> {
    private static final Map<Object, MessagesHelper> instances = new HashMap<>();

    public static MessagesHelper getInstance(Object plugin) {
        return instances.computeIfAbsent(plugin, (key) -> new MessagesHelper());
    }

    protected ConfigurationNode messages;

    public void setMessages(ConfigurationNode messages) {
        this.messages = messages;
    }

    @Override
    public void loadMessages(File file) throws IOException {
        messages = YamlConfigurationLoader.builder().file(file).build().load();
    }

    public String getString(String id) {
        if(messages == null) {
            return "";
        }

        return messages.node((Object[]) id.split("\\.")).getString("Message " + id + " does not exist");
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
