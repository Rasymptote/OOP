package ru.nsu.babich.application.mapper;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Locale;
import ru.nsu.babich.application.dto.FieldDto;
import ru.nsu.babich.application.dto.FoodDto;
import ru.nsu.babich.application.dto.GameStateDto;
import ru.nsu.babich.application.dto.PointDto;
import ru.nsu.babich.application.dto.SnakeDto;
import ru.nsu.babich.application.exception.ValidationException;
import ru.nsu.babich.domain.model.Field;
import ru.nsu.babich.domain.model.Food;
import ru.nsu.babich.domain.model.FoodType;
import ru.nsu.babich.domain.model.GameState;
import ru.nsu.babich.domain.model.GameStatus;
import ru.nsu.babich.domain.model.Point;
import ru.nsu.babich.domain.model.Snake;

/**
 * Mapper utilities for converting application DTOs to domain model objects.
 */
public final class GameStateMapper {
    /**
     * Utility class constructor.
     */
    private GameStateMapper() {

    }

    /**
     * Converts game state DTO to domain model.
     *
     * @param dto Source DTO.
     * @return Domain game state.
     * @throws ValidationException If DTO or nested values are invalid.
     */
    public static GameState toModel(GameStateDto dto) {
        requireNotNull(dto, "gameStateDto must not be null");
        return new GameState(
                toModel(dto.field()),
                toModel(dto.snake()),
                toFoods(dto.foods()),
                toGameStatus(dto.status())
        );
    }

    /**
     * Converts field DTO to domain model.
     *
     * @param dto Source DTO.
     * @return Domain field.
     * @throws ValidationException If DTO is null.
     */
    public static Field toModel(FieldDto dto) {
        requireNotNull(dto, "fieldDto must not be null");
        return new Field(dto.rows(), dto.columns());
    }

    /**
     * Converts snake DTO to domain model.
     *
     * @param dto Source DTO.
     * @return Domain snake.
     * @throws ValidationException If DTO content is invalid.
     */
    public static Snake toModel(SnakeDto dto) {
        requireNotNull(dto, "snakeDto must not be null");
        if (dto.pendingGrowthTicks() < 0) {
            throw new ValidationException("pendingGrowthTicks must be non-negative");
        }
        return new Snake(
                toSegments(dto.segments()),
                dto.pendingGrowthTicks()
        );
    }

    /**
     * Converts food DTO to domain model.
     *
     * @param dto Source DTO.
     * @return Domain food.
     * @throws ValidationException If DTO content is invalid.
     */
    public static Food toModel(FoodDto dto) {
        requireNotNull(dto, "foodDto must not be null");
        return new Food(
                new Point(dto.x(), dto.y()),
                toFoodType(dto.type())
        );
    }

    /**
     * Converts list of food DTOs.
     *
     * @param value Source list.
     * @return Converted food list.
     */
    private static List<Food> toFoods(List<FoodDto> value) {
        requireNotNull(value, "foods must not be null");
        return value.stream().map(GameStateMapper::toModel).toList();
    }

    /**
     * Parses food type enum from string.
     *
     * @param value Source value.
     * @return Parsed food type.
     * @throws ValidationException If value is blank or unknown.
     */
    private static FoodType toFoodType(String value) {
        var normalized = normalizeEnumValue(value, "food type");
        try {
            return FoodType.valueOf(normalized);
        } catch (IllegalArgumentException e) {
            throw new ValidationException("Unknown food type: " + value, e);
        }
    }

    /**
     * Parses game status enum from string.
     *
     * @param value Source value.
     * @return Parsed game status.
     * @throws ValidationException If value is blank or unknown.
     */
    private static GameStatus toGameStatus(String value) {
        var normalized = normalizeEnumValue(value, "game status");
        try {
            return GameStatus.valueOf(normalized);
        } catch (IllegalArgumentException e) {
            throw new ValidationException("Unknown game status: " + value, e);
        }
    }

    /**
     * Converts snake segment DTO list to deque.
     *
     * @param value Source segment list.
     * @return Ordered segment deque.
     * @throws ValidationException If list is null or empty.
     */
    private static Deque<Point> toSegments(List<PointDto> value) {
        requireNotNull(value, "snake segments must not be null");
        if (value.isEmpty()) {
            throw new ValidationException("snake segments must not be empty");
        }
        var deque = new ArrayDeque<Point>();
        value.stream().map(GameStateMapper::toPoint).forEach(deque::add);
        return deque;
    }

    /**
     * Converts point DTO to domain point.
     *
     * @param value Source DTO.
     * @return Domain point.
     */
    private static Point toPoint(PointDto value) {
        requireNotNull(value, "pointDto must not be null");
        return new Point(value.x(), value.y());
    }

    /**
     * Normalizes enum value for case-insensitive parsing.
     *
     * @param value Raw enum name.
     * @param fieldName Logical field name used in error messages.
     * @return Trimmed and uppercased enum name.
     * @throws ValidationException If value is null or blank.
     */
    private static String normalizeEnumValue(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new ValidationException(fieldName + " must not be blank");
        }
        return value.trim().toUpperCase(Locale.ROOT);
    }

    /**
     * Validates that value is not null.
     *
     * @param value Value to check.
     * @param message Error message.
     * @param <T> Value type.
     * @throws ValidationException If value is null.
     */
    private static <T> void requireNotNull(T value, String message) {
        if (value == null) {
            throw new ValidationException(message);
        }
    }
}
