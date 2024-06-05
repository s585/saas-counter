package name.svetov.saas.counter.rabbitmq;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ChannelConstants {
    public static final String DIALOGUE_MESSAGE_CREATED_QUEUE = "dialogue_message.created.queue";
    public static final String DIALOGUE_MESSAGE_READ_QUEUE = "dialogue_message.read.queue";
}
