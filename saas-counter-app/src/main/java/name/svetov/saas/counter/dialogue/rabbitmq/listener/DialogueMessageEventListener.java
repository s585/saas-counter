package name.svetov.saas.counter.dialogue.rabbitmq.listener;

import name.svetov.saas.counter.dialogue.rabbitmq.event.DialogueMessageCreatedEvent;
import name.svetov.saas.counter.dialogue.rabbitmq.event.DialogueMessageReadEvent;

public interface DialogueMessageEventListener {
    void process(DialogueMessageCreatedEvent event);
    void process(DialogueMessageReadEvent event);
}
