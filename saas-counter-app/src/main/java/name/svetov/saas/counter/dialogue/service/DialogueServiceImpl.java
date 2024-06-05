package name.svetov.saas.counter.dialogue.service;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import name.svetov.saas.counter.dialogue.model.UnreadDialogueMessagesCountEntry;
import name.svetov.saas.counter.dialogue.repository.DialogueRepository;
import org.reactivestreams.Publisher;

import java.util.UUID;

@Singleton
@RequiredArgsConstructor
public class DialogueServiceImpl implements DialogueService {
    private final DialogueRepository repository;

    @Override
    public Publisher<Boolean> incrementUnreadDialogueMessageCount(UUID dialogueId, UUID userId) {
        return repository.incrementUnreadDialogueMessagesCount(dialogueId, userId);
    }

    @Override
    public Publisher<Boolean> decrementUnreadDialogueMessageCount(UUID dialogueId, UUID userId) {
        return repository.decrementUnreadDialogueMessagesCount(dialogueId, userId);
    }

    @Override
    public Publisher<UnreadDialogueMessagesCountEntry> findUnreadDialogueMessageCount(UUID dialogueId, UUID userId) {
        return repository.findUnreadDialogueMessagesCount(dialogueId, userId);
    }
}
