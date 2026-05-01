package ru.nsu.babich.server.application.port;

import ru.nsu.babich.server.domain.model.PlayerId;

/**
 * Stores mapping between WebSocket sessions and player identifiers.
 */
public interface SessionRepository {

    /**
     * Saves the player id for the given session.
     *
     * @param sessionId Session identifier.
     * @param playerId Player identifier.
     */
    void save(String sessionId, PlayerId playerId);

    /**
     * Returns the player id associated with the given session.
     *
     * @param sessionId Session identifier.
     * @return Player identifier, or {@code null} if not found.
     */
    PlayerId getPlayerId(String sessionId);

    /**
     * Removes session mapping.
     *
     * @param sessionId Session identifier.
     */
    void remove(String sessionId);
}
