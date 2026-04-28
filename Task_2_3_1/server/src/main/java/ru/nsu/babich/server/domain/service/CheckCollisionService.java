package ru.nsu.babich.server.domain.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import ru.nsu.babich.server.domain.model.Field;
import ru.nsu.babich.server.domain.model.Player;
import ru.nsu.babich.server.domain.model.PlayerId;
import ru.nsu.babich.server.domain.model.Point;
import ru.nsu.babich.server.domain.model.Snake;

/**
 * Domain service for detecting snake collisions.
 */
public final class CheckCollisionService {

    private CheckCollisionService() {
    }

    /**
     * Checks whether the snake collides with walls or with its own body.
     *
     * @param snake Current snake state.
     * @param field Active game board.
     * @return {@code true} if a collision is detected.
     */
    public static boolean hasCollision(Snake snake, Field field) {
        Objects.requireNonNull(snake, "snake must not be null");
        Objects.requireNonNull(field, "field must not be null");
        return hasWallCollision(snake, field) || hasSelfCollision(snake);
    }

    /**
     * Checks whether snake head is outside field bounds.
     *
     * @param snake Current snake state.
     * @param field Active game board.
     * @return {@code true} if the head is outside the field.
     */
    public static boolean hasWallCollision(Snake snake, Field field) {
        Objects.requireNonNull(snake, "snake must not be null");
        Objects.requireNonNull(field, "field must not be null");
        return !field.isInside(snake.getHead());
    }

    /**
     * Checks whether snake head collides with its own body.
     *
     * @param snake Current snake state.
     * @return {@code true} when self-collision is detected.
     */
    public static boolean hasSelfCollision(Snake snake) {
        Objects.requireNonNull(snake, "snake must not be null");
        var head = snake.getHead();
        return snake.getBody().stream().anyMatch(segment -> isSameCell(head, segment));
    }

    /**
     * Finds players that collide after simultaneous movement.
     *
     * @param players Players with already moved snakes.
     * @param field Active game board.
     * @return Set of collided player ids.
     */
    public static Set<PlayerId> findCollidedPlayerIds(List<Player> players, Field field) {
        Objects.requireNonNull(players, "players must not be null");
        Objects.requireNonNull(field, "field must not be null");

        Set<PlayerId> collided = new HashSet<>();

        for (Player player : players) {
            if (hasCollision(player.snake(), field)) {
                collided.add(player.id());
            }
        }

        collided.addAll(findHeadToHeadCollisions(players));
        collided.addAll(findHeadToBodyCollisions(players));
        return Set.copyOf(collided);
    }

    /**
     * Checks whether two points refer to the same field cell.
     *
     * @param first First point.
     * @param second Second point.
     * @return {@code true} when both points are equal.
     */
    public static boolean isSameCell(Point first, Point second) {
        Objects.requireNonNull(first, "first point must not be null");
        Objects.requireNonNull(second, "second point must not be null");
        return first.equals(second);
    }

    private static Set<PlayerId> findHeadToHeadCollisions(List<Player> players) {
        Map<Point, List<Player>> byHeadCell = new HashMap<>();

        for (Player player : players) {
            byHeadCell.computeIfAbsent(player.snake().getHead(), ignored -> new ArrayList<>()).add(player);
        }

        Set<PlayerId> collided = new HashSet<>();
        for (List<Player> sameCellPlayers : byHeadCell.values()) {
            if (sameCellPlayers.size() > 1) {
                sameCellPlayers.stream().map(Player::id).forEach(collided::add);
            }
        }
        return collided;
    }

    private static Set<PlayerId> findHeadToBodyCollisions(List<Player> players) {
        Set<PlayerId> collided = new HashSet<>();

        for (Player candidate : players) {
            Point head = candidate.snake().getHead();

            for (Player other : players) {
                if (candidate.id().equals(other.id())) {
                    continue;
                }
                boolean intersectsOtherBody = other.snake().getBody()
                        .stream().anyMatch(cell -> isSameCell(head, cell));
                if (intersectsOtherBody) {
                    collided.add(candidate.id());
                    break;
                }
            }
        }

        return collided;
    }
}
