package ru.nsu.babich.server.domain.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.stereotype.Service;
import ru.nsu.babich.server.domain.model.Field;
import ru.nsu.babich.server.domain.model.Food;
import ru.nsu.babich.server.domain.model.Player;
import ru.nsu.babich.server.domain.model.Point;

/**
 * Domain service for calculating currently free cells on the field.
 */
@Service
public class FreeCellsService {

    /**
     * Computes all cells that are not occupied by snakes or foods.
     *
     * @param field Active field dimensions.
     * @param players Players with snake segments occupying cells.
     * @param foods Foods occupying cells.
     * @return List of free cells.
     */
    public List<Point> getFreeCells(Field field, List<Player> players, List<Food> foods) {
        Objects.requireNonNull(field, "field must not be null");
        Objects.requireNonNull(players, "players must not be null");
        Objects.requireNonNull(foods, "foods must not be null");

        Set<Point> occupied = new HashSet<>();
        for (Player player : players) {
            occupied.addAll(player.snake().getSegments());
        }
        foods.stream().map(Food::position).forEach(occupied::add);

        List<Point> free = new ArrayList<>();
        for (int y = 0; y < field.rows(); y++) {
            for (int x = 0; x < field.columns(); x++) {
                Point point = new Point(x, y);
                if (!occupied.contains(point)) {
                    free.add(point);
                }
            }
        }
        return List.copyOf(free);
    }
}
