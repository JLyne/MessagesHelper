package uk.co.notnull.messageshelper;

import ninja.leaping.configurate.ConfigurationNode;

public class MessagesHelper extends AbstractMessageHelper {
    private static ConfigurationNode messages;
    public static void setMessages(ConfigurationNode messages) {
        MessagesHelper.messages = messages;
    }

    public static String getString(String id) {
        if(messages == null) {
            return "";
        }

        return messages.getNode(id).getString("Message " + id + " does not exist");
    }

    public static String getPrefix(Message.MessageType messageType) {
        if(messageType.equals(Message.MessageType.ERROR)) {
            return MessagesHelper.getString("prefix.error");
        } else if(messageType.equals(Message.MessageType.WARNING)) {
            return MessagesHelper.getString("prefix.warning");
        } else {
            return MessagesHelper.getString("prefix.info");
        }
    }
}
