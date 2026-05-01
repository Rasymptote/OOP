package ru.nsu.babich.client.presentation.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Loads FXML scenes and applies them to the current stage.
 */
public class SceneManager {

    private final Stage stage;

    /**
     * Creates a scene manager for the given stage.
     *
     * @param stage JavaFX stage used to display scenes.
     */
    public SceneManager(Stage stage) {
        this.stage = stage;
    }

    /**
     * Loads the scene from the given FXML resource and returns its controller.
     *
     * @param fxmlPath FXML resource path.
     * @param <T> Controller type.
     * @return Loaded controller.
     */
    public <T> T setScene(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            return loader.getController();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}