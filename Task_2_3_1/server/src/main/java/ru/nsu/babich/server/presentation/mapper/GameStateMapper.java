package ru.nsu.babich.server.presentation.mapper;

import org.mapstruct.Mapper;
import ru.nsu.babich.server.domain.model.GameState;
import ru.nsu.babich.server.domain.model.PlayerId;
import ru.nsu.babich.server.presentation.dto.GameStateDto;

/**
 * Maps domain game state to transport DTO representation.
 */
@Mapper(componentModel = "spring")
public interface GameStateMapper {
    /**
     * Converts domain game state to DTO.
     *
     * @param gameState Domain game state.
     * @return Game state DTO.
     */
    GameStateDto toDto(GameState gameState);

    /**
     * Maps player id value object to plain string.
     *
     * @param playerId Player identifier.
     * @return Identifier string.
     */
    default String map(PlayerId playerId) {
        return playerId.id();
    }
}
