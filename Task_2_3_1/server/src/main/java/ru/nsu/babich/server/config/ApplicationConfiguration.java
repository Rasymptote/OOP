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
    public GameTickService gameTickService(
            MovementService movementService,
            EatingService eatingService,
            FoodReplenishmentService foodReplenishmentService
    ) {
        return new GameTickService(movementService, eatingService, foodReplenishmentService);
    }

    @Bean
    public MovementService movementService() {
        return new MovementService();
    }

    @Bean
    public EatingService eatingService() {
        return new EatingService();
    }

    @Bean
    public FoodReplenishmentService foodReplenishmentService(
            FoodGenerator foodGenerator,
            @Value("${game.food.replenish-count:3}") int replenishCount
    ) {
        return new FoodReplenishmentService(foodGenerator, replenishCount);
    }

    @Bean
    public FoodGenerator foodGenerator(
            CellPickingStrategy cellPickStrategy,
            FoodTypePickingStrategy foodTypePickStrategy,
            FreeCellsService freeCellsService
    ) {
        return new FoodGenerator(cellPickStrategy, foodTypePickStrategy, freeCellsService);
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
    public FreeCellsService freeCellsService() {
        return new FreeCellsService();
    }

    @Bean
    public JoinPlayerUseCase joinPlayerUseCase(
            GameStateRepository gameStateRepository,
            SessionRepository sessionRepository,
            FreeCellsService freeCellsService,
            PlayerFactory playerFactory
    ) {
        return new JoinPlayerUseCase(gameStateRepository, sessionRepository, freeCellsService, playerFactory);
    }

    @Bean
    public PlayerFactory playerFactory() {
        return new PlayerFactory();
    }

    @Bean
    public LeavePlayerUseCase leavePlayerUseCase(
            SessionRepository sessionRepository,
            GameStateRepository gameStateRepository
    ) {
        return new LeavePlayerUseCase(sessionRepository, gameStateRepository);
    }

    @Bean
    public ChangeDirectionUseCase changeDirectionUseCase(
            GameStateRepository gameStateRepository,
            SessionRepository sessionRepository
    ) {
        return new ChangeDirectionUseCase(gameStateRepository, sessionRepository);
    }

    @Bean
    public SessionRepository sessionRepository() {
        return new InMemorySessionRepository();
    }
}
