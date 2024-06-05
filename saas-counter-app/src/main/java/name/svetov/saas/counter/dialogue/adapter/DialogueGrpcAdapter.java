package name.svetov.saas.counter.dialogue.adapter;

import name.svetov.saas.counter.grpc.DialogueMessageCounter;
import org.reactivestreams.Publisher;

public interface DialogueGrpcAdapter {
    Publisher<DialogueMessageCounter.UnreadDialogueMessageCountRs>
    findUnreadDialogueMessageCount(DialogueMessageCounter.UnreadDialogueMessageCountRq request);
}
