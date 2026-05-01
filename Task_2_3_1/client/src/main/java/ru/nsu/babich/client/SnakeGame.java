package ru.nsu.babich.client;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.nsu.babich.client.presentation.api.StompRoutes;
import ru.nsu.babich.client.presentation.api.WebSnakeClient;
import ru.nsu.babich.client.presentation.view.SceneManager;
import ru.nsu.babich.client.presentation.view.ViewPaths;
import ru.nsu.babich.client.presentation.view.controller.MenuController;

/**
 * Launches the Snake Game JavaFX client and wires the initial menu scene.
 */
public class SnakeGame extends Application {

    private WebSnakeClient client;
    private SceneManager sceneManager;

    /** {@inheritDoc} */
    @Override
    public void start(Stage stage) {

        client = new WebSnakeClient(StompRoutes.SERVER_URL);

        sceneManager = new SceneManager(stage);

        showMenu();

        stage.setWidth(800);
        stage.setHeight(600);
        stage.setTitle("Snake Game");
        stage.setOnCloseRequest(e -> shutdownClient());
        stage.show();
    }

    private void showMenu() {
        MenuController controller = sceneManager.setScene(ViewPaths.MENU);
        controller.init(client, sceneManager);
    }

    private void shutdownClient() {
        try {
            client.leave();
        } catch (Exception ignored) {
        }
    }

    /**
     * Starts the JavaFX application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}