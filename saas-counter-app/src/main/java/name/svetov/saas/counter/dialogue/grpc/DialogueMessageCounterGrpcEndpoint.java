package name.svetov.saas.counter.dialogue.grpc;

import io.grpc.stub.StreamObserver;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import name.svetov.saas.counter.dialogue.adapter.DialogueGrpcAdapter;
import name.svetov.saas.counter.grpc.DialogueMessageCounter;
import name.svetov.saas.counter.grpc.DialogueMessageCounterServiceGrpc;
import reactor.core.publisher.Mono;

@Singleton
@RequiredArgsConstructor
public class DialogueMessageCounterGrpcEndpoint extends DialogueMessageCounterServiceGrpc.DialogueMessageCounterServiceImplBase {
    private final DialogueGrpcAdapter grpcAdapter;

    @Override
    public void getUnreadMessageCount(DialogueMessageCounter.UnreadDialogueMessageCountRq request,
                                      StreamObserver<DialogueMessageCounter.UnreadDialogueMessageCountRs> responseObserver) {
        Mono.from(grpcAdapter.findUnreadDialogueMessageCount(request))
            .doOnNext(responseObserver::onNext)
            .doOnNext(empty -> responseObserver.onCompleted())
            .block();
    }
}
