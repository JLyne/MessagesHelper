package uk.co.notnull.messageshelper;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.yaml.YAMLConfigurationLoader;

import java.io.File;
import java.io.IOException;

public class MessagesHelper extends AbstractMessageHelper<ConfigurationNode> {
    private static MessagesHelper instance;

    public static MessagesHelper getInstance() {
        if(instance == null) {
            instance = new MessagesHelper();
        }

        return instance;
    }

    protected ConfigurationNode messages;

    public void setMessages(ConfigurationNode messages) {
        this.messages = messages;
    }

    @Override
    public void loadMessages(File file) throws IOException {
        messages = YAMLConfigurationLoader.builder().setFile(file).build().load();
    }

    public String getString(String id) {
        if(messages == null) {
            return "";
        }

        return messages.getNode((Object[]) id.split("\\.")).getString("Message " + id + " does not exist");
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
