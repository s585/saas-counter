package name.svetov.saas.counter.dialogue.rabbitmq.listener;

import io.micronaut.rabbitmq.annotation.Queue;
import io.micronaut.rabbitmq.annotation.RabbitListener;
import lombok.RequiredArgsConstructor;
import name.svetov.saas.counter.dialogue.rabbitmq.event.DialogueMessageCreatedEvent;
import name.svetov.saas.counter.dialogue.rabbitmq.event.DialogueMessageReadEvent;
import name.svetov.saas.counter.dialogue.service.DialogueService;
import reactor.core.publisher.Mono;

import static name.svetov.saas.counter.rabbitmq.ChannelConstants.DIALOGUE_MESSAGE_CREATED_QUEUE;
import static name.svetov.saas.counter.rabbitmq.ChannelConstants.DIALOGUE_MESSAGE_READ_QUEUE;

@RabbitListener
@RequiredArgsConstructor
public class DialogueMessageEventListenerImpl implements DialogueMessageEventListener {
    private final DialogueService dialogueService;

    @Override
    @Queue(DIALOGUE_MESSAGE_CREATED_QUEUE)
    public void process(DialogueMessageCreatedEvent event) {
        Mono.from(dialogueService.incrementUnreadDialogueMessageCount(event.getDialogueId(), event.getRecipientId()))
            .subscribe();
    }

    @Override
    @Queue(DIALOGUE_MESSAGE_READ_QUEUE)
    public void process(DialogueMessageReadEvent event) {
        Mono.from(dialogueService.decrementUnreadDialogueMessageCount(event.getDialogueId(),event.getRecipientId()))
            .subscribe();
    }
}
