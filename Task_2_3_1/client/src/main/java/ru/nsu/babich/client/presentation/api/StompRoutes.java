package ru.nsu.babich.client.presentation.api;

/**
 * Stores STOMP endpoints used by the client.
 */
public final class StompRoutes {
    private StompRoutes() {
    }

    public static final String SERVER_URL = "ws://localhost:8080/ws";
    public static final String APP_PREFIX = "/app";
    public static final String TOPIC_PREFIX = "/topic";

    public static final String STATE_TOPIC = TOPIC_PREFIX + "/state";

    public static final String JOIN_MAPPING = APP_PREFIX + "/join";

    public static final String DIRECTION_ENDPOINT = APP_PREFIX + "/direction";
}
