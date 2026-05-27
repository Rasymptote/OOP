package ru.nsu.babich.client.presentation.api;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import ru.nsu.babich.client.presentation.api.dto.GameStateDto;

/**
 * Handles a connected STOMP session and forwards game state updates to listeners.
 */
public class GameSessionHandler extends StompSessionHandlerAdapter {
    private final List<Consumer<GameStateDto>> gameStateListeners = new ArrayList<>();

    /** {@inheritDoc} */
    @Override
    public void afterConnected(StompSession session, @NonNull StompHeaders connectedHeaders) {
        session.subscribe(StompRoutes.STATE_TOPIC, new StompFrameHandler() {
            @Override
            @NonNull
            public Type getPayloadType(@NonNull StompHeaders headers) {
                return GameStateDto.class;
            }

            @Override
            public void handleFrame(@NonNull StompHeaders headers, @Nullable Object payload) {
                if (payload instanceof GameStateDto gameState) {
                    for (var listener : gameStateListeners) {
                        listener.accept(gameState);
                    }
                }
            }
        });

        session.send(StompRoutes.JOIN_MAPPING, "");
    }

    /**
     * Registers a listener for incoming game state updates.
     *
     * @param gameStateListener Listener that handles game state changes.
     */
    public void addGameStateListener(Consumer<GameStateDto> gameStateListener) {
        gameStateListeners.add(gameStateListener);
    }
}
