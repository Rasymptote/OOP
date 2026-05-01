package ru.nsu.babich.server.infrastructure.scheduler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * Enables scheduling and configures task scheduler used for game ticks.
 */
@Configuration
@EnableScheduling
public class SchedulingConfig {
    /**
     * Creates scheduler with single thread for sequential game ticks.
     *
     * @return Task scheduler bean.
     */
    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(1);
        scheduler.setThreadNamePrefix("game-tick-");
        scheduler.initialize();
        return scheduler;
    }
}
