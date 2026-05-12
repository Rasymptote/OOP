package ru.nsu.babich.server.config;

import java.util.Random;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.nsu.babich.server.application.port.GameStateRepository;
import ru.nsu.babich.server.application.port.SessionRepository;
import ru.nsu.babich.server.application.usecase.ChangeDirectionUseCase;
import ru.nsu.babich.server.application.usecase.GameTickUseCase;
import ru.nsu.babich.server.application.usecase.JoinPlayerUseCase;
import ru.nsu.babich.server.application.usecase.LeavePlayerUseCase;
import ru.nsu.babich.server.domain.factory.PlayerFactory;
import ru.nsu.babich.server.domain.service.EatingService;
import ru.nsu.babich.server.domain.service.FoodGenerator;
import ru.nsu.babich.server.domain.service.FoodReplenishmentService;
import ru.nsu.babich.server.domain.service.FreeCellsService;
import ru.nsu.babich.server.domain.service.GameTickService;
import ru.nsu.babich.server.domain.service.MovementService;
import ru.nsu.babich.server.domain.service.strategy.cellpicking.CellPickingStrategy;
import ru.nsu.babich.server.domain.service.strategy.cellpicking.RandomCellPickingStrategy;
import ru.nsu.babich.server.domain.service.strategy.foodtypepicking.FoodTypePickingStrategy;
import ru.nsu.babich.server.domain.service.strategy.foodtypepicking.RandomFoodTypePickingStrategy;
import ru.nsu.babich.server.infrastructure.repository.InMemoryGameStateRepository;
import ru.nsu.babich.server.infrastructure.repository.InMemorySessionRepository;

/**
 * Spring configuration class that defines application beans and their dependencies.
 */
@Configuration
public class ApplicationConfiguration {

    @Bean
    public GameTickUseCase gameTickUseCase(
            GameStateRepository gameStateRepository,
            GameTickService gameTickService

    ) {
        return new GameTickUseCase(gameStateRepository, gameTickService);
    }

    @Bean
    public GameStateRepository gameStateRepository(
            @Value("${game.field.width:20}") int width,
            @Value("${game.field.height:20}") int height
    ) {
        var repository = new InMemoryGameStateRepository(width, height);
        repository.init();
        return repository;
    }


    @Bean
    public CellPickingStrategy cellPickingStrategy(
            @Value("${game.random-seed:1}") long seed
    ) {
        return new RandomCellPickingStrategy(new Random(seed));
    }

    @Bean
    public FoodTypePickingStrategy foodTypePickingStrategy(
            @Value("${game.random-seed:1}") long seed
    ) {
        return new RandomFoodTypePickingStrategy(new Random(seed));
    }

    @Bean
    public SessionRepository sessionRepository() {
        return new InMemorySessionRepository();
    }
}
