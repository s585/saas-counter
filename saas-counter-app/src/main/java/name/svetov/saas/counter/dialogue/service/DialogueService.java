package name.svetov.saas.counter.dialogue.service;

import name.svetov.saas.counter.dialogue.model.UnreadDialogueMessagesCountEntry;
import org.reactivestreams.Publisher;

import java.util.UUID;

public interface DialogueService {
    Publisher<Boolean> incrementUnreadDialogueMessageCount(UUID dialogueId, UUID userId);
    Publisher<Boolean> decrementUnreadDialogueMessageCount(UUID dialogueId, UUID userId);
    Publisher<UnreadDialogueMessagesCountEntry> findUnreadDialogueMessageCount(UUID dialogueId, UUID userId);
}
