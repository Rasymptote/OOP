package ru.nsu.babich.client.presentation.api;

import java.util.function.Consumer;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import ru.nsu.babich.client.presentation.api.dto.GameStateDto;

/**
 * Wraps the WebSocket STOMP client used to communicate with the game server.
 */
public class WebSnakeClient {
    private final String serverUrl;
    private final WebSocketStompClient stompClient;
    private GameSessionHandler sessionHandler;
    private StompSession session;

    /**
     * Creates a client for the given server URL.
     *
     * @param serverUrl WebSocket server URL.
     */
    public WebSnakeClient(String serverUrl) {
        this.serverUrl = serverUrl;
        this.stompClient = new WebSocketStompClient(new StandardWebSocketClient());
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
    }

    /**
     * Connects to the server and starts receiving game state updates.
     */
    public void join() {
        sessionHandler = new GameSessionHandler();
        stompClient.connectAsync(serverUrl, sessionHandler)
                .thenAccept(stompSession -> this.session = stompSession);
    }

    /**
     * Disconnects from the server and clears the current session state.
     */
    public void leave() {
        this.session.disconnect();

        this.session = null;
        this.sessionHandler = null;
    }

    /**
     * Sends a direction change request to the server.
     *
     * @param direction Direction name.
     */
    public void changeDirection(String direction) {
        if (session == null) {
            return;
        }

        this.session.send(StompRoutes.DIRECTION_ENDPOINT, direction);
    }

    /**
     * Registers a listener for game state updates.
     *
     * @param gameStateListener Listener that consumes game state updates.
     */
    public void addGameStateListener(Consumer<GameStateDto> gameStateListener) {
        if (sessionHandler != null) {
            sessionHandler.addGameStateListener(gameStateListener);
        }
    }
}
