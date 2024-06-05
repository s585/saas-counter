package name.svetov.saas.counter.dialogue.adapter;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import name.svetov.saas.counter.dialogue.converter.UnreadDialogueMessagesCountGrpcConverter;
import name.svetov.saas.counter.dialogue.service.DialogueService;
import name.svetov.saas.counter.grpc.DialogueMessageCounter;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Singleton
@RequiredArgsConstructor
public class DialogueGrpcAdapterImpl implements DialogueGrpcAdapter {
    private final DialogueService dialogueService;
    private final UnreadDialogueMessagesCountGrpcConverter converter;

    @Override
    public Publisher<DialogueMessageCounter.UnreadDialogueMessageCountRs>
    findUnreadDialogueMessageCount(DialogueMessageCounter.UnreadDialogueMessageCountRq request) {
        return Mono.from(
            dialogueService.findUnreadDialogueMessageCount(
                UUID.fromString(request.getDialogueId()),
                UUID.fromString(request.getUserId())
            )
        ).map(converter::map);
    }
}
