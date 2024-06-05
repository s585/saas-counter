package name.svetov.saas.counter.dialogue.repository;

import name.svetov.saas.counter.dialogue.model.UnreadDialogueMessagesCountEntry;
import org.reactivestreams.Publisher;

import java.util.UUID;

public interface DialogueRepository {
    Publisher<Boolean> incrementUnreadDialogueMessagesCount(UUID dialogueId, UUID userId);
    Publisher<Boolean> decrementUnreadDialogueMessagesCount(UUID dialogueId, UUID userId);
    Publisher<UnreadDialogueMessagesCountEntry> findUnreadDialogueMessagesCount(UUID dialogueId, UUID userId);
}
