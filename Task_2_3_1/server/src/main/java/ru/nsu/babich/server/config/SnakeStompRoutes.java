package ru.nsu.babich.server.config;

/**
 * Stores STOMP endpoints used by the server.
 */
public final class SnakeStompRoutes {
    private SnakeStompRoutes() {
    }

    public static final String WS_ENDPOINT = "/ws";
    public static final String APP_PREFIX = "/app";
    public static final String TOPIC_PREFIX = "/topic";

    public static final String STATE_TOPIC = TOPIC_PREFIX + "/state";

    public static final String JOIN_MAPPING = "/join";

    public static final String DIRECTION_MAPPING = "/direction";
}
