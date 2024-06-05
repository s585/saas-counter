CREATE KEYSPACE dialogue
            WITH replication = {'class': 'SimpleStrategy', 'replication_factor' : 1};

CREATE TABLE dialogue.unread_dialogue_messages
(
    dialogue_id UUID,
    user_id     UUID,
    count       INT,
    PRIMARY KEY ( dialogue_id, user_id )
);
