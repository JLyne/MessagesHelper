package uk.co.notnull.messageshelper;

import net.kyori.adventure.text.ComponentLike;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Message {
	private final String id;
	private final boolean prefixed;
	private final MessageType type;
	private final Map<String, String> stringReplacements;
	private final Map<String, ComponentLike> componentReplacements;

	public Message(@NotNull String id, MessageType type, boolean prefixed,
				   Map<String, String> stringReplacements, Map<String, ComponentLike> componentReplacements) {
		this.id = id;
		this.type = type;
		this.prefixed = prefixed;
		this.stringReplacements = Collections.unmodifiableMap(stringReplacements);
		this.componentReplacements = Collections.unmodifiableMap(componentReplacements);
	}

	public String getId() {
		return id;
	}

	public boolean isPrefixed() {
		return prefixed;
	}

	public MessageType getType() {
		return type;
	}

	public Map<String, String> getStringReplacements() {
		return stringReplacements;
	}

	public Map<String, ComponentLike> getComponentReplacements() {
		return componentReplacements;
	}

	public static Message.Builder builder(String id) {
		return new Message.Builder(id);
	}

	public enum MessageType {
		INFO,
		ERROR,
		WARNING
	}

	public static class Builder {
		private final String id;
		private boolean prefixed = false;
		private MessageType type = MessageType.INFO;
		private final Map<String, String> stringReplacements = new HashMap<>();
		private final Map<String, ComponentLike> componentReplacements = new HashMap<>();

		public Builder(String id) {
			this.id = id;
		}

		public Builder prefixed() {
			this.prefixed = true;
			return this;
		}

		public Builder prefixed(boolean prefixed) {
			this.prefixed = prefixed;
			return this;
		}

		public Builder type(MessageType type) {
			this.type = type;
			return this;
		}

		public Builder replacement(String find, String replace) {
			stringReplacements.put(find, replace);
			return this;
		}

		public Builder replacement(String find, ComponentLike replace) {
			componentReplacements.put(find, replace);
			return this;
		}

		public Builder stringReplacements(Map<String, String> replacements) {
			stringReplacements.putAll(replacements);
			return this;
		}

		public Builder componentReplacements(Map<String, ComponentLike> replacements) {
			componentReplacements.putAll(replacements);
			return this;
		}

		public Message build() {
			return new Message(id, type, prefixed, stringReplacements, componentReplacements);
		}
	}
}
