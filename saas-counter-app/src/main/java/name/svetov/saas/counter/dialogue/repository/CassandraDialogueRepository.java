package name.svetov.saas.counter.dialogue.repository;

import com.datastax.dse.driver.api.core.cql.reactive.ReactiveRow;
import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import name.svetov.saas.counter.dialogue.model.UnreadDialogueMessagesCountEntry;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.bindMarker;

@Singleton
@RequiredArgsConstructor
public class CassandraDialogueRepository implements DialogueRepository {
    private static final CqlIdentifier COUNTER_KEYSPACE = CqlIdentifier.fromCql("counter");
    private static final CqlIdentifier UNREAD_DIALOGUE_MESSAGES_TABLE = CqlIdentifier.fromCql("unread_dialogue_messages");
    private static final CqlIdentifier DIALOGUE_ID = CqlIdentifier.fromCql("dialogue_id");
    private static final CqlIdentifier USER_ID = CqlIdentifier.fromCql("user_id");
    private static final CqlIdentifier COUNT = CqlIdentifier.fromCql("count");

    private final CqlSession cqlSession;

    @Override
    public Publisher<Boolean> incrementUnreadDialogueMessagesCount(UUID dialogueId, UUID userId) {
        return Mono.from(cqlSession.executeReactive(incrementCounter().bind(dialogueId, userId)))
            .map(ReactiveRow::wasApplied);
    }

    @Override
    public Publisher<Boolean> decrementUnreadDialogueMessagesCount(UUID dialogueId, UUID userId) {
        return Mono.from(cqlSession.executeReactive(decrementCounter().bind(dialogueId, userId)))
            .map(ReactiveRow::wasApplied);
    }

    @Override
    public Publisher<UnreadDialogueMessagesCountEntry> findUnreadDialogueMessagesCount(UUID dialogueId, UUID userId) {
        return Mono.from(cqlSession.executeReactive(findUnreadMessagesCount().bind(dialogueId, userId)))
            .map(this::convert);
    }

    private PreparedStatement incrementCounter() {
        return cqlSession.prepare(
            QueryBuilder.update(COUNTER_KEYSPACE, UNREAD_DIALOGUE_MESSAGES_TABLE)
                .increment(COUNT)
                .whereColumn(DIALOGUE_ID).isEqualTo(bindMarker(DIALOGUE_ID))
                .whereColumn(USER_ID).isEqualTo(bindMarker(USER_ID))
                .build()
                .setIdempotent(true)
        );
    }

    private PreparedStatement decrementCounter() {
        return cqlSession.prepare(
            QueryBuilder.update(COUNTER_KEYSPACE, UNREAD_DIALOGUE_MESSAGES_TABLE)
                .decrement(COUNT)
                .whereColumn(DIALOGUE_ID).isEqualTo(bindMarker(DIALOGUE_ID))
                .whereColumn(USER_ID).isEqualTo(bindMarker(USER_ID))
                .build()
                .setIdempotent(true)
        );
    }

    private PreparedStatement findUnreadMessagesCount() {
        return cqlSession.prepare(
            QueryBuilder.selectFrom(COUNTER_KEYSPACE, UNREAD_DIALOGUE_MESSAGES_TABLE).all()
                .whereColumn(DIALOGUE_ID).isEqualTo(bindMarker(DIALOGUE_ID))
                .whereColumn(USER_ID).isEqualTo(bindMarker(USER_ID))
                .build()
        );
    }

    private UnreadDialogueMessagesCountEntry convert(ReactiveRow row) {
        return UnreadDialogueMessagesCountEntry.builder()
            .dialogueId(row.getUuid(DIALOGUE_ID))
            .userId(row.getUuid(USER_ID))
            .count(row.getInt(COUNT))
            .build();
    }
}
