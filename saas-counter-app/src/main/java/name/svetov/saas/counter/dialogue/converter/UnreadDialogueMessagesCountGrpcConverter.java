package name.svetov.saas.counter.dialogue.converter;

import name.svetov.saas.counter.config.SaasMapperDefaultConfig;
import name.svetov.saas.counter.dialogue.model.UnreadDialogueMessagesCountEntry;
import name.svetov.saas.counter.grpc.DialogueMessageCounter;
import org.mapstruct.Mapper;

@Mapper(config = SaasMapperDefaultConfig.class)
public interface UnreadDialogueMessagesCountGrpcConverter {
    DialogueMessageCounter.UnreadDialogueMessageCountRs map(UnreadDialogueMessagesCountEntry source);
}
