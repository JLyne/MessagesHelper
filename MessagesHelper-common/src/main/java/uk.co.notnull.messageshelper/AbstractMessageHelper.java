package uk.co.notnull.messageshelper;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

import java.util.Map;

public abstract class AbstractMessageHelper {
	public static final MiniMessage miniMessage = MiniMessage.miniMessage();

	public static String getString(String id) {
        return "Message " + id + " does not exist";
    }

	 public static String getString(Message message) {
		String result = message.isPrefixed() ?
                getPrefix(message.getType()) + getString(message.getId()) : getString(message.getId());

		for(Map.Entry<String, String> replacement: message.getStringReplacements().entrySet()) {
			result = result.replaceAll(replacement.getKey(), replacement.getValue());
		}

		return result;
	}

	public static Component getComponent(Message message) {
		String result = message.isPrefixed() ?
                getPrefix(message.getType()) + getString(message.getId()) : getString(message.getId());

		TagResolver.Builder placeholders = TagResolver.builder();

		for(Map.Entry<String, String> replacement: message.getStringReplacements().entrySet()) {
			placeholders.resolver(Placeholder.parsed(replacement.getKey(), replacement.getValue()));
		}

		for(Map.Entry<String, ComponentLike> replacement: message.getComponentReplacements().entrySet()) {
			placeholders.resolver(Placeholder.component(replacement.getKey(), replacement.getValue()));
		}

		return miniMessage.deserialize(result, placeholders.build());
	}

    public static void send(Audience audience, Message message) {
        audience.sendMessage(getComponent(message));
    }

	public static String getPrefix(Message.MessageType type) {
		return "";
	}
}
