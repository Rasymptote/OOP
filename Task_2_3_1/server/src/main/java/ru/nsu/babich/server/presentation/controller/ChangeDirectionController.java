package ru.nsu.babich.server.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import ru.nsu.babich.server.application.exception.ValidationException;
import ru.nsu.babich.server.application.usecase.ChangeDirectionUseCase;
import ru.nsu.babich.server.config.SnakeStompRoutes;
import ru.nsu.babich.server.domain.model.Direction;

/**
 * Handles incoming direction change messages.
 */
@Controller
public class ChangeDirectionController {
    private final ChangeDirectionUseCase changeDirectionUseCase;

    @Autowired
    public ChangeDirectionController(final ChangeDirectionUseCase changeDirectionUseCase) {
        this.changeDirectionUseCase = changeDirectionUseCase;
    }

    /**
     * Parses direction payload and delegates update to use case.
     *
     * @param sessionId STOMP session identifier.
     * @param direction Direction value as string.
     */
    @MessageMapping(SnakeStompRoutes.DIRECTION_MAPPING)
    public void onChangeDirection(
            @Header("simpSessionId") String sessionId,
            @Payload final String direction
    ) {
        final Direction parsedDirection;
        try {
            parsedDirection = Direction.valueOf(direction);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new ValidationException("Invalid direction: " + direction, e);
        }
        changeDirectionUseCase.execute(sessionId, parsedDirection);
    }
}
