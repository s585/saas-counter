package name.svetov.saas.counter.dialogue.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UnreadDialogueMessagesCountEntry {
    private UUID dialogueId;
    private UUID userId;
    private int count;
}
