package ru.nsu.babich.client.presentation.view.controller;

import javafx.fxml.FXML;
import ru.nsu.babich.client.presentation.api.WebSnakeClient;
import ru.nsu.babich.client.presentation.view.SceneManager;
import ru.nsu.babich.client.presentation.view.ViewPaths;

/**
 * Controller for the main menu.
 */
public class MenuController {
    private WebSnakeClient webSnakeClient;
    private SceneManager sceneManager;

    public void init(WebSnakeClient webSnakeClient, SceneManager sceneManager) {
        this.webSnakeClient = webSnakeClient;
        this.sceneManager = sceneManager;
    }

    @FXML
    private void startGame() {
        SnakeGameController controller = sceneManager.setScene(ViewPaths.SNAKE_GAME);
        controller.init(webSnakeClient);
        webSnakeClient.join();
        webSnakeClient.addGameStateListener(controller);
    }
}