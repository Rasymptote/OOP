package ru.nsu.babich.server.infrastructure.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import ru.nsu.babich.server.application.port.SessionRepository;
import ru.nsu.babich.server.domain.model.PlayerId;

/**
 * In-memory storage for session-to-player mapping.
 */
public class InMemorySessionRepository implements SessionRepository {
    private final Map<String, PlayerId> sessions = new ConcurrentHashMap<>();

    /** {@inheritDoc} */
    @Override
    public void save(String sessionId, PlayerId playerId) {
        sessions.put(sessionId, playerId);
    }

    /** {@inheritDoc} */
    @Override
    public PlayerId getPlayerId(String sessionId) {
        return sessions.get(sessionId);
    }

    /** {@inheritDoc} */
    @Override
    public void remove(String sessionId) {
        sessions.remove(sessionId);
    }
}
